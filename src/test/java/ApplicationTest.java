import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * @ProjectName: hadoopLearn
 * @Package: PACKAGE_NAME
 * @ClassName: ApplicationTest
 * @Author: liuxiaoyuan
 * @Description: ${description}
 * @Date: 30/01/2019 17:40
 * @Version: 1.0
 */

public class ApplicationTest {
//     FileSystem fileSystem = null;
//
//    @Before
//    public  void init() throws Exception {
//        Configuration conf = new Configuration();
//        conf.set("dfs.replication","2"); //指定本客户端上传文件到hdfs需要保存的副本2
//        conf.set("dfs.blocksize","64m"); //指定本客户端上传文件到hdfs切换的规格大小
//        fileSystem = FileSystem.get(new URI("hdfs://192.168.222.4:9000/"),conf,"root");
//    }
//
//    /**
//     * @Method testGet
//     * @Author liuxiaoyuan
//     * @Version  1.0
//     * @Description 从HDFS下载文件到本地磁盘
//     * @param
//     * @Return void
//     * @Exception
//     * @Date 30/01/2019 17:45
//     */
//    @Test
//    public void testGet() throws Exception{
//        fileSystem.copyToLocalFile(new Path("/user/mkdir/input/yy.jpg"),new Path("G:\\bigdata"));
//        fileSystem.close();
//
//    }
//    /**
//     * @Method testCp
//     * @Author liuxiaoyuan
//     * @Version  1.0
//     * @Description 在hdfs 中移动文件
//     * @param
//     * @Return void
//     * @Exception
//     * @Date 31/01/2019 15:46
//     */
//    @Test
//    public void testCp()throws Exception{
//
//        fileSystem.rename(new Path("/user/root/input/NOTICE.txt"),new Path("/user/hadoop"));
//        fileSystem.close();
//    }
//
//    /**
//     * @Method testCp
//     * @Author liuxiaoyuan
//     * @Version  1.0
//     * @Description 在hdfs 中创建文件
//     * @param
//     * @Return void
//     * @Exception
//     * @Date 31/01/2019 15:46
//     */
//    @Test
//    public void testMakedir()throws Exception{
//
//        fileSystem.mkdirs(new Path("/user/mkdir/input/NOTICE.txt"));
//        fileSystem.close();
//    }
//
//
//    /**
//     * @Method testCp
//     * @Author liuxiaoyuan
//     * @Version  1.0
//     * @Description 在hdfs 中删除
//     * @param
//     * @Return void
//     * @Exception
//     * @Date 31/01/2019 15:46
//     */
//    @Test
//    public void testDelete()throws Exception{
//
////        fileSystem.delete(new Path("/wordcount/input"),true);
////        fileSystem.close();
//    }
//
//
//    /**
//     * @Method testCp
//     * @Author liuxiaoyuan
//     * @Version  1.0
//     * @Description 在hdfs 中查询
//     * @param
//     * @Return void
//     * @Exception
//     * @Date 31/01/2019 15:46
//     */
//    @Test
//    public void testList()throws Exception{
//
//        RemoteIterator<LocatedFileStatus> ri = fileSystem.listFiles(new Path("/"),true);
//        while (ri.hasNext()){
//            LocatedFileStatus ld = ri.next();
//            System.out.println(ld.getPath()+"-"+String.valueOf(ld.getAccessTime())+"-"+ Arrays.toString(ld.getBlockLocations())+(ld.isFile()?"是文件":"是文件夹"));
//        }
//        fileSystem.close();
//    }
//    /**
//     * @Method TestreadFile
//     * @Author liuxiaoyuan
//     * @Version  1.0
//     * @Description 读取文件
//     * @param
//     * @Return void
//     * @Exception
//     * @Date 31/01/2019 17:19
//     */
//    @Test
//    public void TestreadFile() throws Exception{
//
//        FSDataInputStream fi =  fileSystem.open(new Path("/user/mkdir/input/LICENSE.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(fi));
//      /*  byte[] buf = new byte[1024];
//        fi.read(buf);
//
//        System.out.println(new String(buf)) ;*/
//        String  line = null;
//        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//        }
//        br.close();
//        fi.close();
//        fileSystem.close();
//
//    }
//
//
//    @Test
//    public void TestWritFile() throws Exception{
//
//        FSDataOutputStream fo =  fileSystem.create(new Path("/wordcount/input/English4.txt"));
//        FileInputStream fis = new FileInputStream("G:\\bigdata\\English4.txt");
//        byte[] buf = new byte[1024];
//        int read = 0;
//
//        while ((read = fis.read(buf)) != -1) {
//           fo.write(buf,0,read);
//        }
//        fo.close();
//        fis.close();
//        fileSystem.close();
//
//    }

}
