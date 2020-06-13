<#include "model/head.ftl">
<div class="layui-container layui-anim layui-anim-scale index-body">

    <div class="layui-row" style="margin: 0 auto;">
        <#include "model/indexLeft.ftl">

        <div class="layui-col-md8 index-body-notification-area" style="height: 0;">

            <div class="layui-col-md12" style="float: right;">

                <div class="layui-col-md8 oneClassArticleTitle">
                    <div class="oneClassArticleTitle_1">
                        <a href="/" class="allArticle">分类></a>
                        <span>&nbsp;/&nbsp;&nbsp;${thisType!""}</span>
                    </div>
                </div>

                <div class="layui-col-md3 index-body-recommend" style="border: 1px solid white;margin-top: 0;">
                    <div class="index-body-recommend-body">
                        今日推荐
                    </div>
                </div>

                <#include "model/articleList.ftl">
                <#assign thisType="article" />
                <#include "model/paging.ftl">
            </div>

        </div>
    </div>
</div>

<script type="text/javascript" src="/js/verify.js"></script>