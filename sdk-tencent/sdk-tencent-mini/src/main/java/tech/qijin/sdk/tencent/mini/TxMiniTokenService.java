package tech.qijin.sdk.tencent.mini;

public interface TxMiniTokenService {
    String DEFAULT_HOST = "https://api.weixin.qq.com";

    String TOKEN_URI = "/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    default String requestUrl(String uri) {
        return String.format("%s%s", DEFAULT_HOST, uri);
    }

    default String getToken() {
        return "";
    }

    ;
}
