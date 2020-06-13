<ul>
    <#list comment as comment>
        <li style="margin-top: 30px;">
            <span>${comment.content}</span><br>
            <span>${comment.commentDate}</span>
            <div style="border-bottom: 1px solid rgba(19, 18, 18, 0.867); opacity: 0.2;margin-top: 10px;"></div>
        </li>
    </#list>

</ul>