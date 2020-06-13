<#if follows?? && (follows?size == 0)>

    <p style="text-align: center;margin-top: 80px;">该用户暂未关注任何用户</p>

<#else >
    <#list follows as follow>


        <div class="layui-col-md8">
            <div class="layui-col-md12 index-body-article">
                <div class="layui-col-md12">
                    <div style="display: inline;">
                        <a class="article-head-userHeadPortrait" href="#" style="width: 0;">
                            <img src="/img/headPortrait/user01.png" style="margin-top: 5px;margin-left: 10px;float: none;">
                        </a>
                        <span style="font-size: 18px;margin: 0 20px;">${follow.followedUserId.userName}</span>
                    </div>

                    <div style="display: inline;">
                        <span style="font-size: 16px;margin: 0 30px;">文章&nbsp;&nbsp;${follow.followedUserId.articleQuantity!0}</span>
                        <span style="font-size: 16px;margin: 0 30px;">问答&nbsp;&nbsp;${follow.followedUserId.questionQuantity!0}</span>
                        <span style="font-size: 16px;margin: 0 30px;">关注&nbsp;&nbsp;${follow.followedUserId.followQuantity!0}</span>
                    </div>
                </div>
            </div>
        </div>
    </#list>
</#if>

