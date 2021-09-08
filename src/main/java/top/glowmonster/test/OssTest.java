package top.glowmonster.test;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import top.glowmonster.base.oss.BaseOSS;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OssTest {
    public static void main(String[] args) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        /*String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "";
        String accessKeySecret = "";
        String bucketName = "yiyunmusic";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ObjectListing objectListing = ossClient.listObjects(bucketName);
        // objectListing.getObjectSummaries获取所有文件的描述信息。
        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println(" - " + objectSummary.getKey() + "  " +
                    "(size = " + objectSummary.getSize() + ")");
        }

        // 关闭OSSClient。
        ossClient.shutdown();*/
        // Timestamp timestamp = new Timestamp(new Date().getTime());
        // System.out.println(timestamp);

        // 构思: 前端将不匹配的文件拦截,后台不再做拦截,直接将文件上传至oss,再调用一下一段代码获取地址,
        // 将期限设置为一年,然后存入数据库,

        OSS ossClient = new BaseOSS().bulidOSSClient();
        // ossClient.putObject("yiyunmusic", "avatar/" + realFileName, inputStream);
        // ossClient.shutdown();generatePresignedUrl
        // 设置URL过期时间为1小时。
        Date expiration = new Date(new Date().getTime() + 3600 * 100);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl("yiyunmusic", "avatar/chi.jpeg", expiration);
        String urlString = url + "";
        System.out.println(urlString);
        // System.out.println(url);
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String date = sdf.format(new Date());
        System.out.println(date);*/
    }
}
