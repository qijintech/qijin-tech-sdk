package tech.qijin.sdk.tencent.mini.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.qijin.util4j.utils.DateUtil;

import java.util.Date;

@Data
@AllArgsConstructor
public class MsgValue {
    private String value;

    public static MsgValue of(Number v) {
        return new MsgValue(String.valueOf(v));
    }

    public static MsgValue of(Date v) {
        return new MsgValue(DateUtil.formatStr(v, DateUtil.DATETIME_FORMAT));
    }

    public static MsgValue of(String v) {
        return new MsgValue(v);
    }
}
