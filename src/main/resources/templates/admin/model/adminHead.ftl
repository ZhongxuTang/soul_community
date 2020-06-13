<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <link rel="stylesheet" href="/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="/css/pageStyle.css"/>
    <script type="text/javascript" src="/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>

    <script></script>
    <style>
        .layui-nav{background-color: #161823;}
        .layui-nav li{
            margin-right: 30px;
            width: 85px;
        }

        .bg-color{
            background-color: #3273dc;
        }
    </style>
</head>
<body>

<div>
    <ul class="layui-nav" style="width: 100%;text-align: center;display: table;">

        <li class="layui-nav-item admin-title" style="width: 170px;">
            <a>Soul Dashboard</a>
        </li>
        <li class="layui-nav-item dash-board">
            <a href="">仪表盘</a>
        </li>
        <li class="layui-nav-item admin-article">
            <a href="#" class="adminArticleMenu">文章</a>
        </li>
        <li class="layui-nav-item admin-question">
            <a href="#" class="adminQuestionMenu">问答</a>
        </li>
        <li class="layui-nav-item admin-user">
            <a href="#" class="adminUserMenu">用户</a>
        </li>
        <li class="layui-nav-item">
            <a href=""><img src="//t.cn/RCzsdCq" class="layui-nav-img"></a>
            <dl class="layui-nav-child">
                <dd><a href="javascript:;" onclick="logOut()">退了</a></dd>
            </dl>
        </li>
    </ul>
</div>


</body>
</html>
<script type="text/javascript" src="/js/ajaxRefresh.js"></script>

<script>

    function logOut(){
        layer.confirm('您要退出吗？', {
            btn: ['退出','取消'] //按钮
        },function(){
            window.location.href="/logout";
        });
    }
</script>