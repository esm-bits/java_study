package multi_catch.sample.model;

@SuppressWarnings("serial")
public class ApplicationException extends Exception {
  public ApplicationException(Exception e) {
    super(e);
  }  
}
