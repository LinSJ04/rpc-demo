package com.pig.rpc.dto;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Mr.Pan
 * @Date 2025/2/19
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RpcReq implements Serializable { // 发请求，携带通用信息
    private static final long serialVersionUID = 1L;

    private String reqId; // 请求Id
    private String interfaceName; // 找这个接口的实现类
    private String methodName; // 方法名
    private Object[] params; // 参数列表
    private Class<?>[] paramTypes; // 参数类型列表
    private String version; // 版本，同一个方法可能会有不同实现
    private String group; // 不同类型的实现，如普通用户和管理员

    // UserService -> CommonUserServiceImpl1.getUser()
    //             -> CommonUserServiceImpl2.getUser()
    //             -> AdminUserServiceImpl1.getUser()
    //             -> AdminUserServiceImpl2.getUser()

    public String rpcServiceName() {
        return getInterfaceName()
            + StrUtil.blankToDefault(getVersion(), StrUtil.EMPTY)
            + StrUtil.blankToDefault(getGroup(), StrUtil.EMPTY);
    }
}
