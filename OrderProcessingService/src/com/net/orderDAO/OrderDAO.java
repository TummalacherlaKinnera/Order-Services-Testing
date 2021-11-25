package com.net.orderDAO;

import java.sql.SQLException;

import com.net.orderDTO.Order;

public interface OrderDAO {
	int create(Order order) throws SQLException;
	Order read(int id) throws SQLException;
	int update(Order order) throws SQLException;
	int delete(int id) throws SQLException;
	
	
}
