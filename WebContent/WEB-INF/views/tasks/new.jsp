<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- appファイルのレイアウトを読み込む --%>
<c:import url="../layout/app.jsp">

    <c:param name="content">
        <h2>タスク新規作成ページ</h2>

        <form method="POST" action="${pageContext.request.contextPath}/create">

            <%-- ここで _form.jspのレイアウトを読み込み合成させる--%>
            <c:import url="_form.jsp" />

        </form>

        <p><a href="${pageContext.request.contextPath}/index">一覧に戻る</a></p>

    </c:param>
</c:import>