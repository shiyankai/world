package test;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Study {
    public static void main(String[] args) {
     Set set = new HashSet();
     set.add(1);
     set.add("1");
     set.add(new String("1"));
     set.add("32s");
     Iterator ss = set.iterator();
     while (ss.hasNext()){
         System.out.println(ss.next());
     }
    }
}
