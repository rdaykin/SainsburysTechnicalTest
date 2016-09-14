package product.factory;

import product.Product;

public class ProductFactory {
	
	public Product createProduct(String name, String description, double pricePerUnit, double pageSize){
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPricePerUnit(pricePerUnit);
		product.setPageSize(pageSize);
		return product;
	}

}
