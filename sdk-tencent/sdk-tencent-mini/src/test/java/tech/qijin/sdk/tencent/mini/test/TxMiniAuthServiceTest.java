package tech.qijin.sdk.tencent.mini.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.qijin.sdk.tencent.base.TxErrorException;
import tech.qijin.sdk.tencent.mini.TxMiniAuthService;
import tech.qijin.sdk.tencent.mini.pojo.UserPhoneInfo;

public class TxMiniAuthServiceTest extends BaseTest{
    @Autowired
    private TxMiniAuthService txMiniAuthService;

    @Test
    public void testJsCode2SessionInfo() throws TxErrorException {
        txMiniAuthService.jsCode2SessionInfo("001Crc5y1ysxTb0l2N4y1aV25y1Crc5a");
    }

    @Test
    public void testDecodeUserInfo() {
        String sd = "dVpjGBvnSyppaM72PnYLgglR98vKrX0bhfavSSwHRAA3MEbUO8oqvO6v0GpVpBY1EW95hmfTEQ6qtNUakwon6OXD8XMG1TCSBhp4bdnleoZ1oWTV9mO9U85lXSzqQX6hSwaUl6Rjv4Sk03SnXnFhBmM7EKD1bMUPRzuJMHPRyFLYuA9EkfMQAj+FNdltgPu5/SNGUHVvsqMsUWS9d1mDyg==";
        String iv = "PNh5mCI/GDuRe23Klvpjfw==";
        String sk = "L4pNE9mRjhYSi5gSUSbaNw==";
        UserPhoneInfo info = txMiniAuthService.decodeUserInfo(sk, sd, iv);
        log.info("result={}", info);
    }
}
