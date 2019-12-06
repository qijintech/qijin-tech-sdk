package tech.qijin.sdk.tencent.test;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tech.qijin.sdk.tencent.pojo.CredentialVo;
import tech.qijin.sdk.tencent.service.TxCosService;

/**
 * @author michealyang
 * @date 2019-12-05
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public class TxCosServiceTest extends BaseTest {
    @Autowired
    private TxCosService txCosService;

    @Test
    public void testGetCredential() {
        CredentialVo credentialVo = txCosService.getCredential();
        Assert.assertNotNull(credentialVo);
        Assert.assertNotNull(credentialVo.getCredentials());
        Assert.assertTrue(StringUtils.isNotBlank(credentialVo.getCredentials().getSessionToken()));
        Assert.assertTrue(StringUtils.isNotBlank(credentialVo.getCredentials().getTmpSecretId()));
        Assert.assertTrue(StringUtils.isNotBlank(credentialVo.getCredentials().getTmpSecretKey()));
        log.info("{}", JSON.toJSONString(credentialVo));
    }
}
