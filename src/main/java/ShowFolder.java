import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by zcl on 5/4/14.
 */
public class ShowFolder {

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        System.out.println(args[0]);
        conf.set("fs.defaultFS",args[0]);// "hdfs://wemeetcluster");
        conf.set("dfs.nameservices", "wemeetcluster");
        conf.set("dfs.ha.namenodes.wemeetcluster", "nn1,nn2");
        conf.set("dfs.namenode.rpc-address.wemeetcluster.nn1", "node124.intra.hiwemeet.com:8020");
        conf.set("dfs.namenode.rpc-address.wemeetcluster.nn2", "node123.intra.hiwemeet.com:8020");
        conf.set("dfs.client.failover.proxy.provider.wemeetcluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        FileSystem fs = null;
        try {
            fs = FileSystem.get(conf);
            FileStatus[] list = fs.listStatus(new Path("/"));
            for (FileStatus file : list) {
                System.out.println(file.getPath().getName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
