function display() {
	$(document).ready(function() {
		$("#btn1").click(function() {
			$.get("test", "type=map", function(data) {
				$("#input1").html(data.length);
			}, "json");
		});

		$("#btn2").click(function() {
			$.get("test", null, function(data) {
				$("#input2").html(data.length);
			}, "json");
		});
	});
}