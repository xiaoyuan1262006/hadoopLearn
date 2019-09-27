package utils;

import java.io.IOException;
import java.util.Properties;

/**
 * @ProjectName: hadoopLearn
 * @Package: utils
 * @ClassName: PropertyHolderLazy
 * @Author: liuxiaoyuan
 * @Description: ${description}
 * @Date: 28/02/2019 17:41
 * @Version: 1.0
 */
public class PropertyHolderLazy {

    private  static  Properties properties;

    public static  Properties getProperties() throws IOException {
        synchronized (PropertyHolderLazy.class){
            if (properties ==null){
                properties = new Properties();
                properties.load(PropertyHolderLazy.class.getClassLoader().getResourceAsStream("collect.properties"));
            }
        }
        return properties;
    }


}
