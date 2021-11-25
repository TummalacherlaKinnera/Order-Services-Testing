package com.net.orderBO;

import com.net.orderBO.Exception.BOException;
import com.net.orderDTO.Order;

public interface OrderBO {
	boolean placeOrder(Order order)throws BOException;
	boolean cancelOrder(int id)throws BOException;
	boolean deleteOrder(int id)throws BOException;
}
