<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String startQuote = basePath + "/quote/start.action";
	String startList = basePath + "/quoteOrder/list.action";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>"></base>
    <title>CFETS FRONT PAGE</title>
<!-- <script type="text/javascript" src="jquery/jquery.min.js"></script> -->
<!-- <script type="text/javascript" src="jquery/jquery.validate.min.js"></script> -->
<!-- <script type="text/javascript" src="jquery/additional-methods.min.js"></script> -->
<!-- <script type="text/javascript" src="jquery/Message_zh_CN.js"></script> -->
     <script type="text/javascript" src="bootstrap/js/jquery-slim.min.js"></script>
     <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
     <script type="text/javascript" src="bootstrap/js/popper.min.js"></script>
     <script type="text/javascript" src="bootstrap/js/util.js"></script>
     <script type="text/javascript" src="bootstrap/js/dropdown.js"></script>
     <script type="text/javascript" src="bootstrap/js/collapse.js"></script>
     <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<h1>Hello World</h1>
	<span><a href="<%=startQuote%>">开始报价</a></span>
    <span><a href="<%=startList%>">报价列表</a></span>
	<!--  
    <form>
	  <div class="form-group">
	    <label for="exampleInputEmail1">Email address</label>
	    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
	    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
	  </div>
	  <div class="form-group">
	    <label for="exampleInputPassword1">Password</label>
	    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
	  </div>
	  <div class="form-group form-check">
	    <input type="checkbox" class="form-check-input" id="exampleCheck1">
	    <label class="form-check-label" for="exampleCheck1">Check me out</label>
	  </div>
	  <button type="submit" class="btn btn-primary">Submit</button>
	</form>
	
	<form>
	  <div class="form-group">
	    <label for="exampleFormControlInput1">Email address</label>
	    <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
	  </div>
	  <div class="form-group">
	    <label for="exampleFormControlSelect1">Example select</label>
	    <select class="form-control" id="exampleFormControlSelect1">
	      <option>1</option>
	      <option>2</option>
	      <option>3</option>
	      <option>4</option>
	      <option>5</option>
	    </select>
	  </div>
	  <div class="form-group">
	    <label for="exampleFormControlSelect2">Example multiple select</label>
	    <select multiple class="form-control" id="exampleFormControlSelect2">
	      <option>1</option>
	      <option>2</option>
	      <option>3</option>
	      <option>4</option>
	      <option>5</option>
	    </select>
	  </div>
	  <div class="form-group">
	    <label for="exampleFormControlTextarea1">Example textarea</label>
	    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
	  </div>
	</form>
        -->

	<!-- 	<div class="progress"> -->
	<!-- 		<div class="progress-bar progress-bar-info" role="progressbar" -->
	<!-- 			aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" -->
	<!-- 			style="width: 20%"> -->
	<!-- 			<span class="sr-only">20% Complete</span> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!-- 	<div class="progress"> -->
	<!-- 		<div class="progress-bar progress-bar-success" role="progressbar" -->
	<!-- 			aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" -->
	<!-- 			style="width: 40%"> -->
	<!-- 			<span class="sr-only">40% Complete (success)</span> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!-- 	<div class="progress"> -->
	<!-- 		<div class="progress-bar progress-bar-warning" role="progressbar" -->
	<!-- 			aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" -->
	<!-- 			style="width: 60%"> -->
	<!-- 			<span class="sr-only">60% Complete (warning)</span> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!-- 	<div class="progress"> -->
	<!-- 		<div class="progress-bar progress-bar-danger" role="progressbar" -->
	<!-- 			aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" -->
	<!-- 			style="width: 80%"> -->
	<!-- 			<span class="sr-only">80% Complete (danger)</span> -->
	<!-- 		</div> -->
	<!-- 	</div> -->

      
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <a class="navbar-brand" href="#">Navbar</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarNavDropdown">
		    <ul class="navbar-nav">
			      <li class="nav-item active">
			        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
			      </li>
		        <li>
		           <div class="dropdown">
					  <button class="btn btn-outline-primary btn-sm dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					                新增报价
					  </button>
					  <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
					    <button class="btn btn-light btn-sm" type="button">新增债券对话报价</button>
					    <button class="btn btn-light btn-sm" type="button">新增质押式回购对话报价</button>
					     <div class="dropdown-divider"></div>
					    <button class="btn btn-light btn-sm" type="button">新增债券做市报价</button>
					    <button class="btn btn-light btn-sm" type="button">新增债券点击成交</button>
					    <button class="btn btn-light btn-sm" type="button">新增债券限价报价</button>
					  <div class="dropdown-divider"></div>
					    <button class="btn btn-light btn-sm" type="button">新增债券请求报价</button>
					  </div>
					</div>
		        </li>
		    </ul>
		  </div>
		</nav>

	<table class="table table-hover">
		<tr style="text-align: center; font: bold; font-size: small;" class="text-body">
			<td width="10%">客户端参考编号</td>
			<td width="10%">审批单号</td>
			<td width="10%">报价类型</td>
			<td width="10%">净价</td>
			<td width="10%">数量</td>
			<td width="10%">报价编号</td>
			<td width="10%">业务时间</td>
			<td width="10%">交易方向</td>
			<td colspan="5" width="20%">操作</td>
		</tr>
		<tr style="text-align: center; font: light; font-size: small"
			class="text-muted">
			<td style="vertical-align:middle" width="10%">CFETS_201804130420000603</td>
			<td style="vertical-align:middle" width="8%">10075</td>
			<td style="vertical-align:middle" width="8%">0101</td>
			<td style="vertical-align:middle" width="8%">98</td>
			<td style="vertical-align:middle" width="8%">100000</td>
			<td style="vertical-align:middle" width="10%">201804130420000603</td>
			<td style="vertical-align:middle" width="10%">20180413-17:43:28.967</td>
			<td style="vertical-align:middle" width="10%">0000000</td>
			<td style="vertical-align:middle"><input type="button" id="" name="" value="回复报价"
			class="btn btn-outline-primary btn-sm"></td>
<!-- 			<td style="vertical-align:middle"><input type="button" id="" name="" value="修改报价" -->
<!-- 				class="btn btn-outline-primary btn-sm"></td> -->
<!-- 			<td style="vertical-align:middle"><input type="button" id="" name="" value="撤销报价"  -->
<!-- 				class="btn btn-outline-danger btn-sm"></td> -->
<!-- 			<td style="vertical-align:middle"><input type="button" id="" name="" value="成交报价"  -->
<!-- 				class="btn btn-outline-success btn-sm "></td> -->
			<td style="vertical-align:middle"><input type="button" id="" name="" value="拒绝报价" 
				class="btn btn-outline-dark btn-sm"></td>
		</tr>
		<tr style="text-align: center; font: light; font-size: small"
			class="text-muted">
			<td style="vertical-align:middle" width="10%">CFETS_201804130420000603</td>
			<td style="vertical-align:middle" width="8%">10075</td>
			<td style="vertical-align:middle" width="8%">0101</td>
			<td style="vertical-align:middle" width="8%">98</td>
			<td style="vertical-align:middle" width="8%">100000</td>
			<td style="vertical-align:middle" width="10%">201804130420000603</td>
			<td style="vertical-align:middle" width="10%">20180413-17:43:28.967</td>
			<td style="vertical-align:middle" width="10%">0000000</td>
<!-- 			<td style="vertical-align:middle"><input type="button" id="" name="" value="回复报价" -->
<!-- 			class="btn btn-outline-primary btn-sm"></td> -->
			<td style="vertical-align:middle"><input type="button" id="" name="" value="修改报价"
				class="btn btn-outline-primary btn-sm"></td>
			<td style="vertical-align:middle"><input type="button" id="" name="" value="撤销报价" 
				class="btn btn-outline-danger btn-sm"></td>
<!-- 			<td style="vertical-align:middle"><input type="button" id="" name="" value="成交报价"  -->
<!-- 				class="btn btn-outline-success btn-sm "></td> -->
<!-- 			<td style="vertical-align:middle"><input type="button" id="" name="" value="拒绝报价"  -->
<!-- 				class="btn btn-outline-dark btn-sm"></td> -->
		</tr>
		<!--  
			<c:forEach items="${allMembers }" var="member">
			<tr style="text-align: center;">
				<td id="mid-${member.mid }">${member.mid }</td>
				<td id="name-${member.mid }">${member.name }</td>
				<td id="regdate-${member.mid }"><fmt:formatDate value="${member.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td id="status-${member.mid }">
				  <c:if test="${member.locked==0 }">
				     <span class="text-success">活跃</span>
				  </c:if>
				  <c:if test="${member.locked==1 }">
				     <span class="text-danger">锁定</span>
				  </c:if>
				</td>
				<c:if test="${member.mid!='admin' }">
					<td><input type="button" id="changeBut-${member.mid }" name="changeBut-${member.mid }" value="修改密码" class="btn btn-xs btn-warning"></td>
					<td><a href="?mid=${member.mid }" shape="rect" >编辑</a></td>
					<td><input type="button" id="statusBut-${member.mid }" name="lockBut-${member.mid }" locked = "${member.locked }" value = ${member.locked==0?"锁定用户":"激活用户" } class="btn btn-xs ${member.locked==0?'btn-danger':'btn-success' }"></td>
				</c:if>
				<c:if test="${member.mid=='admin' }">
				    <td></td>
				    <td></td>
				    <td></td>
				</c:if>
			</tr>
			</c:forEach>
			-->
	</table>

</body>
</html>