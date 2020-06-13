



/*个人中心*/
$(".questionMenu").click(function () {
    var url= "/userCenter/personalQuestion";
    $.ajax({
        type:"GET",
        url:url,
        async:false,
        success:function (res) {
            $(".personalOperation").html(res);
        }
    });
});
$(".logMenu").click(function () {
    var url= "/userCenter/userLog";
    $.ajax({
        type:"GET",
        url:url,
        async:false,
        success:function (res) {
            $(".personalOperation").html(res);
        }
    });
});
$(".informationMenu").click(function () {
    var url= "/userCenter/personalInformation";
    $.ajax({
        type:"GET",
        url:url,
        async:false,
        success:function (res) {
            $(".personalOperation").html(res);
        }
    });
    form.render();
});

/*用户信息（他人可见）*/



/*admin*/
$(".adminArticleMenu").click(function () {
    $(".dash-board").removeClass("bg-color");
    $(".admin-question").removeClass("bg-color");
    $(".admin-user").removeClass("bg-color");
    $(".admin-article").addClass("bg-color");
    var url= "/admin/article";
    console.log("test");
    $.ajax({
        type:"POST",
        url:url,
        async:false,
        success:function (res) {
            $(".layui-container").html(res);
        }
    });
});

$(".adminQuestionMenu").click(function () {
    $(".dash-board").removeClass("bg-color");
    $(".admin-article").removeClass("bg-color");
    $(".admin-user").removeClass("bg-color");
    $(".admin-question").addClass("bg-color");
    var url= "/admin/question";
    console.log("test");
    $.ajax({
        type:"POST",
        url:url,
        async:false,
        success:function (res) {
            $(".layui-container").html(res);
        }
    });
});

$(".adminUserMenu").click(function () {
    $(".dash-board").removeClass("bg-color");
    $(".admin-article").removeClass("bg-color");
    $(".admin-question").removeClass("bg-color");
    $(".admin-user").addClass("bg-color");
    var url= "/admin/user";
    $.ajax({
        type:"POST",
        url:url,
        async:false,
        success:function (res) {
            $(".layui-container").html(res);
        }
    });
});






