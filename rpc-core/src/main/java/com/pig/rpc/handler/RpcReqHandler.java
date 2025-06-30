package com.pig.rpc.handler;

import com.pig.rpc.dto.RpcReq;
import com.pig.rpc.provider.ServiceProvider;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @Author Mr.Pan
 * @Date 2025/2/21
 **/
@Slf4j
public class RpcReqHandler {
    private final ServiceProvider serviceProvider;

    public RpcReqHandler(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @SneakyThrows
    public Object invoke(RpcReq rpcReq) {
        String rpcServiceName = rpcReq.rpcServiceName();
        Object service = serviceProvider.getService(rpcServiceName);

        log.debug("获取到对应服务: {}", service.getClass().getCanonicalName());

        Method method = service.getClass().getMethod(rpcReq.getMethodName(), rpcReq.getParamTypes());

        return method.invoke(service, rpcReq.getParams());
    }
}
