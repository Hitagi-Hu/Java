package Algorithm.exercise22_5;
/*
*Test class for map
 */
import java.util.Collection;
import java.util.LinkedHashMap;


public class Test {
    public static void main(String[] args){
        LinkedHashMap<Integer, Character> map = new LinkedHashMap<>();
        map.put(1, 'A');
        map.put(2, 'B');
        map.put(3, 'C');
        map.put(4, 'A');
        map.put(2, 'D');   //会覆盖之前的键对应的值
        System.out.println(map);
        Collection<Character> collection = map.values();
        System.out.println(collection);

     }
}
