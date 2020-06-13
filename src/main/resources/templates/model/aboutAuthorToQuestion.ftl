<div class="about-author">
    <div class="about-author-title">
        <p>关于作者</p>
    </div>
    <div style="border-bottom: 1px solid rgba(19, 18, 18, 0.867); opacity: 0.2;"></div>
    <div class="about-author-body" style="margin-bottom: 0px;">
        <a href="/page/user/${question.user.userId}">
            <img src="${question.user.userHeadPortrait!""}">
        </a>
        <div class="about-myself-personalDate">
            <a href="/page/user/${question.user.userId}" class="userName">${question.user.userName}</a>
            <#if (question.user.userSex) == "男">
                <span class="layui-icon userSexMan">&#xe662;</span>
            <#elseif question.user.userSex == "女">
                <span class="layui-icon userSexWoman">&#xe661;</span>
            <#elseif question.user.userSex ?exists>
                <span></span>
            </#if>
            <br>
            <span class="layui-badge layui-bg-orange">V ${question.user.userGrade}</span>
        </div>
    </div>
    <div class="layui-col-md12 about-myself-question">
        <div class="layui-col-md12">
            <span class="layui-badge layui-bg-blue">问</span>
            <span>提了${question.user.questionQuantity!0}个问题，${question.user.answerQuantityToUser!0}人进行了回答</span>
        </div>
        <div class="layui-col-md12">
            <span class="layui-badge layui-bg-blue">答</span>
            <span>回答了${question.user.answerQuantity!0}个问题</span>
        </div>
    </div>
</div>