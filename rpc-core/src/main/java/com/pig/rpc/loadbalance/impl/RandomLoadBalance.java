package com.pig.rpc.loadbalance.impl;

import cn.hutool.core.util.RandomUtil;
import com.pig.rpc.loadbalance.LoadBalance;

import java.util.List;

/**
 * @Author Mr.Pan
 * @Date 2025/2/22
 **/
public class RandomLoadBalance implements LoadBalance {
    @Override
    public String select(List<String> list) {
        return RandomUtil.randomEle(list);
    }
}
