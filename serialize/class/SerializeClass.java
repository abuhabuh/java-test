import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * Serialize an bunch of things to file and read back
 * 
 * NOTES:
 * - writing Strings as primitives with writeUTF() is incompatible with writing Strings as Objects
 */
public class SerializeClass {

  public static void main(String args []) throws FileNotFoundException, IOException{
    System.out.println("=== Start test ===");
    serializeThings("fname");
    deserializeThings("fname");
  }

  private static void deserializeThings(String fname) throws FileNotFoundException, IOException {
    System.out.println("Deserialize");
    File file = new File(fname);
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
    try {
      System.out.println("> reading TestClass...");
      TestClass obj1 = (TestClass) ois.readObject();
      System.out.println(String.format(">> fname: %s", obj1.firstName));
      System.out.println(String.format(">> lname: %s", obj1.lastName));
      System.out.println("> reading String written as UTF...");
      String str2 = ois.readUTF();
      System.out.println(String.format(">> str: %s", str2));
      System.out.println("> reading String written as Obj...");
      String str3 = (String) ois.readObject();
      System.out.println(String.format(">> str: %s", str3));
    }
    catch (Exception e) {
      System.out.println(String.format("Exception deserializing: %s", e));
    }
  }

  private static void serializeThings(String fname) throws FileNotFoundException, IOException {
    TestClass obj1 = new TestClass();
    String str2 = "string2";
    String str3 = "string3";

    File file = new File(fname);
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
    try {
      oos.writeObject(obj1);
      oos.writeUTF(str2);
      oos.writeObject(str3);
    }
    catch (IOException e) {
      System.out.println("hahahaa");
      System.out.println(e.toString());
    }
    catch (Exception e) {
      System.out.println("***********");
      System.out.println(e.toString());
    }
    oos.close();
  }

}


