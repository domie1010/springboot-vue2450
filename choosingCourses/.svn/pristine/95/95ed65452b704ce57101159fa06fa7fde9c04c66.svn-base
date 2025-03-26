package com.longwang.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import com.longwang.entity.User;
import com.longwang.repository.UserRepository;
import com.longwang.service.UserService;
import com.longwang.util.StringUtil;

/**
 * 用户Service接口实现类
 * @author LongWang
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

  @Resource
  private UserRepository userRepository;

  @Override
  public List<User> list(User user, String s_bregistrationDate, String s_eregistrationDate, Integer page,
      Integer pageSize) {
    // TODO Auto-generated method stub
    Page<User> pageUser = userRepository.findAll(new Specification<User>() {
      @Override
      public Predicate toPredicate(Root<User> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
        // TODO Auto-generated method stub
        Predicate predicate = cb.conjunction();
        if (StringUtil.isNotEmpty(s_bregistrationDate)) {
          predicate.getExpressions()
              .add(cb.greaterThanOrEqualTo(root.get("latelyLoginTime").as(String.class), s_bregistrationDate));
        }
        if (StringUtil.isNotEmpty(s_eregistrationDate)) {
          predicate.getExpressions()
              .add(cb.lessThanOrEqualTo(root.get("latelyLoginTime").as(String.class), s_eregistrationDate));
        }
        if (user != null) {
          if (StringUtil.isNotEmpty(user.getSex())) {
            predicate.getExpressions().add(cb.equal(root.<String> get("sex"), user.getSex()));
          }
          if (StringUtil.isNotEmpty(user.getNickname())) {
            predicate.getExpressions().add(cb.like(root.get("nickname"), "%" + user.getNickname().trim() + "%"));
          }
        }
        return predicate;
      }
    }, PageRequest.of(page, pageSize, Sort.Direction.DESC, "registrationDate"));
    return pageUser.getContent();
  }

  @Override
  public Long getCount(User user, String s_bregistrationDate, String s_eregistrationDate) {
    // TODO Auto-generated method stub
    Long count = userRepository.count(new Specification<User>() {
      @Override
      public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        // TODO Auto-generated method stub
        Predicate predicate = cb.conjunction();
        if (StringUtil.isNotEmpty(s_bregistrationDate)) {
          predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("latelyLoginTime").as(LocalDateTime.class),
              LocalDate.now().atTime(0, 0, 0)));
        }
        if (StringUtil.isNotEmpty(s_eregistrationDate)) {
          predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("latelyLoginTime").as(LocalDateTime.class),
              LocalDate.now().atTime(23, 59, 59)));
        }
        if (user != null) {
          if (StringUtil.isNotEmpty(user.getSex())) {
            predicate.getExpressions().add(cb.equal(root.<String> get("sex"), user.getSex()));
          }
          if (StringUtil.isNotEmpty(user.getNickname())) {
            predicate.getExpressions().add(cb.like(root.get("nickname"), "%" + user.getNickname().trim() + "%"));
          }
        }
        return predicate;
      }
    });
    return count;
  }

  @Override
  public Long getTodayRegistCount(User user, String s_bregistrationDate, String s_eregistrationDate) {
    // TODO Auto-generated method stub
    Long count = userRepository.count(new Specification<User>() {

      @Override
      public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        // TODO Auto-generated method stub
        Predicate predicate = cb.conjunction();
        if (StringUtil.isNotEmpty(s_bregistrationDate)) {
          predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("registrationDate").as(LocalDateTime.class),
              LocalDate.now().atTime(0, 0, 0)));
        }
        if (StringUtil.isNotEmpty(s_eregistrationDate)) {
          predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("registrationDate").as(LocalDateTime.class),
              LocalDate.now().atTime(23, 59, 59)));
        }
        if (user != null) {
          if (StringUtil.isNotEmpty(user.getSex())) {
            predicate.getExpressions().add(cb.equal(root.<String> get("sex"), user.getSex()));
          }
          if (StringUtil.isNotEmpty(user.getNickname())) {
            predicate.getExpressions().add(cb.like(root.get("nickname"), "%" + user.getNickname().trim() + "%"));
          }
        }
        return predicate;
      }
    });
    return count;
  }

  @Override
  public void delete(Integer id) {
    // TODO Auto-generated method stub
    userRepository.deleteById(id);
  }

  @Override
  public void save(User user) {
    // TODO Auto-generated method stub
    userRepository.save(user);
  }

  @Override
  public User findById(Integer id) {
    // TODO Auto-generated method stub
    return userRepository.findById(id).orElse(null);
  }

  @Override
  public User findByOpenId(String openId) {
    // TODO Auto-generated method stub
    return userRepository.findByOpenId(openId);
  }

  @Override
  public Integer getUserIdByTrueName(String nickname) {
    // TODO Auto-generated method stub
    return userRepository.getUserIdByTrueName(nickname);
  }

  @Override
  public User findByUsernameAndPassword(String username, String password) {
    return userRepository.findByUsernameAndPassword(username, password);
  }

  @Override
  public Long getCount() {
    return userRepository.count();
  }

  @Override
  public List<User> findByListId(List<Integer> retIds) {
    return userRepository.findAllById(retIds);
  }

  @Override
  public User findByTrueName(String nickname) {
    return userRepository.findByNickname(nickname);
  }

}
