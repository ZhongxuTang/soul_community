<#include "model/head.ftl">
<style>
    img{
        width: 500px;
    }
</style>
<#--代码高亮显示-->
<link rel="stylesheet" href="/highlight/default.css">
<script src="/highlight/highlight.pack.js"></script>
<script>hljs.initHighlightingOnLoad();</script>

    <div class="layui-container article-container">
        <div class="layui-col-md1 article-share-area">
            <div class="article-share">
                <a class="layui-icon" href="#" style="color: #7b68ee;"><p>${article.userCommentQuantity!""}</p>&#xe63a;</a>
                <a class="layui-icon share-qq" target="_blank"
                   href="#" style="color: #56b6e7;">&#xe676;</a>
                <a class="layui-icon share-weixin" target="_blank"
                   href="#" style="color: #7bc549;">&#xe677;</a>
                <a class="layui-icon shae-weibo" target="_blank"
                   href="#" style="color: #ff763b;">&#xe675;</a>
            </div>
        </div>
        <div class="layui-col-md8 article-body-area">

            <div class="article-body-head">
                <time>${article.articleCreateTime?string('yyyy-MM-dd')!""}</time>
                <a href="#">${article.articleType.articleTypeName!""}</a>
            </div>
            <h1>${article.articleName!""}</h1>
            <div class="article-body-content">
                ${article.content!""}
            </div>
        </div>

        <div class="layui-col-md2 about-the-author">
            <#include "model/aboutAuthor.ftl">
        </div>

        <div class="layui-col-md2 article-recommend">
            <div class="index-body-recommend-body">
                <#include "model/recommendedArticle.ftl">
            </div>
        </div>

        <div class="layui-col-md8 article-card">
            <div class="article-card-body">
                <p>
                    本文由
                    <a href="#">${article.user.userName!""}</a>
                    创作，如果您觉得不错，请点个赞<br>
                    本站文章除注明转载/出处外，均为本站原创或翻译，转载前请务必署名<br>
                    最后更新时间：${article.articleCreateTime!""}
                </p>
            </div>
        </div>

        <#--<div class="layui-col-md8 article-admire">
            <div class="article-admire-body">
                <p>这篇文章如何？请留下您宝贵的评分！</p>
                <div id="admire" class="admire"></div>
            </div>
        </div>-->

        <div class="layui-col-md8 article-comment">
            <div class="article-comment-body">
                <p>评论</p>
                <div class="article-comment-headPortrait">
                    <#if !Session["currentUser"]?exists>
                        <i class="layui-icon">&#xe66f;</i>
                    <#elseif Session["currentUser"]?exists>
                        <img src="${Session.currentUser.userHeadPortrait}">
                    </#if>
                </div>
                <div class="article-comment-body article-comment-body-content">
                    <form class="layui-form">
                        <div class="layui-form-item layui-form-text">
                            <div class="layui-input-block">
                                <textarea name="conent" placeholder="撰写评论" class="layui-textarea" lay-verify="comment_content"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="publishComment" style="background-color: #babf9c;">提交</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="layui-col-md12">
                <div class="comment-body">
                    <#list comment as comment>
                        <div class="it-comment">
                            <div class="comment-body-head">
                                <img src="${comment.user.userHeadPortrait}" class="layui-nav-img">
                                <div class="comment-body-mess">
                                    <ul>
                                        <li>
                                            <span style="font-size: 16px;">
                                                <i>${comment.user.userName}</i>
                                            </span>
                                            <#--<span>
                                                <button class="layui-btn reply-btn">回复</button>
                                            </span>-->
                                        </li>
                                        <#--<li>
                                            <span style="font-size: 5px;">
                                                <i>windows</i>
                                                <i>chrome</i>
                                            </span>
                                        </li>-->
                                        <li style="display: block;font-size: 5px;">
                                            <span>${comment.commentDate}</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="comment-body-content">
                                ${comment.content}
                            </div>
                        </div>
                        <div style="border-bottom: 1px solid rgba(19, 18, 18, 0.867); opacity: 0.2;margin-bottom: 10px;margin-top: 10px;"></div>
                    </#list>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-col-md12" style="float: left;border-radius: 10px;margin-bottom: 2%;height: 10px;">
    </div>
<#--
    <div class="layui-col-md12 article-bottom">
        <div class="article-bottom-content">
            <p>总访问量***</p>
        </div>
    </div>
-->

<script>
    layui.use(['rate','layer','form'], function() {
        var rate = layui.rate;

        //显示文字
        rate.render({
            elem: '#admire'
            , value: 2 //初始值
            , text: true //开启文本
        });

        var form = layui.form;

        form.verify({
            comment_content:[
                /^[\S]{5,100}$/
                ,"评论必须5到100位"
            ]
        });

        form.on('submit(publishComment)',function () {
            var url = "/comment/publishComment";
            // var content = $("form").serializeArray();
            var content = $('textarea[name=conent]').val();
            var comment={
                "articleId":${article.articleId!""},
                "content":content
            };
            $.ajax({
                type:"POST",
                url:url,
                data:comment,
                success:function (res) {
                    if (res.success){
                        layer.msg('评论发布成功',{
                            icon: 1,
                            time: 1000
                        });
                        window.location.reload();
                    } else {
                        layer.msg(res.errorInfo);
                    }
                }
            })
            return false;
        });

    });

    $(".search-btn").click(function () {

        var searchContext = $(".search-context").val();
        url="/search/article";
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
                $(".article-container").html(res);
            }
        });

    });

</script>

<script type="text/javascript" src="/js/verify.js"></script>