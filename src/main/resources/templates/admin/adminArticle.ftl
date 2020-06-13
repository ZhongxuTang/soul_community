<style>
    .panel ul{display: flex;text-align: center;padding: 0 15px;}
    .panel ul li{margin: 20px 20px;text-align: left;width: 70px;text-align: center;}
    .panel{padding: 0 10px;width: 95%;}
</style>


<div class="layui-row layui-col-space10">

    <div class="layui-col-md12" style="margin-top: 20px;">
        <div class="col-xs-6 col-md-12">
            <div class="panel" style="background-color: ">

                <div class="panel" style="background-color: #fffbf0;margin-top: 20px;">
                    <div>
                        <ul>
                            <li style="width: 150px;">标题</li>
                            <li>状态</li>
                            <li>标签</li>
                            <li>作者</li>
                            <li>评论</li>
                            <li style="width: 150px;">发布时间</li>
                            <li style="width: 200px;">操作</li>
                        </ul>
                    </div>
                </div>
                <#list article as article>
                <div style="padding: 0 10px;width: 95%;">
                    <ul>
                        <li style="width: 150px;">
                            <a href="/page/article/${article.articleId}.ftl">${article.articleName!""}</a>
                        </li>
                        <#if article.state == 2>
                            <li><span class="layui-badge-dot" style="background-color: #00bc12;"></span>已发布</li>
                        <#else>
                            <li><span class="layui-badge-dot" style="background-color: red;"></span>未审核</li>
                        </#if>
                        <li><span class="layui-badge" style="background-color: #3eede7;">${article.articleType.articleTypeName!""}</span></li>
                        <li>${article.user.userName}</li>
                        <li>${article.userCommentQuantity!""}</li>
                        <li style="width: 150px;">${article.articleCreateTime}</li>
                        <li style="width: 200px;">
                            <#if article.state == 2>
                                <a href="#" style="padding-right: 15px;">通过</a>
                            <#else>
                                <a href="#" style="padding-right: 15px;" onclick="agreeArticle('${article.articleId}')">通过</a>
                            </#if>
                            <a href="#" style="padding-right: 15px;" onclick="deleteArticle('${article.articleId}')">删除</a>
                            <a href="#" onclick="managerComment('${article.articleId}')">评论管理</a>
                        </li>
                    </ul>
                    <div style="border-bottom: 1px solid rgba(19, 18, 18, 0.867); opacity: 0.2;margin-bottom: 10px;"></div>
                </div>
                </#list>

            </div>
        </div>
    </div>
</div>

<script>

    layui.use(['element','layer'], function(){
        var element = layui.element;
        var layer = layui.layer
    });

    function agreeArticle(articleId) {
        layer.confirm('您确定要通过该文章吗？', {
            btn: ['确定','取消'] //按钮
        },function(){
            $.ajax({
                type:"POST",
                url:"/admin/manage/agreeArticle",
                data:{
                    "articleId":articleId,
                },
                success:function (res) {
                    console.log(res);
                    layer.msg('审核成功!',{
                        icon: 1,
                        time: 1000
                    });
                    $(".layui-container").html(res);
                }
            })
        });
    }



    function deleteArticle(articleId) {
        layer.confirm('您确定要删除该文章吗？', {
            btn: ['确定','取消'] //按钮
        },function(){
            $.ajax({
                type:"POST",
                url:"/admin/manage/deleteArticle",
                data:{
                    "articleId":articleId,
                },
                success:function (res) {
                    console.log(res);
                    layer.msg('删除成功!',{
                        icon: 1,
                        time: 1000
                    });
                    $(".layui-container").html(res);
                }
            })
        });
    };

    function managerComment(articleId) {
        $.ajax({
            type:"POST",
            url:"/admin/manage/managerComment",
            data:{
                "Id":articleId,
                "mess":"article",
            },
            success:function (res) {
                $(".layui-container").html(res);
            }
        });
    };

</script>