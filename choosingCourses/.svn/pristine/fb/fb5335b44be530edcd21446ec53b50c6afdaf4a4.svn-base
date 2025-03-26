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

import com.longwang.entity.Timeline;
import com.longwang.repository.TimelineRepository;
import com.longwang.service.TimelineService;
import com.longwang.util.StringUtil;

/**
 * 时光轴Service接口实现类
 * @author LongWang
 *
 */
@Service("timelineService")
@Transactional
public class TimelineServiceImpl implements TimelineService {

	@Resource
	private TimelineRepository timelineRepository;
	
	@Override
	public List<Timeline> list(Timeline timeline,Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		Page<Timeline> pageWebSiteInfo=timelineRepository.findAll(new Specification<Timeline>() {
			
			@Override
			public Predicate toPredicate(Root<Timeline> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate=cb.conjunction();
				if(timeline!=null) {
					if(StringUtil.isNotEmpty(timeline.getYear())) {
						predicate.getExpressions().add(cb.like(root.get("year"),"%"+timeline.getYear().trim()+"%"));
					}
					if(StringUtil.isNotEmpty(timeline.getMonth())) {
						predicate.getExpressions().add(cb.like(root.get("month"),"%"+timeline.getMonth().trim()+"%"));
					}
				}
				return predicate;
			}
		},PageRequest.of(page, pageSize, Sort.Direction.DESC,"publishDate"));
		return pageWebSiteInfo.getContent();
	}

	@Override
	public Long getCount(Timeline timeline) {
		// TODO Auto-generated method stub
		Long count=timelineRepository.count(new Specification<Timeline>() {
			
			@Override
			public Predicate toPredicate(Root<Timeline> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate=cb.conjunction();
				if(timeline!=null) {
					if(StringUtil.isNotEmpty(timeline.getYear())) {
						predicate.getExpressions().add(cb.like(root.get("year"),"%"+timeline.getYear().trim()+"%"));
					}
					if(StringUtil.isNotEmpty(timeline.getMonth())) {
						predicate.getExpressions().add(cb.like(root.get("month"),"%"+timeline.getMonth().trim()+"%"));
					}
				}
				return predicate;
			}
		});
		return count;
	}

	@Override
	public Timeline findById(Integer id) {
		// TODO Auto-generated method stub
		return timelineRepository.getOne(id);
	}

	@Override
	public void save(Timeline timeline) {
		// TODO Auto-generated method stub
		timelineRepository.save(timeline);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		timelineRepository.deleteById(id);
	}

}
