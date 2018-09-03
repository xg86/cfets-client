<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String addBondDlg = basePath + "/bond/dlg/add.action";
	String updateBondDlg = basePath + "/bond/dlg/update.action";
	String cancelBondDlg = basePath + "/bond/dlg/cancel.action";
	String confirmBondDlg = basePath + "/bond/dlg/confirm.action";
	String rejectBondDlg = basePath + "/bond/dlg/reject.action";
	
	String addPledgeBondDlg = basePath + "/pledge/dlg/add.action";
	String updatePlegdeBondDlg = basePath + "/pledge/dlg/update.action";
	String cancelPledgeBondDlg = basePath + "/pledge/dlg/cancel.action";
	String confirmPlegdeBondDlg = basePath + "/pledge/dlg/confirm.action";
	String rejectPledgeBondDlg = basePath + "/pledge/dlg/reject.action";
	
	String addMarketMakeDlg = basePath + "/bond/marketmake/add.action";
	String cancelMarketMakeDlg = basePath + "/bond/marketmake/cancel.action";
	
	String addPricelimitDlg = basePath + "/bond/pricelimit/add.action";
	String cancelPricelimitDlg = basePath + "/bond/pricelimit/cancel.action";
	
	String addClickDealDlg = basePath + "/bond/clickdeal/add.action";
	String cancelClickDealDlg = basePath + "/bond/clickdeal/cancel.action";
	
	String addBondRfq = basePath + "/bond/rfq/add.action";
	String cancelBondRfq = basePath + "/bond/rfq/cancel.action";
	String rejectBondRfq = basePath + "/bond/rfq/reject.action";
	
	String addBondRes = basePath + "/bond/res/add.action";
	String updateBondRes = basePath + "/bond/res/update.action";
	String cancelBondRes = basePath + "/bond/res/cancel.action";
	String confirmBondRes = basePath + "/bond/res/confirm.action";
	
	String backUrl = basePath + "index.jsp";
	
	String messageSubmit = basePath + "/bond/recv/add.action";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
/*    div {display:inline;} */
   	.one,.two{
			width: 100%;
			height: 350px;
			border:2px solid #F2F2F2;
			float: left;
			box-sizing: border-box;
			}
</style>
<script type="text/javascript">
     var xmlHttpRequest;
     function create(){
    	 xmlHttpRequest = new XMLHttpRequest();
     }
     window.onload = function(){
    	 loadMessage();
     }
     
     function setOrdid(){
    	 var value = document.getElementById("ordid").value;
    	 if(value != ""){
    		 document.getElementById("")
    	 }
     }
     
     function loadMessage(){
    	 create();
//     	 console.log("============create Ok=============");
//     	 xmlHttpRequest.open("post","asynController/asyn.action");
    	 xmlHttpRequest.open("post","<%=basePath%>/asynController/asyn.action");
		xmlHttpRequest.send(null);
		xmlHttpRequest.onreadystatechange = function() {
			//     		 console.log("readyState = "+xmlHttpRequest.readyState);
			//     		 console.log("status = "+xmlHttpRequest.status);
			if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
				var str = xmlHttpRequest.responseText;
				//     			 console.log(str);
				if (str == "false") {
					//     				 console.log("错了！");
					//     				 document.getElementById("showDiv").innerHTML="";
					//     				 alert(xmlHttpRequest.responseText);
				} else {
					//     				 console.log(xmlHttpRequest.responseText);
					//     				 document.getElementById("showDiv").innerHTML += "<p>"+xmlHttpRequest.responseText+"</p>";
					alert(str);
				}
				window.setTimeout(loadMessage(), 5000);
			}
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<span><a href="<%=backUrl%>">返回界面</a></span>
	<br>
	<div id="leftDiv" align="middle" style="width: 300px; height: 580px;" class="one">
	    <form action="<%=addBondDlg%>">
	        <input type="submit" value="新增债券对话报价" style="width: 250px; height: 30px;">
	    </form>
		<form action="<%=updateBondDlg%>">
		  <font size="2" color="#FF9900">报价编号:</font>	<input type="text" id="ordid" name="ordid" value="${BondDialogueQuoteOrder.ordId}"> 
			<input type="submit" value="修改债券对话报价" style="width: 250px; height: 30px;">
		</form>
		<form action="<%=cancelBondDlg%>">
			<font size="2" color="#FF9900">报价编号:</font>	<input type="text" id="ordid" name="ordid"
				value="${BondDialogueQuoteOrder.ordId}"> <input type="submit"
				value="撤销债券对话报价" style="width: 250px; height: 30px;">
		</form>
		<form action="<%=confirmBondDlg%>">
			<font size="2" color="#FF9900">报价编号:</font>	<input type="text" id="ordid" name="ordid"> 
			<input type="submit" value="成交债券对话报价" style="width: 250px; height: 30px;">
		</form>
		<form action="<%=rejectBondDlg%>">
			<font size="2" color="#FF9900">报价编号:</font>	<input type="text" id="ordid" name="ordid"
				> <input type="submit"
				value="拒绝债券对话报价" style="width: 250px; height: 30px;">
		</form>
	   <br>	
		 <form action="<%=addPledgeBondDlg%>">
	        <input type="submit" value="新增质押式回购对话报价" style="width: 250px; height: 30px;">
	    </form>
		<form action="<%=updatePlegdeBondDlg%>">
			<font size="2" color="#FF9900">报价编号:</font>	<input type="text" id="ordid" name="ordid"
				value="${PledgeRepoDialogueQuoteOrder.ordId}"> <input type="submit"
				value="修改质押式回购对话报价" style="width: 250px; height: 30px;">
		</form>
		<form action="<%=cancelPledgeBondDlg%>">
			<font size="2" color="#FF9900">报价编号:</font>	<input type="text" id="ordid" name="ordid"
				value="${PledgeRepoDialogueQuoteOrder.ordId}"> <input type="submit"
				value="撤销质押式回购对话报价" style="width: 250px; height: 30px;">
		</form>
		<form action="<%=confirmPlegdeBondDlg%>">
			<font size="2" color="#FF9900">报价编号:</font>	<input type="text" id="ordid" name="ordid"
				value="${PledgeRepoDialogueQuoteOrder.ordId}"> <input type="submit"
				value="成交质押式回购对话报价" style="width: 250px; height: 30px;">
		</form>
		<form action="<%=rejectPledgeBondDlg%>">
			<font size="2" color="#FF9900">报价编号:</font>	<input type="text" id="ordid" name="ordid"
				value="${PledgeRepoDialogueQuoteOrder.ordId}"> <input type="submit"
				value="拒绝质押式回购对话报价" style="width: 250px; height: 30px;">
		</form>
		<br>
	</div>
	
	
	<div id="middleleftDiv" align="middle" style="width: 300px; height: 580px;" class="one">
	    <form action="<%=addMarketMakeDlg%>">
	        <input type="submit" value="新增债券做市报价" style="width: 250px; height: 30px;">
	    </form>    
		<form action="<%=cancelMarketMakeDlg%>">
			<font size="2" color="#FF9900">报价编号:</font>	<input type="text" id="ordid" name="ordid"
				value="${BondMarketMakeQuoteOrder.ordId}"> <input type="submit"
				value="撤销债券做市报价" style="width: 250px; height: 30px;">
		</form>
		
		<br><br>
	   	
		<form action="<%=addClickDealDlg%>">
	        <input type="submit" value="新增债券点击成交" style="width: 250px; height: 30px;">
	    </form>
		<form action="<%=cancelClickDealDlg%>">
			<font size="2" color="#FF9900">报价编号:</font>	<input type="text" id="ordid" name="ordid"
				value="${BondClickDealQuoteOrder.ordId}"> <input type="submit"
				value="撤销债券点击成交" style="width: 250px; height: 30px;">
		</form>
		
		<br><br>
	   	
		<form action="<%=addPricelimitDlg%>">
	        <input type="submit" value="新增债券限价报价" style="width: 250px; height: 30px;">
	    </form>
		<form action="<%=cancelPricelimitDlg%>">
			<font size="2" color="#FF9900">报价编号:</font>	<input type="text" id="ordid" name="ordid"
				value="${BondPriceLimitQuoteOrder.ordId}"> <input type="submit"
				value="撤销债券限价报价" style="width: 250px; height: 30px;">
		</form>
		<br>
	</div>
	
	
	<div id="middlerifhtDiv" align="middle" style="width: 300px; height: 580px;" class="one">
	    <form action="<%=addBondRfq%>">
	        <input type="submit" value="新增请求报价" style="width: 250px; height: 30px;">
	    </form>
		<form action="<%=cancelBondRfq%>">
			<font size="2" color="#FF9900">报价编号:</font>	<input type="text" id="ordid" name="ordid"
				value="${BondRfqReqQuoteOrder.ordId}"> <input type="submit"
				value="撤销请求报价" style="width: 250px; height: 30px;">
		</form>
		<form action="<%=rejectBondRfq%>">
			<font size="2" color="#FF9900">报价编号:</font>	<input type="text" id="ordid" name="ordid"> 
			<input type="submit" value="拒绝请求报价" style="width: 250px; height: 30px;">
		</form>
		<br>
		 <form action="<%=addBondRes%>">
	        <font size="2" color="#FF9900">原请求报价编号:</font>	
	        <input type="text" id="quoteReqID" name="quoteReqID"><br> 
	        <font size="2" color="#FF9900">有效时间:</font>	
	        <input type="text" id="waitUntilTime" name="waitUntilTime"><br> 
	        <font size="2" color="#FF9900">债券代码:</font>
	        <input type="text" id="iCode" name="iCode"><br> 
	        <font size="2" color="#FF9900">交易方向:</font>
	        <input type="text" id="side" name="side"><br> 
	        <input type="submit" value="新增请求回复报价" style="width: 250px; height: 30px;">
	    </form>
		<form action="<%=updateBondRes%>">
			&nbsp;&nbsp;&nbsp;<font size="2" color="#FF9900">报价编号:</font>	
			<input type="text" id="ordid" name="ordid" value="${BondRfqReplyQuoteOrder.ordId}"> <br>
			<font size="2" color="#FF9900">原请求编号:</font>
			<input type="text" id="quoteReqID" name="quoteReqID" value="${BondRfqReplyQuoteOrder.quoteReqID}"><br> 
			 <font size="2" color="#FF9900">有效时间:</font>	
	        <input type="text" id="waitUntilTime" name="waitUntilTime" value="${BondRfqReplyQuoteOrder.waitUntilTime}"><br> 
	        <font size="2" color="#FF9900">债券代码:</font>
	        <input type="text" id="iCode" name="iCode"><br> 
	         <font size="2" color="#FF9900">交易方向:</font>
	        <input type="text" id="side" name="side" value="${side}"><br> 
			<input type="submit" value="修改请求回复报价" style="width: 250px; height: 30px;">
		</form>
		<form action="<%=cancelBondRes%>">
			<font size="2" color="#FF9900">报价编号:</font>	<input type="text" id="ordid" name="ordid"
				value="${BondRfqReplyQuoteOrder.ordId}"> <input type="submit"
				value="撤销请求回复报价" style="width: 250px; height: 30px;">
		</form>	
			<form action="<%=confirmBondRes%>">
			<font size="2" color="#FF9900">报价编号:</font>	<input type="text" id="ordid" name="ordid"> 
			<input type="submit" value="成交请求回复报价" style="width: 250px; height: 30px;">
		</form>	
	</div>
	
	
	
	<div id="rightDiv" align="middle" style="width: 400px; height: 580px;" class="two">
		<form action="<%=messageSubmit%>" method="post">
		             输入报文：<br>
	        <textarea rows="25" cols="40" id="message" name="message"></textarea>
	        <button type="submit" name="sub" id="sub">提交</button>
	    </form>
	</div>
	<div id="showDiv"></div>
</body>
</html>