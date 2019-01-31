package wordCount;

/**
 * @ProjectName: hadoopLearn
 * @Package: wordCount
 * @ClassName: Mapper
 * @Author: liuxiaoyuan
 * @Description: ${description}
 * @Date: 31/01/2019 17:53
 * @Version: 1.0
 */
public interface Mapper {
    public void map(String line, Context Context);
}
