<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String backUrl = basePath + "index.jsp";

	String messageSubmit = basePath + "/bond/recv/add.action";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CLIENT---A</title>
<script type="text/javascript" src="js/jquery_3.1.1_jquery.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/sockjs-client_1.1.1_sockjs.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/stomp.js_2.3.3_stomp.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/stomp.js" charset="UTF-8"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="bootstrap/js/popper.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="bootstrap/js/util.js" charset="UTF-8"></script>
<script type="text/javascript" src="bootstrap/js/dropdown.js" charset="UTF-8"></script>
<script type="text/javascript" src="bootstrap/js/collapse.js" charset="UTF-8"></script>
<!--      <script src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.min.js"></script> -->
<!-- 	 <script src="http://cdn.bootcss.com/stomp.js/2.3.3/stomp.js"></script> -->
<!-- 	 <script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>   -->
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css" charset="UTF-8"/>
<script type="text/javascript" src="js/quote.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/cancel.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/add.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/update.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/confirm.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/reject.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/reply.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/alert.js" charset="UTF-8"></script>

</head>
<body>
<table class="table table-hover" id="quoteTab">
		<tr style="text-align: center; font: bold; font-size: small;" class="text-body">
			<td width="10%">clordidClientId</td>
			<td width="8%">ordId</td>
			<td width="8%">quoteType</td>
			<td width="8%">price</td>
			<td width="8%">volume</td>
			<td width="10%">quoteId</td>
			<td width="15%">transactTime</td>
			<td width="5%">side</td>
			<td colspan="5" width="20%">action</td>
		</tr>

		<c:forEach items="${allQuoteOrders}" var="quoteOrder">
			<tr style="text-align: center; font: light; font-size: small" class="text-muted" id="trquoteOrder-${quoteOrder.quoteId}">
				<td style="vertical-align: middle" width="10%" id="clordidClientId-${quoteOrder.quoteId}">${quoteOrder.clordidClientId}</td>
				<td style="vertical-align: middle" width="8%" id="ordId-${quoteOrder.quoteId}">${quoteOrder.ordId}</td>
				<c:if test="${quoteOrder.quoteType == 0101 }">
					<td style="vertical-align: middle" width="8%" id="quoteType-${quoteOrder.quoteId}">债券对话报价</td>
				</c:if>
				<c:if test="${quoteOrder.quoteType == 0102 }">
					<td style="vertical-align: middle" width="8%" id="quoteType-${quoteOrder.quoteId}">债券点击成交</td>
				</c:if>
				<c:if test="${quoteOrder.quoteType == 0103 }">
					<td style="vertical-align: middle" width="8%" id="quoteType-${quoteOrder.quoteId}">债券限价报价</td>
				</c:if>
				<c:if test="${quoteOrder.quoteType == 0104 }">
					<td style="vertical-align: middle" width="8%" id="quoteType-${quoteOrder.quoteId}">债券做市报价</td>
				</c:if>
				<c:if test="${quoteOrder.quoteType == 0105 }">
					<td style="vertical-align: middle" width="8%" id="quoteType-${quoteOrder.quoteId}">债券请求报价</td>
				</c:if>
				<c:if test="${quoteOrder.quoteType == 0106 }">
					<td style="vertical-align: middle" width="8%" id="quoteType-${quoteOrder.quoteId}">请求回复报价</td>
				</c:if>
				<c:if test="${quoteOrder.quoteType == 0201 }">
					<td style="vertical-align: middle" width="8%" id="quoteType-${quoteOrder.quoteId}">质押式回购</td>
				</c:if>
				<c:if test="${quoteOrder.quoteType == 0301 }">
					<td style="vertical-align: middle" width="8%" id="quoteType-${quoteOrder.quoteId}">利率互换</td>
				</c:if>

				<td style="vertical-align: middle" width="8%" id="price-${quoteOrder.quoteId}"><fmt:formatNumber type="number"
						value="${quoteOrder.price}" maxFractionDigits="4" /></td>
				<td style="vertical-align: middle" width="8%" id="volume-${quoteOrder.quoteId}"><fmt:formatNumber type="number"
						pattern="0" value="${quoteOrder.volume}" maxFractionDigits="0" /></td>
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
					<td style="vertical-align: middle"><input type="button" id="reply-${quoteOrder.quoteId}-${quoteOrder.quoteType}"
						name="reply-${quoteOrder.quoteId}-${quoteOrder.quoteType}" value="回复报价" class="btn btn-outline-primary btn-sm"></td>
					<td style="vertical-align: middle"><input type="button" id="reject-${quoteOrder.quoteId}-${quoteOrder.quoteType}"
						name="reject-${quoteOrder.quoteId}-${quoteOrder.quoteType}" value="拒绝报价" class="btn btn-outline-dark btn-sm"></td>
				</c:if>
				<!-- 本方报价  且不是请求报价-->
				<c:if test="${quoteOrder.clrOrdId != null}">
					<c:if test="${quoteOrder.quoteType==0101 || quoteOrder.quoteType==0201 || quoteOrder.quoteType==0106 }">
						<td style="vertical-align: middle"><input type="button" id="update-${quoteOrder.quoteId}-${quoteOrder.quoteType}"
							name="update-${quoteOrder.quoteId}-${quoteOrder.quoteType}" value="修改报价" class="btn btn-outline-primary btn-sm"></td>
					</c:if>
					<td style="vertical-align: middle"><input type="button" id="cancel-${quoteOrder.quoteId}-${quoteOrder.quoteType}"
						name="cancel-${quoteOrder.quoteId}-${quoteOrder.quoteType}" value="撤销报价" class="btn btn-outline-danger btn-sm"></td>
				</c:if>
				<!-- 对手报价 -->
				<c:if test="${quoteOrder.clrOrdId == null}">
					<c:if test="${quoteOrder.quoteType==0101 || quoteOrder.quoteType==0201 || quoteOrder.quoteType==0106}">
						<td style="vertical-align: middle"><input type="button" id="confirm-${quoteOrder.quoteId}-${quoteOrder.quoteType}"
							name="confirm-${quoteOrder.quoteId}-${quoteOrder.quoteType}" value="成交报价" class="btn btn-outline-success btn-sm "></td>
					</c:if>
					<c:if test="${quoteOrder.quoteType==0101 || quoteOrder.quoteType==0201 }">
						<td style="vertical-align: middle"><input type="button" id="reject-${quoteOrder.quoteId}-${quoteOrder.quoteType}"
							name="reject-${quoteOrder.quoteId}-${quoteOrder.quoteType}" value="拒绝报价" class="btn btn-outline-dark btn-sm"></td>
					</c:if>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</body>
</html>