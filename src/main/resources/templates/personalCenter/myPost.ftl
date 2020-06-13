<#include "../model/personalCenter.ftl">
<div class="layui-container" style="margin: 0;padding: 0;">
    <#include "../model/personalCenterMenu.ftl">
    <div class="layui-col-md7 personalOperation">
        <#if Session.currentUser.articleQuantity == 0>
            <p style="text-align: center;margin-top: 70px;">暂未发布帖子</p>
            <#else>

                <#list articleList as article>
                    <div class="personalOperation-article-list">
                        <img src="${Session.currentUser.userHeadPortrait}">
                        <div class="personalOperation-article">
                            <ul>
                                <span style="margin-left: 10px;">
                                    <i class="layui-icon">&#xe617;</i>
                                </span>
                                <li class="personalOperation-article-userName">
                                    ${Session.currentUser.userName}
                                </li>
                                <li>
                                    ${article.articleCreateTime?string('yyyy-MM-dd')}
                                </li>
                            </ul>
                            <a href="/page/article/${article.articleId}.ftl">${article.articleName}</a>
                            <#if article.state == 1>
                                <span class="layui-badge layui-bg-orange">审核中</span>
                            <#elseif article.state == 2>
                                <span class="layui-badge layui-bg-blue">已发布</span>
                            <#elseif article.state == 3>
                                <span class="layui-badge layui-bg-gray">草稿</span>
                            </#if>
                        </div>
                        <span class="user-version">等级 ${Session.currentUser.userGrade}</span>
                        <aside style="float: right;">
                            <ul class="personalOperation-article-but">
                                <li>
                                    <a href="/page/editArticle/${article.articleId!""}" style="width: 50px;">编辑</a>
                                    <#--                        <button class="layui-btn layui-btn-radius layui-btn-primary layui-btn-xs" onclick="javascript:window.location.href='/page/editArticle/${article.articleId!""}'">编辑</button>-->
                                </li><#---->
                                <li>
                                    <#--                        <button class="layui-btn layui-btn-radius layui-btn-primary layui-btn-xs" onclick="deleArticle()">删除</button>-->
                                    <a href="#" onclick="deleteArticle('${article.articleId!""}')" style="width: 50px;">删除</a>
                                </li>
                            </ul>
                        </aside>
                        <div class="layui-progress experience-strip">
                            <#assign experience = (Session.currentUser.userExperience-(Session.currentUser.userGrade * 100))/100>
                            <div class="layui-progress-bar" lay-percent="${experience?string("0.00%")}"></div>
                        </div>
                        <div style="border-bottom: 1px solid rgba(19, 18, 18, 0.867);margin-top: 25px; opacity: 0.5;"></div>
                    </div>
                </#list>

        </#if>


    </div>
</div>

<script type="text/javascript">
    //注意进度条依赖 element 模块，否则无法进行正常渲染和功能性操作
    layui.use(['element','layer'], function(){
        var element = layui.element;
        var layer = layui.layer
    });

    function deleteArticle(articleId) {
        layer.confirm('您确定要删除该文章吗？', {
            btn: ['确定','取消'] //按钮
        },function(){
            $.ajax({
                type:"POST",
                url:"/userCenter/deleteUserArticle",
                data:{
                    "articleId":articleId,
                },
                success:function (res) {
                    console.log(res);
                    if (res.success){
                        layer.msg('删除成功!',{
                            icon: 1,
                            time: 1000
                        });
                        parent.location.reload();
                    } else {
                        layer.msg('删除失败!',{
                            icon: 2,
                            time: 1000
                        });
                        parent.location.reload();
                    }

                }
            })
        });
    };
</script>
<script type="text/javascript" src="/js/verify.js"></script>
<script type="text/javascript" src="/js/ajaxRefresh.js"></script>