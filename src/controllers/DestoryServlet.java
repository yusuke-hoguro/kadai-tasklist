package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class DestoryServlet
 */
@WebServlet("/destroy")
public class DestoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestoryServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //CSRF対策のトークン取得
        String _token = request.getParameter("_token");
        //トークンを確認して問題なければ実行
        if(_token != null && _token.equals(request.getSession().getId())){

            EntityManager em = DBUtil.createEntityManager();

            //セッションスコープからメッセージIDを取得し、そのデータをデータベースから取得する
            Task tasktable = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));

            //データベースの削除処理
            em.getTransaction().begin();
            em.remove(tasktable);
            em.getTransaction().commit();

            em.close();

            //セッションスコープの不要になったデータを削除 ⇒ Indexサーブレットで一括削除
            //request.getSession().removeAttribute("task_id");

            //indexページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/index");

        }

    }



}
