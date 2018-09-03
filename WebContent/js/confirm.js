function confirmBondDlg(quoteid) {
	if (window.confirm("您确定要成交当前报价吗？")) {
		$.post("bond/dlg/confirm.action", {
			"quoteid" : quoteid
		}, function(data) {
			console.log("confirmBondDlg data = " + data);
			operateAlert(data.trim() == "true", "成交报价发送成功！！", "成交报价发送失败！！");
		}, "text");
	}
}

function confirmBondres(quoteid) {
	if (window.confirm("您确定要成交当前报价吗？")) {
		$.post("bond/res/confirm.action", {
			"quoteid" : quoteid
		}, function(data) {
			console.log("confirmBondres data = " + data);
			operateAlert(data.trim() == "true", "成交报价发送成功！！", "成交报价发送失败！！");
		}, "text");
	}
}

function confirmBondpledge(quoteid) {
	if (window.confirm("您确定要成交当前报价吗？")) {
		$.post("pledge/dlg/confirm.action", {
			"quoteid" : quoteid
		}, function(data) {
			console.log("confirmBondpledge data = " + data);
			operateAlert(data.trim() == "true", "成交报价发送成功！！", "成交报价发送失败！！");
		}, "text");
	}
}