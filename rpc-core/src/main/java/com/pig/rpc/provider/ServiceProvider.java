package com.pig.rpc.provider;

import com.pig.rpc.config.RpcServiceConfig;

/**
 * @Author Mr.Pan
 * @Date 2025/2/21
 **/
public interface ServiceProvider {
    /**
     * 发布服务
     * @param config
     */
    void publishService(RpcServiceConfig config);

    /**
     * 获取服务
     * @param rpcServiceName
     * @return
     */
    Object getService(String rpcServiceName);
}
