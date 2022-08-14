package tech.qijin.sdk.tencent.mini.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import tech.qijin.sdk.tencent.base.TxError;
import tech.qijin.sdk.tencent.base.TxType;

import java.io.Serializable;

/**
 * @author michealyang
 * @date 2020-02-25
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@Data
public class TxSubSendResp extends TxError implements Serializable {
    private static final long serialVersionUID = -1060216618475607934L;

    public static TxSubSendResp fromJson(String json) {
        TxSubSendResp resp = JSON.parseObject(json, TxSubSendResp.class);
        if (resp.getErrcode() != 0) {
            TxError error = fromJson(json, TxType.MINI);
            log.error("MINI subscribe send request error, code={}, msg={}, msgEn={}",
                    error.getErrcode(), error.getErrmsg(),error.getErrorMsgEn());
            return resp;
        }
        return resp;
    }
}
