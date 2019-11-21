package tech.qijin.sdk.huanxin.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author michealyang
 * @date 2019-11-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
public class HuanXinMsgBase {
    /**
     * 发送的目标类型；
     * users：给用户发消息，chatgroups：给群发消息，chatrooms：给聊天室发消息
     */
    @NotBlank
    private String targetType;
    /**
     * 发送的目标；
     * 注意这里需要用数组，数组长度建议不大于20，即使只有一个用户，也要用数组 ['u1']；给用户发送时数组元素是用户名，给群组发送时，数组元素是groupid
     */
    @NotEmpty
    private List<String> target;
    /**
     * 表示消息发送者;无此字段Server会默认设置为“from”:“admin”，有from字段但值为空串(“”)时请求失败
     */
    private String from;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 消息类型；
     * txt:文本消息，img：图片消息，loc：位置消息，audio：语音消息，video：视频消息，file：文件消息
     */
    @NotBlank
    protected String type;
}
