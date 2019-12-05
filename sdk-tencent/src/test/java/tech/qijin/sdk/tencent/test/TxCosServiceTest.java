package tech.qijin.sdk.tencent.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.qijin.sdk.tencent.pojo.CredentialVo;
import tech.qijin.sdk.tencent.service.TxCosService;

import java.io.IOException;

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
        log.info("{}", JSON.toJSONString(credentialVo));
    }
}
