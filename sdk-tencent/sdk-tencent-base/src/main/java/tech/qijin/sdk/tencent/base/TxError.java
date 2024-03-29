package tech.qijin.sdk.tencent.base;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 微信错误码.
 * 请阅读：
 * 公众平台：<a href="http://mp.weixin.qq.com/wiki/10/6380dc743053a91c544ffd2b7c959166.html">全局返回码说明</a>
 * 企业微信：<a href="https://work.weixin.qq.com/api/doc#10649">全局错误码</a>
 *
 * @author Daniel Qian & Binary Wang
 */
@Data
public class TxError implements Serializable {
    private static final long serialVersionUID = 7869786563361406291L;

    /**
     * 微信错误代码.
     */
    private int errcode;

    /**
     * 微信错误信息.
     * （如果可以翻译为中文，就为中文）
     */
    private String errmsg;

    /**
     * 微信接口返回的错误原始信息（英文）.
     */
    private String errorMsgEn;

    private String json;

    public static TxError fromJson(String json) {
        return fromJson(json, null);
    }

    public static TxError fromJson(String json, TxType type) {
        final TxError txError = JSON.parseObject(json, TxError.class);
        if (txError.getErrcode() == 0 || type == null) {
            return txError;
        }

        if (StringUtils.isNotEmpty(txError.getErrmsg())) {
            txError.setErrorMsgEn(txError.getErrmsg());
        }

        switch (type) {
            case MP: {
                final String msg = TxMpErrorCode.findMsgByCode(txError.getErrcode());
                if (msg != null) {
                    txError.setErrmsg(msg);
                }
                break;
            }
            case WORK: {
                final String msg = TxWorkErrorCode.findMsgByCode(txError.getErrcode());
                if (msg != null) {
                    txError.setErrmsg(msg);
                }
                break;
            }
            case MINI: {
                final String msg = TxMiniErrorCode.findMsgByCode(txError.getErrcode());
                if (msg != null) {
                    txError.setErrmsg(msg);
                }
                break;
            }
            default:
                return txError;
        }

        return txError;
    }

    @Override
    public String toString() {
        if (this.json == null) {
            return "错误代码：" + this.errcode + ", 错误信息：" + this.errmsg;
        }

        return "错误代码：" + this.errcode + ", 错误信息：" + this.errmsg + "，微信原始报文：" + this.json;
    }

}
