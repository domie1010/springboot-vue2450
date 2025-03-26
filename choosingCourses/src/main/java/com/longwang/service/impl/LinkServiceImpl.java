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

import com.longwang.entity.Link;
import com.longwang.repository.LinkRepository;
import com.longwang.service.LinkService;
import com.longwang.util.StringUtil;

/**
 * 友情链接Service接口实现类
 * @author LongWang
 *
 */
@Service("linkService")
@Transactional
public class LinkServiceImpl implements LinkService {

	@Resource
	private LinkRepository linkRepository;
	
	@Override
	public List<Link> list(Link link,Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable=new PageRequest(page, pageSize,Sort.Direction.ASC,"orderNum");
		Page<Link> pageWebSiteInfo=linkRepository.findAll(new Specification<Link>() {
			
			@Override
			public Predicate toPredicate(Root<Link> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate=cb.conjunction();
				if(link!=null) {
					if(StringUtil.isNotEmpty(link.getLinkName())) {
						predicate.getExpressions().add(cb.like(root.get("linkName"),"%"+link.getLinkName().trim()+"%"));
					}
					if(StringUtil.isNotEmpty(link.getLinkUrl())) {
						predicate.getExpressions().add(cb.like(root.get("linkUrl"),"%"+link.getLinkUrl().trim()+"%"));
					}
				}
				return predicate;
			}
		},pageable);
		return pageWebSiteInfo.getContent();
	}

	@Override
	public Long getCount(Link link) {
		// TODO Auto-generated method stub
		Long count=linkRepository.count(new Specification<Link>() {
			
			@Override
			public Predicate toPredicate(Root<Link> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate=cb.conjunction();
				if(link!=null) {
					if(StringUtil.isNotEmpty(link.getLinkName())) {
						predicate.getExpressions().add(cb.like(root.get("linkName"),"%"+link.getLinkName().trim()+"%"));
					}
					if(StringUtil.isNotEmpty(link.getLinkUrl())) {
						predicate.getExpressions().add(cb.like(root.get("linkUrl"),"%"+link.getLinkUrl().trim()+"%"));
					}
				}
				return predicate;
			}
		});
		return count;
	}

	@Override
	public Link findById(Integer id) {
		// TODO Auto-generated method stub
		return linkRepository.getOne(id);
	}

	@Override
	public void save(Link link) {
		// TODO Auto-generated method stub
		linkRepository.save(link);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		linkRepository.deleteById(id);
	}

}
