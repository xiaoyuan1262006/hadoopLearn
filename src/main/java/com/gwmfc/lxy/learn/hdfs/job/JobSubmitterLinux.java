package com.gwmfc.lxy.learn.hdfs.job;

import com.gwmfc.lxy.learn.hdfs.task.map.WordCountMap;
import com.gwmfc.lxy.learn.hdfs.task.reduce.WordCountReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @ProjectName: hadoopLearn
 * @Package: com.gwmfc.lxy.learn.hdfs.job
 * @ClassName: JobSubmitterLinux
 * @Author: liuxiaoyuan
 * @Description: linux服务器上，任务提交给yarn
 * @Date: 30/08/2019 16:56
 * @Version: 1.0
 */
public class JobSubmitterLinux {
    public static void main(String[] args) throws Exception{
        Configuration configuration=new Configuration();
       // configuration.set();


        Job job=Job.getInstance(configuration);
        job.setJarByClass(JobSubmitterLinux.class);

        job.setMapperClass(WordCountMap.class);
        job.setReducerClass(WordCountReduce.class);

        //map 输出类
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path("/aaa"));
        FileOutputFormat.setOutputPath(job,new Path("/wordcount/outputLinux"));

        job.setNumReduceTasks(3);

        boolean resp =job.waitForCompletion(true);

        System.exit(resp?0:1);




    }
}
