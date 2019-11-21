package tech.qijin.sdk.huanxin.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.sdk.huanxin.common.HuanXinCode;
import tech.qijin.sdk.huanxin.config.HuanXinProperties;
import tech.qijin.sdk.huanxin.pojo.HuanXinErrorResp;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.HttpClient;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.pojo.HttpResp;

import java.io.IOException;
import java.util.Map;

import static tech.qijin.sdk.huanxin.service.HuanXinService.*;

/**
 * @author michealyang
 * @date 2019-11-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@Service
public class HuanXinRestClient {
    @Autowired
    private HuanXinProperties properties;

    private static final String URL_TOKEN = "HOST/ORG_NAME/APP_NAME/SUFFIX";

    public <T> T doPostWithoutAuth(String suffix, Map<String, Object> body, Class<T> clazz) {
        String url = URL_TOKEN.replace(HX_HOST, properties.getHost())
                .replace(HX_ORG_NAME, properties.getOrgName())
                .replace(HX_APP_NAME, properties.getAppName())
                .replace(HX_URL_SUFFIX, suffix);
        try {
            HttpResp resp = HttpClient.doPost(url, body);
            return JSON.parseObject(resp.getData(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T doPost(String suffix, Map<String, Object> body, Class<T> clazz) {
        String url = URL_TOKEN.replace(HX_HOST, properties.getHost())
                .replace(HX_ORG_NAME, properties.getOrgName())
                .replace(HX_APP_NAME, properties.getAppName())
                .replace(HX_URL_SUFFIX, suffix);
        Map<String, String> headers = Maps.newHashMap();
        headers.put("Authorization", "Bearer YWMtWIazKgp1Eeq-t_mB37HunQAAAAAAAAAAAAAAAAAAAAH9wEqQTt8R5pq3jcllktt-AgMAAAFugYRt6QBPGgDZqxy8XTKHl_NsS3Teil2HaAt6kVjGyCTtJB0QsCiy_A");
        try {
            HttpResp resp = HttpClient.doPost(url, body, headers);
            if (resp.getCode() != HuanXinCode.SUCCESS.getCode()) {
                handlerHuanXinRequestError(url, resp);
            }
            return JSON.parseObject(resp.getData(), clazz);
        } catch (IOException e) {
            log.error("call huanxin rest api io exception | e={}", e);
        }
        MAssert.isTrue(false, ResEnum.INTERNAL_ERROR);
        return null;
    }

    private void handlerHuanXinRequestError(String url, HttpResp resp) {
        HuanXinErrorResp errorResp = JSON.parseObject(resp.getData(), HuanXinErrorResp.class);
        log.error("call huanxin rest api error | url={} | errorResp={}", url, errorResp);
        MAssert.isTrue(false, ResEnum.INTERNAL_ERROR);
    }
}
