package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 스프링이 유저 객체와 user table을 같은 것으로 바라본다는 의미
@Entity // : 저장되고 관리되어야 하는 데이터
@Table(name="user")
public class User {

    @Id // : 해당 필드를 primary key로 간주한다는 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY) // : primary key를 자동 생성한다는 의미
    private Long id = null;

    // 이름이 동일할 경우 Column의 name 생략 가능
    // table과 완전 동일할 경우, Column 생략 가능
    @Column(nullable = false, length = 20, name = "name") // name varchar(20)을 의미
    private String name;
    private Integer age;

    // 연관관계의 주인의 값이 설정되어야만 진정한 데이터가 저장된다.
    // cascade: 유저가 삭제될 때 연결된 UserLoanHistory까지 함께 삭제된다.
    // orphanRemoval: 객체간의 관계가 끊어진 데이터를 자동으로 제거
    // fetch =  FetchType.EAGER: 유저와 유저 LoanHistory를 한번에 가져오도록 함. lazy를 사용하면 연결되어 있는 객체를 꼭 필요한 순간에만 가져오도록 함.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    // jpa 사용을 위해서는 기본 생성자가 꼭 있어야 한다.
    protected User() {}

    public User(String name, Integer age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void loanBook(String bookName){
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
    }

    public void returnBook(String bookName){
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        targetHistory.doReturn();
    }

}
