package controller.web;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import product.Product;
import product.factory.ProductFactory;
import web.connection.PageFinder;
import web.scraper.exception.IncorrectElementException;
import web.scraper.impl.TechTestProductDetailsPageScraper;
import web.scraper.impl.TechTestProductListPageScraper;

public class WebController {
	
	@Autowired
	TechTestProductListPageScraper listPageScraper;

	@Autowired
	TechTestProductDetailsPageScraper detailsPageScraper;

	@Autowired
	ProductFactory productFactory;

	@Autowired
	PageFinder pageFinder;
	
	public Product[] getProductsFromUrl(String url) throws IOException {
		Document doc = pageFinder.findPage(url);
		String[] productElements = listPageScraper.getLinksFromListPage(doc);
		Product[] products = new Product[productElements.length];
		double pageSize;
		String productPageTitle;
		String productPageDescription;
		double productPagePrice;
		for(int i=0;i<productElements.length;i++){
			try {
				doc = pageFinder.findPage(productElements[i]);
			} catch (IOException e) {
				products[i] = productFactory.createProduct("", "", 0, 0);
				e.printStackTrace();
				continue;
			}
			try {
				pageSize = pageFinder.measureSizeKb(productElements[i]);
			} catch (IOException e) {
				pageSize = 0;
				e.printStackTrace();
			}
			try {
				productPageTitle = detailsPageScraper.getProductTitle(doc);
			} catch (IncorrectElementException e) {
				productPageTitle = "";
				e.printStackTrace();
			}
			try {
				productPageDescription = detailsPageScraper.getProductDescription(doc);
			} catch (IncorrectElementException e) {
				productPageDescription = "";
				e.printStackTrace();
			}
			productPagePrice = detailsPageScraper.getProductPricePerUnit(doc);
			products[i] = productFactory.createProduct(productPageTitle, productPageDescription, productPagePrice, pageSize);
		}
		
		return products;
	}
	
	public void setPageFinder(PageFinder pageFinder){
		this.pageFinder = pageFinder;
	}
	
	public void setProductFactory(ProductFactory productFactory){
		this.productFactory = productFactory;
	}
	
	public void setDetailsPageScraper(TechTestProductDetailsPageScraper detailsPageScraper){
		this.detailsPageScraper = detailsPageScraper;
	}
	
	public void setTechTestProductListPageScraper(TechTestProductListPageScraper listPageScraper){
		this.listPageScraper = listPageScraper;
	}

}
