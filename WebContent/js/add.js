function AddBondDlg() {
	console.log("债券对话报价新增");
	$.post("bond/dlg/add.action", {
	// 没有参数
	}, function(data) {
		console.log("data = " + data);
		operateAlert(data.trim() == "true", "新增报价发送成功！！", "新增报价发送失败！！");
	}, "text");
}

function AddleBondcd() {
	$.post("bond/clickdeal/add.action", {
	// 没有参数
	}, function(data) {
		console.log("data = " + data);
		operateAlert(data.trim() == "true", "新增报价发送成功！！", "新增报价发送失败！！");
	}, "text");
}

function AddBondpl() {
	$.post("bond/pricelimit/add.action", {
	// 没有参数
	}, function(data) {
		console.log("data = " + data);
		operateAlert(data.trim() == "true", "新增报价发送成功！！", "新增报价发送失败！！");
	}, "text");

}

function AddBondmm() {
	$.post("bond/marketmake/add.action", {
	// 没有参数
	}, function(data) {
		console.log("data = " + data);
		operateAlert(data.trim() == "true", "新增报价发送成功！！", "新增报价发送失败！！");
	}, "text");

}

function AddBondrfq() {
	$.post("bond/rfq/add.action", {
	// 没有参数
	}, function(data) {
		console.log("data = " + data);
		operateAlert(data.trim() == "true", "新增报价发送成功！！", "新增报价发送失败！！");
	}, "text");

}

function AddBondpledge() {
	$.post("pledge/dlg/add.action", {
	// 没有参数
	}, function(data) {
		console.log("data = " + data);
		operateAlert(data.trim() == "true", "新增报价发送成功！！", "新增报价发送失败！！");
	}, "text");

}

function AddXswap() {
	$.post("xswap/add.action", {
	// 没有参数
	}, function(data) {
		console.log("data = " + data);
		operateAlert(data.trim() == "true", "新增报价发送成功！！", "新增报价发送失败！！");
	}, "text");

}
