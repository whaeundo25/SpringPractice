package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 데이터 베이스 - 데이터를 구조화하여 저장
// RDB: 데이터를 표처럼 구조화하여 저장하는 친구
// sql: 표처럼 구조화된 데이터를 조회하는 언어
// RestController
// => 1. API의 진입 지점으로 등록
// => 2. UserController의 클래스를 스프링 빈으로 등록시켜줌.

// 스프링 빈이란?
// 서버가 시작되면, 스프링 서버 내부에 거대한 컨테이너를 만들게 됨 =>컨테이너 안에  여러 클래스가 들어가게 됨
// 스프링 컨테이너안에 들어간 클래스를 클래스 빈이라 함. JdbcTemplate도 스프링 빈으로 등록되어 있음
@RestController
public class UserController {

    private final UserServiceV2 userService;

    // 생성자에 있는 매개변수에 스프링 빈을 연결 시켜달라는 의미 - Autowired
    @Autowired
    public UserController(UserServiceV2 userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request) {
        userService.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
    }


}
