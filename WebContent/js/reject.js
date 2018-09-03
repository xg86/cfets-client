function rejectBondDlg(quoteid) {
	if (window.confirm("您确定要拒绝当前报价吗？")) {
		rejectBondDlgWithOutConfirm(quoteid);
	}
}

function rejectBondrfq(quoteid) {
	if (window.confirm("您确定要拒绝当前报价吗？")) {
		rejectBondrfqWithOutConfirm(quoteid);
	}
}

function rejectBondpledge(quoteid) {
	if (window.confirm("您确定要拒绝当前报价吗？")) {
		rejectBondpledgeWithOutConfirm(quoteid);
	}
}

function rejectBondDlgWithOutConfirm(quoteid) {
	$.post("bond/dlg/reject.action", {
		"quoteid" : quoteid
	}, function(data) {
		console.log("rejectBondDlg data = " + data);
		operateAlert(data.trim() == "true", "拒绝报价发送成功！！", "拒绝报价发送失败！！");
	}, "text");
}

function rejectBondrfqWithOutConfirm(quoteid) {
	$.post("bond/rfq/reject.action", {
		"quoteid" : quoteid
	}, function(data) {
		console.log("rejectBondrfq data = " + data);
		operateAlert(data.trim() == "true", "拒绝报价发送成功！！", "拒绝报价发送失败！！");
	}, "text");
}

function rejectBondpledgeWithOutConfirm(quoteid) {
	$.post("pledge/dlg/reject.action", {
		"quoteid" : quoteid
	}, function(data) {
		console.log("rejectBondpledge data = " + data);
		operateAlert(data.trim() == "true", "拒绝报价发送成功！！", "拒绝报价发送失败！！");
	}, "text");
}