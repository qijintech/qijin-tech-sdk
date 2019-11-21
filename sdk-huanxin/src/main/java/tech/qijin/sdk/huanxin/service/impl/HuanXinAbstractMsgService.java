package tech.qijin.sdk.huanxin.service.impl;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import tech.qijin.sdk.huanxin.pojo.HuanXinMsgBase;
import tech.qijin.util4j.utils.DateUtil;

import java.util.Map;

/**
 * @author michealyang
 * @date 2019-11-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public abstract class HuanXinAbstractMsgService {
    protected Map<String, Object> buildReq(HuanXinMsgBase msgBase) {
        Map<String, Object> req = Maps.newHashMap();
        req.put("target_type", msgBase.getTargetType());
        req.put("target", msgBase.getTarget());
        if (StringUtils.isNotBlank(msgBase.getFrom())) {
            req.put("from", msgBase.getFrom());
        }
        // 填充消息信息
        Map<String, Object> msg = Maps.newHashMap();
        msg.put("msg", msgBase.getMsg());
        msg.put("type", msgBase.getType());
        req.put("msg", msg);
        // 填充扩展信息
        Map<String, Object> ext = Maps.newHashMap();
        ext.put("HXFromUserAvatar", "http://avatar.cdn.gmugmu.com/3805665_b6lttq");
        ext.put("HXFromUserID", 3805665);
        ext.put("HXFromUserName", "sender");
        ext.put("HXFromUserSenderTime", DateUtil.now().getTime() / 1000);
        ext.put("HXConversationId", "new_jimu_gmu_3814260new_jimu_gmu_3805665");
        req.put("ext", ext);
        return req;
    }
}
