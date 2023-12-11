package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Review;

public class ReviewDAO extends JpaDAO<Review> implements GenericDAO<Review> {

	@Override
	public Review create(Review review) {
	    review.setReviewTime(new Date());
	    // 리뷰의 작성 시간을 현재 시간으로 설정합니다.
	    return super.create(review);
	    // 상위 클래스의 create 메서드를 호출하여 리뷰를 생성하고 반환합니다.
	}

	@Override
	public Review get(Object reviewId) {
	    return super.find(Review.class, reviewId);
	    // 상위 클래스의 find 메서드를 호출하여 특정 리뷰를 가져와 반환합니다.
	}

	@Override
	public void delete(Object reviewId) {
	    super.delete(Review.class, reviewId);
	    // 상위 클래스의 delete 메서드를 호출하여 특정 리뷰를 삭제합니다.
	}

	@Override
	public List<Review> listAll() {
	    return super.findWithNamedQuery("Review.listAll");
	    // "Review.listAll" 명명된 쿼리를 사용하여 모든 리뷰를 가져와 리스트로 반환합니다.
	}

	@Override
	public long count() {
	    return super.countWithNamedQuery("Review.countAll");
	    // "Review.countAll" 명명된 쿼리를 사용하여 모든 리뷰의 개수를 반환합니다.
	}

	
	public Review findByCustomerAndBook(Integer customerId, Integer bookId) {
	    // 고객 ID와 책 ID를 받아 특정 리뷰를 찾는 메서드입니다.
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("customerId", customerId);
	    parameters.put("bookId", bookId);
	    
	    List<Review> result = super.findWithNamedQuery("Review.findByCustomerAndBook", parameters);
	    // 이름이 "Review.findByCustomerAndBook"인 명명된 쿼리를 사용하여 고객과 책에 대한 리뷰를 가져옵니다.
	    
	    if (!result.isEmpty()) {
	        return result.get(0);
	        // 결과 리스트가 비어있지 않다면, 첫 번째 리뷰를 반환합니다.
	    }
	    
	    return null;
	    // 결과가 없을 경우 null을 반환합니다.
	}

	public List<Review> listMostRecent() {
	    // 가장 최근에 작성된 리뷰를 가져오는 메서드입니다.
	    return super.findWithNamedQuery("Review.listAll", 0, 3);
	    // 이름이 "Review.listAll"인 명명된 쿼리를 사용하여 최근에 작성된 3개의 리뷰를 가져옵니다.
	}

	// ReviewDAO 클래스에 메서드 추가
	public long countByCustomer(int customerId) {
	    return super.countWithNamedQuery("Review.countByCustomer", "customerId", customerId);
	}
//ReviewDAO 클래스에 countByCustomer라는 이름의 메서드가 추가되었습니다. 이 메서드는 Review 클래스에 추가한 명명된 쿼리 "Review.countByCustomer"를 호출하여 특정 customerId를 가진 고객이 작성한 리뷰의 수를 반환합니다. countWithNamedQuery() 메서드는 JPA EntityManager를 사용하여 명명된 쿼리를 실행하고, 이를 통해 해당 customerId를 가진 고객이 작성한 리뷰의 수를 구합니다. 그리고 그 값을 반환합니다.
//이렇게 구현된 메서드는 특정 customerId를 가진 고객이 작성한 리뷰의 수를 확인하는 데 활용될 수 있습니다.
}
