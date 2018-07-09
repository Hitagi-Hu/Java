public class test {
    public static void main(String[] args){
        String str1 = "HelloWorld";
        String str2 = "HelloWorld";
        String str3 = "Hello" + new String("World");
        String str4 = new String("HelloWorld");
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str1 == str4);
        System.out.println(str1.equals(str4));
        System.out.println(str1.equals(str2));

    }
}
