package com.pig.rpc.registry.impl;

import cn.hutool.core.util.StrUtil;
import com.pig.rpc.constant.RpcConstant;
import com.pig.rpc.dto.RpcReq;
import com.pig.rpc.factory.SingletonFactory;
import com.pig.rpc.loadbalance.LoadBalance;
import com.pig.rpc.loadbalance.impl.RandomLoadBalance;
import com.pig.rpc.registry.ServiceDiscovery;
import com.pig.rpc.registry.zk.ZkClient;
import com.pig.rpc.util.IPUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @Author Mr.Pan
 * @Date 2025/2/22
 **/
@Slf4j
public class ZkServiceDiscovery implements ServiceDiscovery {
    private final ZkClient zkClient;
    private final LoadBalance loadBalance;

    public ZkServiceDiscovery() {
        this(
            SingletonFactory.getInstance(ZkClient.class),
            SingletonFactory.getInstance(RandomLoadBalance.class)
        );
    }

    public ZkServiceDiscovery(ZkClient zkClient, LoadBalance loadBalance) {
        this.zkClient = zkClient;
        this.loadBalance = loadBalance;
    }

    @Override
    public InetSocketAddress lookupService(RpcReq rpcReq) {
        String path = RpcConstant.ZK_RPC_ROOT_PATH
            + StrUtil.SLASH
            + rpcReq.rpcServiceName();

        List<String> children = zkClient.getChildrenNode(path);
        String address = loadBalance.select(children);

        return IPUtils.toInetSocketAddress(address);
    }
}
