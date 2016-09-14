package product;

public class Product {
	
	private String name;
	private String description;
	private double pricePerUnit;
	private double pageSize;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getPricePerUnit() {
		return pricePerUnit;
	}
	
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	
	public double getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(double pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
