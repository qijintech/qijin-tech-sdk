package tech.qijin.sdk.huanxin.common;

/**
 * @author michealyang
 * @date 2019-11-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public enum HuanXinCode {
    SUCCESS(200, "成功"),
    UNKNOWN_REQUEST(400, "服务器不理解请求的语法"),
    UNAUTHORIZED(401, "请求要求身份验证"),
    FORBIDDEN(403, "服务器拒绝请求"),
    NOT_FOUND(404, "接口不存在"),
    REQ_TIMEOUT(408, "服务器等候请求时发生超时"),
    OVERSIZE(413, "请求体超过了5kb"),
    UNSUPPORTED(415, "请求体的类型不支持"),
    RATE_LIMITED(429, "接口被限流"),
    SERVER_ERROR(500, "服务器遇到错误"),
    Not_IMPLEMENTED(501, "尚未实施"),
    BAD_GATEWAY(502, "上游服务无响应"),
    SERVICE_UNAVAILABLE(503, "服务器遇到错误"),
    GATEWAY_TIMEOUT(504, "网关超时");

    private int code;
    private String desc;

    HuanXinCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
