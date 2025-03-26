package com.longwang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.longwang.entity.Reply;

/**
 * 回复Repository接口
 * @author LongWang
 *
 */
public interface ReplyRepository extends JpaRepository<Reply, Integer>,JpaSpecificationExecutor<Reply> {

}
