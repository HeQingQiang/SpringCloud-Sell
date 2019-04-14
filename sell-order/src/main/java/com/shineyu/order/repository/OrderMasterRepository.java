package com.shineyu.order.repository;

import com.shineyu.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 主订单
 *
 * @author shineYu
 * @Date 19-3-5 上午11:43
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {




}
