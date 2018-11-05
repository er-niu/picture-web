package com.erniu.pictureweb.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by songxiao on 2018/11/2.
 */
@Component
@Slf4j
public class CosClientUtil implements CommandLineRunner {

    private COSClient cosClient;

    @Value("${secretId}")
    private String secretId;

    @Value("${secretKey}")
    private String secretKey;

    @Value("${region}")
    private String region;

    @Value("${bucketName}")
    private String bucketName;

    @Override
    public void run(String... strings) throws Exception {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 3 生成cos客户端
        cosClient = new COSClient(cred, clientConfig);
    }

    // 删除文件
    public void deleteObject(String key) {
        try {
            cosClient.deleteObject(bucketName, key);
        } catch (Exception cle) {
            // 自定义异常处理比如打印异常等
            log.error("del object failed.", cle);
        }
    }

}
