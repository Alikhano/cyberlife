package ru.alikhano.cyberlife.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
	private int cartId;
	private double grandTotal;
	private Set<CartItemDTO> items;
}
