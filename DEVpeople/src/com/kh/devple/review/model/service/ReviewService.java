package com.kh.devple.review.model.service;

import static com.kh.devple.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.devple.review.model.dao.ReviewDAO;
import com.kh.devple.review.model.vo.Review;
import com.kh.devple.review.model.vo.VReview;

public class ReviewService {

	private ReviewDAO rDAO = new ReviewDAO();

	/**
	 * @author 이진선
	 * @return list
	 * @throws Exception
	 */
	public List<VReview> selectReview() throws Exception {
		Connection conn = getConnection();
		
		List<VReview> list = rDAO.selectReview(conn);
		
		
		close(conn);
	
		
		
		
		return list;
	}

	
	/** 리뷰 작성 Service
	 * @author 이진선
	 * @param review
	 * @return
	 * @throws Exception
	 */
	public int insertReview(Review review) throws Exception{
		
		Connection conn = getConnection();
		
		int result = rDAO.insertReview(conn, review);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
}
