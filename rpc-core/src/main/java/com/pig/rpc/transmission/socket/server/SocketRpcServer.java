package com.pig.rpc.transmission.socket.server;

import com.pig.rpc.config.RpcServiceConfig;
import com.pig.rpc.constant.RpcConstant;
import com.pig.rpc.factory.SingletonFactory;
import com.pig.rpc.handler.RpcReqHandler;
import com.pig.rpc.provider.ServiceProvider;
import com.pig.rpc.provider.impl.ZkServiceProvider;
import com.pig.rpc.transmission.RpcServer;
import com.pig.rpc.util.ShutdownHookUtils;
import com.pig.rpc.util.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * @Author Mr.Pan
 * @Date 2025/2/19
 **/
@Slf4j
public class SocketRpcServer implements RpcServer {
    private final int port;
    private final RpcReqHandler rpcReqHandler;
    private final ServiceProvider serviceProvider;
    private final ExecutorService executor;


    public SocketRpcServer() {
        this(RpcConstant.SERVER_PORT);
    }

    public SocketRpcServer(int port) {
        this(port, SingletonFactory.getInstance(ZkServiceProvider.class));
    }

    public SocketRpcServer(int port, ServiceProvider serviceProvider) {
        this.port = port;
        this.serviceProvider = serviceProvider;
        this.rpcReqHandler = new RpcReqHandler(serviceProvider);
        // 网络交互，IO密集型
        this.executor = ThreadPoolUtils.createIoIntensiveThreadPool("socket-rpc-server-");
    }

    @Override
    public void start() {
        ShutdownHookUtils.clearAll();

        try (ServerSocket serverSocket = new ServerSocket(port)) { // 服务启动，监听port
            log.info("服务启动, 端口: {}", port);

            Socket socket;
            while ((socket = serverSocket.accept()) != null) { // 接收到了请求
                // 用线程池处理
                executor.submit(new SocketReqHandler(socket, rpcReqHandler));
            }
        } catch (Exception e) {
            log.error("服务端异常", e);
        }
    }

    @Override
    public void publishService(RpcServiceConfig config) {
        serviceProvider.publishService(config);
    }
}
