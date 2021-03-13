package tech.qijin.sdk.tencent.mini.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.qijin.sdk.tencent.base.TxErrorException;
import tech.qijin.sdk.tencent.mini.TxMiniAuthService;

public class TxMiniAuthServiceTest extends BaseTest{
    @Autowired
    private TxMiniAuthService txMiniAuthService;

    @Test
    public void testJsCode2SessionInfo() throws TxErrorException {
        txMiniAuthService.jsCode2SessionInfo("001Crc5y1ysxTb0l2N4y1aV25y1Crc5a");
    }
}
