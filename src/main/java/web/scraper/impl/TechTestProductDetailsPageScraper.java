package web.scraper.impl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import web.scraper.TechTestProductPageScraper;
import web.scraper.exception.IncorrectElementException;

public class TechTestProductDetailsPageScraper extends TechTestProductPageScraper{

	protected Element getTitleElement(Document doc) throws IncorrectElementException {
		return getSingleElementByTag(doc,"title");
	}

	protected Element getProductDescriptionElement(Document doc) throws IncorrectElementException {
		return getSingleElementByAttributeValue(doc,"name","description");
	}

	protected Element getPricePerUnitElement(Document doc) throws IncorrectElementException {
		return getSingleElementByClass(doc,"pricePerUnit");
	}

	public String getProductTitle(Document doc) throws IncorrectElementException {
		Element element = getTitleElement(doc);
		return element.text();
	}

	public String getProductDescription(Document doc) throws IncorrectElementException {
		Element element = getProductDescriptionElement(doc);
		return element.attr("content");
	}

	public double getProductPricePerUnit(Document doc){
		try{
			Element element = getPricePerUnitElement(doc);
			return Double.parseDouble(element.ownText().substring(1));
		}catch(IncorrectElementException e){
			return 0.0;
		}
	}
	
	

}
