function replyBondrfq(quoteid) {
	if (window.confirm("您确定要回复当前报价吗？")) {
		$.post("bond/res/add.action", {
			"quotereqid" : quoteid
		}, function(data) {
			console.log("data = " + data);
			operateAlert(data.trim() == "true", "请求报价回复成功！！", "请求报价回复失败！！");
		}, "text");
	}
}
