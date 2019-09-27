package com.gwmfc.lxy.learn.hdfs.entityBean;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


import java.io.IOException;

/**
 * @ProjectName: hadoopLearn
 * @Package: com.gwmfc.lxy.learn.hdfs.entityBean
 * @ClassName: WordCountCombiner
 * @Author: liuxiaoyuan
 * @Description: ${description}
 * @Date: 05/09/2019 16:40
 * @Version: 1.0
 */
public class WordCountCombiner extends Reducer<Text, IntWritable,Text, IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count=0;
        for (IntWritable value: values) {
            count+=value.get();
        }
        context.write(key,new IntWritable(count));
    }
}
