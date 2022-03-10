package tech.qijin.sdk.tencent.mini.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.qijin.sdk.tencent.base.TxAuditScene;
import tech.qijin.sdk.tencent.base.TxErrorException;
import tech.qijin.sdk.tencent.mini.TxMiniAuditService;
import tech.qijin.sdk.tencent.mini.impl.TxMiniTokenServiceImpl;

public class TxMiniAuditServiceTest extends BaseTest {
    @Autowired
    private TxMiniAuditService txMiniAuditService;

    @Test
    public void checkMsg() throws TxErrorException {
        txMiniAuditService.checkMsg("owGNP4xqsc9FZ6avjbd6saHzAS7Q", "老赵学生妹他中午童颜巨乳吃太多强奸，下午性虐就援交一直美女少妇", TxAuditScene.PROFILE);
    }

}
