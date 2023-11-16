package com.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    // 모든 사용자를 가져와서 사용자의 전체 이름(fullName)을 기준으로 결과를 정렬하는 Named 쿼리
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u ORDER BY u.fullName"),

    // 이메일 주소를 기준으로 사용자를 찾는 Named 쿼리
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),

    // 모든 사용자 수를 세는 Named 쿼리
    @NamedQuery(name = "Users.countAll", query = "SELECT Count(*) FROM Users u"),

    // 이메일과 비밀번호를 기반으로 사용자 로그인 자격을 확인하는 Named 쿼리
    @NamedQuery(name = "Users.checkLogin", query = "SELECT u FROM Users u WHERE u.email = :email AND password = :password")
})
public class Users {
    private Integer userId;
    private String email;
    private String fullName;
    private String password;

    // 기본 생성자
    public Users() {
    }

    // userId를 제외한 모든 필드를 포함하는 생성자
    public Users(Integer userId, String email, String fullName, String password) {
        this(email, fullName, password);
        this.userId = userId;
    }

    // userId를 제외한 모든 필드를 포함하는 생성자
    public Users(String email, String fullName, String password) {
        super();
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)


//    @Column(name = "user_id"), @Id, 및 @GeneratedValue(strategy = GenerationType.IDENTITY)는 JPA(Java Persistence API) 애너테이션을 사용하여 데이터베이스 테이블의 열과 엔터티 클래스의 속성 간의 매핑을 지정하는 부분입니다. 아래에 각 애너테이션의 역할과 의미를 설명해 드리겠습니다:
//
//    	@Column(name = "user_id"):
//
//    	이 애너테이션은 엔터티 클래스의 속성을 데이터베이스 테이블의 열에 매핑하는 데 사용됩니다.
//    	name 속성은 데이터베이스 테이블에서 해당 열의 이름을 지정합니다. 이 경우 "user_id"라는 열에 해당 속성을 매핑합니다.
//    	@Id:
//
//    	이 애너테이션은 엔터티 클래스에서 기본 키(primary key) 속성을 나타냅니다.
//    	기본 키는 각 행을 고유하게 식별하는 열로 사용되며, 일반적으로 데이터베이스 테이블에서 중복된 값이 허용되지 않습니다.
//    	@GeneratedValue(strategy = GenerationType.IDENTITY):
//
//    	이 애너테이션은 기본 키 속성의 값을 자동으로 생성하는 방법을 지정합니다.
//    	strategy 속성은 기본 키 생성 전략을 설정합니다. GenerationType.IDENTITY는 데이터베이스에서 기본 키 값을 자동으로 생성하도록 지정하는 전략입니다. 일반적으로 데이터베이스 시퀀스나 자동 증가 열을 사용하여 기본 키 값을 생성합니다.
//    	이것은 일반적으로 auto-increment 열이 있는 데이터베이스에서 사용됩니다. 새로운 엔터티가 데이터베이스에 추가될 때, user_id 필드가 자동으로 증가되어 고유한 값을 갖게 됩니다.
//    	따라서 이러한 애너테이션 조합은 Users 엔터티 클래스의 userId 속성이 "user_id"라는 열에 매핑되고 이 열이 기본 키이며, 기본 키 값은 데이터베이스에서 자동으로 생성되도록 설정됨을 나타냅니다.
//    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // 이메일, 전체 이름, 비밀번호 필드에 대한 getter 및 setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "full_name")
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
