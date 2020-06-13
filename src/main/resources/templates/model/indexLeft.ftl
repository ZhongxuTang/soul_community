<div class="layui-col-md3 index-body-website-mess" style="border: 1px solid white;">

    <div class="index-body-website-mess_1">
        <button class="layui-btn" onclick="new_text()" id="postMess">我要发帖</button>
        <div class="layui-col-md12 index-body-website-mess_1_info" id="index-body-website-mess_1_info">
            <div class="layui-col-md4 index-body-website-mess_1_info_1" id="quertionInfo">
                <p>文章</p>
                <#if Session["currentUser"]?exists>
                    <p>${Session.currentUser.articleQuantity!""}</p>
                <#else>
                    <p>0</p>
                </#if>

            </div>
            <div class="layui-col-md4 index-body-website-mess_1_info_2" id="quertionInfo">
                <p>问答</p>
                <#if Session["currentUser"]?exists>
                    <p>${Session.currentUser.questionQuantity!""}</p>
                <#else>
                    <p>0</p>
                </#if>
            </div>
            <div class="layui-col-md4 index-body-website-mess_1_info_3" id="quertionInfo">
                <p>关注</p>
                <#if Session["currentUser"]?exists>
                    <p>${Session.currentUser.followQuantity!0}</p>
                <#else>
                    <p>0</p>
                </#if>
            </div>
        </div>
    </div>

    <div class="index-body-class layui-col-md12" id="index-body-class" style="border: 1px solid white;">
        <div class="index-body-class-title">
            分类
        </div>
        <#list articleType as articleType>
            <a href="/articleClass/oneClassArticle/${articleType.articleTypeId}" class="index-body-class-body">
                <span>${articleType.articleTypeName}</span>
                <span style="float: right">${articleType.count!0}</span>
            </a>
        </#list>
    </div>


</div>