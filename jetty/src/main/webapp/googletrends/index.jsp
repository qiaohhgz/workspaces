<html>
<head>
    <meta charset="utf-8">
    <title>Google Trends</title>
</head>
<script type="text/javascript" src="../lib/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
    var baseUrl = "http://www.google.com/trends/fetchComponent?";
    var testQuery = "iphone,ipad";
    $(document).ready(function () {
        $(".test").click(function () {
            googleTrends(testQuery);
        })
        $(".trends").click(function () {
            var query = $("#param").val().replace("\n", ",");
            googleTrends(query);
        });
        $("input[type=number]").change(function () {
            var val = $(this).val();
            var field = $(this).attr("name");
            req[field] = val;
        })
    });
    var req = {
        hl: "zh-CN",
        date: "today 12-m",
        cmpt: "q",
        content: "1",
        cid: "TIMESERIES_GRAPH_0",
        export: "5",
        w: 500,
        h: 330
    }
    function googleTrends(query) {
        req.q = query;
        var url = baseUrl + $.param(req);
        var iframeID = "#chart";

        console.log(req);
        console.log("url = %s", url);

        $(iframeID).get(0).src = url;
        $(iframeID).show();
    }
</script>
<body>
<iframe id="chart" width="500" height="330" src="" style="border: none; display: none"></iframe>

<p>
    content:<input type="number" name="content" id="content" value="1" min="1" max="100" step="1">
    export:<input type="number" name="export" id="export" value="5" min="1" max="100" step="1">
</p>

<p>
    w:<input type="number" name="w" id="w" value="500" min="10" max="1000" step="10">
    h:<input type="number" name="h" id="h" value="330" min="10" max="1000" step="10">
</p>

<p>Keywords:</p>

<p><textarea name="param" id="param" cols="30" rows="10"></textarea></p>

<p>
    <button class="test">test</button>
    <button class="trends">Trends</button>
</p>

</body>
</html>
