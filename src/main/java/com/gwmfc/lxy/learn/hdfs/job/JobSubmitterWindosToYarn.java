package com.gwmfc.lxy.learn.hdfs.job;

import com.gwmfc.lxy.learn.hdfs.task.map.WordCountMap;
import com.gwmfc.lxy.learn.hdfs.task.reduce.WordCountReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

import java.net.URI;

/**
 * @ProjectName: hadoopLearn
 * @Package: com.gwmfc.lxy.learn.hdfs.job
 * @ClassName: JobSubmitter
 * @Author: liuxiaoyuan
 * @Description: ${description}
 * @Date: 30/08/2019 15:48
 * @Version: 1.0
 */
public class JobSubmitterWindosToYarn {
    public static void main(String[] args) throws Exception {
        final Logger logger = Logger.getLogger(JobSubmitterWindosToYarn.class);
        //在代码中设置jvm系统参数设置用户身份
        System.setProperty("HADOOP_USER_NAME","root");

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://192.168.222.4:9000"); //设置job运行时要访问的文件系统
        conf.set("mapreduce.framework.name","yarn"); //设置job提交到哪里运行
        conf.set("yarn.resourcemanager.hostname","192.168.222.10");

        conf.set("mapreduce.app-submission.cross-platform","true"); //如果从window系统上运行这个job提交客户端程序，需要加这个跨平台提交参数
        Job job = Job.getInstance(conf);
        //分装参数：jar包所在的位置
        //job.setJarByClass(JobSubmitter.class);
        job.setJar("G:\\bigdata.jar");
        //分装参数：本次job所要调用的mappter实现类，reduce实现类
        job.setMapperClass(WordCountMap.class);
        job.setReducerClass(WordCountReduce.class);
        //分装参数：本次job的mapper实现类，Reduce 实现类产生的数据结果的key，value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        Path output = new Path("/wordcount/output");
        FileSystem fs = FileSystem.get(new URI("hdfs://192.168.222.4:9000"),conf,"root");
        if(fs.exists(output)){
            fs.delete(output,true);
        }

        //分装参数：本次处理数据的输入路径，输出路径
        FileInputFormat.setInputPaths(job,new Path("/wordcount"));
        FileOutputFormat.setOutputPath(job,output);

        //分装参数：想要启动的reduce task数量
        job.setNumReduceTasks(2);

        boolean res = job.waitForCompletion(true);

        System.exit(res?0:-1);
        logger.info("计数结束");


    }
}
