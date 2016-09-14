package web.scraper.impl;

import static org.junit.Assert.*;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;


import web.scraper.exception.IncorrectElementException;
import web.scraper.impl.TechTestProductListPageScraper;

public class TestTechTestProductListPageScraper {

	TechTestProductListPageScraper pageScraper;
	
	@Mock
	Document doc;
	
	@Mock
	Document doc2;
	
	@Mock
	Document doc3;
	
	@Mock
	Elements productInfoElements;
	
	@Mock
	Elements partialProductInfoElements;
	
	@Mock
	Elements emptyProductInfoElements;
	
	@Mock
	Element element;
	
	@Mock
	Element element2;
	
	@Mock
	Element element3;
	
	@Mock
	Element noLinksElement;
	
	@Mock
	Element tooManyLinksElement;
	
	@Mock
	Element emptyLinkElement;
	
	@Mock
	Elements elements;
	
	@Mock
	Elements elements2;
	
	@Mock
	Elements elements3;
	
	@Mock
	Elements noLinks;
	
	@Mock
	Elements tooManyLinks;
	
	@Mock
	Elements emptyLinks;
	
	@Mock
	Element linkElement;
	
	@Mock
	Element linkElement2;
	
	@Mock
	Element linkElement3;
	
	@Mock
	Element emptyLink;
	
	
	String link = "http://sainsburys.co.uk/linkedProduct";
	
	String link2 = "http://sainsburys.co.uk/linkedProduct2";
	
	String link3 = "http://sainsburys.co.uk/linkedProduct3";
	
	String error1 = "There were no link elements.";
	String error2 = "Only 1 element is allowed.  There were 2 link elements.";
	String error3 = "The link contains no data.";
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		when(doc.getElementsByClass("productInfo")).thenReturn(productInfoElements);
		when(doc2.getElementsByClass("productInfo")).thenReturn(partialProductInfoElements);
		when(doc3.getElementsByClass("productInfo")).thenReturn(emptyProductInfoElements);
		when(productInfoElements.size()).thenReturn(3);
		when(productInfoElements.get(0)).thenReturn(element);
		when(productInfoElements.get(1)).thenReturn(element2);
		when(productInfoElements.get(2)).thenReturn(element3);
		when(partialProductInfoElements.size()).thenReturn(6);
		when(partialProductInfoElements.get(0)).thenReturn(element);
		when(partialProductInfoElements.get(1)).thenReturn(noLinksElement);
		when(partialProductInfoElements.get(2)).thenReturn(element2);
		when(partialProductInfoElements.get(3)).thenReturn(tooManyLinksElement);
		when(partialProductInfoElements.get(4)).thenReturn(element3);
		when(partialProductInfoElements.get(5)).thenReturn(emptyLinkElement);
		when(emptyProductInfoElements.size()).thenReturn(0);
		when(element.getElementsByAttribute("href")).thenReturn(elements);
		when(element2.getElementsByAttribute("href")).thenReturn(elements2);
		when(element3.getElementsByAttribute("href")).thenReturn(elements3);
		when(noLinksElement.getElementsByAttribute("href")).thenReturn(noLinks);
		when(tooManyLinksElement.getElementsByAttribute("href")).thenReturn(tooManyLinks);
		when(tooManyLinksElement.absUrl("href")).thenReturn(link);
		when(emptyLinkElement.getElementsByAttribute("href")).thenReturn(emptyLinks);
		when(elements.size()).thenReturn(1);
		when(elements2.size()).thenReturn(1);
		when(elements3.size()).thenReturn(1);
		when(noLinks.size()).thenReturn(0);
		when(tooManyLinks.size()).thenReturn(2);
		when(emptyLinks.size()).thenReturn(1);
		when(elements.get(0)).thenReturn(linkElement);
		when(elements2.get(0)).thenReturn(linkElement2);
		when(elements3.get(0)).thenReturn(linkElement3);
		when(emptyLinks.get(0)).thenReturn(emptyLink);
		when(emptyLink.absUrl("href")).thenReturn("");
		when(linkElement.absUrl("href")).thenReturn(link);
		when(linkElement2.absUrl("href")).thenReturn(link2);
		when(linkElement3.absUrl("href")).thenReturn(link3);
		when(tooManyLinks.get(0)).thenReturn(linkElement);
		pageScraper = new TechTestProductListPageScraper();
	}
	
	
	@Test
	public void testGetLinksFromElementReturnsTheCorrectUrlForValidElement() throws IncorrectElementException{
		String s = pageScraper.getLinkFromElement(element);
		assertEquals(link,s);
	}
	
	@Test(expected=IncorrectElementException.class)
	public void testGetLinksFromElementThrowsIncorrectElementExceptionForTooShortLinkElements() throws IncorrectElementException{
		String s = pageScraper.getLinkFromElement(noLinksElement);
		assertEquals(link,s);
	}
	
	@Test(expected=IncorrectElementException.class)
	public void testGetLinksFromElementThrowsIncorrectElementExceptionForEmptyLink() throws IncorrectElementException{
		String s = pageScraper.getLinkFromElement(emptyLinkElement);
	}
	
	@Test
	public void testGetLinksFromListPageReturnsCorrectLinksForValidDocument() throws IncorrectElementException{
		String[] s = pageScraper.getLinksFromListPage(doc);
		assertEquals(link,s[0]);
		assertEquals(link2,s[1]);
		assertEquals(link3,s[2]);
	}
	
	@Test
	public void testGetLinksFromListPageReturnsCorrectLinksAndFailureMessagesForPartiallyValidDocument() throws IncorrectElementException{
		String[] s = pageScraper.getLinksFromListPage(doc2);
		assertEquals(link,s[0]);
		assertEquals(error1,s[1]);
		assertEquals(link2,s[2]);
		assertEquals(link,s[3]);
		assertEquals(link3,s[4]);
		assertEquals(error3,s[5]);
	}
	
	@Test(expected = IncorrectElementException.class)
	public void testGetLinksFromListPageReturnsCorrectLinksAndFailureMessagesForInvalidDocument() throws IncorrectElementException{
		String[] s = pageScraper.getLinksFromListPage(doc3);
	}
	
	@Test
	public void testGetProductInfoElementsReturnsCorrectElementsObjectFromDocument(){
		Elements elements = pageScraper.getProductInfoElements(doc);
		assertEquals(productInfoElements,elements);
	}
	
}
