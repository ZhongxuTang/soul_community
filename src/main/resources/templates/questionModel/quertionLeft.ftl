<div class="layui-col-md3 index-body-website-mess" style="border: 1px solid white;">

    <div class="index-body-website-mess_1">
        <button class="layui-btn" onclick="quertionTinymce()" id="postMess">我要发布问题</button>
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
                <p>点赞</p>
                <p>0</p>
            </div>
            <div class="layui-col-md4 index-body-website-mess_1_info_3" id="quertionInfo">
                <p>收藏</p>
                <p>0</p>
            </div>
        </div>
    </div>

    <#if Session["currentUser"]?exists>
        <div class="index-body-class layui-col-md12" id="index-body-class" style="border: 1px solid white;">
            <#include "../model/aboutMyself.ftl">
        </div>
    </#if>

    <div class="index-body-class layui-col-md12" id="index-body-class" style="border: 1px solid white;">
        <div class="index-body-class-title">
            标签
        </div>
        <div style="padding: 15px;">
            <ul class="personalOperation-article-but">
                <#list articleType as articleType>
                    <li style="margin: 2px 15px 15px 0;">
                        <a style="padding: 4px 10px;">${articleType.articleTypeName}</a>
                    </li>
                </#list>
            </ul>
        </div>
    </div>


</div>