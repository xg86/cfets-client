<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/include_show_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CLIENT---A</title>
<%@ include file="/WEB-INF/pages/include_javascript.jsp"%>
</head>
<body>


	<nav class="navbar navbar-expand-xl navbar-dark bg-dark">
	<form class="form-inline" action="<%=backUrl%>">
		<div class="form-group row">
			<div class="col-md-auto">
				<button class="badge badge-info btn btn-outline-warnning btn-sm">Navbar Navbar Navbar</button>
			</div>
			<div class="col-md-auto">
				<span class="glyphicon glyphicon-user">
					<button id="archive" class="badge badge-light btn btn-outline-success btn-sm">数据归档
				</span>
				</button>
			</div>
			<div class="col-md-auto">
				<button id="endAll" class="badge badge-warning btn btn-outline-success btn-sm">终止所有报价</button>
			</div>
			<div class="col-md-auto">
				<button class="badge badge-secondary btn btn-outline-primary btn-sm dropdown-toggle" type="button" id="dropdownMenu2"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">新增报价</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenu2">
					<button id="addquote-0101" class="badge badge-light btn btn-light btn-sm" type="button">新增债券对话报价</button>
					<button id="addquote-0201" class="badge badge-light btn btn-light btn-sm" type="button">新增质押式回购对话报价</button>
					<div class="dropdown-divider"></div>
					<button id="addquote-0104" class="badge badge-light btn btn-light btn-sm" type="button">新增债券做市报价</button>
					<button id="addquote-0102" class="badge badge-light btn btn-light btn-sm" type="button">新增债券点击成交</button>
					<button id="addquote-0103" class="badge badge-light btn btn-light btn-sm" type="button">新增债券限价报价</button>
					<div class="dropdown-divider"></div>
					<button id="addquote-0105" class="badge badge-light btn btn-light btn-sm" type="button">新增债券请求报价</button>
					<div class="dropdown-divider"></div>
					<button id="addquote-0301" class="badge badge-light btn btn-light btn-sm" type="button">新增利率互换报价</button>
				</div>
			</div>
			<div class="col-md-auto">
				<button class="badge badge-secondary btn btn-outline-primary btn-sm dropdown-toggle" type="button" id="dropdownMenu3"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">授信</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenu3">
					<button id="limitRelationSet" class="badge badge-light btn btn-light btn-sm" type="button">关系授信设置</button>
					<button id="limitUnRelationSet" class="badge badge-light btn btn-light btn-sm" type="button">解除关系设置</button>
					<button id="limitAmountSet" class="badge badge-light btn btn-light btn-sm" type="button">额度授信设置</button>
					<br>
					<button id="limitQuery" class="badge badge-light btn btn-light btn-sm" type="button">授信查询</button>
					<div class="dropdown-divider"></div>
					<button id="ratioSet" class="badge badge-light btn btn-light btn-sm" type="button">授信风险系数设置</button>
					<button id="ratioQuery" class="badge badge-light btn btn-light btn-sm" type="button">授信风险系数查询</button>
					<br>
				</div>
			</div>
			<div class="col-md-auto">
				<button class="badge badge-secondary btn btn-outline-primary btn-sm dropdown-toggle" type="button" id="dropdownMenu4"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">xswap 市场行情订阅</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenu4">
					<button id="irbest" class="badge badge-light btn btn-light btn-sm" type="button">利率互换市场行情最优订阅</button>
					<button id="irdeep" class="badge badge-light btn btn-light btn-sm" type="button">利率互换市场行情深度订阅</button>
					<button id="irsbest" class="badge badge-light btn btn-light btn-sm" type="button">标准利率互换市场行情最优订阅</button>
					<button id="irsdeep" class="badge badge-light btn btn-light btn-sm" type="button">标准利率互换市场行情深度订阅</button>
					<div class="dropdown-divider"></div>
					<button id="unirbest" class="badge badge-light btn btn-light btn-sm" type="button">取消利率互换市场行情最优订阅</button>
					<button id="unirdeep" class="badge badge-light btn btn-light btn-sm" type="button">取消利率互换市场行情深度订阅</button>
					<button id="unirsbest" class="badge badge-light btn btn-light btn-sm" type="button">取消标准利率互换市场行情最优订阅</button>
					<button id="unirsdeep" class="badge badge-light btn btn-light btn-sm" type="button">取消标准利率互换市场行情深度订阅</button>
				</div>
			</div>
		</div>
	</form>
	</nav>

	<table class="table table-hover" id="quoteTab">
		<tr style="text-align: center; font: bold; font-size: small;" class="text-body">
			<td width="14%" class="text-uppercase font-weight-bold">clordidClientId</td>
			<td width="8%" class="text-uppercase font-weight-bold">ordId</td>
			<td width="8%" class="text-uppercase font-weight-bold">quoteType</td>
			<td width="8%" class="text-uppercase font-weight-bold">price</td>
			<td width="8%" class="text-uppercase font-weight-bold">volume</td>
			<td width="10%" class="text-uppercase font-weight-bold">quoteId</td>
			<td width="15%" class="text-uppercase font-weight-bold">transactTime</td>
			<td width="5%" class="text-uppercase font-weight-bold">side</td>
			<td width="8%" class="text-uppercase font-weight-bold">action1</td>
			<td width="8%" class="text-uppercase font-weight-bold">action2</td>
		</tr>

		<c:forEach items="${allQuoteOrders}" var="quoteOrder">
			<tr style="text-align: center; font: light; font-size: small" class="text-muted" id="trquoteOrder-${quoteOrder.quoteId}">
				<td style="vertical-align: middle" id="clordidClientId-${quoteOrder.quoteId}">${quoteOrder.clordidClientId}</td>
				<td style="vertical-align: middle" id="ordId-${quoteOrder.quoteId}">${quoteOrder.ordId}</td>
				<c:if test="${quoteOrder.quoteType == 0101 }">
					<td style="vertical-align: middle" id="quoteType-${quoteOrder.quoteId}">债券对话报价</td>
				</c:if>
				<c:if test="${quoteOrder.quoteType == 0102 }">
					<td style="vertical-align: middle" id="quoteType-${quoteOrder.quoteId}">债券点击成交</td>
				</c:if>
				<c:if test="${quoteOrder.quoteType == 0103 }">
					<td style="vertical-align: middle" id="quoteType-${quoteOrder.quoteId}">债券限价报价</td>
				</c:if>
				<c:if test="${quoteOrder.quoteType == 0104 }">
					<td style="vertical-align: middle" id="quoteType-${quoteOrder.quoteId}">债券做市报价</td>
				</c:if>
				<c:if test="${quoteOrder.quoteType == 0105 }">
					<td style="vertical-align: middle" id="quoteType-${quoteOrder.quoteId}">债券请求报价</td>
				</c:if>
				<c:if test="${quoteOrder.quoteType == 0106 }">
					<td style="vertical-align: middle" id="quoteType-${quoteOrder.quoteId}">请求回复报价</td>
				</c:if>
				<c:if test="${quoteOrder.quoteType == 0201 }">
					<td style="vertical-align: middle" id="quoteType-${quoteOrder.quoteId}">质押式回购</td>
				</c:if>
				<c:if test="${quoteOrder.quoteType == 0301 }">
					<td style="vertical-align: middle" id="quoteType-${quoteOrder.quoteId}">利率互换</td>
				</c:if>

				<td style="vertical-align: middle" width="8%" id="price-${quoteOrder.quoteId}"><fmt:formatNumber type="number"
						value="${quoteOrder.price}" maxFractionDigits="4" /></td>
				<td style="vertical-align: middle" width="8%" id="volume-${quoteOrder.quoteId}"><fmt:formatNumber type="number" pattern="0"
						value="${quoteOrder.volume}" maxFractionDigits="0" /></td>
				<td style="vertical-align: middle" width="10%" id="quoteId-${quoteOrder.quoteId}">${quoteOrder.quoteId}</td>
				<td style="vertical-align: middle" width="15%" id="transactTime-${quoteOrder.quoteId}">${quoteOrder.transactTime}</td>
				<c:if test="${quoteOrder.side == 0000000 || quoteOrder.side == 0123100 || quoteOrder.side == 0500110}">
					<td style="vertical-align: middle" width="5%" id="side-${quoteOrder.quoteId}">买入</td>
				</c:if>
				<c:if test="${quoteOrder.side == 0000001 || quoteOrder.side == 0123101 || quoteOrder.side == 0500111}">
					<td style="vertical-align: middle" width="5%" id="side-${quoteOrder.quoteId}">卖出</td>
				</c:if>


				<!-- 对手报价 而且为请求报价  进行拒绝或回复-->
				<c:if test="${quoteOrder.clrOrdId == null && quoteOrder.quoteType==0105}">
					<td><button id="reply-${quoteOrder.quoteId}-${quoteOrder.quoteType}" class="badge badge-primary btn btn-light btn-sm"
							type="button">回复报价</button></td>
					<td><button id="reject-${quoteOrder.quoteId}-${quoteOrder.quoteType}" class="badge badge-dark btn btn-light btn-sm" type="button">拒绝报价</button></td>
				</c:if>
				<!-- 本方报价  且不是请求报价-->
				<c:if test="${quoteOrder.clrOrdId != null}">
					<c:if test="${quoteOrder.quoteType==0101 || quoteOrder.quoteType==0201 || quoteOrder.quoteType==0106 }">
						<td><button id="update-${quoteOrder.quoteId}-${quoteOrder.quoteType}" class="badge badge-primary btn btn-light btn-sm"
								type="button">修改报价</button></td>
					</c:if>
					<td><button id="cancel-${quoteOrder.quoteId}-${quoteOrder.quoteType}" class="badge badge-danger btn btn-light btn-sm"
							type="button">撤销报价</button></td>
					<c:if test="${quoteOrder.quoteType!=0101 && quoteOrder.quoteType!=0201 &&  quoteOrder.quoteType!=0106 }">
						<td></td>
					</c:if>
				</c:if>
				<!-- 对手报价 -->
				<c:if test="${quoteOrder.clrOrdId == null}">
					<c:if test="${quoteOrder.quoteType==0101 || quoteOrder.quoteType==0201 || quoteOrder.quoteType==0106}">
						<td><button id="confirm-${quoteOrder.quoteId}-${quoteOrder.quoteType}" class="badge badge-success btn btn-light btn-sm"
								type="button">成交报价</button></td>
					</c:if>
					<c:if test="${quoteOrder.quoteType==0101 || quoteOrder.quoteType==0201 }">
						<td><button id="reject-${quoteOrder.quoteId}-${quoteOrder.quoteType}" class="badge badge-dark btn btn-light btn-sm" type="button">拒绝报价</button></td>
					</c:if>
					<c:if test="${quoteOrder.quoteType==0106}">
						<td></td>
					</c:if>
				</c:if>
			</tr>
		</c:forEach>
	</table>

	<%@ include file="/WEB-INF/pages/include_show_alert.jsp"%>

</body>
</html>