package com.pig.rpc.transmission.socket.client;

import com.pig.rpc.dto.RpcReq;
import com.pig.rpc.dto.RpcResp;
import com.pig.rpc.factory.SingletonFactory;
import com.pig.rpc.registry.ServiceDiscovery;
import com.pig.rpc.registry.impl.ZkServiceDiscovery;
import com.pig.rpc.transmission.RpcClient;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Author Mr.Pan
 * @Date 2025/2/19
 **/
@Slf4j
public class SocketRpcClient implements RpcClient {
    private final ServiceDiscovery serviceDiscovery;

    public SocketRpcClient() {
        this(SingletonFactory.getInstance(ZkServiceDiscovery.class));
    }

    public SocketRpcClient(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    @Override
    public RpcResp<?> sendReq(RpcReq rpcReq) {
        InetSocketAddress address = serviceDiscovery.lookupService(rpcReq);

        // 用java网络编程的socket发请求
        // new Socket()：建立连接
        try (Socket socket = new Socket(address.getAddress(), address.getPort())) { // 结束之后，就会关闭socket
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcReq);
            outputStream.flush(); // 刷新输出，确保数据可以发送到服务器

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object o = inputStream.readObject();

            return (RpcResp<?>) o;
        } catch (Exception e) {
            log.error("发送rpc请求失败", e);
            throw new RuntimeException(e);
        }
    }
}
