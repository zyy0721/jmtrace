import java.lang.instrument.Instrumentation;

public class Jmtrace{
    public static void premain(String args, Instrumentation inst){
        inst.addTransformer(new ClassTransformer());
    }
}