package tech.qijin.sdk.huanxin.test;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.qijin.sdk.huanxin.pojo.HuanXinMsgImg;
import tech.qijin.sdk.huanxin.pojo.HuanXinMsgText;
import tech.qijin.sdk.huanxin.service.HuanXinAdminService;
import tech.qijin.sdk.huanxin.service.HuanXinMsgService;

/**
 * @author michealyang
 * @date 2019/2/15
 * 开始做眼保健操：←_← ↑_↑ →_→ ↓_↓
 **/
public class AppTest extends BaseTest{
    @Autowired
    private HuanXinAdminService adminService;
    @Autowired
    private HuanXinMsgService msgService;

    @Test
    public void token() {
        adminService.getToken();
    }

    @Test
    public void sendMsgText() {
        HuanXinMsgText msg = new HuanXinMsgText();
        msg.setTargetType("users");
        msg.setTarget(Lists.newArrayList("dev_new_jimu_gmu_5000030"));
        msg.setFrom("dev_new_jimu_gmu_2601394");
        msg.setMsg("这是一条来自虚空世界的测试消息3");
        msgService.sendText(msg);
    }

    @Test
    public void sendMsgImg() {
        HuanXinMsgImg msg = new HuanXinMsgImg();
        msg.setTargetType("users");
        msg.setTarget(Lists.newArrayList("new_jimu_gmu_3814260"));
        msg.setFrom("new_jimu_gmu_3805665");
        msg.setUuid("406471a0-0d0a-11ea-a107-7f1e7ff6d34f");
        msgService.sendImg(msg);
    }
}
