package tech.qijin.sdk.huanxin.service;

import tech.qijin.sdk.huanxin.pojo.HuanXinMsgImg;
import tech.qijin.sdk.huanxin.pojo.HuanXinMsgText;

/**
 * @author michealyang
 * @date 2019-11-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public interface HuanXinMsgService {
    void sendText(HuanXinMsgText text);

    void sendImg(HuanXinMsgImg img);
}
