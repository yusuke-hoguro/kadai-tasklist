package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        EntityManager em = DBUtil.createEntityManager();

        //データベースからタスクテーブルの全データを取得
        List<Task> tasks = em.createNamedQuery("getAllTasks",Task.class).getResultList();

        em.close();

        //リクエストスコープにセット
        request.setAttribute("tasks", tasks);

        //セッションスコープの不要になったデータを削除(Editで設定したtask_idの削除)
        if(request.getSession().getAttribute("task_id") != null){
            request.getSession().removeAttribute("task_id");
        }


        //セッションスコープに登録されているフラッシュメッセージをリクエストスコープに移行し削除
        if(request.getSession().getAttribute("flush") != null){

            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");

        }


        //リクエストをビューに転送する
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/index.jsp");
        rd.forward(request, response);

    }

}
