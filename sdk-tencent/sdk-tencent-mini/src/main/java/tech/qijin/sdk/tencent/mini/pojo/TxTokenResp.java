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
public class TxTokenResp extends TxError implements Serializable {
    private static final long serialVersionUID = -1060216618475607934L;

    private String accessToken;
    private Long expiresIn;

    public static TxTokenResp fromJson(String json) {
        TxTokenResp resp = JSON.parseObject(json, TxTokenResp.class);
        if (resp.getErrcode() != 0) {
            TxError error = fromJson(json, TxType.MINI);
            log.error("MINI token request error, code={}, msg={}", error.getErrcode(), error.getErrmsg());
            return null;
        }
        return resp;
    }
}
