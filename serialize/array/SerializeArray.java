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
      ArrayList<ArrayList<StrDblPair>> ar = (ArrayList<ArrayList<StrDblPair>>) ois.readObject();
      for (ArrayList<StrDblPair> list: ar) {
        System.out.println("list");
        for (StrDblPair pair: list) {
          System.out.println(String.format("> pair: %s %s", pair.m_str, pair.m_dbl));
        }
      }
    }
    catch (Exception e) {
      System.out.println(String.format("Exception deserializing: %s", e));
    }
  }

  private static void serializeThings(String fname) throws FileNotFoundException, IOException {
    System.out.println("Serialize");
    ArrayList<ArrayList<StrDblPair>> ar = buildList();

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


