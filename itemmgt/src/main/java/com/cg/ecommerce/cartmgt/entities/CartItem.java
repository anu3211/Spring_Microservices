package com.cg.ecommerce.cartmgt.entities;

import java.util.Objects;

public class CartItem 
{
private int id;
private String productId;
private int userId;


public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getProductId() {
	return productId;
}
public void setProductId(String productId) {
	this.productId = productId;
}

@Override
public boolean equals(Object o) {
	if (this == o)
		return true;
	if (o == null)
		return false;
	if (getClass() != o.getClass())
		return false;
	CartItem cartItem = (CartItem) o;
	return id==cartItem.id;
}

@Override
public int hashCode() {
	return Objects.hash(id);
}


}
