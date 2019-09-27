package utils;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.io.IOException;
import java.util.Properties;

/**
 * @ProjectName: hadoopLearn
 * @Package: utils
 * @ClassName: PropertyHolder
 * @Author: liuxiaoyuan
 * @Description: ${description}
 * @Date: 28/02/2019 17:32
 * @Version: 1.0
 */
public class PropertyHolderHungery {

    private static Properties properties = new Properties();

    static{
        try {
            properties.load(PropertyHolderHungery.class.getClassLoader().getResourceAsStream("collect.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProps() throws IOException {
        return properties;
    }




}
