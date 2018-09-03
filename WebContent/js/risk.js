$(function() {

	// 处理授信设置
	$(limitRelationSet).on(
			"click",
			function() {
				console.log("进行关系授信设置！");
				$.post("xswap/risk/limitRelationSet.action", {
				// 没有参数
				}, function(data) {
					console.log("data = " + data);
					operateAlert(data.trim() == "true", "关系授信设置发送成功！！",
							"关系授信设置发送失败！！");
				}, "text");
				// event.stoppropagation();
				event.preventDefault();
			});

	// 处理授信设置
	$(limitUnRelationSet).on(
			"click",
			function() {
				console.log("进行关系授信设置！");
				$.post("xswap/risk/limitUnRelationSet.action", {
				// 没有参数
				}, function(data) {
					console.log("data = " + data);
					operateAlert(data.trim() == "true", "解除关系授信设置发送成功！！",
							"解除关系授信设置发送失败！！");
				}, "text");
				// event.stoppropagation();
				event.preventDefault();
			});

	// 处理授信设置
	$(limitAmountSet).on(
			"click",
			function() {
				console.log("进行额度授信设置！");
				$.post("xswap/risk/limitAmountSet.action", {
				// 没有参数
				}, function(data) {
					console.log("data = " + data);
					operateAlert(data.trim() == "true", "额度授信设置发送成功！！",
							"额度授信设置发送失败！！");
				}, "text");
				// event.stoppropagation();
				event.preventDefault();
			});

	// 处理授信查询
	$(limitQuery).on("click", function() {
		console.log("进行授信查询！");
		$.post("xswap/risk/limitQuery.action", {
		// 没有参数
		}, function(data) {
			console.log("data = " + data);
			operateAlert(data.trim() == "true", "授信查询发送成功！！", "授信查询发送失败！！");
		}, "text");
		// event.stoppropagation();
		event.preventDefault();
	});

	// 处理授信风险系数设置
	$(ratioSet).on(
			"click",
			function() {
				console.log("进行授信风险系数设置！");
				$.post("xswap/risk/ratioSet.action", {
				// 没有参数
				}, function(data) {
					console.log("data = " + data);
					operateAlert(data.trim() == "true", "授信风险系数设置发送成功！！",
							"授信风险系数设置发送失败！！");
				}, "text");
				// event.stoppropagation();
				event.preventDefault();
			});

	// 处理授信风险系数查询
	$(ratioQuery).on(
			"click",
			function() {
				console.log("进行授信风险系数查询！");
				$.post("xswap/risk/ratioQuery.action", {
				// 没有参数
				}, function(data) {
					console.log("data = " + data);
					operateAlert(data.trim() == "true", "授信风险系数查询发送成功！！",
							"授信风险系数查询发送失败！！");
				}, "text");
				// event.stoppropagation();
				event.preventDefault();
			});

})
