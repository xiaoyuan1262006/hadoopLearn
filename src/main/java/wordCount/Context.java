package wordCount;

import java.util.HashMap;

/**
 * @ProjectName: hadoopLearn
 * @Package: wordCount
 * @ClassName: Context
 * @Author: liuxiaoyuan
 * @Description: ${description}
 * @Date: 31/01/2019 17:54
 * @Version: 1.0
 */
public class Context {
    private HashMap<Object, Object> contextMap = new HashMap<Object, Object>();

    public void write(Object key,Object value){
        contextMap.put(key, value);
    }
}
