package com.fd.demo.utils.controller;

import com.fd.demo.utils.utils.PdfUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zxq
 */
@RestController
@RequestMapping("/")
@Slf4j
public class TestController {

    @GetMapping("/pdf")
    public void pdf( HttpServletResponse response){
        try {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + "test.pdf" + "\"");
            ServletOutputStream outputStream = response.getOutputStream();

            PdfUtil.pdf(outputStream);

            outputStream.close();
        } catch (IOException e) {
            log.error("pdf下载异常");
        }
    }

    @GetMapping("/test")
    public String test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        Cookie[] cookies = httpServletRequest.getCookies();

        if (cookies!=null){
            for (Cookie cookie1 : cookies) {
                System.out.println(cookie1.getName()+"--"+cookie1.getValue());
            }
        }

        // new一个Cookie对象,键值对为参数
        Cookie cookie = new Cookie("sessionId", "123456");
        // tomcat下多应用共享
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        log.info("123");

        return "ok";
    }
}
