package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    // 내가 다수이고 상대가 1이라는 소리
    // 연관관계에서 UserLoanHistory가 userId를 사용하기 때문에 주도권을 가지고 있음.
    // 1. 상대 table을 참조하고 있으면 연관관계의 주인
    // 2. 연관관계의 주인이 아닐 경우, mappedBy사용
    // 3. 연관관계의 주인의 setter가 사용되어야만 테이블 연결
    @ManyToOne
    private User user;

    private String bookName;

    private boolean isReturn;

    protected UserLoanHistory() {

    }

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doReturn() {
        this.isReturn = true;
    }

    public String getBookName() {
        return this.bookName;
    }
}
