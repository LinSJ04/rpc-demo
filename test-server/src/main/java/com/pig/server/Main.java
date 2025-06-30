package com.pig.server;

import com.pig.rpc.config.RpcServiceConfig;
import com.pig.rpc.transmission.RpcServer;
import com.pig.rpc.transmission.socket.server.SocketRpcServer;
import com.pig.server.service.UserServiceImpl;

/**
 * @Author Mr.Pan
 * @Date 2025/2/19
 **/
public class Main {
    public static void main(String[] args) {
        RpcServiceConfig config = new RpcServiceConfig(new UserServiceImpl());

        RpcServer rpcServer = new SocketRpcServer();
        rpcServer.publishService(config);

        rpcServer.start();

    }
}
