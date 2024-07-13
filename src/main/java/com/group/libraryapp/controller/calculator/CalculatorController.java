package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// ctrl + option + o : 안쓰는 import 삭제

// 해당 클래스를 api의 진입 지점으로 만들어줌. - 주어진 class를 Controller로 등록
@RestController
public class CalculatorController {

    // Get /add - API 명세
    @GetMapping("/add")
    // 쿼리를 통해서 넘어온 데이터를 함수에 연결해줄 때는 @RequestParam을 붙인다.
    // 쿼리가 CalculatorAddRequest 객체에 있는 값에 들어감
    public int addTwoNumbers(CalculatorAddRequest request){
        return request.getNumber1() + request.getNumber2();
    }

    // Post
    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
        return request.getNumber1() * request.getNumber2();
    }

}
