package web.scraper.impl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import web.scraper.TechTestProductPageScraper;
import web.scraper.exception.IncorrectElementException;

public class TechTestProductListPageScraper extends TechTestProductPageScraper{
	
	public String[] getLinksFromListPage(Document doc) throws IncorrectElementException{
		Elements elements = getProductInfoElements(doc);
		if(elements.size()==0){
			throw new IncorrectElementException("There are no productInfo elements in the page");
		}
		String[] links = new String[elements.size()];
		for(int i=0;i<elements.size();i++){
			try {
				links[i]=getLinkFromElement(elements.get(i));
			} catch (IncorrectElementException e) {
				links[i]=e.getReason();
			}
		}
		return links;
	}

	protected Elements getProductInfoElements(Document doc) {
		return doc.getElementsByClass("productInfo");
	}

	protected String getLinkFromElement(Element element) throws IncorrectElementException {
		Element linkElement = getSingleElementByAttribute("href",element);
		return getLinkFromLinkElement(linkElement);
	}
	
	protected String getLinkFromLinkElement(Element element) throws IncorrectElementException{
		String link = element.absUrl("href");
		if(link.length()==0){
			throw new IncorrectElementException("The link contains no data.");
		}else{
			return link;
		}
	}

}
