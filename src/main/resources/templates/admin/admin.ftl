<#include "model/adminHead.ftl">
<style>
    .layui-top-box {padding:20px 20px 20px 20px;}
    .panel {margin-bottom:17px;background-color:#fff;border:1px solid transparent;border-radius:3px;-webkit-box-shadow:0 1px 1px rgba(0,0,0,.05);box-shadow:0 1px 1px rgba(0,0,0,.05)}
    .panel-body {padding:15px}
    .panel-title {margin-top:0;margin-bottom:0;font-size:14px;color:inherit}
    .main_btn p {height:40px;}
    .panel-title{margin-bottom: 10px;}
    .panel-content{margin-bottom: 10px;}
    .bashboard-mess h5 h1{color: black;}
</style>
<div class="layui-container">

    <div class="layuimini-main layui-top-box">
        <div class="layui-row layui-col-space10">

            <div class="layui-col-md3">
                <div class="col-xs-6 col-md-3">
                    <div class="panel layui-bg-cyan">
                        <div class="panel-body">
                            <div class="panel-title">
                                <h5>用户统计</h5>
                            </div>
                            <div class="panel-content">
                                <h1 class="no-margins">${userCount}</h1>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-col-md3">
                <div class="col-xs-6 col-md-3">
                    <div class="panel layui-bg-blue">
                        <div class="panel-body">
                            <div class="panel-title">
                                <h5>文章统计</h5>
                            </div>
                            <div class="panel-content">
                                <h1 class="no-margins">${articleCount}</h1>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-col-md3">
                <div class="col-xs-6 col-md-3">
                    <div class="panel layui-bg-green">
                        <div class="panel-body">
                            <div class="panel-title">
                                <h5>问答统计</h5>
                            </div>
                            <div class="panel-content">
                                <h1 class="no-margins">${questionCount}</h1>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-col-md3">
                <div class="col-xs-6 col-md-3">
                    <div class="panel layui-bg-orange">
                        <div class="panel-body">
                            <div class="panel-title">
                                <h5>公告</h5>
                            </div>
                            <div class="panel-content">
                                <h1 class="no-margins">${noticeCount}</h1>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="layuimini-main layui-top-box" style="padding: 0 20px;">
        <div class="layui-row layui-col-space10">

            <div class="layui-col-md3">
                <div class="col-xs-6 col-md-3">
                    <div class="panel">
                        <div class="panel-body">
                            <div class="panel-title">
                                <h5>新文章</h5>
                            </div>
                            <div class="panel-content">
                                <a id="newArticle" href="#">最新文章</a>
                                <a id="newComment" href="#">最新评论</a>
                            </div>
                            <div class="switchArticleOrComment">
                                <ul>
                                    <#include "model/newArticle.ftl">

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-col-md5">
                <div class="col-xs-6 col-md-5">
                    <div class="panel">
                        <div class="panel-body bashboard-mess">
                            <div class="panel-title">
                                <h5>发布公告</h5>
                            </div>
                            <div style="border-bottom: 1px solid rgba(19, 18, 18, 0.867); opacity: 0.2;margin-top: 10px;"></div>
                            <div class="panel-content" style="margin-top: 10px;">
                                    <textarea name="desc" id="notice" placeholder="请输入内容" class="layui-textarea" style="height: 200px;"></textarea>
                                    <button id="postNotice" class="layui-btn" lay-submit lay-filter="formDemo" onclick="postNotice()" style="margin: 10px 0;width: 90px;">立即提交</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-col-md4">
                <div class="col-xs-6 col-md-4">
                    <div class="panel">
                        <div class="panel-body bashboard-mess">
                            <div class="panel-title">
                                <h5>用户日志</h5>
                            </div>
                            <div style="border-bottom: 1px solid rgba(19, 18, 18, 0.867); opacity: 0.2;margin-top: 10px;"></div>
                            <div class="panel-content">
                                <ul>
                                    <#list userLogs as userLog>
                                        <li style="margin-top: 30px;">
                                            用户${userLog.user.userName}
                                            <span>2019.5.20</span>
                                            在${userLog.source}${userLog.behavior}
                                            <div style="border-bottom: 1px solid rgba(19, 18, 18, 0.867); opacity: 0.2;margin-top: 10px;"></div>
                                        </li>
                                    </#list>

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script>

    layui.use(['element','layer'], function(){
        var element = layui.element;
        var layer = layui.layer
    });


    $("#newArticle").css("color","blue");
    $(".dash-board").addClass("bg-color");

    $("#newComment").click(function () {
        $("#newArticle").css("color","#333");
        $("#newComment").css("color","blue");

        var url= "/admin/switchComment";
        console.log("test");
        $.ajax({
            type:"POST",
            url:url,
            async:false,
            success:function (res) {
                $(".switchArticleOrComment").html(res);
            }
        });
    });

    $("#newArticle").click(function () {
        $("#newComment").css("color","#333");
        $("#newArticle").css("color","blue");

        var url= "/admin/switchArticle";
        console.log("test");
        $.ajax({
            type:"POST",
            url:url,
            async:false,
            success:function (res) {
                $(".switchArticleOrComment").html(res);
            }
        });
    });

    function postNotice() {
       var $notice = $('#notice');
        var notice={
            "notice":$notice.val()
        }
        $.ajax({
            type:"POST",
            url:"/admin/postNotice",
            data:notice,
            success:function (res) {
                if (res.success){
                    layer.msg("公告发布成功");
                    window.location.reload();
                } else {
                    layer.msg(res.errorInfo);
                }
            }

        });

    };

</script>