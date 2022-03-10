package tech.qijin.sdk.tencent.base;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 腾讯内容检测场景
 */
public enum TxAuditScene {
    PROFILE(1, "资料"),
    COMMENT(2, "评论"),
    FEED(3, "论坛"),
    LOG(4, "社交日志"),
    ;

    TxAuditScene(int code, String description) {
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

    private static Map<Integer, TxAuditScene> map
            = Arrays.stream(TxAuditScene.values())
            .collect(Collectors.toMap(scene -> scene.code, Function.identity()));

    public static TxAuditScene valueOf(int code) {
        return map.get(code);
    }
}
