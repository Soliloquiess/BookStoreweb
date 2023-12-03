package com.bookstore.entity;

import javax.persistence.Column; // javax.persistence 패키지에서 Column 어노테이션을 import합니다.
import javax.persistence.Entity; // javax.persistence 패키지에서 Entity 어노테이션을 import합니다.
import javax.persistence.GeneratedValue; // javax.persistence 패키지에서 GeneratedValue 어노테이션을 import합니다.
import javax.persistence.GenerationType; // javax.persistence 패키지에서 GenerationType을 import합니다.
import javax.persistence.Id; // javax.persistence 패키지에서 Id 어노테이션을 import합니다.
import javax.persistence.NamedQueries; // javax.persistence 패키지에서 NamedQueries 어노테이션을 import합니다.
import javax.persistence.NamedQuery; // javax.persistence 패키지에서 NamedQuery 어노테이션을 import합니다.

@Entity
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u ORDER BY u.fullName"), // 사용자 전체 조회하는 쿼리
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"), // 이메일로 사용자 조회하는 쿼리
    @NamedQuery(name = "Users.countAll", query = "SELECT Count(*) FROM Users u"), // 사용자 수를 세는 쿼리
    @NamedQuery(name = "Users.checkLogin", query = "SELECT u FROM Users u WHERE u.email = :email AND password = :password") // 로그인 확인을 위한 쿼리
})

/*
 * @NamedQuery 어노테이션은 JPA (Java Persistence API)에서 지정된 이름으로 쿼리를 정의할 수 있도록 도와주는
 * 어노테이션입니다. 이를 통해 명명된 쿼리를 사용하여 코드에서 해당 쿼리를 실행하거나 검색할 수 있습니다.
 * 
 * java Copy code
 * 
 * @NamedQuery( name = "Users.findAll", query =
 * "SELECT u FROM Users u ORDER BY u.fullName" ) 여기서 @NamedQuery는 다음을 정의합니다:
 * 
 * name: 이 쿼리에 대한 고유한 이름을 지정합니다. 이 이름을 사용하여 쿼리를 참조합니다. 위의 예시에서는
 * "Users.findAll"이라는 이름을 사용하여 사용자의 전체 목록을 조회하는 쿼리를 참조할 수 있습니다. query: 해당 명명된
 * 쿼리에서 실행될 JPQL(Java Persistence Query Language)을 지정합니다. 이 경우 "Users.findAll"
 * 명명된 쿼리는 사용자(Users) 엔티티에서 사용자의 전체 목록을 fullName을 기준으로 정렬하여 가져옵니다. 이러한 명명된 쿼리는
 * 일반적으로 복잡한 쿼리나 자주 사용되는 쿼리에 대한 별칭을 만들고, 애플리케이션에서 해당 쿼리를 참조하고 실행하는 데 사용됩니다.
 */

public class Users {
    private Integer userId; // 사용자 ID
    private String email; // 사용자 이메일
    private String fullName; // 사용자 전체 이름
    private String password; // 사용자 비밀번호

    public Users() {
    }

    public Users(Integer userId, String email, String fullName, String password) {
        this(email, fullName, password);
        this.userId = userId;
    }

    public Users(String email, String fullName, String password) {
        super();
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    @Column(name = "user_id") // "user_id" 컬럼을 매핑
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 방식으로 user_id를 생성
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "full_name") // "full_name" 컬럼을 매핑
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
