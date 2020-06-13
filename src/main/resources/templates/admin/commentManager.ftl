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
                            <li style="width: 150px;">所属文章</li>
                            <li>发布者</li>
                            <li style="width: 200px;">评论</li>
                            <li style="width: 150px;">发布时间</li>
                            <li style="width: 150px;">操作</li>
                        </ul>
                    </div>
                </div>

                <#if mess == "article">
                    <#list comment as comment>

                        <div style="padding: 0 10px;width: 95%;">
                            <ul>
                                <li style="width: 150px;">${comment.article.articleName}</li>
                                <li><span class="layui-badge" style="background-color: #3eede7;">${comment.user.userName}</span></li>
                                <#if comment.content?length gt 10>
                                    <li style="width: 200px;">${comment.content[0..10]!""}</li>
                                <#elseif comment.content?length lte 10>
                                    <li style="width: 200px;">${comment.content!""}</li>
                                </#if>

                                <li style="width: 150px;">${comment.commentDate}</li>
                                <li style="width: 150px;">
                                    <a href="#" style="width: 60px;display: inline-block;text-align: left;" onclick="deleteComment('${comment.commentId!""}','${comment.article.articleId}')">删除</a>
                                    <#--<a href="#" style="width: 50px;" onclick="">封禁</a>-->
                                </li>
                            </ul>
                            <div style="border-bottom: 1px solid rgba(19, 18, 18, 0.867); opacity: 0.2;margin-bottom: 10px;"></div>
                        </div>
                    </#list>
                <#elseif mess == "question">
                    <#list answer as answer>

                        <div style="padding: 0 10px;width: 95%;">
                            <ul>
                                <li style="width: 150px;">${answer.question.questionTitle}</li>
                                <li><span class="layui-badge" style="background-color: #3eede7;">${answer.user.userName}</span></li>
                                <#if answer.content?length gt 10>
                                    <li style="width: 200px;">${answer.content[0..10]!""}</li>
                                <#elseif answer.content?length lte 10>
                                    <li style="width: 200px;">${answer.content!""}</li>
                                </#if>

                                <li style="width: 150px;">${answer.answerDate}</li>
                                <li style="width: 150px;">
                                    <a href="#" style="width: 60px;display: inline-block;text-align: left;" onclick="deleteAnswer('${answer.answerId}','${answer.question.questionId}')">删除</a>
                                    <#--<a href="#" style="width: 50px;" onclick="">封禁</a>-->
                                </li>
                            </ul>
                            <div style="border-bottom: 1px solid rgba(19, 18, 18, 0.867); opacity: 0.2;margin-bottom: 10px;"></div>
                        </div>
                    </#list>
                <#else>

                </#if>



            </div>
        </div>
    </div>
</div>

<script>

    function deleteComment(commentId,articleId) {
        layer.confirm('您确定要删除该评论吗？', {
            btn: ['确定','取消'] //按钮
        },function(){
            $.ajax({
                type:"POST",
                url:"/admin/manage/deleteComment",
                data:{
                    "commentId":commentId,
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

    function deleteAnswer(answerId,questionId) {
        layer.confirm('您确定要删除该回复吗？', {
            btn: ['确定','取消'] //按钮
        },function(){
            $.ajax({
                type:"POST",
                url:"/admin/manage/deleteAnswer",
                data:{
                    "answerId":answerId,
                    "questionId":questionId,
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
</script>