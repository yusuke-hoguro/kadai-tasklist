package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //CSRF対策
        request.setAttribute("_token", request.getSession().getId());

        /*
         * 更新時等に内容を残すためにフォームに現在のタスク内容を表示するように設定
         * messageオブジェクトのデータがnewのJSP表示時に入っていないとエラーになるので初期値として設定
         */
        request.setAttribute("task", new Task());

        //リクエストを転送するためのインスタンス作成
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/new.jsp");
        //リクエストを転送
        rd.forward(request, response);

    }

}
