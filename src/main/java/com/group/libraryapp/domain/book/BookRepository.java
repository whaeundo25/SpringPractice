package com.group.libraryapp.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//// Configuration: 클래스에 붙이는 어노테이션, @Bean을 사용할 때 함께 사용해야 함
//// Bean: 메소드에 붙이는 어노테이션, 메소드에서 반환되는 객체를 스프링 빈에 등록
//// Primary: 우선권을 결정하는 어노테이션
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);
}
