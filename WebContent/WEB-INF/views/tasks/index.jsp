<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">

        <%-- flushメッセージ用 --%>
        <c:if test = "${flush != null }">
            <div id="flush_success">
                <c:out value="${flush}" />
            </div>
        </c:if>
        <h2>タスク一覧</h2>
        <ul>
            <c:forEach var = "task" items="${tasks}">
                <li>
                    <a href = "${pageContext.request.contextPath }/show?id=${task.id}">
                        <c:out value="${task.id}"></c:out>
                    </a>
                    : <c:out value="${task.content}"></c:out>
                </li>
            </c:forEach>
        </ul>

        <%-- ページネーション機能追加 --%>
        <div id="pagination">
            (全 ${tasksCount} 件)<br>
            <c:forEach var="i" begin="1" end="${((tasksCount - 1) / displayCount) + 1 }" step = "1">
                <c:choose>
                    <%-- &nbsp; 無視・改行されない空白 --%>
                    <c:when test="${i == indexPage}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/index?page=${i}"><c:out value="${i}"></c:out></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p><a href = "${pageContext.request.contextPath}/new">新規タスクの追加</a></p>
    </c:param>

</c:import>