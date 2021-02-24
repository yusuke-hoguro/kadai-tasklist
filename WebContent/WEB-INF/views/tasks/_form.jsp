<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<label for="content">タスク</label><br>
<input type="text" name="content" value="${task.content}" />
<br><br>

<%-- input type="hidden" にすると非表示データを送ることができる --%>
<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>
