package com.pig.rpc.provider.impl;

import cn.hutool.core.collection.CollUtil;
import com.pig.rpc.config.RpcServiceConfig;
import com.pig.rpc.provider.ServiceProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Mr.Pan
 * @Date 2025/2/21
 **/
@Slf4j
public class SimpleServiceProvider implements ServiceProvider {
    private final Map<String, Object> SERVICE_CACHE = new HashMap<>();

    @Override
    public void publishService(RpcServiceConfig config) {
        List<String> rpcServiceNames = config.rpcServiceNames();

        if (CollUtil.isEmpty(rpcServiceNames)) {
            throw new RuntimeException("该服务没有实现接口");
        }

        log.debug("发布服务: {}", rpcServiceNames);

        // rpcServiceName ==> 对应的服务
        rpcServiceNames.forEach(rpcServiceName -> SERVICE_CACHE.put(rpcServiceName, config.getService()));
    }

    @Override
    public Object getService(String rpcServiceName) {
        if (!SERVICE_CACHE.containsKey(rpcServiceName)) {
            throw new IllegalArgumentException("找不到对应服务: " + rpcServiceName);
        }

        return SERVICE_CACHE.get(rpcServiceName);
    }
}
