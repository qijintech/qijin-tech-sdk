package tech.qijin.sdk.huanxin.service;

import tech.qijin.sdk.huanxin.pojo.HuanXinToken;

/**
 * @author michealyang
 * @date 2019-11-15
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public interface HuanXinAdminService {
    /**
     * 获取环信REST API请求token
     * <p>POST /{org_name}/{app_name}/token</p>
     *
     * @return
     * @refer http://docs-im.easemob.com/im/server/ready/user
     */
    HuanXinToken getToken();
}
