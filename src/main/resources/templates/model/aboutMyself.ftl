<div class="about-author">
    <div class="about-author-title">
        <p>我的问答</p>
    </div>
    <div style="border-bottom: 1px solid rgba(19, 18, 18, 0.867); opacity: 0.2;"></div>
    <div class="about-author-body" style="margin-bottom: 0px;">
        <a href="/page/user/${Session.currentUser.userId}">
            <img src="${Session.currentUser.userHeadPortrait!""}">
        </a>
        <div class="about-myself-personalDate">
            <a href="/page/user/${Session.currentUser.userId}" class="userName">${Session.currentUser.userName}</a>
            <#if (Session.currentUser.userSex) == "男">
                <span class="layui-icon userSexMan">&#xe662;</span>
            <#elseif Session.currentUser.userSex == "女">
                <span class="layui-icon userSexWoman">&#xe661;</span>
            <#elseif Session.currentUser.userSex ?exists>
                <span></span>
            </#if>
            <br>
            <span class="layui-badge layui-bg-orange">V ${Session.currentUser.userGrade}</span>
        </div>
    </div>
    <div class="layui-col-md12 about-myself-question">
        <div class="layui-col-md12">
            <span class="layui-badge layui-bg-blue">问</span>
            <span>提了${Session.currentUser.questionQuantity!0}个问题，${Session.currentUser.answerQuantityToUser!0}人进行了回答</span>
        </div>
        <div class="layui-col-md12">
            <span class="layui-badge layui-bg-blue">答</span>
            <span>回答了${Session.currentUser.answerQuantity!0}个问题</span>
        </div>
    </div>
</div>