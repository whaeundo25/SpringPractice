package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

//    함수의 이름이 중요
//    find: 1개의 데이터만 가져옴. By 뒤에 붙는 필드의 이름으로 SELECT 쿼리의 WHERE문이 작성됨.
    Optional<User> findByName(String name);

    boolean existsByName(String name);

    long countByAge(Integer age);

    List<User> findAllByNameAndAge(String name, int age);

    List<User> findAllByAgeBetween(int startAge, int endAge);
}
