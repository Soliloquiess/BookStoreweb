package com.bookstore.dao;

import java.util.HashMap; // 키-값 쌍을 매핑하기 위한 HashMap을 import합니다.
import java.util.List; // 컬렉션을 위한 List를 import합니다.
import java.util.Map; // 키와 값을 매핑하는 Map 인터페이스를 import합니다.

import com.bookstore.entity.Users; // Users 엔티티를 import합니다.

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

    public UserDAO() {
    }

    public Users create(Users user) {
    	// 사용자의 비밀번호를 MD5 해시로 암호화합니다.
    	// HashGenerator 클래스의 generateMD5 메서드를 사용하여 암호화를 수행합니다.
    	// 이렇게 함으로써 사용자의 비밀번호를 해싱하여 저장할 수 있습니다.
    	String encryptedPassword = HashGenerator.generateMD5(user.getPassword());
    	// 암호화된 비밀번호를 사용자 객체에 설정합니다.
    	user.setPassword(encryptedPassword);

        return super.create(user); // 사용자를 생성하고 데이터베이스에 유저 정보를 저장합니다.
    }
    
    @Override
    public Users update(Users user) {
        return super.update(user); // 데이터베이스에서 유저 정보를 업데이트합니다.
    }

    @Override
    public Users get(Object userId) {
        return super.find(Users.class, userId); // 유저 ID에 따른 유저 정보를 가져옵니다.
    }
    
    public Users findByEmail(String email) {
        List<Users> listUsers = super.findWithNamedQuery("Users.findByEmail", "email", email);
        
        if (listUsers != null && listUsers.size() > 0) {	//listUsers.size() ==1 만약 이렇게 하면 중복 된거 없이 하나만 존재하는 거로 이해
            return listUsers.get(0); // 이메일 주소에 따른 사용자를 찾습니다.
        }
        
        return null; // 제공된 이메일로 찾은 사용자가 없는 경우 null을 반환합니다.
    }
    
    public boolean checkLogin(String email, String password) {
        Map<String, Object> parameters = new HashMap<>(); // 로그인 확인을 위한 매개변수를 생성합니다.
        parameters.put("email", email); // 이메일을 매개변수로 설정합니다.
        parameters.put("password", password); // 비밀번호를 매개변수로 설정합니다.
        
        List<Users> listUsers = super.findWithNamedQuery("Users.checkLogin", parameters);
        
        if (listUsers.size() == 1) {
            return true; // 로그인 자격 증명이 하나의 사용자와 일치하는 경우 true를 반환합니다.
        }
        
        return false; // 사용자가 없거나 여러 사용자가 자격 증명과 일치하는 경우 false를 반환합니다.
    }

    @Override
    public void delete(Object userId) {
        super.delete(Users.class, userId); // 유저 ID에 따라 사용자를 삭제합니다.
    }

    @Override
    public List<Users> listAll() {
        return super.findWithNamedQuery("Users.findAll"); // 모든 사용자의 목록을 가져옵니다.
    }

    @Override
    public long count() {
        return super.countWithNamedQuery("Users.countAll"); // 전체 사용자 수를 계산합니다.
    }

}
