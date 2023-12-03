package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest {
	private static BookDAO bookDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // 테스트 클래스의 시작 전 BookDAO 인스턴스를 생성합니다.
        bookDao = new BookDAO();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        // 테스트 클래스가 끝난 후 BookDAO 인스턴스를 닫습니다.
        bookDao.close();
    }

    @Test
    public void testUpdateBook() throws ParseException, IOException {
        // Book을 업데이트하는 테스트 메서드입니다.
        Book existBook = new Book();
        existBook.setBookId(1);

        Category category = new Category("Java Core");
        category.setCategoryId(1);
        existBook.setCategory(category);

        existBook.setTitle("Effective Java (3rd Edition)");
        existBook.setAuthor("Joshua Bloch");
        existBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
        existBook.setPrice(40f);
        existBook.setIsbn("0321356683");

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date publishDate = dateFormat.parse("05/28/2008");
        existBook.setPublishDate(publishDate);

        String imagePath = "D:\\eclipse\\ProjectWorkspace\\BookStoreWebsite\\dummy-data-books\\books\\Effective Java.JPG";

        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
        existBook.setImage(imageBytes);

        Book updatedBook = bookDao.update(existBook);

        // 업데이트된 Book 객체의 제목이 예상한 값과 같은지 확인합니다.
        assertEquals(updatedBook.getTitle(), "Effective Java (3rd Edition)");
    }
	
    @Test
    public void testCreateBook() throws ParseException, IOException {
        // Book을 생성하는 테스트 메서드입니다.
        Book newBook = new Book();
        
        Category category = new Category("Advanced Java");
        category.setCategoryId(2);
        newBook.setCategory(category);
        
        newBook.setTitle("Effective Java (2nd Edition)");
        newBook.setAuthor("Joshua Bloch");
        newBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
        newBook.setPrice(38.87f);
        newBook.setIsbn("0321356683");
        
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");        
        Date publishDate = dateFormat.parse("05/28/2008");
        newBook.setPublishDate(publishDate);
        
        String imagePath = "D:\\eclipse\\ProjectWorkspace\\BookStoreWebsite\\dummy-data-books\\books\\Effective Java.JPG";
        
        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
        newBook.setImage(imageBytes);
        
        Book createdBook = bookDao.create(newBook);
        
        // 생성된 Book 객체의 ID가 0보다 큰지 확인합니다.
        assertTrue(createdBook.getBookId() > 0);
    }

    @Test
    public void testCreate2ndBook() throws ParseException, IOException {
        // 두 번째 Book을 생성하는 테스트 메서드입니다.
        Book newBook = new Book();
        
        Category category = new Category("Java Core");
        category.setCategoryId(1);
        newBook.setCategory(category);
        
        newBook.setTitle("Java 8 in Action");
        newBook.setAuthor("Alan Mycroft");
        newBook.setDescription("Java 8 in Action is a clearly written guide to the new features of Java 8");
        newBook.setPrice(36.72f);
        newBook.setIsbn("1617291994");
        
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");        
        Date publishDate = dateFormat.parse("08/28/2014");
        newBook.setPublishDate(publishDate);
        
//        String imagePath = "D:\\BookStoreProject\\dummy-data\\books\\Java 8 in Action.JPG";

        String imagePath = "D:\\eclipse\\ProjectWorkspace\\BookStoreWebsite\\dummy-data-books\\books\\Java 8 in Action.JPG";
       //        D:\eclipse\ProjectWorkspace\BookStoreWebsite\dummy-data-books\books
        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
        newBook.setImage(imageBytes);
        
        Book createdBook = bookDao.create(newBook);
        
        // 생성된 Book 객체의 ID가 0보다 큰지 확인합니다.
        assertTrue(createdBook.getBookId() > 0);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteBookFail() {
        // 존재하지 않는 Book을 삭제하는 테스트 메서드입니다.
        Integer bookId = 100;
        bookDao.delete(bookId);
    }

    @Test
    public void testGetBookFail() {
        // 존재하지 않는 Book을 조회하는 테스트 메서드입니다.
        Integer bookId = 99;
        Book book = bookDao.get(bookId);
        
        // 조회된 Book 객체가 null인지 확인합니다.
        assertNull(book);
    }

    @Test
    public void testGetBookSuccess() {
        // 존재하는 Book을 조회하는 테스트 메서드입니다.
        Integer bookId = 2;
        Book book = bookDao.get(bookId);
        
        // 조회된 Book 객체가 null이 아닌지 확인합니다.
        assertNotNull(book);
    }

	
    @Test
    public void testListAll() {
        // 모든 책을 검색하고 제목과 저자를 출력합니다.
        List<Book> listBooks = bookDao.listAll();
        
        // 검색된 목록의 각 책의 제목과 저자를 출력합니다.
        for (Book aBook : listBooks) {
            System.out.println(aBook.getTitle() + " - " + aBook.getAuthor()); 
        }
        
        // 검색된 목록이 비어있지 않은지 확인합니다.
        assertFalse(listBooks.isEmpty());
    }

    @Test
    public void testFindByTitleNotExist() {
        // 데이터베이스에 존재하지 않는 특정 제목의 책을 검색합니다.
        String title = "Thinkin in Java";
        Book book = bookDao.findByTitle(title);
        
        // 반환된 책 객체가 null인지 확인합니다.
        assertNull(book);
    }

    @Test
    public void testFindByTitleExist() {
        // 데이터베이스에 존재하는 특정 제목의 책을 검색합니다.
        String title = "Java 8 in Action";
        Book book = bookDao.findByTitle(title);
        
        // 검색된 책 객체가 null이 아닌지 확인합니다.
        assertNotNull(book);
        
        // 책의 저자와 가격 정보를 출력합니다.
        System.out.println(book.getAuthor());
        System.out.println(book.getPrice());
    }

    @Test
    public void testCount() {
        // 데이터베이스에 저장된 전체 책의 수를 계산합니다.
        long totalBooks = bookDao.count();
        
        // 계산된 수가 예상 값과 일치하는지 확인합니다 (이 경우에는 2여야 함).
        assertEquals(2, totalBooks);
    }

    @Test
    public void testDeleteBookSuccess() {
        // 데이터베이스에서 특정 ID의 책을 삭제합니다.
        Integer bookId = 1;
        bookDao.delete(bookId);
        
        // 삭제 작업이 예외 없이 완료되었는지 확인합니다.
        assertTrue(true);
    }

    @Test
    public void testListNewBooks() {
        // 새로운 책 목록을 가져와 제목과 출판일을 출력합니다.
        List<Book> listNewBooks = bookDao.listNewBooks();
        for (Book aBook : listNewBooks) {
            System.out.println(aBook.getTitle() + " - " + aBook.getPublishDate());
        }
        
        // 검색된 목록의 크기가 예상 카운트와 일치하는지 확인합니다 (이 경우에는 4여야 함).
        assertEquals(4, listNewBooks.size());
    }

	
    @Test
    public void testSearchBookInTitle() {
        // 'Java' 키워드로 책을 제목에서 검색합니다.
        String keyword = "Java"; //기대하는 문구 넣음
        List<Book> result = bookDao.search(keyword);
        
        // 검색된 각 책의 제목을 출력합니다.
        for (Book aBook : result) {
            System.out.println(aBook.getTitle());
        }
        
        // 'Java' 키워드를 포함한 제목의 책이 6권이어야 합니다.
        assertEquals(6, result.size()); //기대하는 개수 넣음. 이 경우 JAVA 를 6개 기대한다는 의미 
    }

    @Test
    public void testSearchBookInAuthor() {
        // 'Kathy' 키워드로 책을 저자에서 검색합니다.
        String keyword = "Kathy";//기대하는 문구 넣음
        List<Book> result = bookDao.search(keyword);
        
        // 검색된 각 책의 제목을 출력합니다.
        for (Book aBook : result) {
            System.out.println(aBook.getTitle());
        }
        
        // 'Kathy'를 저자로 하는 책이 2권이어야 합니다.
        assertEquals(2, result.size()); //기대하는 개수 넣음. 이 경우 Kathy 를 두개 기대한다는 의미 
    }   

    @Test
    public void testSearchBookInDescription() {
        // 'The Big Picture' 키워드로 책을 설명에서 검색합니다.
        String keyword = "The Big Picture";	//기대하는 문구 넣음
        List<Book> result = bookDao.search(keyword);
        
        // 검색된 각 책의 제목을 출력합니다.
        for (Book aBook : result) {
            System.out.println(aBook.getTitle());
        }
        
        // 'The Big Picture'를 설명에 포함하는 책이 1권이어야 합니다.
        assertEquals(1, result.size());	//기대하는 개수 넣음. 이 경우 The Big Picture 를 한개 기대한다는 의미 
    }   

    @Test
    public void testListByCategory() {
        // 카테고리별로 책을 검색합니다.
        int categoryId = 2;
        
        // 해당 카테고리의 책 목록을 가져옵니다.
        List<Book> listBooks = bookDao.listByCategory(categoryId);
        
        // 검색된 책 목록이 비어있지 않은지 확인합니다.
        assertTrue(listBooks.size() > 0);
    }

    @Test
    public void testCountByCategory() {
        // 특정 카테고리의 책 수를 계산합니다.
        int categoryId = 1;
        long numOfBooks = bookDao.countByCategory(categoryId);
        
        // 계산된 수가 예상 값과 일치하는지 확인합니다 (이 경우 7권이어야 함).
        assertTrue(numOfBooks == 7);
    }

    @Test
    public void testListBestSellingBooks() {
        // 가장 많이 판매된 책 목록을 가져옵니다.
        List<Book> topBestSellingBooks = bookDao.listBestSellingBooks();
        
        // 가장 많이 판매된 책 목록의 제목을 출력합니다.
        for (Book book : topBestSellingBooks) {
            System.out.println(book.getTitle());
        }
        
        // 가장 많이 판매된 책 목록의 크기가 4여야 합니다.
        assertEquals(4, topBestSellingBooks.size());
    }

    @Test
    public void testListMostFavoredBooks() {
        // 가장 선호도가 높은 책 목록을 가져옵니다.
        List<Book> topFavoredBooks = bookDao.listMostFavoredBooks();

        // 가장 선호도가 높은 책 목록의 제목을 출력합니다.
        for (Book book : topFavoredBooks) {
            System.out.println(book.getTitle());
        }
        
        // 가장 선호도가 높은 책 목록의 크기가 4여야 합니다.
        assertEquals(4, topFavoredBooks.size());
    }
}
