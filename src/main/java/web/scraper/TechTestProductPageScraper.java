package web.scraper;

import java.io.IOException;
import web.scraper.exception.IncorrectElementException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class TechTestProductPageScraper {
	
	protected Element getSingleElementByAttribute(String attribute,Element element) throws IncorrectElementException{
		Elements elements = element.getElementsByAttribute(attribute);
		checkElementsSize(elements);
		return elements.get(0);	
	}
	
	protected Element getSingleElementByTag(Document doc,String tag) throws IncorrectElementException{
		Elements elements = doc.getElementsByTag(tag);
		checkElementsSize(elements);
		return elements.get(0);
	}
	
	protected Element getSingleElementByAttributeValue(Document doc,String attribute, String value) throws IncorrectElementException{
		Elements elements = doc.getElementsByAttributeValue(attribute, value);
		checkElementsSize(elements);
		return elements.get(0);
	}
	
	protected Element getSingleElementByClass(Document doc,String elementClass) throws IncorrectElementException{
		Elements elements = doc.getElementsByClass(elementClass);
		checkElementsSize(elements);
		return elements.get(0);
	}
	
	protected Element getFirstElementByClass(Document doc,String elementClass) {
		Elements elements = doc.getElementsByClass(elementClass);
		return elements.get(0);
	}
	
	protected void checkElementsSize(Elements elements) throws IncorrectElementException{
		double size = elements.size();
		if(size>1){
			logNewElementWarning(size);
		}else if(size<1){
			throw new IncorrectElementException("There were no link elements.");
		}
	}
	
	public void logNewElementWarning(double size){
		System.err.println("Warning: "+size+" link elements detected.  Attempting to use first element.");
	}
}
