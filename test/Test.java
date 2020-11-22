public class Test{
    String str[] = {"apple","orange"};
    String strr[] = {"Nanjing", "University"};
    static int a[] = {1,2,3,4,5};
    int b[] = {5,4,3,2,1};

    public void test(){
        a[1] = b[1];
        b[2] = a[2];

        str[0] = strr[1];
        strr[0] = str[1];
    }

    public static void main(String[] args){
        System.out.println("Start...");

        new Test().test();

        SomeClass someClass = new SomeClass();
        someClass.staticField = 1111;
        SomeClass someClass2 = new SomeClass();
        someClass2.staticField = someClass.staticField;
        System.out.println("End...");
    }
}
class SomeClass{
    public static int staticField = 1212;
}
