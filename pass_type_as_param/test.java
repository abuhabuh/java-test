import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import java.lang.reflect.ParameterizedType;

import org.python.google.common.reflect.TypeToken;

// todo: Look at these examples
// - https://stackoverflow.com/questions/6596772/java-create-an-object-whose-type-is-a-type-parameter
// https://www.google.com/search?ei=EicHWqK3Goq_jwS1_peoCA&q=java+unable+to+specify+generic+type+at+compile+time+typetoken&oq=java+unable+to+specify+generic+type+at+compile+time+typetoken&gs_l=psy-ab.3...35697.39904.0.40328.12.9.1.0.0.0.257.828.2j3j1.6.0....0...1.1.64.psy-ab..5.2.356...33i21k1.0.VBwdh85_kxk


/*

  TODO: figure out how to instantiate a generic correctly
  Erroring right now because not instantiating the generic with newInstance() correctly


*/

public class test {

  public static void main(String args []) throws InstantiationException, IllegalAccessException {
    TypeToken token;

    System.out.println("*******************");
    Mammal m = Tiger.<String, String>of(MyMammal.class, "asdf", "asdf", new Mammal<String, String>() {
      @DoThing
      public void doThing() {
        System.out.println("real mammal: its me");
      }
    });
    token = TypeToken.of(m.getClass());
    for (Object obj: token.getTypes().classes()) {
      System.out.println(obj);
    }
    System.out.println("*******************");

    System.out.println("***");
    System.out.println("***");
    System.out.println("***");

    System.out.println("*******************");
    Tiger<Integer, Integer> t = new Tiger(3, 6);

    token = TypeToken.of(t.getClass());
    for (Object obj: token.getTypes().classes()) {
      System.out.println(obj);
    }
    System.out.println("*******************");

  }

  
  public static class Tiger<TInput, TOutput> extends Mammal<TInput, TOutput> {
    TInput m_in;
    TOutput m_out;

    public Tiger(TInput in, TOutput out) {
      m_in = in;
      m_out = out;
    }

    public static <TInput, TOutput> Mammal<TInput, TOutput> of (
        Class<?> cls, TInput in, TOutput out, Mammal aMammal) throws InstantiationException, IllegalAccessException {

      Class<?> t1 = (Class<?>) ((ParameterizedType) cls.getGenericSuperclass()).getActualTypeArguments()[0];
      System.out.println("t1: " + t1);
      Class<?> t2 = (Class<?>) ((ParameterizedType) cls.getGenericSuperclass()).getActualTypeArguments()[1];
      System.out.println("t1: " + t2);
      
      return (Mammal<TInput, TOutput>) cls.newInstance();
    }

    /* Option B
    public static <TInput, TOutput> Mammal<TInput, TOutput> of (
        TInput in, TOutput out, Mammal aMammal) throws InstantiationException, IllegalAccessException {
      Class<?> t1 = (Class<?>) ((ParameterizedType) aMammal.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
      System.out.println("t1: " + t1);
      Class<?> t2 = (Class<?>) ((ParameterizedType) aMammal.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
      System.out.println("t1: " + t2);
      
      // todo: try to instantiate a class given typed parameters passed in

      return new Mammal<TInput, TOutput>() {
        @DoThing
        public void doThing(TInput in) {
          aMammal.say();
        }    
      };
    }
    */


  }


  public static abstract class Mammal<MInput, MOutput> {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface DoThing {};

    public void say() {
      System.out.println("mooooo");
    }
  }

  public static abstract class MyMammal extends Mammal<String, String> {}

}

