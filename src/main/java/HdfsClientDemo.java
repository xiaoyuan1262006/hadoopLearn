
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.net.URI;


/**
 * @ProjectName: hadoopLearn
 * @Package: PACKAGE_NAME
 * @ClassName: HdfsClientDemo
 * @Author: liuxiaoyuan
 * @Description: ${description}
 * @Date: 30/01/2019 15:50
 * @Version: 1.0
 */
public class HdfsClientDemo {
    public static void main(String[] args) {
        //会从项目的classpath中加载core-default.xml hdfs-default.xml core-site.xlm
        Configuration conf = new Configuration();
        conf.set("dfs.replication","2"); //指定本客户端上传文件到hdfs需要保存的副本2
        conf.set("dfs.blocksize","64m"); //指定本客户端上传文件到hdfs切换的规格大小
        try {
            //构造一个访问指定HDFS系统的客户端对象 参数1 HDFS系统的uri
            FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.222.4:9000/"),conf,"root");
            fileSystem.copyFromLocalFile(new Path("G:\\Downloads\\scala-2.12.8.tgz"),new Path("/bigdata/"));
            fileSystem.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



//    public static class TokenizerMapper
//            extends Mapper<Object, Text, Text, IntWritable> {
//
//        private final static IntWritable one = new IntWritable(1);
//        private Text word = new Text();
//
//        public void map(Object key, Text value, Context context
//        ) throws IOException, InterruptedException {
//            StringTokenizer itr = new StringTokenizer(value.toString());
//            while (itr.hasMoreTokens()) {
//                word.set(itr.nextToken());
//                context.write(word, one);
//            }
//        }
//    }
//
//    public static class IntSumReducer
//            extends Reducer<Text, IntWritable, Text, IntWritable> {
//        private IntWritable result = new IntWritable();
//
//        public void reduce(Text key, Iterable<IntWritable> values,
//                           Context context
//        ) throws IOException, InterruptedException {
//            int sum = 0;
//            for (IntWritable val : values) {
//                sum += val.get();
//            }
//            result.set(sum);
//            context.write(key, result);
//        }
//    }
}
