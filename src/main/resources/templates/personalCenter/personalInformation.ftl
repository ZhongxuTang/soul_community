<div class="personalOperation" style="border: 0px solid rgba(19, 18, 18, 0.867);width: 50%;">

    <form class="layui-form personalInformation-form" action="">
        <fieldset>
            <legend>个人资料</legend>
            <ul>
                <li>
                    <img src="${Session.currentUser.userHeadPortrait}" class="layui-nav-img user-headPoint">
                </li>
                <li style="float: right;margin-right: 40px;margin-top: 15px;">
                    <button type="button" class="layui-btn uploadHeadPoint" id="uploadHeadPoint">更换头像</button>
                </li>
                <li style="margin-top: 10px;display: inline-block;">
                    <span>ID:${Session.currentUser.userId}</span>
                </li>
                <li>
                    <span>等级&nbsp;&nbsp;V${Session.currentUser.userGrade!0}</span>
                </li>
                <li>
                    <span>经验值&nbsp;&nbsp;</span>
                    <#assign experience = (Session.currentUser.userExperience-(Session.currentUser.userGrade * 100))/100>
                    <div class="layui-progress personalInformation-form-experience-strip">
                        <div class="layui-progress-bar" lay-percent="${experience?string("0.00%")}" style="width: ${experience?string("0.00%")}"></div>
                    </div>
                </li>
                <div style="border-bottom: 3px solid #eaedf1;margin-top: 20px; opacity: 0.5;margin-bottom: 10px;"></div>
                <li>
                    <div class="layui-form-item">
                        <label class="layui-form-label">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
                        <div class="layui-input-block">
                            <input type="text" name="userName" placeholder="${Session.currentUser.userName}" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </li>
                <li>
                    <div class="layui-form-item">
                        <label class="layui-form-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
                        <div class="layui-input-block">
                            <input type="radio" name="userSex" value="男" title="男" checked="">
                            <input type="radio" name="userSex" value="女" title="女">
                        </div>
                    </div>
                </li>
                <li>
                    <div class="layui-form-item">
                        <label class="layui-form-label">修改密码</label>
                        <div class="layui-input-block">
                            <input type="password" name="userPassword" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </li>
                <li>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">个性签名</label>
                        <div class="layui-input-block">
                            <textarea name="personalizedSignature" placeholder="请输入内容" class="layui-textarea"></textarea>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="formDemo">立即修改</button>
                        </div>
                    </div>
                </li>
            </ul>
        </fieldset>
    </form>

</div>

<script type="text/javascript">

    layui.use(['form','element','layer','upload'], function(){
        var form = layui.form;
        var element = layui.element;
        var layer = layui.layer;
        form.render();
        var upload = layui.upload;

        //执行实例
        var uploadInst = upload.render({
            elem: '#uploadHeadPoint' //绑定元素
            ,url: '/userCenter/uploadHeadPoint' //上传接口
            ,accept: 'images'
            ,acceptMime: 'image/*'
            ,size: 500
            ,done: function(res){
                console.log(res);
                //上传完毕回调
                layer.msg("上传成功");
                window.location.reload();
            }
            ,error: function(){
                console.log(res);
                //请求异常回调
                layer.msg("上传失败");
            }
        });

    //监听提交
    form.on('submit(formDemo)', function(data){
        var url = "/userCenter/modifyPersonalInformation";
        var data = $("form").serializeArray();
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function (res) {
                console.log(data)
                if (res.success){
                    layer.msg('修改成功!',{
                            icon: 1,
                            time: 1000
                        },
                        function () {
                            parent.location.reload();
                        });
                } else{
                    layer.msg(res.errorInfo);
                }
            } ,
            error: function (data) {
                layer.msg("网络错误");
            }
        });
        return false;
    });
});

</script>
<script type="text/javascript" src="/js/verify.js"></script>