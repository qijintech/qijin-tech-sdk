package tech.qijin.sdk.huanxin.pojo;

import lombok.Data;

/**
 * @author michealyang
 * @date 2019-11-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
public class HuanXinMsgFile extends HuanXinMsgBase {
    public HuanXinMsgFile() {
        type = "file";
    }

    /**
     * 图片资源在环信的uuid
     */
    private String uuid;
}
