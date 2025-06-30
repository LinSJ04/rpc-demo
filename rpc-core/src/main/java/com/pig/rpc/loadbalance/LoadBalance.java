package com.pig.rpc.loadbalance;

import java.util.List;

/**
 * @Author Mr.Pan
 * @Date 2025/2/22
 **/
public interface LoadBalance {
    public String select(List<String> list);
}
