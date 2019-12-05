package tech.qijin.sdk.tencent.service.impl;

import com.alibaba.fastjson.JSON;
import com.tencent.cloud.CosStsClient;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import tech.qijin.sdk.tencent.pojo.CredentialVo;
import tech.qijin.sdk.tencent.service.TxCosService;

import java.io.IOException;
import java.util.TreeMap;

/**
 * @author michealyang
 * @date 2019-12-05
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@Service
public class TxCosServiceImpl implements TxCosService {
    @Override
    public CredentialVo getCredential() {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        // 云 API 密钥 secretId
        config.put("secretId", "AKIDTigPr3vXrugBpCr5d2A3liQs51jA3D3g");
        // 云 API 密钥 secretKey
        config.put("secretKey", "au97pGQKyQcYlkUx96nDMSJujHZbNt87");
        // 临时密钥有效时长，单位是秒
        config.put("durationSeconds", 1800);

        // 换成你的 bucket
        config.put("bucket", "img-1300635595");
        // 换成 bucket 所在地区
        config.put("region", "ap-beijing");
        // 这里改成允许的路径前缀，可以根据自己网站的用户登录态判断允许上传的具体路径，
        // 例子： a.jpg 或者 a/* 或者 * (使用通配符*存在重大安全风险, 请谨慎评估使用)
        config.put("allowPrefix", "*");

        // 密钥的权限列表。简单上传和分片需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
        String[] allowActions = new String[]{
                // 简单上传
                "name/cos:PutObject",
                // 表单上传
                "name/cos:PostObject",
                // 分片上传： 初始化分片
                "name/cos:InitiateMultipartUpload",
                // 分片上传： 查询 bucket 中未完成分片上传的UploadId
                "name/cos:ListMultipartUploads",
                // 分片上传： 查询已上传的分片
                "name/cos:ListParts",
                // 分片上传： 上传分片块
                "name/cos:UploadPart",
                // 分片上传： 完成分片上传
                "name/cos:CompleteMultipartUpload"
        };
        config.put("allowActions", allowActions);
        // 请求临时密钥信息
        try {
            JSONObject credential = CosStsClient.getCredential(config);
            CredentialVo credentialVo = JSON.parseObject(credential.toString(), CredentialVo.class);
            return credentialVo;
        } catch (IOException e) {
            log.error("get tencent credentail exception | e={}", e);
            return null;
        }
    }
}
