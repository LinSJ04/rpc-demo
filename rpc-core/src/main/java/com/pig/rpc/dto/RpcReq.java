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
public class RpcReq implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reqId;
    private String interfaceName;
    private String methodName;
    private Object[] params;
    private Class<?>[] paramTypes;
    private String version;
    private String group;

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
