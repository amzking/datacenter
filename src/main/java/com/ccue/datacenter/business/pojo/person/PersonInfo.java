package com.ccue.datacenter.business.pojo.person;

/**
 * @author joking@aliyun.com
 * @description
 * @date 2019-02-14
 */
public class PersonInfo {

    private final String id;

    private final String personName;

    private final String personCardNum;

    private final String mineNum;

    private final String team;

    private final String duty;

    public static class Builder {

        private String id;

        private final String personName;

        private final String personCardNum;

        private String mineNum;

        private String team;

        private String duty;

        public Builder(String personCardNum, String personName) {
            this.personCardNum = personCardNum;
            this.personName = personName;
        }

        public Builder id(String id) {
            this.id = id;
            return  this;
        }

        public Builder mineNum(String mineNum) {
            this.mineNum = mineNum;
            return this;
        }

        public Builder team(String team) {
            this.team = team;
            return this;
        }

        public Builder duty(String duty) {
            this.duty = duty;
            return this;
        }

        public PersonInfo build() {
            return new PersonInfo(this);
        }
    }

    private PersonInfo(Builder builder) {
        this.id = builder.id;
        this.personCardNum = builder.personCardNum;
        this.personName = builder.personName;
        this.mineNum = builder.mineNum;
        this.team = builder.team;
        this.duty = builder.duty;
    }

}
