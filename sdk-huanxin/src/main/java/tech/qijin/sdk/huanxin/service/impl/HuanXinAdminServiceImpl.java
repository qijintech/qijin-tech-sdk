package tech.qijin.sdk.huanxin.service.impl;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.sdk.huanxin.pojo.HuanXinToken;
import tech.qijin.sdk.huanxin.service.HuanXinAdminService;
import tech.qijin.sdk.huanxin.service.HuanXinRestClient;
import tech.qijin.sdk.huanxin.service.HuanXinService;

import java.util.Map;

/**
 * @author michealyang
 * @date 2019-11-15
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@Service
public class HuanXinAdminServiceImpl extends HuanXinService implements HuanXinAdminService {
    private static final String URL_TOKEN_SUFFIX = "token";
    private static final String KMS_PREFIX = "huanxin";

    @Autowired
    private HuanXinRestClient huanXinRestClient;

    @Override
    public HuanXinToken getToken() {
        Map<String, Object> req = Maps.newHashMap();
        req.put("grant_type", "client_credentials");
        req.put("client_id", kmsBean.getSecretId(KMS_PREFIX).get());
        req.put("client_secret", kmsBean.getSecretKey(KMS_PREFIX).get());
        try {
            String res = huanXinRestClient.doPostWithoutAuth(URL_TOKEN_SUFFIX, req, String.class);
            log.info(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
