package tech.qijin.sdk.tencent.base;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 腾讯内容检测标签
 */
public enum TxAuditLabel {
    NORMAL(100, "正常"),
    AD(10001, "广告"),
    POLITICS(20001, "时政"),
    PORN(20002, "色情"),
    LOG(20003, "辱骂"),
    CRIME(20006, "违法犯罪"),
    CHEAT(20008, "欺诈"),
    VULGAR(20012, "低俗"),
    COPYRIGHT(20013, "版权"),
    OTHER(21000, "其他"),
    ;

    TxAuditLabel(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private int code;
    private String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    private static Map<Integer, TxAuditLabel> map
            = Arrays.stream(TxAuditLabel.values())
            .collect(Collectors.toMap(label -> label.code, Function.identity()));

    public static TxAuditLabel valueOf(int code) {
        return map.get(code);
    }
}
