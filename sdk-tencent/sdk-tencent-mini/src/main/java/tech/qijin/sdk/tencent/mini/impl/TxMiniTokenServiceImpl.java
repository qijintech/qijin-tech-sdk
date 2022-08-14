package tech.qijin.sdk.tencent.mini.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.qijin.sdk.tencent.base.Constants;
import tech.qijin.sdk.tencent.mini.TxMiniTokenService;
import tech.qijin.sdk.tencent.mini.pojo.TxTokenResp;
import tech.qijin.util4j.kms.KmsBean;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.redis.RedisUtil;
import tech.qijin.util4j.utils.DateUtil;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TxMiniTokenServiceImpl implements TxMiniTokenService {
    @Autowired
    private KmsBean kmsBean;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RestTemplate txMiniClient;

    @Scheduled(fixedDelay = 10000)
    public void refresh() {
        Long ttl = redisUtil.ttl(tokenCacheKey());
        if (!NumberUtil.gtZero(ttl) || ttl < 60) {
            log.info("TxMiniTokenService refresh token, ttl={}", ttl);
            getTokenFromApi();
        }
    }

    @Override
    public String getToken() {
        String token = getTokenFromCache();
        if (StringUtils.isNotBlank(token)) return token;
        return getTokenFromApi();
    }

    public boolean delToken() {
        return redisUtil.delete(tokenCacheKey());
    }

    private String getTokenFromApi() {
        try {
            if (!tryLock()) return "";
            return requestFromApi();
        } finally {
            unlock();
        }
    }

    private String requestFromApi() {
        Optional<String> appIdOpt = kmsBean.getAppId(Constants.TX_KMS_PREFIX);
        Optional<String> secretOpt = kmsBean.getSecret(Constants.TX_KMS_PREFIX);
        MAssert.isTrue(appIdOpt.isPresent() && secretOpt.isPresent(), ResEnum.INVALID_CONFIG);
        String uri = String.format(TOKEN_URI, appIdOpt.get(), secretOpt.get());
        ResponseEntity<String> entity = txMiniClient.getForEntity(requestUrl(uri), String.class);
        TxTokenResp tokenResp = TxTokenResp.fromJson(entity.getBody());
        if (tokenResp != null) {
            String token = tokenResp.getAccessToken();
            Long expire = tokenResp.getExpiresIn();
            cacheToken(token, expire);
            return token;
        }
        return "";
    }

    private String getTokenFromCache() {
        return redisUtil.getString(tokenCacheKey());
    }

    private void cacheToken(String token, Long expire) {
        redisUtil.setString(tokenCacheKey(), token, expire, TimeUnit.SECONDS);
    }

    private boolean tryLock() {
        return redisUtil.setIfAbsent(tokenMutexKey(), String.valueOf(DateUtil.now().getTime()), 10 * DateUtil.MILLI_PER_SECOND);
    }

    private boolean unlock() {
        return redisUtil.delete(tokenMutexKey());
    }

    private String tokenCacheKey() {
        return "tencent:sdk:token";
    }

    private String tokenMutexKey() {
        return "tencent:sdk:token:mutex";
    }
}
