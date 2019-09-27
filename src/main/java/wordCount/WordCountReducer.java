package wordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @ProjectName: hadoopLearn
 * @Package: wordCount
 * @ClassName: WordCountReducer
 * @Author: liuxiaoyuan
 * @Description: ${description}
 * @Date: 02/02/2019 10:33
 * @Version: 1.0
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text,IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count=0;
        Iterator<IntWritable> iterator = values.iterator();
        while (iterator.hasNext()) {
            IntWritable value = iterator.next();
            count += value.get();
        }
        context.write(new Text(key),new IntWritable(count));
    }
}
