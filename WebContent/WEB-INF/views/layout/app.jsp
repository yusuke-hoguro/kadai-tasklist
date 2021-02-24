<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>タスク管理</title>
    </head>
    <body>
        <div id = "wapper">
            <div id = "header">
                <h1>タスク管理 アプリケーション</h1>
            </div>
            <div id = "content">
                <%--HTMLのタグが入る可能性があるのでJSTL使用しない --%>
                ${param.content}
            </div>
            <div id = "footer">
                by Yusuke Hoguro.
            </div>
        </div>
    </body>
</html>
