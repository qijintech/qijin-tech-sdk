package tech.qijin.sdk.tencent.mini.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import tech.qijin.sdk.tencent.base.TxError;
import tech.qijin.sdk.tencent.base.TxType;

import java.io.Serializable;
import java.util.List;

/**
 * @author michealyang
 * @date 2020-02-25
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@Data
public class TxAuditResp extends TxError implements Serializable {
    private static final long serialVersionUID = -1060216618475607934L;

    private String traceId;
    private TxAuditResult result;
    private List<TxAuditDetail> detail;

    public static TxAuditResp fromJson(String json) {
        TxAuditResp resp = JSON.parseObject(json, TxAuditResp.class);
        if (resp.getErrcode() != 0) {
            TxError error = fromJson(json, TxType.MINI);
            log.error("MINI audit request error, code={}, msg={}", error.getErrcode(), error.getErrmsg());
            return null;
        }
        return resp;
    }

    @Data
    public class TxAuditResult {
        private String suggest;
        private String label;
    }

    @Data
    public class TxAuditDetail {
        private int errcode;
        private String strategy;
        private String suggest;
        private Integer label;
        private Integer prob;
        private String keyword;
    }

}
