package com.pig.rpc.registry;

import com.pig.rpc.dto.RpcReq;

import java.net.InetSocketAddress;

/**
 * @Author Mr.Pan
 * @Date 2025/2/22
 **/
public interface ServiceDiscovery {
    InetSocketAddress lookupService(RpcReq rpcReq);
}
