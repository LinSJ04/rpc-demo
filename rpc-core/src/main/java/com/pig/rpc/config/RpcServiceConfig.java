package com.pig.rpc.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务发布配置类
 * @Author Mr.Pan
 * @Date 2025/2/21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcServiceConfig {
    private String version = "";
    private String group = "";
    private Object service; // 如定位到UserService的某个具体实现

    public RpcServiceConfig(Object service) {
        this.service = service;
    }

    public List<String> rpcServiceNames() {
        return interfaceNames().stream()
            .map(interfaceName -> interfaceName + getVersion() + getGroup())
            .collect(Collectors.toList());
    }

    private List<String> interfaceNames() {
        // service.getClass()：获取service对象实际运行的类 一个实现类可能实现了多个接口
        // .getInterfaces()：获取该类直接实现的所有接口（返回的是 Class[] 类型）
        return Arrays.stream(service.getClass().getInterfaces())
                // 将每个 Class 对象映射为其“规范名称”（Canonical Name），即包含包名的完整类名或接口名。
            .map(Class::getCanonicalName)
            .collect(Collectors.toList());
    }
}
