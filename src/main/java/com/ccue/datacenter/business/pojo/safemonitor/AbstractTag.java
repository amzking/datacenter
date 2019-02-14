package com.ccue.datacenter.business.pojo.safemonitor;

/**
 * @author joking@aliyun.com
 * @description
 * @date 2019-02-14
 */
public abstract class AbstractTag {

    /**
     * @description: id
     * @since: 2019-02-14
     */
    private String id;

    /**
     * @description: 测点名称
     * @since: 2019-02-14
     */
    private String tagName;

    /**
     * @description: 测点编号
     * @since: 2019-02-14
     */
    private String tagNum;

    /**
     * @description: 煤矿编码
     * @since: 2019-02-14
     */
    private String mineNum;

    /**
     * @description: 区域编码
     * @since: 2019-02-14
     */
    private String areaNum;

    /**
     * @description: 分站编码
     * @since: 2019-02-14
     */
    private String stationNum;

    abstract static class Builder<T extends Builder<T>> {

    }

}
