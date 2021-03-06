import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.stream.Collectors;
import java.util.stream.Stream; 
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Serialize an ArrayList with NaN values
 */
public class SerializeNaN {

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
      System.out.println("list");
      for (Double val: ar) {
        System.out.println(String.format("> %s", val));
        if (Double.isNaN(val)) {
          System.out.println("  - value is NaN");
        }
        if (val == 0.0) {
          System.out.println("  - value is 0");
        }
      }
    }
    catch (Exception e) {
      System.out.println(String.format("Exception deserializing: %s", e));
    }
  }

  private static void serializeThings(String fname) throws FileNotFoundException, IOException {
    System.out.println("Serialize");
    ArrayList<Double> ar = new ArrayList(Arrays.asList(3.0, 0.0, Double.NaN, 1.2));

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

  private static ArrayList<ArrayList<StrDblPair>> buildList() {
    ArrayList<ArrayList<StrDblPair>> list = (ArrayList<ArrayList<StrDblPair>>) 
      Stream.generate(ArrayList<StrDblPair>::new).limit(3).collect(Collectors.toList());
    for (ArrayList<StrDblPair> l: list) {
      l.add(new StrDblPair("john", 3.16));
    }
    return list;
  }

  private static class StrDblPair implements Serializable {
    public String m_str;
    public Double m_dbl;
    StrDblPair(String s, Double d) {
      m_str = s;
      m_dbl = d;
    }
  }

}


