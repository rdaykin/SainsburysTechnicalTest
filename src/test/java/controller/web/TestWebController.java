package controller.web;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import product.factory.ProductFactory;
import web.connection.PageFinder;
import web.scraper.exception.IncorrectElementException;
import web.scraper.impl.TechTestProductDetailsPageScraper;
import web.scraper.impl.TechTestProductListPageScraper;

import static org.mockito.Mockito.*;

public class TestWebController {
	
	WebController webController = new WebController();
	
	@Mock
	TechTestProductListPageScraper listPageScraper;

	@Mock
	TechTestProductDetailsPageScraper detailsPageScraper;

	@Mock
	ProductFactory productFactory;

	@Mock
	PageFinder pageFinder;
	
	@Mock
	Document noLinksDoc;
	
	String badUrl = "this is a bad url";
	String noLinksUrl = "http://nolinks.com";
	
	@Before
	public void init() throws IOException{
		MockitoAnnotations.initMocks(this);
		when(pageFinder.findPage(badUrl)).thenThrow(new IOException());
		when(pageFinder.findPage(noLinksUrl)).thenReturn(noLinksDoc);
		when(listPageScraper.getLinksFromListPage(noLinksDoc)).thenThrow(new IncorrectElementException("no links"));
		webController.setDetailsPageScraper(detailsPageScraper);
		webController.setPageFinder(pageFinder);
		webController.setProductFactory(productFactory);
		webController.setTechTestProductListPageScraper(listPageScraper);
	}
	
	@Test(expected=IOException.class)
	public void testGetProductsFromUrlThrowsIOExceptionForNoDocument() throws IOException{
		webController.getProductsFromUrl(badUrl);
	}
	
	@Test(expected=IncorrectElementException.class)
	public void testGetProductsFromUrlThrowsExceptionForNoLinksOnPage() throws IOException{
		webController.getProductsFromUrl(noLinksUrl);
	}

}
