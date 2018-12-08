package com.ccue.datacenter;

import com.ccue.datacenter.utils.properties.PropertyUtil;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        List str = PropertyUtil.getInstance().getList("zk");
        System.out.println(str);
    }
}
