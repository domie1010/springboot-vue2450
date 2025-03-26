package com.longwang.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longwang.entity.Comment;
import com.longwang.repository.CommentRepository;
import com.longwang.service.CommentService;
import com.longwang.util.StringUtil;

/**
 * 评论Service接口实现类
 * @author LongWang
 *
 */
@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentRepository commentRepository;

	@Override
	public List<Comment> list(Comment comment,String s_bCommentDate, String s_eCommentDate, Integer page, Integer pageSize,Integer userId) {
		// TODO Auto-generated method stub
		Page<Comment> pageComment= commentRepository.findAll(new Specification<Comment>() {

			@Override
			public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate=cb.conjunction();
				if(StringUtil.isNotEmpty(s_bCommentDate)) {
					predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("commentDate").as(String.class), s_bCommentDate));
				}
				if(StringUtil.isNotEmpty(s_eCommentDate)) {
					predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("commentDate").as(String.class), s_eCommentDate));
				}
				if(comment!=null) {
					if(comment.getArticle()!=null) {
						predicate.getExpressions().add(cb.equal(root.get("article").get("articleId"),comment.getArticle().getArticleId()));
					}
				}
				if(userId!=null) {
					predicate.getExpressions().add(cb.equal(root.<String>get("user"),userId));
				}
				return predicate;
			}
		}, PageRequest.of(page, pageSize, Sort.Direction.DESC,"commentDate"));
		return pageComment.getContent();
	}

	@Override
	public Long getCount(Comment comment,String s_bCommentDate, String s_eCommentDate,Integer userId) {
		// TODO Auto-generated method stub
		Long count=commentRepository.count(new Specification<Comment>() {

			@Override
			public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate=cb.conjunction();
				if(StringUtil.isNotEmpty(s_bCommentDate)) {
					predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("commentDate").as(String.class), s_bCommentDate));
				}
				if(StringUtil.isNotEmpty(s_eCommentDate)) {
					predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("commentDate").as(String.class), s_eCommentDate));
				}
				if(comment!=null) {
					if(comment.getArticle()!=null) {
						predicate.getExpressions().add(cb.equal(root.get("article").get("articleId"),comment.getArticle().getArticleId()));
					}
				}
				if(userId!=null) {
					predicate.getExpressions().add(cb.equal(root.<String>get("user"),userId));
				}
				return predicate;
			}
		});
		return count;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		commentRepository.deleteById(id);
	}

	@Override
	public void add(Comment comment) {
		// TODO Auto-generated method stub
		commentRepository.save(comment);
	}

	@Override
	public Integer getArticleId(Integer commentId) {
		// TODO Auto-generated method stub
		return commentRepository.getArticleId(commentId);
	}

	@Override
	public List<Comment> massageList(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
				Page<Comment> pageComment= commentRepository.findAll(new Specification<Comment>() {

					@Override
					public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
						// TODO Auto-generated method stub
						Predicate predicate=cb.conjunction();
						predicate.getExpressions().add(cb.isNull(root.get("article")));
						return predicate;
					}
				}, PageRequest.of(page, pageSize,Sort.Direction.DESC,"commentDate"));
				return pageComment.getContent();
	}

	@Override
	public Long getCount2() {
		Long count=commentRepository.count(new Specification<Comment>() {

			@Override
			public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate=cb.conjunction();
				predicate.getExpressions().add(cb.isNull(root.get("article")));
				return predicate;
			}
		});
		return count;
	}
	
}
