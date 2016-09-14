package web.scraper.exception;

import java.io.IOException;

public class IncorrectElementException extends IOException{

	private String reason;

	public IncorrectElementException(String reason){
		super();
		this.reason = reason;
	}

	@Override
	public void printStackTrace(){
		System.out.println(reason);
		super.printStackTrace();
	}

	public String getReason(){
		return reason;
	}

}


