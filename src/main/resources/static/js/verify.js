function draw(show_num) {
    var canvas_width = $('#canvas').width();
    var canvas_height = $('#canvas').height();
    var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
    var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
    canvas.width = canvas_width;
    canvas.height = canvas_height;
    var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
    var aCode = sCode.split(",");
    var aLength = aCode.length;//获取到数组的长度

    for (var i = 0; i <= 3; i++) {
        var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
        var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
        var txt = aCode[j];//得到随机的一个内容
        show_num[i] = txt.toLowerCase();
        var x = 10 + i * 20;//文字在canvas上的x坐标
        var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
        context.font = "bold 23px 微软雅黑";

        context.translate(x, y);
        context.rotate(deg);

        context.fillStyle = randomColor();
        context.fillText(txt, 0, 0);

        context.rotate(-deg);
        context.translate(-x, -y);
    }
    for (var i = 0; i <= 5; i++) { //验证码上显示线条
        context.strokeStyle = randomColor();
        context.beginPath();
        context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.stroke();
    }
    for (var i = 0; i <= 30; i++) { //验证码上显示小点
        context.strokeStyle = randomColor();
        context.beginPath();
        var x = Math.random() * canvas_width;
        var y = Math.random() * canvas_height;
        context.moveTo(x, y);
        context.lineTo(x + 1, y + 1);
        context.stroke();
    }
}

function randomColor() {//得到随机的颜色值
    var r = Math.floor(Math.random() * 256);
    var g = Math.floor(Math.random() * 256);
    var b = Math.floor(Math.random() * 256);
    return "rgb(" + r + "," + g + "," + b + ")";
}

var show_num = [];
$(function(){
    draw(show_num);
$("#canvas").on('click',function(){
    draw(show_num);
})
});


//表单提交验证
layui.use(['layer', 'form','layedit'], function () {

    var layer = layui.layer
        , form = layui.form;
    var $ = layui.$;
    //登录表单提交验证
    form.verify({
        login_username:[
            /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/
            ,"邮箱格式不正确"
        ],
        login_password:[
            /^[\S]{6,12}$/
            ,"密码必须6到12位"
        ],
        login_verify:function (value) {
            var num = show_num.join("");
            var val = value.toLowerCase();
            if (val == ''){
                return '请输入验证码';
            }else if (val != num){
                draw(show_num);
                return '验证码错误，请重新输入';
            }
        },
    });


    //注册表单提交验证
     form.verify({
         /*reg_username:[
            /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/
             ,"邮箱格式不正确"
         ],*/
         reg_password:[
             /^[\S]{6,12}$/
             ,"密码必须6到12位"
         ],
         reg_confirm_password:function (value) {
             if($('input[name=userPassword]').val()!==value)
                 return '两次密码输入不一致！';
         },
         reg_verify:function (value) {
            var num = show_num.join("");
            var val = value.toLowerCase();
            if (val == ''){
                return '请输入验证码';
            }else if (val != num){
                draw(show_num);
                return '验证码错误，请重新输入';
            }
         },
     });


    //登录验证
    form.on('submit(login)', function(data){
        var url = "/user/login";
        var data = $("form").serializeArray();
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function (res) {
                if (res.success){
                    layer.msg('登录成功!',{
                            icon: 1,
                            time: 1000
                        },
                        function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.location.reload();
                            parent.layer.close(index);
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

    //注册验证
    form.on('submit(reg)', function(data){
        var url = "/user/reg";
        var data = $("form").serializeArray();
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function (res) {
                console.log(data)
                if (res.success){
                    layer.msg('注册成功!',{
                            icon: 1,
                            time: 500
                        },
                        function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.location.reload();
                            parent.layer.close(index);
                            console.log(res);
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

    //忘记密码
    form.on('submit(forgetPass)', function(data){
        var url = "/user/forgetPass";
        var data = $("form").serializeArray();
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function (res) {
                console.log(data)
                if (res.success){
                    layer.msg('密码修改成功!',{
                            icon: 1,
                            time: 1000
                        },
                        function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.location.reload();
                            parent.layer.close(index);
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


    //获取邮件
    form.on('submit(get-mail)', function(data){
        var emailFormat = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
        var url = "/user/getMail";
        var email = $('input[name=userEmail]').val();
        var userdata={//前端可以发送数据给后端，json形式
            "name":email,
        }
        if (!emailFormat.test(email)){
            layer.msg("请输入正确的邮箱");
        } else {
            layer.msg('请稍等!',{
                icon: 1,
                time: 2000
            });
            $.ajax({
                type: "POST",
                url: url,
                data: userdata,
                success: function (res) {
                    if (res.success){
                        layer.msg('邮件发送成功!',{
                            icon: 1,
                            time: 1000
                        },function () {

                        });
                    } else{
                        layer.msg(res.errorInfo);
                    }
                } ,
            });
        };
        return false;
    });

    /*//新建文章
    form.on('submit(submitNewText)',function (data) {
        var title = $("#article-title").val();
        if(title.length == 0){
            layer.msg("标题不能为空");
            return false;
        };
        var articleLabelId =$('input[name="checkbox"]:checked').val();
        if(typeof(articleLabelId) == "undefined"){
            layer.msg("请选择一个文章标签");
            return false;
        };
        var content = testEditor.getHTML();
        if(content.length == 0){
            layer.msg("文章内容不能为空");
            return false;
        };
        var contentMd = testEditor.getMarkdown();
        var url = "/page/saveArticle";
        var textdata={
            "title":title,
            "content":content,
            "contentMd":contentMd,
            "articleLabelId":articleLabelId,
        };
        $.ajax({
            type:"POST",
            url:url,
            data:textdata,
            success:function (res) {
                if (res.success){
                    layer.msg('文章提交成功',{
                        icon: 1,
                        time: 1000
                    });
                } else {
                    layer.msg(res.errorInfo);
                }
            }

        });
        return false;
    });


    //重新编辑更新文章
    form.on('submit(updateText)',function (data) {
        var title = $("#article-title").val();
        if(title.length == 0){
            layer.msg("标题不能为空");
            return false;
        };
        var articleLabelId =$('input[name="checkbox"]:checked').val();
        if(typeof(articleLabelId) == "undefined"){
            layer.msg("请选择一个文章标签");
            return false;
        };
        var content = testEditor.getHTML();
        if(content.length == 0){
            layer.msg("文章内容不能为空");
            return false;
        };
        var contentMd = testEditor.getMarkdown();
        var url = "/page/updateArticle";
        var textdata={
            "title":title,
            "content":content,
            "contentMd":contentMd,
            "articleLabelId":articleLabelId,
        };
        $.ajax({
            type:"POST",
            url:url,
            data:textdata,
            success:function (res) {
                if (res.success){
                    layer.msg('文章提交成功',{
                        icon: 1,
                        time: 1000
                    });
                } else {
                    layer.msg(res.errorInfo);
                }
            }

        });
        return false;
    });*/


});


function getEmail(){
    var emailFormat = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
    var url = "/user/getMail";
    var email = $('input[name=userEmail]').val();
    var userdata={//前端可以发送数据给后端，json形式
        "email":email,
    };
    if (!emailFormat.test(email)){
        layer.msg("请输入正确的邮箱");
    } else {
        layer.msg('请稍等!',{
            icon: 1,
            time: 2000
        });
        $.ajax({
            type: "POST",
            url: url,
            data: userdata,
            success: function (res) {
                if (res.success){
                    layer.msg('邮件发送成功!',{
                        icon: 1,
                        time: 1000
                    },function () {

                    });
                } else{
                    layer.msg(res.errorInfo);
                }
            } ,
        });
    };
};



//导航栏部分
    layui.use('element', function(){
      var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
      
      //监听导航点击
      element.on('nav(demo)', function(elem){
        //console.log(elem)
        layer.msg(elem.text());
      });
    });


/*退出登录*/
function logOut(){
    layer.confirm('您要退出吗？', {
        btn: ['退出','取消'] //按钮
    },function(){
        window.location.href="/logout";
    });
}

    /*弹出层操作*/
function login(){
    //登录弹出层
    layer.open({
      type: 2,
      id: 1,
      title: false,
      shadeClose: true,
      closeBtn: 0,
      area: ['350px', '500px'],
      content: ['/popup/login_1.html','yes'],
         });
  }

function reg(){

    //注册弹出层
    layer.open({
      type: 2,
      id: 1,
      title: false,
      shadeClose: true,
      closeBtn: 0,
      area: ['350px', '500px'],
      content: ['/popup/register_1.html','yes'],
         });
  }

  function quertionTinymce() {

      layer.open({
          type: 2,
          id: 1,
          title: false,
          shadeClose: true,
          closeBtn: 0,
          area: ['800px', '500px'],
          content: ['/popup/edit.html','yes'],
      });
  }

function feedBack() {
    layer.open({
        type: 2,
        id: 1,
        title: false,
        shadeClose: true,
        closeBtn: 0,
        area: ['800px', '500px'],
        content: ['/popup/feedback.html','yes'],
    });
}

  function new_text(){
    //文章发布
      var url = "/page/editMd";
      $.ajax({
          type:"POST",
          url:url,
          success:function (res) {
              if (res.success){
                  $(location).attr('href', '/edit_md?article=false');
              } else {
                  layer.msg(res.errorInfo);
              }
          }

      });
  }

  function getLogin(){
      /*var aa = parent.layer.getFrameIndex(window.name);
      parent.layer.close(aa);*/

      login();
  }

  function getReg(){
      /*var aa = parent.layer.getFrameIndex(window.name);
      parent.layer.close(aa);*/
      reg();
  }


    function newText(){
        var url = "/page/newText";
        $.ajax({
            type: "GET",
            url: url,
            success: function (res) {
                if (res.success){
                    new_text();
                } else{
                    login();
                }
            } ,
        });
    };





