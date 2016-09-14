package web.connection;

import static org.junit.Assert.*;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.mockito.Mockito.*;

public class TestPageFinder {

	@Spy
	PageFinder pageFinder;

	@Mock 
	Connection firstConnection;
	
	@Mock
	Connection secondConnection;
	
	@Mock 
	Connection timeoutConnection;
	
	@Mock
	Document firstPage;
	
	@Mock
	Document secondPage;
	
	@Mock
	Response response1;
	
	@Mock
	Response response2;
	
	double byteArraySize1 = 31415.0/1024.0;
	
	double byteArraySize2 = 92653.0/1024.0;
	
	byte[] byteArray1 = new byte[31415];
	
	byte[] byteArray2 = new byte[92653];

	String firstURL = "http://sainsburys.com";
	
	String secondURL = "http://sainsburys.co.uk";
	
	String malformedURL = "that's not a URL!";
	
	String timeoutURL = "http://timeoutwebsite.com";

	@Before
	public void init() throws IOException{
		MockitoAnnotations.initMocks(this);
		when(pageFinder.getConnection(firstURL)).thenReturn(firstConnection);
		when(pageFinder.getConnection(secondURL)).thenReturn(secondConnection);
		when(pageFinder.getConnection(timeoutURL)).thenReturn(timeoutConnection);
		when(firstConnection.get()).thenReturn(firstPage);
		when(secondConnection.get()).thenReturn(secondPage);
		when(timeoutConnection.get()).thenThrow(new SocketTimeoutException());
		when(firstConnection.execute()).thenReturn(response1);
		when(secondConnection.execute()).thenReturn(response2);
		when(timeoutConnection.execute()).thenThrow(new SocketTimeoutException());
		when(response1.bodyAsBytes()).thenReturn(byteArray1);
		when(response2.bodyAsBytes()).thenReturn(byteArray2);
		//when(byteArray1.length).thenReturn(31415);
		//when(byteArray2.length).thenReturn(92653);
	}

	@Test
	public void testFindPageReturnsCorrectPageDocument() throws IOException{
		Document doc1 = pageFinder.findPage(firstURL);
		Document doc2 = pageFinder.findPage(secondURL);
		assertEquals(firstPage,doc1);
		assertEquals(secondPage,doc2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindPageReturnsIllegalArgumentExceptionForBadUrl() throws IOException{
		Document doc1 = pageFinder.findPage(malformedURL);
	}
	
	@Test(expected=IOException.class)
	public void testFindPageReturnsIOExceptionForBadUrl() throws IOException{
		pageFinder.findPage(timeoutURL);
	}
	
	@Test
	public void testMeasureSizeKbReturnsTheLengthOfTheDocumentInKb() throws IOException{
		double a = pageFinder.measureSizeKb(firstURL);
		double b = pageFinder.measureSizeKb(secondURL);
		assertTrue(a==byteArraySize1);
		assertTrue(b==byteArraySize2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMeasureSizeKbReturnsIllegalArgumentExceptionForBadUrl() throws IOException{
		double a = pageFinder.measureSizeKb(malformedURL);
	}
	
	@Test(expected=IOException.class)
	public void testMeasureSizeKbReturnsIOExceptionForBadUrl() throws IOException{
		pageFinder.measureSizeKb(timeoutURL);
	}
	
	

}
