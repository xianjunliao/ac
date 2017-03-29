<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link rel="stylesheet" href="tree_files/font-awesome.min.css">

<!-- page specific plugin styles -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Open+Sans:400,300"> -->


<!-- ace styles -->
<link rel="stylesheet" href="tree_files/ace.min.css">
<script type="text/javascript">
	
</script>
</head>
<body>
	<input type="hidden" id="menu1" value="${menuJson}" />
	<input type="hidden" id="menu2" value="${menu1Json}" />
	<input type="hidden" id="menu3" value="${menu2Json}" />
	<table border="1" width="100%" height="100%">
		<tr height="5%">
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td width="25%">
				<div class="row">
					<div class="col-sm-6">
						<div class="widget-box">
							<div class="widget-header header-color-green2">
								<h3 class="lighter smaller">
									<b>记忆树</b>
								</h3>
							</div>

							<div class="widget-body">
								<div class="widget-main padding-8">
									<div id="tree2" class="tree tree-unselectable">
										<div class="tree-folder" style="display: none;">
											<div class="tree-folder-header">
												<i class="icon-folder-close"></i>
												<div class="tree-folder-name"></div>
												<div class="tree-folder-code"></div>
											</div>
											<div class="tree-folder-content"></div>
											<div class="tree-loader" style="display: none;"></div>
										</div>
										<div class="tree-item" style="display: none;">
											<div class="tree-item-name"></div>
											<div class="tree-item-code"></div>
										</div>
										<c:forEach items="${menu}" var="m">
											<div class="tree-folder" style="display: block;">
												<div class="tree-folder-header">
													<i class="icon-folder-close "></i>
													<div class="tree-folder-code">
														${m.code}</div>
													<div class="tree-folder-name">${m.name}</div>
												</div>
												<div class="tree-folder-content"></div>
												<div class="tree-loader" style="display: none;">
													<div class="tree-loading">
														<i class="icon-refresh icon-spin "></i>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</td>
			<td colspan="2" id="baidu"></td>
		</tr>
		<tr height="5%">
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	<script src="tree_files/bootstrap.min.js"></script>
	<script src="tree_files/fuelux.tree-sampledata.js"></script>
	<script src="tree_files/fuelux.tree.min.js"></script>
	<script src="tree_files/ace-elements.min.js"></script>
	<script src="tree_files/ace.min.js"></script>
	<script type="text/javascript">
		jQuery(function($) {
			$('#tree2')
					.ace_tree(
							{
								dataSource : treeDataSource2,
								loadingHTML : '<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
								'open-icon' : 'icon-folder-open',
								'close-icon' : 'icon-folder-close',
								'selectable' : false,
								'selected-icon' : null,
								'unselected-icon' : null
							});

		});
	</script>
</body>
</html>