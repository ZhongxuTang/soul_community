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
                            <li style="width: 50px;">ID</li>
                            <li>用户名</li>
                            <li>状态</li>
                            <li>发布文章数量</li>
                            <li>发布问题量</li>
                            <li style="width: 150px;">上次登录时间</li>
                            <li style="width: 150px;">操作</li>
                        </ul>
                    </div>
                </div>
                <#list users as user>
                    <div style="padding: 0 10px;width: 95%;">
                        <ul>
                            <li style="width: 50px;">${user.userId!""}</li>
                            <li><span class="layui-badge" style="background-color: #3eede7;">${user.userName!""}</span></li>
                            <#if user.seal == false>
                                <li class="status"><span class="layui-badge-dot" style="background-color: #00bc12;"></span>正常</li>
                            <#else>
                                <li><span class="layui-badge-dot" style="background-color: red;"></span>封禁中</li>
                            </#if>
                            <li>${user.articleQuantity!""}</li>
                            <li>${user.questionQuantity!""}</li>
                            <li style="width: 150px;">${user.userLatelyLoginTime}</li>
                            <li style="width: 150px;">
                                <a href="#" style="width: 50px;display: inline-block;text-align: left;" onclick="sealUser('${user.userId!""}')">封禁</a>
                                <a href="#" style="width: 50px;" onclick="relieveSealUser('${user.userId!""}')">解封</a>
                            </li>
                        </ul>
                        <div style="border-bottom: 1px solid rgba(19, 18, 18, 0.867); opacity: 0.2;margin-bottom: 10px;"></div>
                    </div>
                </#list>

            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    layui.use(['element','layer'], function(){
        var element = layui.element;
        var layer = layui.layer
    });

    function sealUser(userId) {
        layer.confirm('您确定要封禁该用户吗？', {
            btn: ['确定','取消'] //按钮
        },function(){
            $.ajax({
                type:"POST",
                url:"/admin/manage/sealUser",
                data:{
                    "userId":userId,
                },
                success:function (res) {
                    console.log(res);
                        layer.msg('封禁成功!',{
                            icon: 1,
                            time: 1000
                        });
                    $(".layui-container").html(res);
                }
            })
        });
    };

    function relieveSealUser(userId) {
        layer.confirm('您确定要封禁该用户吗？', {
            btn: ['确定','取消'] //按钮
        },function(){
            $.ajax({
                type:"POST",
                url:"/admin/manage/relieveSealUser",
                data:{
                    "userId":userId,
                },
                success:function (res) {
                    console.log(res);
                    layer.msg('解封成功!',{
                        icon: 1,
                        time: 1000
                    });
                    $(".layui-container").html(res);
                }
            })
        });
    }


</script>