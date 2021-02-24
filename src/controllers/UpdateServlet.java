package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //edit.jspから送られてきたトークンを取得
        String _token = request.getParameter("_token");

        //不正なアクセスでないかチェック
        if(_token != null && _token.equals(request.getSession().getId())){

            //EntityManagerのインスタンス作成
            EntityManager em = DBUtil.createEntityManager();

            //セッションスコープからIDを取得し、該当のIDのメッセージを取得
            Task taskTable = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));

            //入力されたタスク（コンテンツをセットする）
            String content = request.getParameter("content");
            taskTable.setContent(content);

            //現在時刻の取得
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            taskTable.setUpdated_at(currentTime);

            //データベースに保存する
            em.getTransaction().begin();
            em.getTransaction().commit();

            //クローズ処理
            em.close();

            //不要になったセッションスコープを削除する
            request.getSession().removeAttribute("task_id");

            //indexにリダイレクト
            response.sendRedirect(request.getContextPath() + "/index");
        }

    }

}
