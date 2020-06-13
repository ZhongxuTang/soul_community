<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>soul社区</title>
    <link rel="icon" href="/img/logo/logoHead.ico" type="image/x-icon">
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" type="text/css" href="/css/pageStyle.css"/>
    <script type="text/javascript" src="/layui/layui.js"></script>
    <script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
</head>
<style>
    .layui-edge,.layui-header,.layui-inline,.layui-main{position:inherit}

</style>
<body>
<div class="layui-layout layui-layout-admin">

    <div class="layui-header header header-demo" winter>
        <div class="layui-main soul-head">
            <ul class="layui-nav">
                <a class="soul-logo" href="/" style="float: left;">
                    <img src="/img/logo/logo.png">
                </a>
                <li class="layui-nav-item layui-hide-xs" id="index" style="margin-right: 30px">
                    <a href="/">首页<!-- <span class="layui-badge-dot"></span> --></a>
                </li>
                <li class="layui-nav-item layui-hide-xs" id="question" style="margin-right: 30px">
                    <a href="/question">问答<!--  --></a>
                </li>

                <li class="layui-nav-item layui-hide-xs" lay-unselect style="margin-right: 30px">
                    <a href="#" onclick="feedBack()">反馈 <!--<span class="layui-badge-dot" style="margin-top: -5px;"></span>--></a>
                </li>
                <div class="soul-user-menu">
                    <#if !Session["currentUser"]?exists>
                        <div class="before-login">
                            <!-- 未登入的状态 -->
                            <li class="layui-nav-item">
                                <a class="iconfont icon-touxiang layui-hide-xs" href="#"></a>
                            </li>
                            <li class="layui-nav-item">
                                <a href="javascript:login()" id="login">登入</a>
                            </li>
                            <li class="layui-nav-item">
                                <a href="javascript:reg()" id="reg">注册</a>
                            </li>
                        </div>
                    </#if>
                    <!-- <li class="layui-nav-item layui-hide-xs">
                      <a href="/app/qq/" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" title="QQ登入" class="iconfont icon-qq"></a>
                    </li>
                    <li class="layui-nav-item layui-hide-xs">
                      <a href="/app/weibo/" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" title="微博登入" class="iconfont icon-weibo"></a>
                    </li>-->


                    <!-- 登入后的状态 -->
                    <#if Session["currentUser"]?exists>
                        <div class="after-login">
                            <#--<li class="layui-nav-item" style="margin-left: 40px;">
                                <a href="" title="消息通知" class="layui-icon layui-icon-notice"style="font-size: 18px;"><span class="layui-badge-dot"></span></a>
                            </li>-->
                            <li class="layui-nav-item" lay-unselect="">
                                <a href="javascript:;"><img src="${Session.currentUser.userHeadPortrait}" class="layui-nav-img">${Session.currentUser.userName}</a>
                                <dl class="layui-nav-child">
                                    <dd><a href="/userCenter/userPost" class="layui-icon">&#xe66f;&nbsp;&nbsp;个人中心</a></dd>
                                    <dd><a href="javascript:;" class="layui-icon" id="logOut" onclick="logOut()" >&#xe682;&nbsp;&nbsp;退出登录</a></dd>
                                </dl>
                            </li>
                        </div>
                    </#if>
                </div>

                <div class="select">
                    <form class="select-form layui-form layui-hide-xs">
                        <input  type="search" placeholder="Search" class="search-context">
                        <a href="#" class="layui-icon layui-icon-search search-btn" style="font-size: 18px;"></a>
                    </form>
                </div>

            </ul>
        </div>
    </div>
</div>
<div style="border-bottom: 1px solid rgba(19, 18, 18, 0.867); opacity: 0.2;"></div>
</body>
</html>

<script>
</script>