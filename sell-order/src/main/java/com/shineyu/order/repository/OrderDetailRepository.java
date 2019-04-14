package com.shineyu.order.repository;

import com.shineyu.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shineYu
 * @Date 19-3-5 上午11:44
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {



}
