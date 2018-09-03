$(function() {

	$(irbest).on(
			"click",
			function() {
				console.log("进行利率互换最优行情订阅！");
				$.post("xswap/market/irbest.action", {}, function(data) {
					console.log("data = " + data);
					operateAlert(data.trim() == "true", "进行利率互换最优行情订阅发送成功！！",
							"进行利率互换最优行情订阅发送失败！！");
				}, "text");
				event.preventDefault();
			});
		
	$(irdeep).on(
			"click",
			function() {
				console.log("进行利率互换深度行情订阅！");
				$.post("xswap/market/irdeep.action", {}, function(data) {
					console.log("data = " + data);
					operateAlert(data.trim() == "true", "进行利率互换深度行情订阅发送成功！！",
							"进行利率互换深度行情订阅发送失败！！");
				}, "text");
				event.preventDefault();
			});
	
	
	$(irsbest).on(
			"click",
			function() {
				console.log("进行标准利率互换最优行情订阅！");
				$.post("xswap/market/irsbest.action", {}, function(data) {
					console.log("data = " + data);
					operateAlert(data.trim() == "true", "进行标准利率互换最优行情订阅发送成功！！",
							"进行标准利率互换最优行情订阅发送失败！！");
				}, "text");
				event.preventDefault();
			});
		
	$(irsdeep).on(
			"click",
			function() {
				console.log("进行标准利率互换深度行情订阅！");
				$.post("xswap/market/irsdeep.action", {}, function(data) {
					console.log("data = " + data);
					operateAlert(data.trim() == "true", "进行标准利率互换深度行情订阅发送成功！！",
							"进行标准利率互换深度行情订阅发送失败！！");
				}, "text");
				event.preventDefault();
			});
	
	
	$(unirbest).on(
			"click",
			function() {
				console.log("取消利率互换最优行情订阅！");
				$.post("xswap/market/unirbest.action", {}, function(data) {
					console.log("data = " + data);
					operateAlert(data.trim() == "true", "取消利率互换最优行情订阅发送成功！！",
							"取消利率互换最优行情订阅发送失败！！");
				}, "text");
				event.preventDefault();
			});
		
	$(unirdeep).on(
			"click",
			function() {
				console.log("取消利率互换深度行情订阅！");
				$.post("xswap/market/unirdeep.action", {}, function(data) {
					console.log("data = " + data);
					operateAlert(data.trim() == "true", "取消利率互换深度行情订阅发送成功！！",
							"取消利率互换深度行情订阅发送失败！！");
				}, "text");
				event.preventDefault();
			});
	
	
	$(unirsbest).on(
			"click",
			function() {
				console.log("取消标准利率互换最优行情订阅！");
				$.post("xswap/market/unirsbest.action", {}, function(data) {
					console.log("data = " + data);
					operateAlert(data.trim() == "true", "取消标准利率互换最优行情订阅发送成功！！",
							"取消标准利率互换最优行情订阅发送失败！！");
				}, "text");
				event.preventDefault();
			});
		
	$(unirsdeep).on(
			"click",
			function() {
				console.log("取消标准利率互换深度行情订阅！");
				$.post("xswap/market/unirsdeep.action", {}, function(data) {
					console.log("data = " + data);
					operateAlert(data.trim() == "true", "取消标准利率互换深度行情订阅发送成功！！",
							"取消标准利率互换深度行情订阅发送失败！！");
				}, "text");
				event.preventDefault();
			});
	
	

})
