package controller;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import controller.web.WebController;
import product.Product;
import product.factory.ProductFactory;
import web.connection.PageFinder;
import web.scraper.impl.TechTestProductDetailsPageScraper;
import web.scraper.impl.TechTestProductListPageScraper;
import writer.SainsburysProductJsonWriter;

public class AppController {

	@Autowired
	WebController webController;
	
	@Autowired
	SainsburysProductJsonWriter jsonWriter;

	@Autowired 
	String url;

	public void generateJson(){
		Product[] products;
		try{
			products = webController.getProductsFromUrl(url);
			jsonWriter.displayProductsAsJson(products);
		}catch(IOException e){
			System.err.println("COULD NOT FIND REQUIRED PAGES. NO PRODUCTS RETURNED.");
		}
	}

}
