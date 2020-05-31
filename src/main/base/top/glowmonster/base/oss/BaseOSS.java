package top.glowmonster.base.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

public class BaseOSS {
    public OSS bulidOSSClient() {
        final String END_POINT = "http://oss-cn-hangzhou.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        final String ACCESS_KEY_ID = "LTAI4FuivYqGr2TLEQZg6QTf";
        final String ACCESS_KEY_SECRET = "zki7KzzgUPnzHse194mmELl9Jk1Xk1";
        return new OSSClientBuilder().build(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }
}
