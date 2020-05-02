package com.cg.ecommerce.productmgt.entities;

import java.util.Objects;

public class Product {

    private String productId;

    public String getProductId(){
        return productId;
    }

    public void setProductId(String id){
        this.productId=id;
    }

    private double price;
    
    public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	private String productName;

    public String getProductName(){
        return productName;
    }

    public void setProductName(String name){
        this.productName=name;
    }

    private int availableItemsCount;

	public int getAvailableItemsCount() {
		return availableItemsCount;
	}

	public void setAvailableItemsCount(int availableItemsCount) {
		this.availableItemsCount = availableItemsCount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Product product = (Product) o;
		return productId.equals(product.productId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId);
	}

    
}
