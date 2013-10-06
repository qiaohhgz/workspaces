<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<title>Insert title here</title>
<%@include file="include/mainTemplate.jsp"%>
<script type="text/javascript" src="js/index.js"></script>
<style>
<!--
.text-center {
	text-align: center;
}
-->
</style>
<div id="appointmentTable"></div>
<div id="appointmentUpdateDialog"></div>
<a href="ap?t=excel">download excel</a>
<!-- <div style="float: right;">
	<a href="ap?t=image" target="_blank"> <img src="ap?t=image" width="10%" height="10%" alt="lbjn" />
	</a>
</div> -->
<button onclick="beginWork()">beginWork</button>
<button onclick="endWork()">endWork</button>
<button onclick="beginOverTime()">beginOverTime</button>
<button onclick="endOverTime()">endOverTime</button>
</body>
</html>