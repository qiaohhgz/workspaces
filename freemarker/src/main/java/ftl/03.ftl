<html>
<head>
    <title></title>
</head>
<body>
${user.id}------------${user.name}--------------${user.age}
<#if user.age lt 18>
${user.name}快成年了
<#elseif user.age == 18>
${user.name}刚成年
<#else >
${user.name}长大了成人了
</#if>

<#if user.age < 18>
${user.name}快成年了
<#elseif user.age == 18>
${user.name}刚成年
<#else >
${user.name}长大了成人了
</#if>
</body>
</html>