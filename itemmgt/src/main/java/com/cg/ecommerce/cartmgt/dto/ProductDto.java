package com.cg.ecommerce.cartmgt.dto;

public class ProductDto 
{
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
}
