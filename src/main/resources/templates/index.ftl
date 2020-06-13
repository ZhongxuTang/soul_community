<style>
    .notice-content{
        width: 20%;
        float: right;
        margin: 50px 30px;
        text-align: center;
    }


</style>


<#include "model/head.ftl">

<div class="layui-container layui-anim layui-anim-scale index-body">

    <div class="layui-row" style="margin: 0 auto;">

        <#include "model/indexLeft.ftl">

        <div class="layui-col-md8 index-body-notification-area">

            <div class="layui-col-md12 index-body-notification-area-body">

                <div class="layui-col-md7">

                    <img src="/img/headPortrait/paly_picture1.jpeg">
                </div>

                <div class="layui-col-md4 notice-content">
                    <div style="margin: 0 auto;">
                        <p style="margin: 0;">${notice.noticeContext}</p>
                    </div>
                    <div style="margin-top: 100px;float:right;">
                        <p style="margin: 0;font-size: 15px;">${notice.admin.adminNickname}</p><br>
                        <p style="margin: 0;font-size: 10px;">${notice.noticeDate}</p>
                    </div>
                </div>

            </div>

            <div class="layui-col-md8 index-body-class-a">
                <#list 0..5 as i>
                    <a href="/articleClass/oneClassArticle/${articleType[i].articleTypeId}" class="allArticle">${articleType[i].articleTypeName}</a>
                </#list>
            </div>

            <div class="layui-col-md12" style="margin-right: 3%;">

                <#include "model/articleList.ftl">

                <div class="layui-col-md3 index-body-recommend">
                    <div class="index-body-recommend-body">
                        <#include "model/recommendedArticle.ftl">
                    </div>
                </div>
                <#assign thisType="article" />
                <#include "model/paging.ftl">
            </div>

        </div>

    </div>


</div>

<script type="text/javascript">
    $("#index").addClass("layui-this");
    $(".search-btn").click(function () {

        var searchContext = $(".search-context").val();
        url="/search/article";
        searchData={
            "searchContext":searchContext,
        };
        $.ajax({
            type:"POST",
            url:url,
            data:searchData,
            async:false,
            success:function (res) {
                console.log(res);
                $(".index-body").html(res);
            }
        });

    });
</script>
<script type="text/javascript" src="/js/verify.js"></script>