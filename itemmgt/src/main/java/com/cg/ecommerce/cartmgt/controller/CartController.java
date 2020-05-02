package com.cg.ecommerce.cartmgt.controller;

import com.cg.ecommerce.cartmgt.dto.AddCartItemDto;
import com.cg.ecommerce.cartmgt.dto.CartItemDetailsDto;
import com.cg.ecommerce.cartmgt.dto.ProductDto;
import com.cg.ecommerce.cartmgt.entities.CartItem;
import com.cg.ecommerce.cartmgt.service.ICartService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cartitems") //base url
public class CartController {
	
	private static Logger log = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	private ICartService service;
	
	@Value("${productservice.baseurl}")
	private String productServiceBaseUrl;
	
	 public String getProductServiceBaseUrl() {
		return productServiceBaseUrl;
	}

	public void setProductServiceBaseUrl(String productServiceBaseUrl) {
		this.productServiceBaseUrl = productServiceBaseUrl;
	}
	
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/add")
	    public ResponseEntity<Boolean> addCartItem(@RequestBody AddCartItemDto dto) {
		log.info("Inside post method");
		 CartItem cartItem = new CartItem();
		 cartItem.setProductId(dto.getProductId());
		 cartItem.setUserId(dto.getUserId());
		 log.info("Add Items");
		 cartItem=service.save(cartItem);
	        ResponseEntity<Boolean> response = new ResponseEntity<>(true, HttpStatus.OK);
	        log.info("Cart item added successfully " + cartItem.getId() +  " " + cartItem.getProductId()+ " " + cartItem.getUserId());
	        
	        return response;
	    }
	
	 @GetMapping("/userdetails/{userid}")
	 public ResponseEntity<List<CartItemDetailsDto>>cartDetails(@PathVariable("userid")int userId)
	 {
		 List<CartItem>cartItems=service.fetchCartItems(userId);
		 List<CartItemDetailsDto>desiredDtos=new ArrayList<>();
		 for(CartItem item:cartItems)
		 {
			 ProductDto productDto=fetchProductById(item.getProductId());
			 CartItemDetailsDto dto=cartItemDetailsDto(item,productDto);
			 desiredDtos.add(dto);
		 }
		 ResponseEntity<List<CartItemDetailsDto>>response=new ResponseEntity<>(desiredDtos,HttpStatus.OK);
		 return response;
	 }
	 public ProductDto fetchProductById(String id)
	 {
		 /*ProductDto dto=new ProductDto();
		 dto.setPrice(6733);
		 dto.setProductId("p1");
		 dto.setProductName("mi");
		 return dto;
		 */
		 String url=productServiceBaseUrl+"/get/"+id;
		 ProductDto dto=restTemplate.getForObject(url,ProductDto.class);
		 return dto;
	 }
	 
	 public CartItemDetailsDto cartItemDetailsDto(CartItem item, ProductDto productDto)
	 {
		 CartItemDetailsDto cartItemDetailsDto=new CartItemDetailsDto();
		 cartItemDetailsDto.setPrice(productDto.getPrice());
		 cartItemDetailsDto.setProductId(item.getProductId());
		 cartItemDetailsDto.setProductName(productDto.getProductName());
		 return cartItemDetailsDto;
	 }
	 
	 
	 
}
