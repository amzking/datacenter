package com.ccue.datacenter.utils.properties;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.util.List;

public class PropertyUtil {

    private final static String DEFAULT_PROPERTY = "config.properties";

    private PropertyUtil() {
    }

    private static Configuration configuration;

    static {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName(DEFAULT_PROPERTY)
                                .setListDelimiterHandler(new DefaultListDelimiterHandler(';')));

        try {
            configuration = builder.getConfiguration();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static class PropertiyUtilHolder {
        private static PropertyUtil util = new PropertyUtil();
    }

    public static PropertyUtil getInstance() {
        return PropertiyUtilHolder.util;
    }


    public int getInt(String key) {
        return configuration.getInt(key);
    }

    public int getInt(String key, int defaultValue) {
        return configuration.getInt(key, defaultValue);
    }

    public String getString(String key, String defaultStr) {
        return configuration.getString(key, defaultStr);
    }

    public String getString(String key) {
        return configuration.getString(key);
    }

    public List getList(String key) {
        List list = configuration.getList(key);
        return list;
    }

}
