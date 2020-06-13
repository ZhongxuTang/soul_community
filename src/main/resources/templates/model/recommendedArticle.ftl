<div class="recommendArticleArea">
    <h3>今日推荐</h3>
    <#list recommendArticles as recommendArticle>
        <div class="recommend">
            <div class="recommendArticle">
                <span>${recommendArticle.articleCreateTime?string('yyyy-MM-dd')!""}</span>
                <a href="/page/article/${recommendArticle.articleId!"#"}.ftl">${recommendArticle.articleName!""}</a>
            </div>
            <div class="recommendArticleIcon">
                <span class="layui-icon">&#xe6c6;</span>
                <span class="layui-icon" style="margin-left: 20px;">&#xe705;</span>
            </div>
        </div>
        <div style="border-bottom: 1px solid rgba(19, 18, 18, 0.867); opacity: 0.2;margin-bottom: 10px;margin-top: 10px;"></div>
    </#list>
</div>