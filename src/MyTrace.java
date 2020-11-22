public class MyTrace{

    private static boolean isLibFunction(String funcName){
        //filter java lib function
        if(funcName.startsWith("java/lang/") || funcName.startsWith("java/util/"))
            return true;
        else
            return false;
    }

    public static void myTraceArrayRead(Object obj, String name, int index){
        if(isLibFunction(name))
            return;
        System.out.printf("R %d %016x %s[%d]\n", Thread.currentThread().getId(),System.identityHashCode(obj),name.replace("/","."),index);
    }
    public static void myTraceArrayWrite(Object obj, String name, int index){
        if(isLibFunction(name))
            return;
        System.out.printf("W %d %016x %s[%d]\n", Thread.currentThread().getId(),System.identityHashCode(obj),name.replace("/","."),index);
    }

    public static void myTraceStaticRead(String obj, String name){
        if(isLibFunction(name))
            return;
        System.out.printf("R %d %016x %s\n", Thread.currentThread().getId(), System.identityHashCode(obj),name.replace("/","."));
    }
    public static void myTraceStaticWrite(String obj, String name){
        if(isLibFunction(name))
            return;
        System.out.printf("W %d %016x %s\n", Thread.currentThread().getId(), System.identityHashCode(obj),name.replace("/","."));
    }

    public static void myTraceFieldRead(Object obj, String name){
        if(isLibFunction(name))
            return;
        System.out.printf("R %d %016x %s\n", Thread.currentThread().getId(), System.identityHashCode(obj),name.replace("/","."));
    }
    public static void myTraceFieldWrite(Object obj, String name){
        if(isLibFunction(name))
            return;
        System.out.printf("W %d %016x %s\n", Thread.currentThread().getId(), System.identityHashCode(obj),name.replace("/","."));
    }


}