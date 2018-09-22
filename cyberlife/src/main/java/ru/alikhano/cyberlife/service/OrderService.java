package ru.alikhano.cyberlife.service;

import java.util.List;
import java.util.Map;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.OrderDTO;


public interface OrderService {
	
	void create(OrderDTO orderDTO);

	List<OrderDTO> getAll();

	OrderDTO getById(int id);

	void update(OrderDTO orderDTO);
	
	List<OrderDTO> getByCustomerId(int id);
	
	int createAndGetId(OrderDTO order);
	
	Map<Integer, Double> getMonthlyRevenue();
	
	double getWeeklyRevenue();
	
	String cartToOrder(OrderDTO orderDTO, CartDTO cartDTO, String username);

}
