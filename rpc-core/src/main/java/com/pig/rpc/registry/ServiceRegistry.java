package com.pig.rpc.registry;

import java.net.InetSocketAddress;

/**
 * @Author Mr.Pan
 * @Date 2025/2/22
 **/
public interface ServiceRegistry {
    void registerService(String rpcServiceName, InetSocketAddress address);

    void clearAll();
}
