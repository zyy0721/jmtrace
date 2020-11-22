import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.Label;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

public class ClassTransformer implements ClassFileTransformer{
    @Override
    public byte[] transform(ClassLoader arg0, String arg1, Class<?>arg2,
    ProtectionDomain arg3, byte[] arg4) throws IllegalClassFormatException{
        if (arg1.startsWith("java/") || arg1.startsWith("jdk/")
        || arg1.startsWith("sun/") || arg1.equals("MyTrace"))
            return arg4;
        
        ClassReader cr = new ClassReader(arg4);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassAdapter ca = new ClassAdapter(cw);

        cr.accept(ca,0);
        
        return cw.toByteArray();
    }

}