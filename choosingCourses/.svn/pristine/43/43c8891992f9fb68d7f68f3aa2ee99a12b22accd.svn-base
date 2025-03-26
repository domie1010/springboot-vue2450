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

import com.longwang.entity.Music;
import com.longwang.repository.MusicRepository;
import com.longwang.service.MusicService;
import com.longwang.util.StringUtil;

/**
 * 音乐Service接口实现类
 * @author LongWang
 *
 */
@Service("musicService")
@Transactional
public class MusicServiceImpl implements MusicService {

	@Resource
	private MusicRepository musicRepository;
	
	@Override
	public List<Music> list(Music music,Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		Page<Music> pageWebSiteInfo=musicRepository.findAll(new Specification<Music>() {
			
			@Override
			public Predicate toPredicate(Root<Music> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate=cb.conjunction();
				if(music!=null) {
					if(StringUtil.isNotEmpty(music.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"),"%"+music.getName().trim()+"%"));
					}
					if(StringUtil.isNotEmpty(music.getArtist())) {
						predicate.getExpressions().add(cb.like(root.get("artist"),"%"+music.getArtist().trim()+"%"));
					}
				}
				return predicate;
			}
		},PageRequest.of(page, pageSize));
		return pageWebSiteInfo.getContent();
	}

	@Override
	public Long getCount(Music music) {
		// TODO Auto-generated method stub
		Long count=musicRepository.count(new Specification<Music>() {
			
			@Override
			public Predicate toPredicate(Root<Music> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate=cb.conjunction();
				if(music!=null) {
					if(StringUtil.isNotEmpty(music.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"),"%"+music.getName().trim()+"%"));
					}
					if(StringUtil.isNotEmpty(music.getArtist())) {
						predicate.getExpressions().add(cb.like(root.get("artist"),"%"+music.getArtist().trim()+"%"));
					}
				}
				return predicate;
			}
		});
		return count;
	}

	@Override
	public Music findById(Integer id) {
		// TODO Auto-generated method stub
		return musicRepository.getOne(id);
	}

	@Override
	public void save(Music music) {
		// TODO Auto-generated method stub
		musicRepository.save(music);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		musicRepository.deleteById(id);
	}

}
