package com.pig.rpc.provider;

import com.pig.rpc.config.RpcServiceConfig;

/**
 * @Author Mr.Pan
 * @Date 2025/2/21
 **/
public interface ServiceProvider {
    void publishService(RpcServiceConfig config);

    Object getService(String rpcServiceName);
}
