import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.python.google.common.reflect.TypeToken;


public class test {

  public static void main(String args []) {

    System.out.println("*** Show generics types with TypeToken");
    Tiger<Integer, Integer> t = new Tiger(3, 6);

    TypeToken token = TypeToken.of(t.getClass());
    for (Object obj: token.getTypes().classes()) {
      System.out.println(obj);
    }
    

    System.out.println("*** Some other test...");
    Mammal<String, String> m = new <String, String> Mammal() {
      @DoThing
      public void doThing(Integer in) {
        System.out.println("1234");
      }
    };
    token = TypeToken.of(m.getClass());
    for (Object obj: token.getTypes().classes()) {
      System.out.println(obj);
    }
  }


  public static class Tiger<TInput, TOutput> extends Mammal<TInput, TOutput> {
    TInput m_in;
    TOutput m_out;

    public Tiger(TInput in, TOutput out) {
      m_in = in;
      m_out = out;
    }

    @DoThing
    public void doThing(TInput in) {
      if (in != m_in) {
        System.out.println("MISMATCH");
      }
      System.out.println(m_out);
    }    

  }


  public static abstract class Mammal<MInput, MOutput> {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface DoThing {};
  }

}

