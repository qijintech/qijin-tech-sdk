package tech.qijin.sdk.tencent.cloud.impl;

import com.alibaba.fastjson.JSON;
import com.tencent.cloud.CosStsClient;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.sdk.tencent.base.Constants;
import tech.qijin.sdk.tencent.cloud.TxCosService;
import tech.qijin.sdk.tencent.cloud.config.SdkTencentCloudProperties;
import tech.qijin.sdk.tencent.cloud.pojo.TxCosType;
import tech.qijin.sdk.tencent.cloud.pojo.TxCredentialVo;
import tech.qijin.util4j.kms.KmsBean;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.LogFormat;
import tech.qijin.util4j.utils.MAssert;

import java.io.IOException;
import java.util.Optional;
import java.util.TreeMap;

/**
 * @author michealyang
 * @date 2020-02-24
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@Service
public class TxCosServiceImpl implements TxCosService {
    @Autowired
    private KmsBean kmsBean;
    @Autowired
    private SdkTencentCloudProperties properties;

    @Override
    public TxCredentialVo getCredential(TxCosType txCosType) {

        TreeMap<String, Object> config = getCosConfig(txCosType);

        // 请求临时密钥信息
        try {
            JSONObject credential = CosStsClient.getCredential(config);
            TxCredentialVo credentialVo = JSON.parseObject(credential.toString(), TxCredentialVo.class);
            return credentialVo;
        } catch (IOException e) {
            LogFormat.builder()
                    .message("get tencent cos credential exception")
                    .put("exception", e).build();
            return null;
        }
    }

    private TreeMap<String, Object> getCosConfig(TxCosType txCosType) {
        Optional<String> secretIdOpt = kmsBean.getSecretId(Constants.TX_KMS_PREFIX);
        Optional<String> secretKeyOpt = kmsBean.getSecretKey(Constants.TX_KMS_PREFIX);
        MAssert.isTrue(secretIdOpt.isPresent() && secretKeyOpt.isPresent(), ResEnum.INVALID_KEY);
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        // 云 API 密钥 secretId
        config.put("secretId", secretIdOpt.get());
        // 云 API 密钥 secretKey
        config.put("secretKey", secretKeyOpt.get());

        // 临时密钥有效时长，单位是秒
        config.put("durationSeconds", properties.getDuration());
        // 换成你的 bucket
        config.put("bucket", properties.getBucket());
        // 换成 bucket 所在地区
        config.put("region", properties.getRegion());

        // 这里改成允许的路径前缀，可以根据自己网站的用户登录态判断允许上传的具体路径，
        // 例子： a.jpg 或者 a/* 或者 * (使用通配符*存在重大安全风险, 请谨慎评估使用)
        switch (txCosType) {
            case IMG:
            case FILE:
            case AUDIO:
            case VIDEO:
                config.put("allowPrefix", "*");
            default:
                config.put("allowPrefix", "*");
        }

        // 密钥的权限列表。简单上传和分片需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
        String[] allowActions = getAllowActions();

        config.put("allowActions", allowActions);

        return config;
    }

    private String[] getAllowActions() {
        return new String[]{
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
    }
}

