package com.longwang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longwang.entity.Notice;
import com.longwang.repository.NoticeRepository;
import com.longwang.service.NoticeService;

/**
 * 公告Service接口实现类
 * @author LongWang
 *
 */
@Service("noticeService")
@Transactional
public class NoticeServiceImpl implements NoticeService {

	@Resource
	private NoticeRepository noticeRepository;

	//分页查询公告
	@Override
	public List<Notice> list(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return noticeRepository.findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC,"publishDate")).getContent();
	}

	@Override
	public Long getCount() {
		// TODO Auto-generated method stub
		return noticeRepository.count();
	}

	@Override
	public Notice findById(Integer id) {
		// TODO Auto-generated method stub
		return noticeRepository.getOne(id);
	}

	@Override
	public void save(Notice notice) {
		// TODO Auto-generated method stub
		noticeRepository.save(notice);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		noticeRepository.deleteById(id);
	}

}
