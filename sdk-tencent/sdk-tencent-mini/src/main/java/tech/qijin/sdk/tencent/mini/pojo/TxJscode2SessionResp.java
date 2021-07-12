package tech.qijin.sdk.tencent.mini.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @author michealyang
 * @date 2020-02-25
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
public class TxJscode2SessionResp implements Serializable {
    private static final long serialVersionUID = -1060216618475607933L;

    private String session_key;
    private String openid;
    private String unionid;

    private int errcode;
    private String errmsg;

    public static TxJscode2SessionResp fromJson(String json) {
        return JSON.parseObject(json, TxJscode2SessionResp.class);
    }
}
