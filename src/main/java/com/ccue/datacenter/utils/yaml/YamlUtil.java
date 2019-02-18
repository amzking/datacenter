package com.ccue.datacenter.utils.yaml;

import org.yaml.snakeyaml.Yaml;
import java.util.Map;

/**
 * @author joking@aliyun.com
 * @description:
 * @since: 2019-01-07
 */
public class YamlUtil {
    /**
     * The default path when a path was not offered.
     */
    private static final String DEFAULT_PATH = "config.yaml";

    /**
     * offer a way to change the path
     */
    private final String path;

    /**
     * @description: yaml
     * @since: 2019-01-07
     */
    private final Yaml yaml;


    public static class Reader {
        private String path = DEFAULT_PATH;
        private Yaml yaml = new Yaml();
        public Reader path(String path) {
            this.path = path;
            return this;
        }
        public YamlUtil load() {
            return new YamlUtil(this);
        }
    }

    public Map read() {
        Map map = yaml.<Map>load(this.getClass().getClassLoader().getResourceAsStream(path));
        return map;
    }

    public <T> T readAs(Class<T> clazz) {
        T res = yaml.<T>loadAs(this.getClass().getClassLoader().getResourceAsStream(path), clazz);
        return res;
    }

    /**
     * @description:
     * @since: 2019-01-07
     * @param reader
     * @return: 
     */
    private YamlUtil(Reader reader) {
        path = reader.path;
        yaml = reader.yaml;
    }

}
