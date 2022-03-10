package tech.qijin.sdk.tencent.mini.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.qijin.sdk.tencent.base.TxErrorException;
import tech.qijin.sdk.tencent.mini.TxMiniAuthService;
import tech.qijin.sdk.tencent.mini.TxMiniTokenService;
import tech.qijin.sdk.tencent.mini.impl.TxMiniTokenServiceImpl;
import tech.qijin.sdk.tencent.mini.pojo.UserPhoneInfo;

public class TxMiniTokenServiceTest extends BaseTest{
    @Autowired
    private TxMiniTokenServiceImpl txMiniTokenService;

    @Test
    public void token() throws TxErrorException {
        String token = txMiniTokenService.getToken();
        System.out.println(token);
    }

}
