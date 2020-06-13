<div class="layui-col-md8 index-page" id="index-page">
    <nav>
        <#--
            pageNum为当前页
            pageCount为页数
        -->
        <#if pageNum == 1>
            <div class="previous-page">
                <a>上一页</a>
            </div>
        <#else>
            <div class="previous-page">
                <a href="/page/${pageNum-1}?type=${thisType}">上一页</a>
            </div>
        </#if>

        <#if pageNum == pageCount || pageCount == 0>
            <div class="next-page">
                <a>下一页</a>
            </div>
        <#else>
            <div class="next-page">
                <a href="/page/${pageNum+1}?type=${thisType}">下一页</a>
            </div>
        </#if>
        <ul>
            <#if pageCount lt 3>
                <#if pageCount == 1 || pageCount == 0>
                    <li style="background-color: #3273dc">
                        <a href="/page/1?type=${thisType}">1</a>
                    </li>
                <#else>
                    <#if pageNum == 1>
                        <li style="background-color: #3273dc">
                            <a href="/page/1?type=${thisType}">1</a>
                        </li>
                        <li>
                            <a href="/page/2?type=${thisType}">2</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/page/1?type=${thisType}">1</a>
                        </li>
                        <li style="background-color: #3273dc">
                            <a href="/page/2?type=${thisType}">2</a>
                        </li>
                    </#if>
                </#if>

            <#elseif pageCount gt 3>
                <#if pageNum == 1>
                    <li style="background-color: #3273dc">
                        <a href="/page/${pageNum}?type=${thisType}">${pageNum}</a>
                    </li>
                    <li>
                        <a href="/page/${pageNum+1}?type=${thisType}">${pageNum+1}</a>
                    </li>
                    <li>
                        <a href="/page/${pageNum+2}?type=${thisType}">${pageNum+2}</a>
                    </li>
                <#elseif pageNum gt 1 && pageNum lt pageCount>
                    <li>
                        <a href="/page/${pageNum-1}?type=${thisType}">${pageNum-1}</a>
                    </li>
                    <li style="background-color: #3273dc">
                        <a href="/page/${pageNum}?type=${thisType}">${pageNum}</a>
                    </li>
                    <li>
                        <a href="/page/${pageNum+2}?type=${thisType}">${pageNum+2}</a>
                    </li>
                <#elseif pageNum == pageCount>
                    <li>
                        <a href="/page/${pageNum-1}?type=${thisType}">${pageNum-1}</a>
                    </li>
                    <li>
                        <a href="/page/${pageNum}?type=${thisType}">${pageNum}</a>
                    </li>
                    <li style="background-color: #3273dc">
                        <a href="/page/${pageNum+2}?type=${thisType}">${pageNum+2}</a>
                    </li>
                </#if>
            </#if>
        </ul>

    </nav>
</div>