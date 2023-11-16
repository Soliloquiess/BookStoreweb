package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {
    private static UserDAO userDAO; // UserDAO 클래스의 객체를 선언

    @BeforeClass
    public static void setUpClass() throws Exception {
        userDAO = new UserDAO(); // UserDAO 객체를 초기화하여 테스트 전 설정
    }

    @Test
    public void testCreateUsers() {
        // 사용자 생성 테스트
        Users user1 = new Users();
        user1.setEmail("john3@gmail.com");
        user1.setFullName("John Smith 3");
        user1.setPassword("johnny3");

        user1 = userDAO.create(user1); // 사용자를 생성하고 반환

        assertTrue(user1.getUserId() > 0); // 사용자 ID가 0보다 큰지 확인
    }

    @Test(expected = PersistenceException.class)
    public void testCreateUsersFieldsNotSet() {
        // 필드가 설정되지 않은 사용자 생성 테스트
        Users user1 = new Users();
        user1 = userDAO.create(user1); // 사용자를 생성하고 PersistenceException이 발생하는지 확인
    }

    @Test
    public void testUpdateUsers() {
        // 사용자 업데이트 테스트
        Users user = new Users();
        user.setUserId(1);
        user.setEmail("nam@codejava.net");
        user.setFullName("Nam Ha Minh");
        user.setPassword("mysecret");

        user = userDAO.update(user); // 사용자 업데이트 후 반환

        String expected = "mysecret";
        String actual = user.getPassword();

        assertEquals(expected, actual); // 기대 비밀번호와 실제 비밀번호가 일치하는지 확인
    }

    @Test
    public void testGetUsersFound() {
        // 특정 ID의 사용자 검색 테스트
        Integer userId = 1;
        Users user = userDAO.get(userId);

        if (user != null) {
            System.out.println(user.getEmail());
        }

        assertNotNull(user); // 사용자가 null이 아닌지 확인
    }

    @Test
    public void testGetUsersNotFound() {
        // 특정 ID의 사용자를 찾지 못하는 경우 테스트
        Integer userId = 99;
        Users user = userDAO.get(userId);

        assertNull(user); // 사용자가 null인지 확인
    }

    @Test
    public void testDeleteUsers() {
        // 사용자 삭제 테스트
        Integer userId = 3;
        userDAO.delete(userId);

        Users user = userDAO.get(userId);

        assertNull(user); // 사용자가 null인지 확인
    }

    @Test(expected = EntityNotFoundException.class)
//    @Test(expected = EntityNotFoundException.class)는 JUnit의 테스트 케이스에서 사용되는 어노테이션입니다. 이것은 해당 테스트 메서드가 특정 예외를 예상하고 해당 예외가 발생해야 한다는 것을 나타냅니다.
//    EntityNotFoundException은 JPA(Java Persistence API)에서 정의된 예외 중 하나입니다. 이 예외는 JPA의 EntityManager에서 특정 엔티티를 찾지 못했을 때 발생합니다.
//    따라서 @Test(expected = EntityNotFoundException.class)는 해당 테스트 메서드가 어떤 조건이 발생했을 때 EntityNotFoundException이 발생해야 한다는 것을 명시하는 것입니다. 테스트가 성공적으로 수행되기 위해서는 예상한 예외가 발생해야 합니다. 만약 예외가 발생하지 않으면 해당 테스트는 실패합니다.
//    즉, 해당 테스트 케이스는 특정 조건하에 EntityNotFoundException이 발생해야 한다는 것을 확인하는데 사용됩니다.
    public void testDeleteNonExistUsers() {
        // 존재하지 않는 사용자 삭제 시도 테스트
        Integer userId = 55;
        userDAO.delete(userId);
    }

    @Test
    public void testListAll() {
        List<Users> listUsers = userDAO.listAll();

        for (Users user : listUsers) {
            System.out.println(user.getEmail());
        }

        assertTrue(listUsers.size() > 0); // 사용자 목록의 크기가 0보다 큰지 확인
    }

    @Test
    public void testCount() {
        long totalUsers = userDAO.count();

        assertTrue(totalUsers > 0); // 총 사용자 수가 0보다 큰지 확인
    }

    @Test
    public void testCheckLoginSuccess() {
        String email = "nam@codejava.net";
        String password = "mysecret";
        boolean loginResult = userDAO.checkLogin(email, password);

        assertTrue(loginResult); // 로그인 성공 여부 확인
    }

    @Test
    public void testCheckLoginFailed() {
        String email = "nam123";
        String password = "mysecret1";
        boolean loginResult = userDAO.checkLogin(email, password);

        assertFalse(loginResult); // 로그인 실패 여부 확인
    }

    @Test
    public void testFindByEmail() {
        String email = "nam@codejava.net";
        Users user = userDAO.findByEmail(email);

        assertNotNull(user); // 해당 이메일을 가진 사용자가 존재하는지 확인
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        userDAO.close(); // 테스트 후 UserDAO 객체를 닫음
    }
}
