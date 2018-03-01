import java.io.Serializable;

public class TestClass implements Serializable{

  public String firstName = "private testClass firstName";
  public String lastName = "private testclass lastName";

  public void printMe () {
    System.out.println(firstName + lastName);
  }

}
