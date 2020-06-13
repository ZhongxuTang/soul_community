<div class="layui-container layui-anim layui-anim-scale index-body">

    <div class="layui-row" style="margin: 0 auto;">
        <#include "../questionModel/quertionLeft.ftl">

        <div class="layui-col-md8 index-body-notification-area" style="height: 0;">



            <div class="layui-col-md12" style="float: right;">

                <div class="layui-col-md12 oneClassArticleTitle">
                    <div class="oneClassArticleTitle_1">
                        <a href="#" class="allArticle">${thisType}</a>
                    </div>
                </div>


                <#include "../model/questionList.ftl">
                <#assign thisType="question" />

                <#include "../model/paging.ftl">
            </div>

        </div>
    </div>
</div>