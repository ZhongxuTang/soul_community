<div class="about-author">
    <div class="about-author-title">
        <p>关于作者</p>
    </div>
    <div style="border-bottom: 1px solid rgba(19, 18, 18, 0.867); opacity: 0.2;"></div>
    <div class="about-author-body">
        <a href="/page/user/${article.user.userId}">
            <img src="${article.user.userHeadPortrait!""}">
        </a>
        <div class="about-author-personalDate">
            <a href="/page/user/${article.user.userId}" class="userName">${article.user.userName}</a>
            <#if (article.user.userSex) == "男">
                <span class="layui-icon userSexMan">&#xe662;</span>
            <#elseif article.user.userSex == "女">
                <span class="layui-icon userSexWoman">&#xe661;</span>
            <#elseif article.user.userSex ?exists>
                <span></span>
            </#if>
            <br>
            <span class="layui-badge layui-bg-orange">V ${article.user.userGrade}</span>
        </div>
    </div>
    <div class="layui-col-md12 about-author-article">
        <div class="layui-col-md5">
            <p>文章</p>
            <p>${article.user.articleQuantity}</p>
        </div>
        <div class="layui-col-md5">
            <p>问答</p>
            <p>${article.user.questionQuantity}</p>
        </div>
    </div>
</div>