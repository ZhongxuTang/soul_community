<#include "head.ftl">
    <div class="layui-container" style="padding: 0;width: 100%;">
        <div class="layui-col-md12 personalCenter-back">
            <div class="personalCenter-head">
                <img src="${user.userHeadPortrait}" class="layui-nav-img">
                <div class="personalDate">
                    <span class="userName">${user.userName}</span>
                    <#if user.userSex == "男">
                        <span class="layui-icon userSex userSexMan">&#xe662;</span>
                    <#elseif user.userSex == "女">
                        <span class="layui-icon userSex userSexWoman">&#xe661;</span>
                    <#elseif user.userSex == "">
                        <span class="userSex"></span>
                    </#if>
                    <ul>
                        <li>
                            <span>
                                <i class="layui-icon">&#xe617;</i>
                                在线
                            </span>
                        </li>
                        <li>
                            注册于${user.userRegisterDate?string('yyyy-MM-dd')}
                        </li>
                        <li style="display: block;margin-top: 10px;">
                            <div class="personalIntroduce">
                                <div class="personalIntroduce-textarea">
                                    <p>${user.personalizedSignature!""}</p>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>



