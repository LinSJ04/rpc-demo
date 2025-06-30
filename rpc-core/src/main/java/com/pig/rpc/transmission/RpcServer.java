package com.pig.rpc.transmission;

import com.pig.rpc.config.RpcServiceConfig;

/**
 * @Author Mr.Pan
 * @Date 2025/2/19
 **/
public interface RpcServer {
    void start();

    void publishService(RpcServiceConfig config);
}
