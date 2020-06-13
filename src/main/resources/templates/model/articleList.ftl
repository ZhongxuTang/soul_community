<div class="layui-col-md8 index-body-articleList">

    <#if article?? && (article?size ==0)>

        <p style="text-align: center;margin-top: 80px;">暂未发布帖子</p>

    <#else>

        <#list article as article>
            <div class="layui-col-md12 index-body-article">
                <div class="layui-col-md12 article-head">
                    <span>${article.articleCreateTime?string('yyyy-MM-dd')!""}</span>
                    <a href="#">${article.articleType.articleTypeName!""}</a>

                </div>
                <div class="layui-col-md12 article-body">

                    <#if article.articleName?length gt 15>
                        <a href="/page/article/${article.articleId}.ftl" class="article-body-title">${article.articleName[0..15]!""}</a>
                    <#elseif article.articleName?length lte 15>
                        <a href="/page/article/${article.articleId}.ftl" class="article-body-title">${article.articleName!""}</a>
                    </#if>

                    <a class="article-head-userHeadPortrait" href="/page/user/${article.user.userId}" style="float: right;">
                        <img src="${article.user.userHeadPortrait!""}">
                    </a>
                    <div class="article-introduce">
                        <p>${article.summary!""}...</p>
                    </div>
                </div>
            </div>
        </#list>
    </#if>
        <#--<#include "../model/paging.ftl">-->
</div>

