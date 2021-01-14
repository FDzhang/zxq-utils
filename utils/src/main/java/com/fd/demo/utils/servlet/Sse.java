package com.fd.demo.utils.servlet;

/**
 * @author ：zxq
 * @date ：Created in 2021/1/14 15:11
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Servlet implementation class SSE
 */
@WebServlet("/SSE")
public class Sse extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sse() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");

        for (int i = 0; i < 50; i++) {
            // 指定事件标识
            response.getWriter().write("event:me\n");

            // 格式： data + 数据 + 2个回车
            response.getWriter().write("data:" + i + "\n\n");
            response.getWriter().flush();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
