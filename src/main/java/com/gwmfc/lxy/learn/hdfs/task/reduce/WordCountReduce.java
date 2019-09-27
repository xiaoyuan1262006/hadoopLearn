package com.gwmfc.lxy.learn.hdfs.task.reduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @ProjectName: hadoopLearn
 * @Package: com.gwmfc.lxy.learn.hdfs.task.reduce
 * @ClassName: WordCountReduce
 * @Author: liuxiaoyuan
 * @Description: ${description}
 * @Date: 23/08/2019 16:29
 * @Version: 1.0
 */
public class WordCountReduce  extends Reducer<Text, IntWritable,Text,IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        Iterator iterator = values.iterator();
        while (iterator.hasNext()) {
            IntWritable value = (IntWritable) iterator.next();
            count += value.get();
        }
        context.write(key,new IntWritable(count));

    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        super.cleanup(context);
    }
}
