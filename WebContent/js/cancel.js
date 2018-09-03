function cancelBondDlg(quoteid) {
	if (window.confirm("您确定要撤销当前报价吗？")) {
		cancelBondDlgWithOutConfirm(quoteid);
//		$.post("bond/dlg/cancel.action", {
//			"quoteid" : quoteid
//		}, function(data) {
//			console.log("data = " + data);
//			operateAlert(data.trim() == "true", "撤销报价发送成功！！", "撤销报价发送失败！！");
//		}, "text");
	}
}

function cancelBondcd(quoteid) {
	if (window.confirm("您确定要撤销当前报价吗？")) {
		cancelBondcdWithOutConfirm(quoteid);
//		$.post("bond/clickdeal/cancel.action", {
//			"quoteid" : quoteid
//		}, function(data) {
//			console.log("data = " + data);
//			operateAlert(data.trim() == "true", "撤销报价发送成功！！", "撤销报价发送失败！！");
//		}, "text");
	}
}

function cancelBondpl(quoteid) {
	if (window.confirm("您确定要撤销当前报价吗？")) {
		cancelBondplWithOutConfirm(quoteid);
	}
}

function cancelBondmm(quoteid) {
	if (window.confirm("您确定要撤销当前报价吗？")) {
		cancelBondmmWithOutConfirm(quoteid);
	}
}

function cancelBondrfq(quoteid) {
	if (window.confirm("您确定要撤销当前报价吗？")) {
		cancelBondrfqWithOutConfirm(quoteid);
	}
}

function cancelBondres(quoteid) {
	if (window.confirm("您确定要撤销当前报价吗？")) {
		cancelBondresWithOutConfirm(quoteid);
	}
}

function cancelBondpledge(quoteid) {
	if (window.confirm("您确定要撤销当前报价吗？")) {
		cancelBondpledgeWithOutConfirm(quoteid);
	}
}

function cancelXswap(quoteid) {
	if (window.confirm("您确定要撤销当前报价吗？")) {
		cancelXswapWithOutConfirm(quoteid);
	}
}

function cancelBondDlgWithOutConfirm(quoteid) {
	$.post("bond/dlg/cancel.action", {
		"quoteid" : quoteid
	}, function(data) {
		console.log("data = " + data);
		operateAlert(data.trim() == "true", "撤销报价发送成功！！", "撤销报价发送失败！！");
	}, "text");
}

function cancelBondcdWithOutConfirm(quoteid) {
	$.post("bond/clickdeal/cancel.action", {
		"quoteid" : quoteid
	}, function(data) {
		console.log("data = " + data);
		operateAlert(data.trim() == "true", "撤销报价发送成功！！", "撤销报价发送失败！！");
	}, "text");
}

function cancelBondplWithOutConfirm(quoteid) {
	$.post("bond/pricelimit/cancel.action", {
		"quoteid" : quoteid
	}, function(data) {
		console.log("data = " + data);
		operateAlert(data.trim() == "true", "撤销报价发送成功！！", "撤销报价发送失败！！");
	}, "text");
}

function cancelBondmmWithOutConfirm(quoteid) {
	$.post("bond/marketmake/cancel.action", {
		"quoteid" : quoteid
	}, function(data) {
		console.log("data = " + data);
		operateAlert(data.trim() == "true", "撤销报价发送成功！！", "撤销报价发送失败！！");
	}, "text");
}

function cancelBondrfqWithOutConfirm(quoteid) {
	$.post("bond/rfq/cancel.action", {
		"quoteid" : quoteid
	}, function(data) {
		console.log("data = " + data);
		operateAlert(data.trim() == "true", "撤销报价发送成功！！", "撤销报价发送失败！！");
	}, "text");
}

function cancelBondresWithOutConfirm(quoteid) {
	$.post("bond/res/cancel.action", {
		"quoteid" : quoteid
	}, function(data) {
		console.log("data = " + data);
		operateAlert(data.trim() == "true", "撤销报价发送成功！！", "撤销报价发送失败！！");
	}, "text");
}

function cancelBondpledgeWithOutConfirm(quoteid) {
	$.post("pledge/dlg/cancel.action", {
		"quoteid" : quoteid
	}, function(data) {
		console.log("data = " + data);
		operateAlert(data.trim() == "true", "撤销报价发送成功！！", "撤销报价发送失败！！");
	}, "text");
}

function cancelXswapWithOutConfirm(quoteid) {
	$.post("xswap/cancel.action", {
		"quoteid" : quoteid
	}, function(data) {
		console.log("data = " + data);
		operateAlert(data.trim() == "true", "撤销报价发送成功！！", "撤销报价发送失败！！");
	}, "text");
}
