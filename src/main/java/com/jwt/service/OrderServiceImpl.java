package com.jwt.service;

import com.jwt.dao.OrderDao;
import com.jwt.model.Orders;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public void CreateOrder(Orders order) {
		order.setDate(new Date());
		orderDao.CreateOrder(order);

	}

	@Override
	public void editOrder(Orders order) {
		orderDao.editOrder(order);
	}

	@Override
	public void deleteOrder(Orders order) {

		orderDao.deleteOrder(order);
	}

}
