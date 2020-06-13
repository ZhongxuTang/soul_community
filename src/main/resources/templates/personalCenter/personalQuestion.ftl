    <#if Session.currentUser.questionQuantity == 0>
        <p style="text-align: center;margin-top: 70px;">暂未发布问题</p>
    <#else>

        <#list questions as question>
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
                            ${question.questionPostDate?string('yyyy-MM-dd')}
                        </li>
                    </ul>
                    <#if question.questionTitle?length gt 10>
                        <a href="/page/question/${question.questionId}">${question.questionTitle[0..10]}......</a>
                    <#else>
                        <a href="/page/question/${question.questionId}">${question.questionTitle}</a>
                    </#if>
                </div>
                <span class="user-version">等级 ${Session.currentUser.userGrade}</span>
                <aside style="float: right;">
                    <ul class="personalOperation-article-but">

                        <li>
                            <#--                        <button class="layui-btn layui-btn-radius layui-btn-primary layui-btn-xs" onclick="deleArticle()">删除</button>-->
                            <a href="#" onclick="deleteQuestion('${question.questionId!""}')" style="width: 50px;">删除</a>
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
<script>

    function deleteQuestion(questionId) {
        layer.confirm('您确定要删除该问题吗？', {
            btn: ['确定','取消'] //按钮
        },function(){
            $.ajax({
                type:"POST",
                url:"/userCenter/deleteUserQuestion",
                data:{
                    "questionId":questionId,
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
    }

</script>




