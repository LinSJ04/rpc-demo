package com.pig.client.utils;

import com.pig.rpc.factory.SingletonFactory;
import com.pig.rpc.proxy.RpcClientProxy;
import com.pig.rpc.transmission.RpcClient;
import com.pig.rpc.transmission.socket.client.SocketRpcClient;

/**
 * @Author Mr.Pan
 * @Date 2025/2/21
 **/
public class ProxyUtils {
    private static final RpcClient rpcClient = SingletonFactory.getInstance(SocketRpcClient.class);
    private static final RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcClient);

    public static <T> T getProxy(Class<T> clazz) {
        return rpcClientProxy.getProxy(clazz);
    }
}
