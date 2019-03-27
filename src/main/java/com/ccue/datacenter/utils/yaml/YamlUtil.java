package com.ccue.datacenter.utils.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * @author joking@aliyun.com
 * @description:
 * @since: 2019-01-07
 */
public class YamlUtil {
    /**
     * @author joking@aliyun.com
     * @description:The default path when a path was not offered.
     * @since: 2019-03-2
     */
    private static final String DEFAULT_PATH = "config.yaml";

    /**
     * @author joking@aliyun.com
     * @description: offer a way to change the path, once settled, should't change, avoid muliti-IO
     * @since: 2019-03-2
     */
    private final InputStream inputStream;

    /**
     * @author joking@aliyun.com
     * @description: yaml
     * @since: 2019-01-07
     */
    private final Yaml yaml;


    public static class Reader {
        private Yaml yaml = new Yaml();
        private InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(DEFAULT_PATH);

        public Reader path(String path) {
            this.inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
            return this;
        }

        public YamlUtil load() {
            return new YamlUtil(this);
        }
    }

    public Map read() {
        Map map = yaml.<Map>load(this.inputStream);
        return map;
    }

    public <T> T readAs(Class<T> clazz) {
        T res = yaml.<T>loadAs(this.inputStream, clazz);
        return res;
    }

    /**
     * @param reader
     * @description:
     * @since: 2019-01-07
     * @return:
     */
    private YamlUtil(Reader reader) {
        inputStream = reader.inputStream;
        yaml = reader.yaml;
    }

}
