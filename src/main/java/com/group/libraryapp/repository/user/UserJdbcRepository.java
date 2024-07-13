package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

// 데이터 베이스에 접근하여 sql을 날리는 역할
// Component: 주어진 클래스를 컴포넌트로 간주, 스프링 서버가 뜰 때 자동으로 감지된다.
// - 컨트롤러, 서비스, 리포지토리가 모두 아니고 개발자가 직접 작성한 클래스를 스프링 빈으로 등록할 때 사용되기도 한다.
// Qualifier: 해당 후보군이 여러개 있을 때 이름으로 붙여 가져오거나 main을 붙여 스프링 빈에서 가져오는 방식 - Qualifier가 primary보다 우선된다.
@Repository // UserRepository를 스프링 빈으로 등록시켜줁다.
public class UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isUserNotExist(long id) {
        // 결과가 하나라도 있다면 0으로 간주, 찾는 유저가 있다면 0이 담겨 있는 리스트가 나올 것 그렇지 않다면 비어있는 리스트 반환
        String readSql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public void updateUserName(String name, long id) {
        String sql = "UPDATE user SET name=? WHERE id=?";
        jdbcTemplate.update(sql, name, id);
    }

    public boolean isUserNotExist(String name) {
        String readSql = "SELECT * FROM user WHERE name = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
    }

    public void deleteUser(String name) {
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    public void saveUser(String name, Integer age) {
        String sql = "INSERT INTO user(name, age) VALUES(?, ?)";
        jdbcTemplate.update(sql, name, age);
    }

    public List<UserResponse> getUsers() {
        String sql = "SELECT * FROM user";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");

            return new UserResponse(id, name, age);
        });
    }

}
