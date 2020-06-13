    <table class="layui-table">
        <colgroup>
            <col>
            <col>
            <col>
        </colgroup>
        <thead>
        <tr>
            <#--<th>行为</th>
            <th>请求类型</th>
            <th>来源</th>
            <th>请求地址</th>
            <th>操作方式</th>
            <th>请求方法</th>
            <th>请求参数</th>
            <th>SessionID</th>
            <th>执行时间</th>-->

            <th>行为</th>
            <th>登录地点</th>
            <th>登录时间</th>

        </tr>
        </thead>
        <tbody>
        <#list log as log>
            <tr>
                <#--<td>${log.behavior!""}</td>
                <td>${log.requestType!""}</td>
                <td>${log.source!""}</td>
                <td>${log.requestAddress!""}</td>
                <td>${log.operationMode!""}</td>
                <td>${log.requestMethod!""}</td>
                <td>${log.requestParameter!""}</td>
                <td>${log.sessionId!""}</td>
                <td>${log.executionTime!""}</td>-->

                <td>${log.behavior!""}</td>
                <td>${log.source!""}</td>
                <td>${log.executionTime!""}</td>
            </tr>
        </#list>
        </tbody>
    </table>