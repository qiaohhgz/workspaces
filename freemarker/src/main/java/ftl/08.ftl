<html>
<head>
    <title>${title!"没有title"}</title>
</head>
<body>
<#assign nums=[1,2,3,4,5] />
<#assign nums2=12..30 />
<#list nums as n>
    ${n}
</#list>
<#list nums2 as n>
    ${n}
</#list>

<#assign num=nums2[1..10] />

${"字符串的截取字符串的截取字符串的截取字符串的截取"[0..8]}...
</body>
</html>