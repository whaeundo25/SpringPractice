package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {

    private String name;
    private Integer age; // Integer는 int와 다르게 null 삽입 가능

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
