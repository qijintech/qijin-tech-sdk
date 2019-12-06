package tech.qijin.sdk.huanxin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.sdk.huanxin.config.HuanXinProperties;
import tech.qijin.util4j.kms.KmsBean;

/**
 * @author michealyang
 * @date 2019-11-15
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Service
public class HuanXinService {
    @Autowired
    protected HuanXinProperties properties;
    @Autowired
    protected KmsBean kmsBean;

    protected static final String HX_HOST = "HOST";
    protected static final String HX_ORG_NAME = "ORG_NAME";
    protected static final String HX_APP_NAME = "APP_NAME";
    protected static final String HX_URL_SUFFIX = "SUFFIX";
    protected static final String HX_CLIENT_ID = "CLIENT_ID";
    protected static final String HX_CLIENT_SECRET = "CLIENT_SECRET";
}
