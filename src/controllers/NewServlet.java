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

        //テーブル作成の動作確認用で一旦、Newに作成

        //EntityManagerのインスタンス作成
        EntityManager em = DBUtil.createEntityManager();

        //taskテーブルのインスタンス作成
        Task taskTable = new Task();

        //コンテンツ（タスクをセット）
        String content = "test";
        taskTable.setContent(content);

        /*
         * 現在時刻を取得して作成時間と更新時間をセット
         * ミリ秒を使用してTimestampオブジェクト作成
         */
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        taskTable.setCreated_at(currentTime);
        taskTable.setUpdated_at(currentTime);

        //データベース保存作業
        em.getTransaction().begin();
        em.persist(taskTable);
        em.getTransaction().commit();

        //自動採番された番号を表示
        response.getWriter().append(Integer.valueOf(taskTable.getId()).toString());

        //EntityManagerをクローズ
        em.close();

    }

}
