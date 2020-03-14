package tech.qijin.sdk.tencent.mini.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.sdk.tencent.base.TxErrorException;
import tech.qijin.sdk.tencent.mini.TxMiniAuthService;
import tech.qijin.sdk.tencent.mini.pojo.TxJscode2SessionResp;
import tech.qijin.util4j.kms.KmsBean;

/**
 * @author michealyang
 * @date 2020-02-25
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@Service
public class TxMiniAuthServiceImpl implements TxMiniAuthService {
    @Autowired
    private KmsBean kmsBean;

    @Override
    public TxJscode2SessionResp jsCode2SessionInfo(String jsCode) throws TxErrorException {
        return null;
    }
}
