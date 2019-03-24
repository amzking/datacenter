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
    private final String id;

    /**
     * @description: 测点名称
     * @since: 2019-02-14
     */
    private final String tagName;

    /**
     * @description: 测点编号
     * @since: 2019-02-14
     */
    private final String tagNum;

    /**
     * @description: 煤矿编码
     * @since: 2019-02-14
     */
    private final String mineNum;

    /**
     * @description: 区域编码
     * @since: 2019-02-14
     */
    private final String areaNum;

    /**
     * @description: 分站编码
     * @since: 2019-02-14
     */
    private final String stationNum;

    abstract static class Builder<T extends Builder<T>> {

        private String id;

        private String tagName;

        private String tagNum;

        private String mineNum;

        private String areaNum;

        private String stationNum;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder tagName(String tagName) {
            this.tagName = tagName;
            return this;
        }

        public Builder tagNum(String tagNum) {
            this.tagNum = tagNum;
            return this;
        }

        public Builder mineNum(String mineNum) {
            this.mineNum = mineNum;
            return this;
        }

        public Builder areaNum(String areaNum) {
            this.areaNum = areaNum;
            return this;
        }

        public Builder stationNum(String stationNum) {
            this.stationNum = stationNum;
            return this;
        }

        abstract AbstractTag build();
        protected abstract T self();
    }

    AbstractTag(Builder<?> builder){
        this.id = builder.id;
        this.tagNum = builder.tagNum;
        this.tagName = builder.tagName;
        this.mineNum = builder.mineNum;
        this.areaNum = builder.areaNum;
        this.stationNum = builder.stationNum;
    }

}
