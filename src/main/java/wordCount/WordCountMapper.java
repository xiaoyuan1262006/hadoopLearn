package wordCount;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @ProjectName: hadoopLearn
 * @Package: wordCount
 * @ClassName: WordCountMapper
 * @Author: liuxiaoyuan
 * @Description: ${description}
 * @Date: 02/02/2019 10:25
 * @Version: 1.0
 */
public class WordCountMapper  extends Mapper<LongWritable, Text,Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //
        String line = value.toString();
        String[] words = line.split(" ");
        for (String  word :words){
            context.write(new Text(word),new IntWritable(1));
        }


    }
}
