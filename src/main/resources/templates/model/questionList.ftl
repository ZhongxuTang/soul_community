<div class="layui-col-md12 index-body-articleList">
    <#if questions?? && (questions?size ==0)>

        <p style="text-align: center;margin-top: 80px;">该用户暂未发布帖子</p>

    <#else>
        <#list questions as question>
            <div class="layui-col-md12 index-body-article">
                <div class="layui-col-md12 article-head">
                    <span>${question.questionPostDate?string('yyyy-MM-dd')!""}</span>
                    <a href="#">${question.articleType.articleTypeName!""}</a>

                </div>
                <div class="layui-col-md12 article-body">
                    <div class="layui-col-md12">
                        <#if question.questionTitle?length gt 15>
                            <a href="/page/question/${question.questionId}" class="article-body-title" style="width: 60%;">${question.questionTitle[0..15]!""}</a>
                        <#elseif question.questionTitle?length lte 15>
                            <a href="/page/question/${question.questionId}" class="article-body-title" style="width: 60%;">${question.questionTitle!""}</a>
                        </#if>

                        <span class="layui-badge layui-bg-gray" style="padding: 15px;float: right;margin-top: -5px;">
                        ${question.questionAnswerQuantity}
                        回复量
                    </span>

                    </div>

                    <div class="article-introduce" style="width: 80%;">
                        <#if question.questionContent?length gt 35>
                            <p>${question.questionContent[0..35]!""}...</p>
                        <#elseif question.questionContent?length lte 15>
                            <p>${question.questionContent!""}</p>
                        </#if>

                    </div>
                </div>
            </div>
        </#list>
    </#if>

    <#--<#include "../model/paging.ftl">-->
</div>