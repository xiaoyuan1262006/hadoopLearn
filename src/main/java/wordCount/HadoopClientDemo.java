package wordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Arrays;


/**
 * @ProjectName: hadoopLearn
 * @Package: wordCount
 * @ClassName: HadoopClientDemo
 * @Author: liuxiaoyuan
 * @Description: ${description}
 * @Date: 18/08/2019 17:05
 * @Version: 1.0
 */

public class HadoopClientDemo {
    public static void main(String[] args) throws Exception{
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication","2");
        configuration.set("dfs.blocksize","3m");
        FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"),configuration,"root");
        fs.copyFromLocalFile(new Path("file:/G:/Downloads/cfs12.log"),new Path("/aaa"));
        fs.close();
    }

    FileSystem fs = null;
    @Before
    public void init() throws Exception{
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication","2");
        configuration.set("dfs.blocksitze","3m");
        fs = FileSystem.get(new URI("hdfs://master:9000"),configuration,"root");
    }


    @Test
    public void testWordCount(){

    }

    @Test
    public void deleteFileToHadoop() throws Exception{
        //fs.
        //fs.delete(new Path("/aaa"));
    }
//
    @Test
    public void putFileToHadoop() throws Exception{
        fs.copyFromLocalFile(new Path("file:/G:/Downloads/DingTalk_v4.6.36.9.exe"),new Path("/aaa"));
        fs.close();
    }

    @After
    public  void end() throws Exception{
        fs.close();
    }

    @Test
    public void testRename() throws Exception{
        fs.rename((new Path("/aaa")), (new Path("/bbb")));
    }


    @Test
    public void testMkdir() throws Exception{
        fs.mkdirs(new Path("/aaa"));
    }


    @Test
    public void testLs() throws Exception{
        RemoteIterator<LocatedFileStatus> ite =  fs.listFiles(new Path("/"),true);
        while (ite.hasNext()){
            LocatedFileStatus status = ite.next();
            System.out.println("块大小=="+status.getBlockSize());
            System.out.println("文件长度==" + status.getLen());
            System.out.println("块信息==" + Arrays.toString(status.getBlockLocations()));
            System.out.println("副本信息==" + status.getReplication());
            System.out.println("文件路径==" + status.getPath());
            System.out.println("------------------------");
        }
    }

    @Test
    public void testReadData(){
        String content=null;
        try {
            FSDataInputStream fsDataInputStream = fs.open(new Path("/bbb"));

/*            byte[] buffer = new byte[1024];
            int size=0;
            //循环来读取该文件中的数据
            while((size=fsDataInputStream.read(buffer))!=-1){
                content=new String(buffer, 0, size);
                System.out.println(content);
            }*/

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fsDataInputStream, "utf-8"));
            while ((content=bufferedReader.readLine())!=null){
                System.out.println(content);
            }
            fsDataInputStream.close();
            fs.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestWriteData(){
        try {
            FSDataOutputStream fsDataOutputStream = fs.create(new Path("/abc.jpg"),false);
            FileInputStream fileInputStream = new FileInputStream("G:/Downloads/wangyi14.jpg");
            byte[] bytes = new byte[1024];
            int read = 0;
            while ((read = fileInputStream.read(bytes))!=-1){
                fsDataOutputStream.write(bytes,0,read);
            }
            fileInputStream.close();
            fsDataOutputStream.close();
            fs.close();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
    }

    /**
     * @Method testReadData
     * @Author liuxiaoyuan
     * @Version  1.0
     * @Description  指定块信息
     * @param
     * @Return void
     * @Exception
     * @Date 21/08/2019 16:09
     */
    @Test
    public void testReadOneClockData(){
//        String content=null;
//        try {
//            FSDataInputStream fsDataInputStream = fs.open(new Path("/bbb"));
//
///*            byte[] buffer = new byte[1024];
//            int size=0;
//            //循环来读取该文件中的数据
//            while((size=fsDataInputStream.read(buffer))!=-1){
//                content=new String(buffer, 0, size);
//                System.out.println(content);
//            }*/
//
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fsDataInputStream, "utf-8"));
//            while ((content=bufferedReader.readLine())!=null){
//                System.out.println(content);
//            }
//            fsDataInputStream.close();
//            fs.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
