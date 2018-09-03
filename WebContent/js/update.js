function updateBondDlg(quoteid, price, volume, side) {
	if (window.confirm("您确定要修改当前报价吗？")) {
		$.post("bond/dlg/update.action", {
			"quoteid" : quoteid,
			"price" : price,
			"volume" : volume
		}, function(data) {
			console.log("data = " + data);
			operateAlert(data.trim() == "true", "修改报价发送成功！！", "修改报价发送失败！！");
		}, "text");
	}
}

function updateBondres(quoteid, price, volume, side) {
	if (window.confirm("您确定要修改当前报价吗？")) {
		console.log("quoteid = "+quoteid+"price = "+price+",volume = "+volume);
		$.post("bond/res/update.action", {
			"quoteid" : quoteid,
			"price" : price,
			"volume" : volume
		}, function(data) {
			console.log("data = " + data);
			operateAlert(data.trim() == "true", "修改报价发送成功！！", "修改报价发送失败！！");
		}, "text");
	}
}

function updateBondpledge(quoteid, price, volume, side) {
	if (window.confirm("您确定要修改当前报价吗？")) {
		$.post("pledge/dlg/update.action", {
			"quoteid" : quoteid,
			"price" : price,
			"volume" : volume
		}, function(data) {
			console.log("data = " + data);
			operateAlert(data.trim() == "true", "修改报价发送成功！！", "修改报价发送失败！！");
		}, "text");
	}
}