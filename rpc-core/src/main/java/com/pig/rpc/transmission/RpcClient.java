package com.pig.rpc.transmission;

import com.pig.rpc.dto.RpcReq;
import com.pig.rpc.dto.RpcResp;

/**
 * @Author Mr.Pan
 * @Date 2025/2/19
 **/
public interface RpcClient {
    RpcResp<?> sendReq(RpcReq req);
}
