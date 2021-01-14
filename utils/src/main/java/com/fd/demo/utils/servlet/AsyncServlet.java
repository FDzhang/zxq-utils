package com.fd.demo.utils.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author ：zxq
 * @date ：Created in 2021/1/14 14:57
 */

@WebServlet(asyncSupported = true, urlPatterns = {"/AsyncServlet"})
public class AsyncServlet extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncServlet() {
        super();
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        long t1 = System.currentTimeMillis();

        //开启异步
        AsyncContext asyncContext = request.startAsync();

        // 执行业务代码
        CompletableFuture.runAsync(() -> deSomeThing(asyncContext,
                asyncContext.getRequest(), asyncContext.getResponse()));

        System.out.println("async use: " + (System.currentTimeMillis() - t1));
    }

    private void deSomeThing(AsyncContext asyncContext, ServletRequest request, ServletResponse response) {

        // 模拟耗时操作
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //
        try {
            response.getWriter().append("done");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 业务代码处理完毕，通知结束
        asyncContext.complete();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
}
