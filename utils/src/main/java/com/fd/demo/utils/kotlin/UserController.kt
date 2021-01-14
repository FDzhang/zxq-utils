//package com.fd.demo.utils.kotlin
//
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//
///**
// * @author     ：zxq
// * @date       ：Created in 2021/1/14 17:54
// */
//
//@RestController
//@RequestMapping("/user")
//class UserController(val userRepository: UserRepository) {
//    @GetMapping
//    fun findAll() = userRepository.findAll()
//
//    @GetMapping("/{name}")
//    fun findByLastName(@PathVariable name: String) = userRepository.findByLastName(name)
//}