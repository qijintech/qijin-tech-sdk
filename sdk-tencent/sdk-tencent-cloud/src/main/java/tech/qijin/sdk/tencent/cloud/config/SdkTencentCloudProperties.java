package tech.qijin.sdk.tencent.cloud.config;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author michealyang
 * @date 2020-02-25
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
@ConfigurationProperties(prefix = "sdk.tencent.cloud")
public class SdkTencentCloudProperties {
    // 默认host
    private String host;
    // COS 桶
    private String bucket;
    // 区域
    private String region;
    // 秘钥有效期，单位是秒
    private Integer duration;
    private List<Route> routes;

    private Map<String, Route> routeMap;

    public String getBucketByScene(String scene) {
        if (StringUtils.isBlank(scene)) {
            return Strings.EMPTY;
        }
        Route r = getRouteMap().get(scene);
        if (r != null) {
            return r.getBucket();
        }
        return Strings.EMPTY;
    }

    public String getHostByScene(String scene) {
        if (StringUtils.isBlank(scene)) {
            return Strings.EMPTY;
        }
        Route r = getRouteMap().get(scene);
        if (r != null) {
            return r.getHost();
        }
        return Strings.EMPTY;
    }

    public String getRegionByScene(String scene) {
        if (StringUtils.isBlank(scene)) {
            return Strings.EMPTY;
        }
        Route r = getRouteMap().get(scene);
        if (r != null) {
            return r.getRegion();
        }
        return Strings.EMPTY;
    }

    private Map<String, Route> getRouteMap() {
        if (MapUtils.isNotEmpty(routeMap)) {
            return routeMap;
        }
        if (CollectionUtils.isEmpty(routes)) {
            return Collections.emptyMap();
        }
        return routes.stream().collect(Collectors.toMap(Route::getScene, Function.identity()));
    }

    @Data
    public static class Route {
        private String scene;
        private String bucket;
        private String host;
        private String region;
    }
}
