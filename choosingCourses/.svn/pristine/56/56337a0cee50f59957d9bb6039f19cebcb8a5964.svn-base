package com.longwang.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longwang.entity.Reply;
import com.longwang.repository.ReplyRepository;
import com.longwang.service.ReplyService;

/**
 * 评论Service接口实现类
 * @author LongWang
 *
 */
@Service("replyService")
@Transactional
public class ReplyServiceImpl implements ReplyService {

	@Resource
	private ReplyRepository replyRepository;

	@Override
	public List<Reply> list(Reply reply) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Sort.Direction.ASC, "replyDate");
		List<Reply> replyList=replyRepository.findAll(new Specification<Reply>() {

			@Override
			public Predicate toPredicate(Root<Reply> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate=cb.conjunction();
				if(reply!=null) {
					if(reply.getComment()!=null) {
						predicate.getExpressions().add(cb.equal(root.<String>get("comment"),reply.getComment().getCommentId()));
					}
				}
				return predicate;
			}
		},sort);
		return replyList;
	}

	@Override
	public Long getCount() {
		// TODO Auto-generated method stub
		Long count=replyRepository.count();
		return count;
	}

	@Override
	public void add(Reply reply) {
		// TODO Auto-generated method stub
		replyRepository.save(reply);
	}

	@Override
	public List<Reply> list(Reply reply,Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		Page<Reply> pageReply=replyRepository.findAll(new Specification<Reply>() {

			@Override
			public Predicate toPredicate(Root<Reply> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate=cb.conjunction();
				if(reply!=null) {
					if(reply.getComment()!=null) {
						predicate.getExpressions().add(cb.equal(root.<String>get("comment"),reply.getComment().getCommentId()));
					}
				}
				return predicate;
			}
		},PageRequest.of(page, pageSize,Sort.Direction.DESC,"replyDate"));
		return pageReply.getContent();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		replyRepository.deleteById(id);
	}


}
