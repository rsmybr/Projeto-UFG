package util;

public class ConectaException extends Exception {
	
	public ConectaException(){
		
	}
	public ConectaException(String arg){
		super(arg);
	}
	public ConectaException(Throwable arg) {
		super(arg);
	}
	public ConectaException(String arg, Throwable arg1){
		super(arg, arg1);
	}

}
