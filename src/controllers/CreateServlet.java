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
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //new.jspから送られてきたトークンを取得
        String _token = request.getParameter("_token");

        //不正なアクセスでないかチェック
        if(_token != null && _token.equals(request.getSession().getId())){

            //EntityManagerのインスタンス作成
            EntityManager em = DBUtil.createEntityManager();

            //Taskテーブルのインスタンス作成
            Task taskTable = new Task();

            //入力されたタスク（コンテンツをセットする）
            String content = request.getParameter("content");
            taskTable.setContent(content);

            //現在時刻の取得
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            taskTable.setCreated_at(currentTime);
            taskTable.setUpdated_at(currentTime);

            //データベースに保存する
            em.getTransaction().begin();
            em.persist(taskTable);
            em.getTransaction().commit();

            //クローズ処理
            em.close();

            //indexにリダイレクト
            response.sendRedirect(request.getContextPath() + "/index");

        }


    }

}
