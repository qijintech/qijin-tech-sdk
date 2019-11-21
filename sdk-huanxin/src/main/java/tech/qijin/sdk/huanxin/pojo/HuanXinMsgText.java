package tech.qijin.sdk.huanxin.pojo;

import lombok.Data;

/**
 * @author michealyang
 * @date 2019-11-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
public class HuanXinMsgText extends HuanXinMsgBase {
    public HuanXinMsgText() {
        type = "txt";
    }
}
