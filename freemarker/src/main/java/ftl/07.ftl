<html>
<head>
    <title>${title!"没有title"}</title>
</head>
<body>
<#-- 字符串 -->
<#assign username="jim"/>
<#assign num=10 />
<#assign b=true bb=false/>

<#--字符串-->
${username}

<#--字符-->
${num+10}

<#--布尔类型不能直接显示需要转换类型-->
${b?string("true的时候显示","false的时候显示")}
${bb?string("true的时候显示","false的时候显示")}

<#--字符拼接-->
${"hello"+username}

<#--字符插拼-->
${"hello${username}"}

<#--日期转字符串-->
${now?string("yyyy-MM-dd HH:mm:ss")}

<#--转换html标签-->
${"<br/>"?html}

</body>
</html>