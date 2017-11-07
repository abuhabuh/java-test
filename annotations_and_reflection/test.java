import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;


public class test {

  public static void main(String args []) {

    Person me = new Person () {
      @ProcessElement
      public void processElement() {
        System.out.println("processing");
      }
    };

    Method[] methods = me.getClass().getDeclaredMethods();
    for (Method method: methods) {
      if (method.isAnnotationPresent(Person.AnotherAnnotation.class)) {
        System.out.println("annotated");
      }
      System.out.println(method.getName());
      try {
        method.invoke(me);
      } catch (Exception e) {
        System.out.println("uh oh");
      }
    }
  }

  public static abstract class Person {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ProcessElement {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface AnotherAnnotation {}

    public void print() {
      System.out.println("hello");
    }
  }

}
