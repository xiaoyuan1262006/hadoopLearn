package com.gwmfc.lxy.learn.hdfs.job;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @ProjectName: hadoopLearn
 * @Package: com.gwmfc.lxy.learn.hdfs.job
 * @ClassName: SkewWordCountMapper
 * @Author: liuxiaoyuan
 * @Description: ${description}
 * @Date: 27/09/2019 15:58
 * @Version: 1.0
 */
public class SkewWordCount {

    public static class SkewWordCountMapper extends Mapper<LongWritable, Text,Text,IntWritable>{
        Random random = new Random();
        Text k=new Text();
        int numReduceTasks=0;
        IntWritable v = new  IntWritable(1);

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            numReduceTasks=context.getNumReduceTasks();
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            int numReduceTasks=context.getNumReduceTasks();

            String[]  words = value.toString().split(" ");
            for (String word: words){
                k.set(word+"-"+random.nextInt(numReduceTasks));
                context.write(k,v);
            }
        }
    }

    public static class SkewWordCountReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count=0;
            for (IntWritable value: values
                 ) {
                count+=value.get();
            }
            context.write(key,new IntWritable(count));
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration=new Configuration();
        Job job=Job.getInstance(configuration);
        job.setJarByClass(SkewWordCount.class);
        job.setMapperClass(SkewWordCountMapper.class);
        job.setReducerClass(SkewWordCountReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.setCombinerClass(SkewWordCountReducer.class);
        FileInputFormat.setInputPaths(job,new Path("G:\\bigdata\\wordcountcombinerinput"));
        FileOutputFormat.setOutputPath(job,new Path("G:\\bigdata\\wordcountcombineroutput"));
        job.setNumReduceTasks(1);
        boolean res=job.waitForCompletion(true);
        System.exit(res?0:1);


    }


}
