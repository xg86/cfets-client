$(function() {

	$(endAll).on("click", function() {

		$("[id*=cancel-]").each(function() {
			var quoteid = this.id.split("-")[1];
			var quoteType = this.id.split("-")[2];
			var inputObj = $(this); // 保存当前对象
			if (quoteType == "0101") {
				cancelBondDlgWithOutConfirm(quoteid);
			} else if (quoteType == "0102") {
				cancelBondcdWithOutConfirm(quoteid);
			} else if (quoteType == "0103") {
				cancelBondplWithOutConfirm(quoteid);
			} else if (quoteType == "0104") {
				cancelBondmmWithOutConfirm(quoteid);
			} else if (quoteType == "0105") {
				cancelBondrfqWithOutConfirm(quoteid);
			} else if (quoteType == "0106") {
				cancelBondresWithOutConfirm(quoteid);
			} else if (quoteType == "0201") {
				cancelBondpledgeWithOutConfirm(quoteid);
			} else if (quoteType == "0301") {
				cancelXswapWithOutConfirm(quoteid);
			}
		});

		// 处理拒绝报价
		$("[id*=reject-]").each(function() {
			var quoteid = this.id.split("-")[1];
			var quoteType = this.id.split("-")[2];
			var inputObj = $(this); // 保存当前对象
			if (quoteType == "0101") {
				rejectBondDlgWithOutConfirm(quoteid);
			} else if (quoteType == "0105") {
				rejectBondrfqWithOutConfirm(quoteid);
			} else if (quoteType == "0201") {
				rejectBondpledgeWithOutConfirm(quoteid);
			}
		});

		event.preventDefault();

	});

	// 处理数据归档
	$(archive).on("click", function() {
		console.log("进行数据归档！");
		$.post("cfets/archive.action", {
		// 没有参数
		}, function(data) {
			console.log("data = " + data);
			operateAlert(data.trim() == "true", "数据归档成功！！", "数据归档失败！！");
		}, "text");
		// event.stoppropagation();
		event.preventDefault();
	});

	// 处理新增
	$("[id*=addquote]").each(function() {
		$(this).on("click", function() {
			var quoteType = this.id.split("-")[1];
			console.log("quoteType = " + quoteType);
			if (quoteType == "0101") {
				AddBondDlg();
			} else if (quoteType == "0102") {
				AddleBondcd();
			} else if (quoteType == "0103") {
				AddBondpl();
			} else if (quoteType == "0104") {
				AddBondmm();
			} else if (quoteType == "0105") {
				AddBondrfq();
			} else if (quoteType == "0201") {
				AddBondpledge();
			} else if (quoteType == "0301") {
				AddXswap();
			}

		});
	});

	// 处理修改
	$("[id*=update-]").each(function() {
		$(this).on("click", function() {
			var quoteid = this.id.split("-")[1];
			var quoteType = this.id.split("-")[2];
			var price = null;
			var volume = null;
			console.log("quoteid = " + quoteid + ",quoteType = " + quoteType);
			price = $("#price-" + quoteid).text().trim();
			volume = $("#volume-" + quoteid).text().trim();
			side = $("#side-" + quoteid).text().trim();
			console.log("price = " + price + ",volume = " + volume);
			if (quoteType == "0101") {
				updateBondDlg(quoteid, price, volume, side);
			} else if (quoteType == "0106") {
				updateBondres(quoteid, price, volume, side);
			} else if (quoteType == "0201") {
				updateBondpledge(quoteid, price, volume, side);
			}
		});
	});

	// 处理撤销
	$("[id*=cancel-]").each(function() {
		$(this).on("click", function() {
			console.log("quoteid = " + quoteid + ",quoteType = " + quoteType);
			var quoteid = this.id.split("-")[1];
			var quoteType = this.id.split("-")[2];
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
	});

	// 处理成交 对话报价和回复报价
	$("[id*=confirm-]").each(function() {
		$(this).on("click", function() {
			var quoteid = this.id.split("-")[1];
			var quoteType = this.id.split("-")[2];
			if (quoteType == "0101") {
				confirmBondDlg(quoteid);
			} else if (quoteType == "0106") {
				confirmBondres(quoteid);
			} else if (quoteType == "0201") {
				confirmBondpledge(quoteid);
			}
		});
	});

	// 处理拒绝报价
	$("[id*=reject-]").each(function() {
		$(this).on("click", function() {
			var quoteid = this.id.split("-")[1];
			var quoteType = this.id.split("-")[2];
			if (quoteType == "0101") {
				rejectBondDlg(quoteid);
			} else if (quoteType == "0105") {
				rejectBondrfq(quoteid);
			} else if (quoteType == "0201") {
				rejectBondpledge(quoteid);
			}
		});
	});

	// 处理回复报价
	$("[id*=reply-]").each(function() {
		$(this).on("click", function() {
			var quoteid = this.id.split("-")[1];
			var quoteType = this.id.split("-")[2];
			if (quoteType == "0105") {
				replyBondrfq(quoteid);
			}
		});
	});
})
