<style>
    .menuColor{
        color: #3273dc;
    }
</style>

<#include "model/personalCenter.ftl">

<div class="layui-col-md12 personalHomePage">
    <div class="layui-col-md4 personalHomePageMenu">
        <ul>
            <li><a href="" class="article-user-menu">帖子</a></li>
            <li><a href="#" class="question-menu">问答</a></li>
            <li><a href="#" class="follow-user-menu">关注</a></li>
        </ul>




    </div>

            <div class="layui-col-md10 personalHomePageContent">

                <#include "model/articleList.ftl">
                <#--<#include "model/paging.ftl">-->
            </div>


</div>


<script type="text/javascript" src="/js/verify.js"></script>

<script>
    $(document).ready(function() {
        var follow=$("<button  class='layui-btn follow' id='follow'></button >").text("${statusMes?js_string}");
        $(".article-user-menu").addClass("menuColor");
        $(".userSex").after(follow);
    });

    layui.use('layer',function () {
        $(".follow").click(function () {
                $.ajax({
                    type:"GET",
                    url:"/follow/followUser",
                    data:{
                        "userId":${user.userId},
                    },
                    success:function (res) {
                        if (res.success){
                            layer.msg(res.yes);
                            $(".follow").text(res.thisStatus);
                        } else {
                            layer.msg(res.errorInfo);
                        }
                    }
                });
        });
    });

    $(".follow-user-menu").click(function () {
        $(".question-menu").removeClass("menuColor");
        $(".article-user-menu").removeClass("menuColor");
        $(".follow-user-menu").addClass("menuColor");
        var url= "/personalInformation/followUser";
        $.ajax({
            type:"POST",
            url:url,
            async:false,
            data:{
                "userId":"${user.userId}"
            },
            success:function (res) {

                $(".personalHomePageContent").html(res);
            }
        });
    });

    $(".article-user-menu").click(function () {
        $(".question-menu").removeClass("menuColor");
        $(".follow-user-menu").removeClass("menuColor");
        $(".article-user-menu").addClass("menuColor");
        var url= "/personalInformation/myArticle";
        $.ajax({
            type:"POST",
            url:url,
            async:false,
            data:{
                "userId":"${user.userId}"
            },
            success:function (res) {
                $(".personalHomePageContent").html(res);
            }
        });
    });

    $(".question-menu").click(function () {
        $(".article-user-menu").removeClass("menuColor");
        $(".follow-user-menu").removeClass("menuColor");
        $(".question-menu").addClass("menuColor");
        var url= "/personalInformation/myQuestion";
        $.ajax({
            type:"POST",
            url:url,
            async:false,
            data:{
                "userId":"${user.userId}"
            },
            success:function (res) {
                $(".personalHomePageContent").html(res);
            }
        });
    });

</script>
<script type="text/javascript" src="/js/ajaxRefresh.js"></script>