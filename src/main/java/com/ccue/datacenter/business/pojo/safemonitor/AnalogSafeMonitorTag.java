package com.ccue.datacenter.business.pojo.safemonitor;

/**
 * @author joking@aliyun.com
 * @description
 * @date 2019-02-14
 */
public class AnalogSafeMonitorTag extends AbstractTag {

    /**
     * @description: 值上限
     * @since: 2019-02-14
     */
    private final String upperValueLimit;

    private final String lowerValueLimit;


    public static class Builder extends AbstractTag.Builder<Builder> {

        private String upperValueLimit;

        private String lowerValueLimit;

        @Override
        AbstractTag build() {
            return new AnalogSafeMonitorTag(this);
        }

        public Builder upperValueLimit(String upperValueLimit) {
            this.upperValueLimit = upperValueLimit;
            return this;
        }

        public Builder lowerValueLimit(String lowerValueLimit) {
            this.lowerValueLimit = lowerValueLimit;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private AnalogSafeMonitorTag(Builder builder) {
        super(builder);
        this.upperValueLimit = builder.upperValueLimit;
        this.lowerValueLimit = builder.lowerValueLimit;
    }
}
