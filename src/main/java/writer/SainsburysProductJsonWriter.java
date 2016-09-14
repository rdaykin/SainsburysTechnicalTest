package writer;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;

import product.Product;

public class SainsburysProductJsonWriter {
	
	@Autowired
	DecimalFormat kbFormat;
	
	@Autowired
	DecimalFormat moneyFormat;

	public void displayProductsAsJson(Product[] products){
		
		double total = getTotal(products);
		//Create JSON array from products
		//kbFormat.setRoundingMode(RoundingMode.HALF_UP);
		//moneyFormat.setRoundingMode(RoundingMode.HALF_UP);
		printJsonStart();
		printProducts(products);
		printJsonEnd(total);
	}
	
	private void printJsonStart(){
		System.out.println("{");
		System.out.println("\t\"results\":[");
	}
	
	private void printJsonEnd(double total){
		System.out.println("\t],");
		System.out.println("\t\"total\": "+ moneyFormat.format(total));
		System.out.println("}");
	}
	
	private void printProducts(Product[] products){
		for(int i=0;i<products.length;i++){
			printOpenObject();
			printName(products[i]);
			printPageSize(products[i]);
			printPricePerUnit(products[i]);
			printDescription(products[i]);
			printCloseObject(i!=products.length-1);
		}
	}
	
	protected void printOpenObject(){
		System.out.println("\t\t{");
	}
	
	protected void printCloseObject(boolean lastObject){
		if(lastObject){
			System.out.println("\t\t},");
		}else{
			System.out.println("\t\t}");
		}
	}
	
	protected void printName(Product product){
		System.out.println("\t\t\t\"title\": \"" + product.getName()+"\"");
	}
	
	protected void printPageSize(Product product){
		System.out.println("\t\t\t\"size\": \"" + kbFormat.format(product.getPageSize())+"kb"+"\"");
	}
	
	protected void printPricePerUnit(Product product){
		System.out.println("\t\t\t\"unit_price\": \"" + moneyFormat.format(product.getPricePerUnit())+"\"");
	}
	
	protected void printDescription(Product product){
		System.out.println("\t\t\t\"description\" \": "+ product.getDescription()+"\"");
	}
	
	private double getTotal(Product[] products){
		double total = 0;
		for(Product product: products){
			total += product.getPricePerUnit();
		}
		return total;
	}
	
}
