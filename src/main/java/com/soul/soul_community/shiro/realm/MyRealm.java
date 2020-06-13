package com.soul.soul_community.shiro.realm;

import com.soul.soul_community.entity.User;
import com.soul.soul_community.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName MyRealm
 * @Deacription 自定义Realm
 * @Author Lemonsoul
 * @Date 2020/2/14 15:28
 * @Version 1.0
 **/

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * 权限认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        User user = userService.findByEmail(userName);
        if (user == null){
            throw new UnknownAccountException();
        }else {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserEmail(),user.getUserPassword(),"xx");
            return authenticationInfo;
        }
    }
}
