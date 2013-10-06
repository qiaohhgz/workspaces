<html>
<head>
    <title>${title!"没有title"}</title>
</head>
<body>
${user.id}------------${user.name}----------------${user.group!}
${user.id}------------${user.name}----------------${user.group!"没有group"}

${user.id}------------${user.name}----------------${(user.group.name)!}
${user.id}------------${user.name}----------------${(user.group.name)!"没有值"}

${(a.b)!"没有a.b"}

<#if (a.b)??>
a.b
<#else >
没有a.b
</#if>
</body>
</html>