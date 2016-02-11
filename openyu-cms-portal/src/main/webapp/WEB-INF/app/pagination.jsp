<%@ include file="/WEB-INF/app/content.jspf"%>

<!-- pageNav -->
<table width="85%" border="0" align="center">
	<s:hidden id="inquiry.pagination.pageIndex" name="inquiry.pagination.pageIndex" />
	<tr>

		<%-- #navIndex --%>
		<td width="45%">
			<%-- fisrt --%> 
			<s:if test="inquiry.pagination.navIndex>0">
				<a href="#"
					onclick="paginationSubmit('searchForm',0,'${param.action}')">
					<img src="${pageContext.request.contextPath}/app/arroow_left1.png"
					width="24" height="24" /></a>
			</s:if>
			
			<s:else>
			    <%--
				<img src="${pageContext.request.contextPath}/app/arroow_left1.png"
					width="24" height="24" />
				--%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</s:else>
			

			<s:iterator begin="inquiry.pagination.navIndex" end="inquiry.pagination.navIndex+9"
				status="pageCountStatus">

				<%-- ... previous --%>
				<s:if test="%{inquiry.pagination.navIndex>0 && #pageCountStatus.index==0}">
					<a href="#"
						onclick="paginationSubmit('searchForm',${(inquiry.pagination.navIndex-1)*10},'${param.action}')">
						<img src="${pageContext.request.contextPath}/app/arroow_left.png"
						width="24" height="24" /></a>
				</s:if>	

				<%-- [1] | 2 | ... --%>
				<%-- ... | [11] | 12 | ... --%>
				<%-- ... | [19] | 20 --%>
				<s:if
					test="%{inquiry.pagination.pageIndex+1==(inquiry.pagination.navIndex*10+#pageCountStatus.index+1)}">
					<span style="color: red; font-weight: bold;">[
						${inquiry.pagination.navIndex*10+(pageCountStatus.index+1)} ]</span>
				</s:if>
				<s:else>
					<s:if
						test="%{inquiry.pagination.navIndex*10+(#pageCountStatus.index) < inquiry.pagination.pageCount}">
						<a href="#"
							onclick="paginationSubmit('searchForm',${inquiry.pagination.navIndex*10+pageCountStatus.index},'${param.action}')">${inquiry.pagination.navIndex*10+(pageCountStatus.index+1)}</a>
					</s:if>
				</s:else>

				<%-- next ... --%>
				<s:if
					test="%{inquiry.pagination.navIndex < inquiry.pagination.navCount && #pageCountStatus.index==9}">
						<a href="#"
						onclick="paginationSubmit('searchForm',${(inquiry.pagination.navIndex+1)*10},'${param.action}')">
						<img
							src="${pageContext.request.contextPath}/app/arroow_right.png"
							width="24" height="24" /></a>
				</s:if>

				<s:if test="!#pageCountStatus.last">
					<s:if
						test="%{inquiry.pagination.navIndex*10+(#pageCountStatus.index+1) < inquiry.pagination.pageCount}">
						&nbsp;|
					</s:if>
				</s:if>
			</s:iterator>
				
			<%-- last --%>	
			<s:if test="%{inquiry.pagination.navIndex < inquiry.pagination.navCount}">
				<a href="#"
					onclick="paginationSubmit('searchForm',${inquiry.pagination.pageCount-1},'${param.action}')">
					<img src="${pageContext.request.contextPath}/app/arroow_right1.png"
					width="24" height="24" />
				</a>
			</s:if>
			<s:else>
			    <%-- 
				<img src="${pageContext.request.contextPath}/app/arroow_right1.png"
					width="24" height="24" />
				--%>	
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
			</s:else>				

		</td>
		
		<!-- pageSize -->
		<td width="40%">
			<s:text name="inquiry.pagination.pageSize" />
				<s:select
					list="inquiry.pagination.pageSizes" name="inquiry.pagination.pageSize"
					onchange="paginationSubmit('searchForm',0)">
				</s:select>
			<s:text name="inquiry.pagination.unit" />&nbsp;,
			
			<s:text name="inquiry.pagination.rowCount">
				<s:param name="value" value="inquiry.pagination.rowCount" />
			</s:text>&nbsp;,
			
			<s:if test="inquiry.pagination.pageCount>0">
				<s:text name="inquiry.pagination.pageCount">
					<s:param name="value" value="inquiry.pagination.pageIndex+1" />
					<s:param name="value" value="inquiry.pagination.pageCount" />
				</s:text>
			</s:if>
			<s:else>
				<s:text name="inquiry.pagination.pageCount">
					<s:param name="value" value="0" />
					<s:param name="value" value="inquiry.pagination.pageCount" />
				</s:text>
			</s:else>&nbsp;,
			<s:text name="inquiry.pagination.processTime">
				<s:param name="value" value="inquiry.pagination.processTime" />
			</s:text></td>
	</tr>
</table>

<script language="JavaScript">
/*
 * 分頁重置
 */
function paginationReset(formName) {
	var form = document.forms[formName];
	var element = document.getElementById("inquiry.pagination.pageIndex");
	if ( element != null) {
		element.value = 0;
	}
	form.reset();
}

/*
 * 分頁提交
 */
function paginationSubmit(formName, pageIndex, action) {
	var form = document.forms[formName];
	var element = document.getElementById("inquiry.pagination.pageIndex");
	if (element != null) {
		element.value = pageIndex;
	}
	//alert("form.action: "+form.action);
	if (typeof(action)!="undefined"){
		form.action=action;
	}
	//alert(form.name+" "+form.action);
	form.submit();
}
;
</script>