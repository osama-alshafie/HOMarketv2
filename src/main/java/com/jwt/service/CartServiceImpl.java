package com.jwt.service;

import com.jwt.dao.CartDao;
import com.jwt.model.Cart;
import com.jwt.model.CartItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;

	@Override
	public void AddCartItem(CartItem cartItem) {
		cartDao.AddCartItem(cartItem);
	}

	@Override
	public void EditCart(Cart cart) {

		cartDao.EditCart(cart);
	}

	@Override
	public List<Cart> getAllCarts() {

		return cartDao.getAllCarts();

	}

	@Override
	public void deleteCartItems(List<CartItem> cartItemses) {

		cartDao.deleteCartItems(cartItemses);

	}

}
