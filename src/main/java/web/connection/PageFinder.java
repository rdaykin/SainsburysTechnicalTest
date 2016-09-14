package web.connection;
import java.io.IOException;
import org.jsoup.Connection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PageFinder {

	public Document findPage(String url) throws IOException{
		Connection connection = getConnection(url);
		return connection.get();
	}

	public double measureSizeKb(String url) throws IOException {
		Connection connection = getConnection(url);
		double byteSize = connection.execute().bodyAsBytes().length;
		return byteSize/1024;
		
	}
	
	Connection getConnection(String url){
		Connection connection = Jsoup.connect(url);
		return connection;
	}

}
