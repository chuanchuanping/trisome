<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>内容列表页面</title>
		<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
		<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<link href="<%= basePath %>resources/css/all.css" rel="stylesheet" type="text/css" />
		<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
		<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
		<script src="<%= basePath %>resources/js/back/list.js"></script>

	</head>
	<body style="background: #e1e9eb;">
		<form action="<%= basePath %>backed/list" id="mainForm" method="post">
			<input type="hidden" name="pageNum" id="pageNum" value="${page.pageNum}"/>
			<div class="right">
				当前位置：
				<div class="btn-group">
					<button type="button" class="btn btn-default dropdown-toggle btn-xs"
							data-toggle="dropdown">
						内容管理 <span class="caret"></span>
					</button>
					&gt; 内容列表
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">功能</a></li>
						<li><a href="<%= basePath %>user/userInfo">个人中心</a></li>
						<li><a href="#">其他</a></li>
						<li class="divider"></li>
						<li><a href="<%= basePath %>user/logOff">我要退出</a></li>
					</ul>
				</div>
				<%--<div class="current"><a href="javascript:void(0)" style="color:#6E6E6E;">内容管理</a> &gt; 内容列表</div>--%>
				<div class="rightCont">
					<p class="g_title fix">内容列表 <a class="btn03" ><button type="button" class="btn03"  data-toggle="modal" data-target="#myModal">新 增</button></a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn03" id ="click1" >删 除</a></p>

					<!-- Modal -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h4 class="modal-title" id="myModalLabel">新增内容</h4>
								</div>
								<div class="modal-body">
									<form class="form-horizontal">
										<div class="form-group">
											<label class="col-sm-2 control-label">指令名称</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" placeholder="Text input" name="command1" value="">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">描述</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" placeholder="Text input" name="description1" value="">
											</div>
										</div>
										<div class="form-group">
											<label  class="col-sm-2 control-label">操作</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" placeholder="Text input" name="content1" value="">
											</div>
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<button type="button" class="btn btn-primary" id="saveChanges">提交</button>
								</div>
							</div>
						</div>
					</div>

					<div class="modal fade" id="myModalTwo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h4 class="modal-title" id="myModalLabel">修改 内容 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a id="myModalLabel2">显示内容</a></h4>
								</div>
								<div class="modal-body">
									<form class="form-horizontal">
										<div class="form-group">
											<label class="col-sm-2 control-label">指令名称</label>
											<div class="col-sm-10">
												<input type="text" id="command2" class="form-control" placeholder="Text input" name="command2" value="">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">描述</label>
											<div class="col-sm-10">
												<input type="text" id="description2" class="form-control" placeholder="Text input" name="description2" value="">
											</div>
										</div>
										<div class="form-group">
											<label  class="col-sm-2 control-label">操作</label>
											<div class="col-sm-10">
												<input type="text" id="content2" class="form-control" placeholder="Text input" name="content2" value="">
											</div>
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<button type="button" class="btn btn-primary" id="Changes">确认修改</button>
								</div>
							</div>
						</div>
					</div>


					<table class="tab1">
						<tbody>
							<tr>
								<td width="90" align="right">指令名称：</td>
								<td>
									<input name="command" type="text" class="allInput" value="${command}"/>
								</td>
								<td width="90" align="right">描述：</td>
								<td>
									<input name="description" type="text" class="allInput" value="${description}"/>
								</td>
	                            <td width="85" align="right"><input type="submit" class="tabSub" value="查 询" /></td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<td width="85" align="right"><input type="button" class="tabSub" value="导出Excel" onclick='location.href="${basePath}downloadExcel?command=${command}&description=${description}"'></td>
								<td width="120" align="right"><input type="button" class="tabSub" value="前往微信公众号" onclick='location.href="../talk"'></td>
								<td width="120" align="right"><input type="button" class="tabSub" value="前往商城" onclick='location.href="../shop/list"'></td>
							</tr>
						</tbody>
					</table>
					<div class="zixun fix">
						<table class="tab2" width="100%">
							<tbody>
								<tr>
								    <th><input type="hidden" id="all" checked="checked" onclick="changeStatus()"/></th>
								    <th>序号</th>
								    <th>指令名称</th>
								    <th>描述</th>
								    <th>操作</th>
								</tr>
								<c:forEach items="${messageList}" var="message" varStatus="status">
									<tr  <c:if test="${status.index % 2 != 0}">style='background-color:#ECF6EE;'</c:if>>
										<td><input type="checkbox"  name="id" value="${message.id}"/></td>
										<td>${status.index + 1}</td>
										<td>${message.command}</td>
										<td>${message.description}</td>
										<td>
											<a id="motify1" > <button type="button" data-toggle="modal" data-target="#myModalTwo" >修改</button></a>&nbsp;&nbsp;&nbsp;
											<a href="${basePath}deleteOne?id=${message.id}&command=${command}&description=${description}&currentPage=${page.pageNum}" class="deleteOne">删除</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<%--<div class='page fix'>--%>
							<%--共 <b>${page.totalNumber}</b> 条--%>
							<%--<c:if test="${page.currentPage != 1}">--%>
								<%--<a href="javascript:changeCurrentPage('1')" class='first'>首页</a>--%>
								<%--<a href="javascript:changeCurrentPage('${page.currentPage-1}')" class='pre'>上一页</a>--%>
							<%--</c:if>--%>
							<%--当前第<span>${page.currentPage}/${page.totalPage}</span>页--%>
							<%--<c:if test="${page.currentPage != page.totalPage}">--%>
								<%--<a href="javascript:changeCurrentPage('${page.currentPage+1}')" class='next'>下一页</a>--%>
								<%--<a href="javascript:changeCurrentPage('${page.totalPage}')" class='last'>末页</a>--%>
							<%--</c:if>--%>
							<%--跳至&nbsp;<input id="currentPageText" type='text' value='${page.currentPage}' class='allInput w28' />&nbsp;页&nbsp;--%>
							<%--<a href="javascript:changeCurrentPage($('#currentPageText').val())" class='go'>GO</a>--%>
						<%--</div>--%>
						<div class='page fix'>
							共 <b>${page.total}</b> 条
							<c:if test="${page.pageNum != 1}">
								<a href="javascript:changeCurrentPage('${page.firstPage}')" class='first'>首页</a>
								<a href="javascript:changeCurrentPage('${page.prePage}')" class='pre'>上一页</a>
							</c:if>
							当前第<span>${page.pageNum}/${page.pages}</span>页
							<c:if test="${page.pageNum != page.pages}">
								<a href="javascript:changeCurrentPage('${page.nextPage}')" class='next'>下一页</a>
								<a href="javascript:changeCurrentPage('${page.lastPage}')" class='last'>末页</a>
							</c:if>
							跳至&nbsp;<input id="currentPageText" type='text' value='${page.pageNum}' class='allInput w28' />&nbsp;页&nbsp;
							<a href="javascript:changeCurrentPage($('#currentPageText').val())" class='go'>GO</a>
						</div>

						<%--<div class='page fix'>--%>
							<%--<p>一共${page.pages}页</p>--%>
							<%--<a href="PageHelper?page=${page.firstPage}">首页</a>--%>
							<%--<a href="PageHelper?page=${page.nextPage}">下一页</a>--%>
							<%--<a href="PageHelper?page=${page.prePage}">上一页</a>--%>
							<%--<a href="PageHelper?page=${page.lastPage}">末页</a>--%>
						<%--</div>--%>
					</div>
				</div>
			</div>
	    </form>
	</body>
</html>