//package com.fd.demo.utils.kotlin
//
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//
///**
// * @author     ：zxq
// * @date       ：Created in 2021/1/14 17:29
// */
//@RestController
//@RequestMapping
//class helloController(val helloService: HelloService) {
//    @GetMapping("/hello")
//    fun helloKotlin(): String {
//        return "hello kotlin"
//    }
//
//    @GetMapping("/hello-service")
//    fun helloKotlinService(): String {
//        return helloService.getHello()
//    }
//
//    @GetMapping("hello-dto")
//    fun helloDTO():HelloDTO{
//        return HelloDTO("hello kotlin from the dto")
//    }
//}
