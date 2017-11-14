import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.python.google.common.reflect.TypeToken;


public class PatternTests {

  public static void main(String args []) {
    System.out.println("");
    System.out.println("");
    System.out.println("****** GOAL of these tests is to have the resulting object");
    System.out.println("****** instance inherit from a Mammal parent class that is");
    System.out.println("****** typed with a concrete type and not a generic");
    System.out.println("****** i.e. we don't want Mammal<GenericType>");
    System.out.println("");
    System.out.println("");

    System.out.println("*** Shows generics types Mammal with TypeToken");
    Tiger<String> t = new Tiger("asdf");

    TypeToken token = TypeToken.of(t.getClass());
    for (Object obj: token.getTypes().classes()) {
      System.out.println(obj);
    }

    System.out.println("");

    System.out.println("*** Abstract subclass pattern works ***");
    BetterTiger<String> t1 = new BetterTiger<String>("asdf"){};

    TypeToken abstractPatternToken = TypeToken.of(t1.getClass());
    for (Object obj: abstractPatternToken.getTypes().classes()) {
      System.out.println(obj);
    }

    System.out.println("");

    System.out.println("*** Factory pattern doesn't work ***");
    MammalFactory<String> factory = new MammalFactory<String>() {};
    Mammal<String> factoryMammal = factory.createMammal();

    TypeToken cloneFactoryToken = TypeToken.of(factoryMammal.getClass());
    for (Object obj: cloneFactoryToken.getTypes().classes()) {
      System.out.println(obj);
    }

    System.out.println("");
    System.out.println("");
  }

  /**
   * Creates a Tiger obj but it inherits from a Mammal that has generic types
   */
  public static class Tiger<TypeT> extends Mammal<TypeT> {
    private TypeT m_attr;

    public Tiger(TypeT in) {
      m_attr = in;
    }

  }
  public static abstract class Mammal<MInput> {
    public void doThing() {
      System.out.println("thing");
    }
  }


  /**
   * Abstract Pattern
   * WORKS: creates a Tiger obj with parent Mammal that is typed with String
   */
  public abstract static class BetterTiger<TypeB> extends Mammal<TypeB> {
    private TypeB m_attr;

    public BetterTiger(TypeB in) {
      m_attr = in;
    }
  }


  /**
   * Clone Factory Pattern
   * DOESN'T WORK: instantiated Mammal is still a generic type
   */
  public abstract static class MammalFactory <FInputT> {

    public Mammal<FInputT> createMammal() {
      return new SomeMammal();
    }

    private class SomeMammal<FInputT> extends Mammal<FInputT> {
      private String m_name = "";
      private SomeMammal () {
        m_name = "John";
      }
    }
  }

}

