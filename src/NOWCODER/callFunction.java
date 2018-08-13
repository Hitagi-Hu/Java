package NOWCODER;
/*
* 当一个类继承于另一个类,用子类的对象调用方法时，会首先在子类中查找，如果子类中没有该方法，再到父类中查找。

当一个方法只在父类中定义时，调用该方法时会使用父类中的属性。
如果该方法中又调用了其他方法，那么还是按照之前的顺序，先在子类中查找，再在父类中查找。*/

class A {
    public void show(int i) {
        System.out.println(i + 1);
    }

    public void set(Data data){
        data = new Data();//把引用data指向新的对象,传入的引用指向的原对象就不会发生变化
        data.i = 2;
    }
}

class B extends A {
    public void show(int i) {
        System.out.println(i + 2);
    }
}

class Data{
    int i=0;
}
/*
class B extends A {

}
*/

public class callFunction{
    public static void main(String[] args) {
        B b = new B();
        b.show(0);
        Data data = new Data();
        b.set(data);
        System.out.println("i = "+ data.i);
    }


}

