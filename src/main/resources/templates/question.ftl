<#include "model/head.ftl">
<div class="layui-container layui-anim layui-anim-scale index-body">

  <div class="layui-row" style="margin: 0 auto;">
    <#include "questionModel/quertionLeft.ftl">

    <div class="layui-col-md8 index-body-notification-area" style="height: 0;">



      <div class="layui-col-md12" style="float: right;">

        <div class="layui-col-md12 oneClassArticleTitle">
          <div class="oneClassArticleTitle_1">
            <a href="#" class="allArticle">${class}</a>
          </div>
        </div>


        <#include "model/questionList.ftl">
        <#assign thisType="question" />

        <#include "model/paging.ftl">
      </div>

    </div>
  </div>
</div>
<script type="text/javascript">
  $("#question").addClass("layui-this");
  $("#index-page").removeClass("layui-col-md8");
  $("#index-page").addClass("layui-col-md12");



  $(".search-btn").click(function () {

    var searchContext = $(".search-context").val();
    url="/search/question";
    searchData={
      "searchContext":searchContext,
    };
    $.ajax({
      type:"POST",
      url:url,
      data:searchData,
      async:false,
      success:function (res) {
        console.log(res);
        $(".index-body").html(res);
      }
    });

  });


</script>

<script type="text/javascript" src="/js/verify.js"></script>