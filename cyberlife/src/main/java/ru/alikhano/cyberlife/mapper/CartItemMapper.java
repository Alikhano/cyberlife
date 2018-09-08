package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.DTO.CartItemDTO;
import ru.alikhano.cyberlife.DTO.CategoryDTO;
import ru.alikhano.cyberlife.DTO.ConsDTO;
import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.model.CartItem;
import ru.alikhano.cyberlife.model.Category;
import ru.alikhano.cyberlife.model.Consciousness;
import ru.alikhano.cyberlife.model.Product;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CartItemMapper {
	
	CartItemDTO cartItemToCartItemDTO(CartItem carItem);
	CartItem cartDTOtoCartItem(CartItemDTO cartItemDTO);
	
	ProductDTO productToProductDTO(Product product);
	Product productDTOtOProduct(ProductDTO productDTO);
	
	CategoryDTO categoryToCategoryDTO(Category category);
	Category categoryDTOtoCategory(CategoryDTO categoryDTO);
	
	ConsDTO consToConsDTO(Consciousness cons);
	Consciousness consDTOtoCons(ConsDTO consDTO);


}
