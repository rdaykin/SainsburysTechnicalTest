package web.scraper.impl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import web.scraper.exception.IncorrectElementException;
import web.scraper.impl.TechTestProductDetailsPageScraper;

public class TestTechTestProductDetailsPageScraper {
	
	TechTestProductDetailsPageScraper pageScraper;
	
	@Mock
	Document goodDocument;
	
	@Mock
	Document tooFewDocument;
	
	@Mock
	Document tooManyDocument;
	
	@Mock
	Elements goodTitleElements;
	
	@Mock
	Element goodTitleElement;
	
	@Mock
	Elements goodDescriptionElements;
	
	@Mock
	Element goodDescriptionElement;
	
	@Mock
	Elements goodPricePerUnitElements;
	
	@Mock
	Element goodPricePerUnitElement;
	
	@Mock
	Elements tooFewTitleElements;
	
	@Mock
	Elements tooFewDescriptionElements;
	
	@Mock
	Elements tooManyTitleElements;
	
	@Mock
	Elements tooManyDescriptionElements;
	
	@Mock
	Element tooFewTitleElement;
	
	@Mock
	Element tooFewDescriptionElement;
	
	@Mock
	Element tooManyTitleElement;
	
	@Mock
	Element tooManyDescriptionElement;
	
	@Mock
	Elements tooManyPricePerUnitElements;
	
	@Mock
	Elements tooFewPricePerUnitElements;
	
	@Mock
	Element tooManyPricePerUnitElement;
	
	@Mock
	Element tooFewPricePerUnitElement;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		when(goodDocument.getElementsByTag("title")).thenReturn(goodTitleElements);
		when(goodDocument.getElementsByAttributeValue("name","description")).thenReturn(goodDescriptionElements);
		when(goodDocument.getElementsByClass("pricePerUnit")).thenReturn(goodPricePerUnitElements);
		when(goodTitleElements.size()).thenReturn(1);
		when(goodTitleElements.get(0)).thenReturn(goodTitleElement);
		when(goodDescriptionElements.size()).thenReturn(1);
		when(goodDescriptionElements.get(0)).thenReturn(goodDescriptionElement);
		when(goodPricePerUnitElements.size()).thenReturn(1);
		when(goodPricePerUnitElements.get(0)).thenReturn(goodPricePerUnitElement);
		when(tooFewDocument.getElementsByTag("title")).thenReturn(tooFewTitleElements);
		when(tooFewDocument.getElementsByAttributeValue("name","description")).thenReturn(tooFewDescriptionElements);
		when(tooFewDocument.getElementsByClass("pricePerUnit")).thenReturn(tooFewPricePerUnitElements);
		when(tooFewTitleElements.size()).thenReturn(0);
		when(tooFewTitleElements.get(0)).thenReturn(tooFewTitleElement);
		when(tooFewDescriptionElements.size()).thenReturn(0);
		when(tooFewDescriptionElements.get(0)).thenReturn(tooFewDescriptionElement);
		when(tooFewPricePerUnitElements.size()).thenReturn(0);
		when(tooFewPricePerUnitElements.get(0)).thenReturn(tooFewPricePerUnitElement);
		when(tooManyDocument.getElementsByTag("title")).thenReturn(tooManyTitleElements);
		when(tooManyDocument.getElementsByAttributeValue("name","description")).thenReturn(tooManyDescriptionElements);
		when(tooManyDocument.getElementsByClass("pricePerUnit")).thenReturn(tooManyPricePerUnitElements);
		when(tooManyTitleElements.size()).thenReturn(2);
		when(tooManyTitleElements.get(0)).thenReturn(tooManyTitleElement);
		when(tooManyDescriptionElements.size()).thenReturn(2);
		when(tooManyDescriptionElements.get(0)).thenReturn(tooManyDescriptionElement);
		when(tooManyPricePerUnitElements.size()).thenReturn(2);
		when(tooManyPricePerUnitElements.get(0)).thenReturn(tooManyPricePerUnitElement);
		pageScraper = new TechTestProductDetailsPageScraper();
	}
	
	@Test
	public void testGetTitleElementGetsCorrectElementForValidDocument() throws IncorrectElementException{
		Element element = pageScraper.getTitleElement(goodDocument);
		assertEquals(goodTitleElement,element);
	}
	
	@Test(expected = IncorrectElementException.class)
	public void testGetTitleElementThrowsErrorForDocumentWithNoElementsOfTag() throws IncorrectElementException{
		Element element = pageScraper.getTitleElement(tooFewDocument);
	}
	
	@Test
	public void testGetDescriptionElementGetsCorrectElementForValidDocument() throws IncorrectElementException{
		Element element = pageScraper.getProductDescriptionElement(goodDocument);
		assertEquals(goodDescriptionElement,element);
	}
	
	@Test(expected = IncorrectElementException.class)
	public void testGetDescriptionElementThrowsErrorForDocumentWithNoElementsOfAttributeValue() throws IncorrectElementException{
		Element element = pageScraper.getProductDescriptionElement(tooFewDocument);
	}
	
	@Test
	public void testGetDescriptionElementPrintsWarningForDocumentWithMultipleElementsOfAttributeValue() throws IncorrectElementException{
		Element element = pageScraper.getProductDescriptionElement(tooManyDocument);
		assertEquals(tooManyDescriptionElement,element);
	}
	
	@Test
	public void testGetPricePerUnitElementGetsCorrectElementForValidDocument() throws IncorrectElementException{
		Element element = pageScraper.getPricePerUnitElement(goodDocument);
		assertEquals(goodPricePerUnitElement,element);
	}
	
	@Test(expected = IncorrectElementException.class)
	public void testGetPricePerUnitElementThrowsErrorForDocumentWithNoElementsOfClass() throws IncorrectElementException{
		Element element = pageScraper.getPricePerUnitElement(tooFewDocument);
	}
	
	@Test
	public void testGetPricePerUnitElementPrintsWarningForDocumentWithMultipleElementsOfClass() throws IncorrectElementException{
		Element element = pageScraper.getPricePerUnitElement(tooManyDocument);
		assertEquals(tooManyPricePerUnitElement,element);
	}
}
