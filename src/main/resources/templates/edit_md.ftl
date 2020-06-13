<#include "model/head.ftl">
<link rel="stylesheet" href="/css/style.css" />
<link rel="stylesheet" href="/css/editormd.css" />
<link rel="stylesheet" href="/css/jquery-labelauty.css">
<style>
    input.labelauty + label { font: 12px;}
    .article-class{
        margin-right: 15px;
        font-size: 15px;
    }
</style>
<div class="layui-container">
    <form style="margin-top: 15px;margin-bottom: 60px;">
        <div>
            <div class="layui-form-item">
                <div class="layui-input-block create-article-title">
                    <#if RequestParameters['article']??>
                        <input type="text" name="title" id="article-title" required  lay-verify="required" placeholder="请输入文章标题" autocomplete="off" class="layui-input">
                    <#else>
                        <input type="text" name="title" id="article-title" required  lay-verify="required" placeholder="请输入文章标题" autocomplete="off" class="layui-input" value="${article.articleName!""}">
                    </#if>
                    <span class="article-class">分类</span>
                    <ul class="dowebok" style="display: inline-flex; list-style-type: none;">
                        <#list articleType as articleType>
                            <li style="display: inline-block; margin: 10px 5px;"><input type="checkbox" name="checkbox" data-labelauty="${articleType.articleTypeName}" value="${articleType.articleTypeId}" onclick="check_count(this)"></li>
                        </#list>
                    </ul>
                </div>
            </div>
        </div>

        <div id="layout" style="position: sticky;" class="layui-form-item layui-form-text">
            <div id="article-editormd" class="layui-input-block">
                <textarea style="display:none;" placeholder="请输入内容"><#if RequestParameters['article']??><#else>${article.contentMd!""}</#if></textarea>
            </div>
        </div>
    </form>
</div>
<div class="layui-col-md12 create-article-footer">
    <#if RequestParameters['article']??>
        <#assign id= "null"/>
        <button class="layui-btn" type="submit" style="margin-right: 10%" lay-filter="submitNewText" lay-submit="">提交</button>
        <button class="layui-btn" lay-filter="saveDraft" style="width: 90px;background-color: #F5F5F5;color: red;border: 1px solid black;" lay-submit="">保存草稿</button>
    <#else>
        <#assign id= article.articleId/>
        <button class="layui-btn" type="submit" style="margin-right: 10%" lay-filter="updateText" lay-submit="">提交</button>
        <button class="layui-btn" type="submit" lay-filter="saveDraft" style="width: 90px;background-color: #F5F5F5;color: red;border: 1px solid black;" lay-submit="">保存草稿</button>
    </#if>
</div>
<script type="text/javascript" src="/js/editormd.min.js"></script>
<script type="text/javascript" src="/js/verify.js"></script>
<script type="text/javascript" src="/js/jquery-labelauty.js"></script>
<script type="text/javascript">
    $(function(){
        $(':input').labelauty();
    });

    layui.use(['layer','form','layedit'], function(){

        var layer = layui.layer;

        var form = layui.form,
            $ = layui.$;
        form.render();
        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });

        //新建文章
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
            var textdata={
                "title":title,
                "content":content,
                "contentMd":contentMd,
                "articleLabelId":articleLabelId,
            };
            var url = "/page/saveArticle?articleId=${id}";
            $.ajax({
                type:"POST",
                url:url,
                data:textdata,
                success:function (res) {
                    if (res.success){
                        layer.msg('文章提交成功,即将返回首页',{
                            icon: 1,
                            time: 2000
                        });
                        window.location.href="/";
                    } else {
                        layer.msg(res.errorInfo);
                    }
                }

            });
            return false;
        });


        //更新文章
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
            var textdata={
                "title":title,
                "content":content,
                "contentMd":contentMd,
                "articleLabelId":articleLabelId,
            };
            var url = "/page/saveArticle?articleId=${id}";
            $.ajax({
                type:"POST",
                url:url,
                data:textdata,
                success:function (res) {
                    if (res.success){
                        layer.msg('文章提交成功,即将返回首页',{
                            icon: 1,
                            time: 3000
                        });
                        window.location.href="/";
                    } else {
                        layer.msg(res.errorInfo);
                    }
                }

            });
            return false;
        });

        //保存草稿
        form.on('submit(saveDraft)',function (data) {
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
            var textdata={
                "title":title,
                "content":content,
                "contentMd":contentMd,
                "articleLabelId":articleLabelId,
            };
            var url = "/page/saveDraft";
            $.ajax({
                type:"POST",
                url:url,
                data:textdata,
                success:function (res) {
                    if (res.success){
                        layer.msg('草稿保存成功,即将返回首页',{
                            icon: 1,
                            time: 3000
                        });
                        window.location.href="/";
                    } else {
                        layer.msg(res.errorInfo);
                    }
                }

            });
            return false;
        });
    });


    //标签复选框数量限制
    function check_count(obj) {
        var length =  $('input[name="checkbox"]:checked').length;
        if (length > 1){
            obj.checked=false;
            layer.msg("只能选择一个标签");
        }
    }
    
    var testEditor;

    $(function() {
        testEditor = editormd("article-editormd", {
            width   : "100%",
            height  : 600,
            syncScrolling : "single",
            path    : "/lib/",
            saveHTMLToTextarea:true,
            placeholder: "  开始编辑...",

            imageUpload    : true,
            imageFormats   : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL : "/page/uploadImg",
        });

    });

</script>
