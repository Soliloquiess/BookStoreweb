package com.bookstore.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

	public BookDAO() {
	}

    @Override
    public Book create(Book book) {
        // 새 책을 추가할 때 마지막 업데이트 시간을 설정하여 추가하는 메서드
        book.setLastUpdateTime(new Date());
        return super.create(book);
    }

    @Override
    public Book update(Book book) {
        // 책 정보를 업데이트할 때 마지막 업데이트 시간을 설정하여 업데이트하는 메서드
        book.setLastUpdateTime(new Date());
        return super.update(book);
    }

    @Override
    public Book get(Object bookId) {
        // 주어진 bookId에 해당하는 Book 객체를 반환하는 메서드
        return super.find(Book.class, bookId);
    }

    @Override
    public void delete(Object bookId) {
        // 주어진 bookId에 해당하는 Book 객체를 삭제하는 메서드
        super.delete(Book.class, bookId);
    }

    @Override
    public List<Book> listAll() {
        // 모든 Book 객체 리스트를 반환하는 메서드
        return super.findWithNamedQuery("Book.findAll");
    }

    public Book findByTitle(String title) {
        // 주어진 제목에 해당하는 Book 객체를 반환하는 메서드
    	// Book 클래스의 리스트를 반환하는데, 이 리스트는 제목에 따라 데이터베이스에서 책을 찾는 메서드입니다.
    	List<Book> result = super.findWithNamedQuery("Book.findByTitle", "title", title);

    	// 만약 결과 리스트가 비어있지 않다면,
    	if (!result.isEmpty()) {
    	    // 리스트의 첫 번째 요소(가장 처음으로 찾은 책)를 반환합니다.
    	    return result.get(0);
    	}

    	// 결과 리스트가 비어있으면, 책을 찾지 못한 것이므로 null을 반환합니다.
    	return null;
    }

    public List<Book> listByCategory(int categoryId) {
        // 주어진 categoryId에 해당하는 Book 객체 리스트를 반환하는 메서드
        return super.findWithNamedQuery("Book.findByCategory", "catId", categoryId);
    }
	
    public List<Book> search(String keyword) {
        // 주어진 키워드로 Book을 검색하는 메서드
        return super.findWithNamedQuery("Book.search", "keyword", keyword);
        //쿼리의 매개변수명은 'keyword'이고, 값은 키워드 문자열, 'Book도메인 모델클래스'
    }

    public List<Book> listNewBooks() {
        // 새로운 Book 리스트를 반환하는 메서드
        return super.findWithNamedQuery("Book.listNew", 0, 4);
    }

    @Override
    public long count() {
        // Book 엔티티의 전체 개수를 반환하는 메서드
        return super.countWithNamedQuery("Book.countAll");
    }

    public long countByCategory(int categoryId) {
        // 특정 카테고리에 속하는 Book 엔티티의 개수를 반환하는 메서드
        return super.countWithNamedQuery("Book.countByCategory", "catId", categoryId);
    }

    public List<Book> listBestSellingBooks() {
        // 가장 많이 팔린 Book 리스트를 반환하는 메서드
        return super.findWithNamedQuery("OrderDetail.bestSelling", 0, 4);
    }

    public List<Book> listMostFavoredBooks() {
        // 가장 많은 좋아요를 받은 Book 리스트를 반환하는 메서드
        List<Book> mostFavoredBooks = new ArrayList<>();
        
        List<Object[]> result = super.findWithNamedQueryObjects("Review.mostFavoredBooks", 0, 4);
        
        if (!result.isEmpty()) {
            for (Object[] elements : result) {
                Book book = (Book) elements[0];
                mostFavoredBooks.add(book);
            }
        } 
        
        return mostFavoredBooks;
    }
}
