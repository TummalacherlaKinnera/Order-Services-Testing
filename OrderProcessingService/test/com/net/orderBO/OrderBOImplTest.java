package com.net.orderBO;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import com.net.orderBO.Exception.BOException;
import com.net.orderDAO.OrderDAO;
import com.net.orderDTO.Order;

public class OrderBOImplTest {
	@Mock
	OrderDAO dao;
	private OrderBOImpl bo;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		bo = new OrderBOImpl();
		bo.setDao(dao);
	}
	@Test
	public void placeOrderShouldCreateAnOrder() throws SQLException,BOException{
		
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(1));
		boolean result=bo.placeOrder(order);
		assertTrue(result);
		verify(dao).create(order);
	}
	@Test
	public void placeOrderShouldNotCreateAnOrder() throws SQLException,BOException{
		
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(0));
		boolean result=bo.placeOrder(order);
		assertFalse(result);
		verify(dao).create(order);
	}
	@Test(expected=BOException.class)
	public void placeOrderShouldThrowBOException() throws SQLException,BOException{
		
		Order order = new Order();
		when(dao.create(order)).thenThrow(SQLException.class);
		boolean result=bo.placeOrder(order);
		
	}
	@Test
	public void cancelOrderShouldCanceTheOrder() throws SQLException, BOException{
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenReturn(1);
		boolean result =bo.cancelOrder(123);
		assertTrue(result);
		verify(dao).read(123);
		verify(dao).update(order);
		
	}
	@Test
	public void cancelOrderShouldNotCancelTheOrder() throws SQLException, BOException{
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenReturn(0);
		boolean result =bo.cancelOrder(123);
		assertFalse(result);
		verify(dao).read(123);
		verify(dao).update(order);
		
	}
	@Test(expected=BOException.class)
	public void cancelOrderShouldThrowBOException() throws SQLException, BOException{
		Order order = new Order();
		when(dao.read(123)).thenThrow(SQLException.class);
		
		bo.cancelOrder(123);
		
	}
	@Test(expected=BOException.class)
	public void cancelOrderShouldThrowBOExceptionOnUpdate() throws SQLException, BOException{
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenThrow(SQLException.class);
		bo.cancelOrder(123);
		
		
	}
}
