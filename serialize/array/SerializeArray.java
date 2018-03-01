import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Serialize an ArrayList
 */
public class SerializeArray {

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
      System.out.println("> reading ArrayList...");
      ArrayList<Double> ar = (ArrayList<Double>) ois.readObject();
      System.out.println(String.format("> desc arr: %s", ar));
    }
    catch (Exception e) {
      System.out.println(String.format("Exception deserializing: %s", e));
    }
  }

  private static void serializeThings(String fname) throws FileNotFoundException, IOException {
    System.out.println("Serialize");
    ArrayList<Double> ar = new ArrayList(Arrays.asList(1.1, 2.2, 3.3));

    File file = new File(fname);
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
    try {
      oos.writeObject(ar);
    }
    catch (IOException e) {
      System.out.println(e.toString());
    }
    catch (Exception e) {
      System.out.println(e.toString());
    }
    oos.close();
  }

}


