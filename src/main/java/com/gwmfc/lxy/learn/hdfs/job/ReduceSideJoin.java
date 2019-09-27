package com.gwmfc.lxy.learn.hdfs.job;

import com.gwmfc.lxy.learn.hdfs.entityBean.JoinBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: hadoopLearn
 * @Package: com.gwmfc.lxy.learn.hdfs.job
 * @ClassName: ReduceSideJoin
 * @Author: liuxiaoyuan
 * @Description: ${description}
 * @Date: 05/09/2019 13:36
 * @Version: 1.0
 */
public class ReduceSideJoin {

    public static class ReduceSideJoinMapper extends Mapper<LongWritable, Text, Text, JoinBean> {
        String fileName;
        JoinBean joinBean=new JoinBean();
        Text k=new Text();

        /*
         * maptask在处理数据是，现调用set方法，调用完之后才对每一行执行map方法
         */
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            FileSplit inputSplit=(FileSplit) context.getInputSplit();
            fileName=inputSplit.getPath().getName();
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] fields=value.toString().split(",");

            if (fileName.startsWith("order")) {
                joinBean.set(fields[0], fields[1], "NULL", -1, "NULL", "order");
            } else {
                joinBean.set("NULL", fields[0], fields[1], Integer.parseInt(fields[2]), fields[3], "user");
            }
            k.set(joinBean.getUserId());
            context.write(k, joinBean);

        }
    }


    public static class ReduceSideJoinReduce extends Reducer<Text, JoinBean, JoinBean, NullWritable> {

        @Override
        protected void reduce(Text key, Iterable<JoinBean> values, Context context) throws IOException, InterruptedException {

            List<JoinBean> joinBeanList=new ArrayList<JoinBean>();
            JoinBean userJoinBean=null;
            try {
                for (JoinBean value : values) {
                    if ("order".equals(value.getUserName())) {
                        JoinBean newjoinBean=new JoinBean();
                        BeanUtils.copyProperties(newjoinBean,value);
                        joinBeanList.add(newjoinBean);
                    } else {
                        userJoinBean=new JoinBean();
                        BeanUtils.copyProperties(userJoinBean,value );
                    }
                }

                for (JoinBean joinBean : joinBeanList) {
                    joinBean.setUserName(userJoinBean.getUserName());
                    joinBean.setAge(userJoinBean.getAge());
                    joinBean.setFriend(userJoinBean.getFriend());
                    context.write(joinBean,NullWritable.get());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }

    //G:\bigdata\orderinput

    public static void main(String[] args) throws Exception {
        Configuration configuration=new Configuration();
        Job job=Job.getInstance(configuration);
        job.setJarByClass(ReduceSideJoin.class);
        job.setMapperClass(ReduceSideJoinMapper.class);
        job.setReducerClass(ReduceSideJoinReduce.class);
        job.setNumReduceTasks(2);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(JoinBean.class);
        job.setOutputKeyClass(JoinBean.class);
        job.setOutputValueClass(NullWritable.class);
        FileInputFormat.setInputPaths(job,new Path("G:\\bigdata\\orderinput"));
        FileOutputFormat.setOutputPath(job,new Path("G:\\bigdata\\orderoutput"));
        job.waitForCompletion(true);

        boolean resp =job.waitForCompletion(true);

        System.exit(resp?0:1);
    }
}
