package com.longwang.service;

import java.util.List;

import com.longwang.entity.Classify;

/**
 * 文章类别service接口
 * 
 * @author LongWang
 *
 */
public interface ClassifyService {

  /**
   * 分页查询文章类别
   * 
   * @param page
   * @param pageSize
   * @return
   */
  public List<Classify> list(Integer page, Integer pageSize);

  /**
   * 获取总记录数
   * 
   * @return
   */
  public Long getCount();

  /**
   * 根据Id查找文章类别
   * 
   * @param id
   * @return
   */
  public Classify findById(Integer id);

  /**
   * 添加或修改文章类别
   * 
   * @param classify
   */
  public void save(Classify classify);

  /**
   * 根据Id删除文章类别
   * 
   * @param id
   */
  public void delete(Integer id);

  public List<Classify> findAll();
}
