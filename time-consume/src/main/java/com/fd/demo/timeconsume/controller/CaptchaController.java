package com.fd.demo.timeconsume.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取图片验证码
 * @author ：zxq
 * @date ：Created in 2020/8/27 20:03
 */
@RestController
@RequestMapping
@Slf4j
public class CaptchaController {


    @GetMapping("getCaptcha")
    public void getImages(HttpServletResponse response) {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100, 4, 150);
        try(ServletOutputStream outputStream = response.getOutputStream()) {
            lineCaptcha.write(outputStream);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        System.out.println(lineCaptcha.getCode());
        System.out.println(lineCaptcha.getImageBase64Data());
    }

}
