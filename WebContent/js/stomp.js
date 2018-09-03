$(function() {
	connect();
	sendName();
});

var stompClient = null;
// this line.
function connect() {
	var userid = 'darren';
	var socket = new SockJS("http://localhost:8080/cfetsclienta/myendpoint");
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/greetings', function(greeting) {
			showGreeting(JSON.parse(greeting.body).content);
		});

		stompClient.subscribe('/topic/notfiyMessage', function(greeting) {
			showNotifyMessage(JSON.parse(greeting.body));
		});

		stompClient.subscribe('/topic/execQuoteOrder', function(greeting) {
			showExecQuoteOrder(JSON.parse(greeting.body));
		});

		stompClient.subscribe('/user/' + userid + '/message',
				function(greeting) {
					alert(JSON.parse(greeting.body).content);
					showGreeting(JSON.parse(greeting.body).content);
				});
	});
}

function sendName() {
	var name = 'darren';
	stompClient.send("/app/hello", {
		atytopic : "greetings"
	}, JSON.stringify({
		'name' : name
	}));
}

function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	console.log("Disconnected");
}

function showGreeting(message) {
	console.log(message);
	operateAlert(true, message, message);
}

function showExecQuoteOrder(message) {
	var execId = message.execID;
	var grossTradeAmt = message.grossTradeAmt;
	var price = message.price;
	console.log("成交编号：" + execId + ",交易金额：" + grossTradeAmt + ",净价：" + price);
	popmessage = "成交编号：" + execId + ",交易金额：" + grossTradeAmt + ",净价：" + price;
	operateAlert(true, popmessage, popmessage);
}

function showNotifyMessage(message) {

	console.log("=============showNotifyMessage=================");

	var quoteId = message.quoteId;
	var flag = message.suceess;
	var opbit = message.opbit;
	var quoteBizType = message.quoteBizType;
	var isSelfOrder = message.selfQuote;

	console
			.log("=============showNotifyMessage=================" + quoteId
					+ "-" + flag + "-" + opbit + "-" + quoteBizType + "-"
					+ isSelfOrder);

	// 界面提示消息
	var popmessage = null;

	if (flag) {
		// 新增成功 需要添加数据到页面
		if (opbit == "新增") {
			addTableRow(message);
			if (isSelfOrder) {
				popmessage = quoteBizType + '新增成功', '报价编号为' + quoteId;
			} else {
				popmessage = '接收' + quoteBizType + '成功', '报价编号为' + quoteId;
			}
		} else
		// 修改成功 必须先删除原数据 再添加数据
		if (opbit == "修改") {
			// deleteRowByQuoteId(quoteId);
			// addTableRow(message);
			updateTableRow(message);
			if (isSelfOrder) {
				popmessage = quoteBizType + '修改成功', '报价编号为' + quoteId;
			} else {
				popmessage = quoteBizType + '被修改', '报价编号为' + quoteId;
			}
		} else
		// 撤销 成交 拒绝都删除掉
		if (opbit == "撤销" || opbit == "拒绝" || opbit == "成交" || opbit == "过期") {
			deleteRowByQuoteId(quoteId);
			popmessage = quoteBizType + "已" + opbit, '报价编号为' + quoteId;
		}
		operateAlert(true, popmessage, popmessage);
	} else {
		popmessage = quoteBizType + opbit + '失败！';
		operateAlert(false, popmessage, popmessage);
	}
}

function addTableRow(message) {

	var clordidClientId = message.clordidClientId;
	var ordId = message.ordId;
	var quoteType = message.quoteType;
	var quoteTypeName = null;

	if (quoteType == "0101") {
		quoteTypeName = "债券对话报价";
	} else if (quoteType == "0102") {
		quoteTypeName = "债券点击成交";
	} else if (quoteType == "0103") {
		quoteTypeName = "债券限价报价";
	} else if (quoteType == "0104") {
		quoteTypeName = "债券做市报价";
	} else if (quoteType == "0105") {
		quoteTypeName = "债券请求报价";
	} else if (quoteType == "0106") {
		quoteTypeName = "请求回复报价";
	} else if (quoteType == "0201") {
		quoteTypeName = "质押式回购";
	} else if (quoteType == "0301") {
		quoteTypeName = "利率互换";
	}
	var price = parseFloat(message.price);
	var volume = parseFloat(message.volume);
	var quoteId = message.quoteId;
	var transactTime = message.transactTime;
	var side = message.side;
	var sideName = null;
	if (side == "0000000" || side == "0123100" || side == "0500110") {
		sideName = "买入";
	} else if (side == "0000001" || side == "0123101" || side == "0500111") {
		sideName = "卖出";
	}

	var clrOrdId = message.clrOrdId;

	console.log("clrOrdId = " + clrOrdId)
	// 判断clrOrdId是否为空
	var hasClrOrdId = clrOrdId != null;
	console.log("hasClrOrdId = " + hasClrOrdId)

	var ordstatus = message.ordstatus;

	var startStr = "<tr style=\"text-align: center; font: light; font-size: small\" class=\"text-muted\" id=\"trquoteOrder-"
			+ quoteId + "\">";
	var clordidClientIdStr = "<td style=\"vertical-align:middle\" id=\"clordidClientId-"
			+ quoteId + "\" >" + clordidClientId + "</td>";
	var ordIdStr = "<td style=\"vertical-align:middle\" id=\"ordId-" + quoteId
			+ "\" >" + ordId + "</td>";
	var quoteTypeNameStr = "<td style=\"vertical-align:middle\" id=\"quoteTypeName-"
			+ quoteId + "\" >" + quoteTypeName + "</td>";
	var priceStr = "<td style=\"vertical-align:middle\" id=\"price-" + quoteId
			+ "\" >" + price + "</td>";
	var volumeStr = "<td style=\"vertical-align:middle\" id=\"volume-"
			+ quoteId + "\" >" + volume + "</td>";
	var quoteIdStr = "<td style=\"vertical-align:middle\" id=\"quoteId-"
			+ quoteId + "\" >" + quoteId + "</td>";
	var transactTimeStr = "<td style=\"vertical-align:middle\" id=\"transactTime-"
			+ quoteId + "\" >" + transactTime + "</td>";
	var sideNameStr = "<td style=\"vertical-align:middle\" id=\"sideName-"
			+ quoteId + "\" >" + sideName + "</td>";

	var commonStr = startStr + clordidClientIdStr + ordIdStr + quoteTypeNameStr
			+ priceStr + volumeStr + quoteIdStr + transactTimeStr + sideNameStr;

	var updateStr = "<td><button id=\"update-"
			+ quoteId
			+ "-"
			+ quoteType
			+ "\" class=\"badge badge-primary btn btn-light btn-sm\" type=\"button\">修改报价</button></td>";
	var cancelStr = "<td><button id=\"cancel-"
			+ quoteId
			+ "-"
			+ quoteType
			+ "\" class=\"badge badge-danger btn btn-light btn-sm\" type=\"button\">撤销报价</button></td>";
	var confirmStr = "<td><button id=\"confirm-"
			+ quoteId
			+ "-"
			+ quoteType
			+ "\" class=\"badge badge-success btn btn-light btn-sm\" type=\"button\">成交报价</button></td>";
	var rejectStr = "<td><button id=\"reject-"
			+ quoteId
			+ "-"
			+ quoteType
			+ "\" class=\"badge badge-dark btn btn-light btn-sm\" type=\"button\">拒绝报价</button></td>";
	var replyStr = "<td><button id=\"reply-"
			+ quoteId
			+ "-"
			+ quoteType
			+ "\" class=\"badge badge-primary btn btn-light btn-sm\" type=\"button\">回复报价</button></td>";
	var blankTd = "<td></td>";

	// var updateStr = "<td style=\"vertical-align:middle\"><input
	// type=\"button\" id=\"update-" + quoteId + "-" + quoteType
	// + "\" name=\"update-" + quoteId + "-" + quoteType + "\" value=\"修改报价\" "
	// + "class=\"btn btn-outline-primary btn-sm\"></td>";
	// var cancelStr = "<td style=\"vertical-align:middle\"><input
	// type=\"button\" id=\"cancel-" + quoteId + "-" + quoteType
	// + "\" name=\"cancel-" + quoteId + "-" + quoteType + "\" value=\"撤销报价\" "
	// + "class=\"btn btn-outline-danger btn-sm\"></td>";
	// var confirmStr = "<td style=\"vertical-align:middle\"><input
	// type=\"button\" id=\"confirm-" + quoteId + "-" + quoteType
	// + "\" name=\"confirm-" + quoteId + "-" + quoteType + "\" value=\"成交报价\" "
	// + "class=\"btn btn-outline-success btn-sm\"></td>";
	// var rejectStr = "<td style=\"vertical-align:middle\"><input
	// type=\"button\" id=\"reject-" + quoteId + "-" + quoteType
	// + "\" name=\"reject-" + quoteId + "-" + quoteType + "\" value=\"拒绝报价\" "
	// + "class=\"btn btn-outline-dark btn-sm\"></td>";
	// var replyStr = "<td style=\"vertical-align:middle\"><input
	// type=\"button\" id=\"reply-" + quoteId + "-" + quoteType
	// + "\" name=\"reply-" + quoteId + "-" + quoteType + "\" value=\"回复报价\" "
	// + "class=\"btn btn-outline-primary btn-sm\"></td>";
	var endStr = "</tr>";

	var commonObj = $(commonStr);
	var updateObj = $(updateStr);
	var cancelObj = $(cancelStr);
	var confirmObj = $(confirmStr);
	var rejectObj = $(rejectStr);
	var replyObj = $(replyStr);
	var endObj = $(endStr);
	var blankTdObj = $(blankTd);

	var tr;

	// 本方发出的对话报价 请求回复报价
	if (hasClrOrdId
			&& (quoteType == "0101" || quoteType == "0201" || quoteType == "0106")) {
		tr = commonObj.append(updateObj).append(cancelObj).append(endObj);
	}
	// 对手发出的对话报价
	else if (!hasClrOrdId && (quoteType == "0101" || quoteType == "0201")) {
		tr = commonObj.append(confirmObj).append(rejectObj).append(endObj);
	}
	// 本方的请求报价 做市报价 点击成交 限价报价 只能撤销
	else if (hasClrOrdId
			&& (quoteType == "0102" || quoteType == "0103"
					|| quoteType == "0104" || quoteType == "0105" || quoteType == "0301")) {
		tr = commonObj.append(cancelObj).append(blankTdObj).append(endObj);
	}
	// 对手的请求报价 进行回复或拒绝
	else if (!hasClrOrdId && quoteType == "0105") {
		tr = commonObj.append(replyObj).append(rejectObj).append(endObj);
	}
	// 对手的请求回复报价 只能进行成交
	else if (!hasClrOrdId && quoteType == "0106") {
		tr = commonObj.append(confirmObj).append(blankTdObj).append(endObj);
	}

	var quoteid = quoteId;

	$(quoteTab).append(tr);

	updateObj.on("click", function() {
		if (quoteType == "0101") {
			updateBondDlg(quoteid, price, volume, side);
		} else if (quoteType == "0106") {
			updateBondres(quoteid, price, volume, side);
		} else if (quoteType == "0201") {
			updateBondpledge(quoteid, price, volume, side);
		}
	});
	cancelObj.on("click", function() {

		if (quoteType == "0101") {
			cancelBondDlg(quoteid);
		} else if (quoteType == "0102") {
			cancelBondcd(quoteid);
		} else if (quoteType == "0103") {
			cancelBondpl(quoteid);
		} else if (quoteType == "0104") {
			cancelBondmm(quoteid);
		} else if (quoteType == "0105") {
			cancelBondrfq(quoteid);
		} else if (quoteType == "0106") {
			cancelBondres(quoteid);
		} else if (quoteType == "0201") {
			cancelBondpledge(quoteid);
		} else if (quoteType == "0301") {
			cancelXswap(quoteid);
		}
	});
	confirmObj.on("click", function() {
		if (quoteType == "0101") {
			confirmBondDlg(quoteid);
		} else if (quoteType == "0106") {
			confirmBondres(quoteid);
		} else if (quoteType == "0201") {
			confirmBondpledge(quoteid);
		}
	});
	rejectObj.on("click", function() {
		if (quoteType == "0101") {
			rejectBondDlg(quoteid);
		} else if (quoteType == "0105") {
			rejectBondrfq(quoteid);
		} else if (quoteType == "0201") {
			rejectBondpledge(quoteid);
		}
	});
	replyObj.on("click", function() {
		if (quoteType == "0105") {
			replyBondrfq(quoteid);
		}
	});
}

function updateTableRow(message) {

	var clordidClientId = message.clordidClientId;
	var ordId = message.ordId;
	var quoteType = message.quoteType;
	var quoteTypeName = null;
	if (quoteType == "0101") {
		quoteTypeName = "债券对话报价";
	} else if (quoteType == "0102") {
		quoteTypeName = "债券点击成交";
	} else if (quoteType == "0103") {
		quoteTypeName = "债券限价报价";
	} else if (quoteType == "0104") {
		quoteTypeName = "债券做市报价";
	} else if (quoteType == "0105") {
		quoteTypeName = "债券请求报价";
	} else if (quoteType == "0106") {
		quoteTypeName = "请求回复报价";
	} else if (quoteType == "0201") {
		quoteTypeName = "质押式回购";
	} else if (quoteType == "0301") {
		quoteTypeName = "利率互换";
	}
	var price = parseFloat(message.price);
	var volume = parseFloat(message.volume);
	var quoteid = message.quoteId;
	var transactTime = message.transactTime;
	var side = message.side;
	var sideName = null;
	if (side == "0000000" || side == "0123100" || side == "0500110") {
		sideName = "买入";
	} else if (side == "0000001" || side == "0123101" || side == "0500111") {
		sideName = "卖出";
	}
	var clrOrdId = message.clrOrdId;
	// 判断clrOrdId是否为空
	var hasClrOrdId = (JSON.stringify(clrOrdId) != "{}");
	var ordstatus = message.ordstatus;

	$("#clordidClientId-" + quoteid).text(clordidClientId);
	$("#ordId-" + quoteid).text(ordId);
	$("#quoteType-" + quoteid).text(quoteTypeName);
	$("#price-" + quoteid).text(price);
	$("#volume-" + quoteid).text(volume);
	$("#transactTime-" + quoteid).text(transactTime);
	$("#side-" + quoteid).text(sideName);

}

function deleteRowByQuoteId(quoteId) {
	console.log("=============deleteRowByQuoteId================" + quoteId)
	var obj = $("#trquoteOrder-" + quoteId);
	obj.remove();
}
