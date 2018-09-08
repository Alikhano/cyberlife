package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.model.Cart;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CartMapper {
	
	CartDTO cartToCartDTO(Cart cart);
	Cart cartDTOtoCart(CartDTO cartDTO);

}