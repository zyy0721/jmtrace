import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodAdapter extends MethodVisitor implements Opcodes{
    boolean flagStatic = true;
    String curOwner = "";
    String curObj = "";
    
    public MethodAdapter(final MethodVisitor mv){
        super(ASM5, mv);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc){
        curOwner = owner;
        curObj = curOwner + "." + name;
        //for field objects
        switch(opcode){
        case GETSTATIC:
            flagStatic = true;//set static type flag
            mv.visitLdcInsn(curOwner);//push curOwner to the stack, LDC curOwner
            mv.visitLdcInsn(curObj);//push curObj to the stack, LDC curObj
            mv.visitMethodInsn(INVOKESTATIC,"MyTrace","myTraceStaticRead", "(Ljava/lang/String;Ljava/lang/String;)V",false);
            break;
        case PUTSTATIC:
            flagStatic = true;
            mv.visitLdcInsn(curOwner);
            mv.visitLdcInsn(curObj);
            mv.visitMethodInsn(INVOKESTATIC,"MyTrace","myTraceStaticWrite","(Ljava/lang/String;Ljava/lang/String;)V",false);
            break;
        case GETFIELD:
            flagStatic = false;
            mv.visitVarInsn(ALOAD,0);
            mv.visitLdcInsn(curObj);
            mv.visitMethodInsn(INVOKESTATIC,"MyTrace","myTraceFieldRead","(Ljava/lang/Object;Ljava/lang/String;)V",false);
            break;
        case PUTFIELD:
            flagStatic = false;
            mv.visitVarInsn(ALOAD,0);
            mv.visitLdcInsn(curObj);
            mv.visitMethodInsn(INVOKESTATIC,"MyTrace","myTraceFieldWrite","(Ljava/lang/Object;Ljava/lang/String;)V",false);
            break;
        }
        mv.visitFieldInsn(opcode,owner,name,desc); //recursively
    }

    @Override
    public void visitInsn(int opcode){
        if (curObj.equals("")){//handle special case, null
            mv.visitInsn(opcode);
            return;
        }
        //for array
        switch(opcode){
        case IALOAD:
        case LALOAD:
        case FALOAD:
        case DALOAD:
        case AALOAD:
        case BALOAD:
        case CALOAD:
        case SALOAD:
            mv.visitInsn(DUP);//add the bytecode for the method implementation, copy the stack top value, and push the copy value forward
            if(flagStatic)//if is static type, visit owner
                mv.visitLdcInsn(curOwner);
            else//else load an object reference from the corresponding location of the local scale to the top of the operand stack
                mv.visitVarInsn(ALOAD,0);
            mv.visitLdcInsn(curObj);
            mv.visitInsn(DUP2_X1);// copy the stack top one(long or double type) or two(other type) value, and push the copy value forward twice
            mv.visitInsn(POP2);//pop stack top one(long or double type) or two(other type) value
            mv.visitMethodInsn(INVOKESTATIC,"MyTrace","myTraceArrayRead","(Ljava/lang/Object;Ljava/lang/String;I)V", false);
            break;

        case IASTORE:
        case LASTORE:
        case FASTORE:
        case DASTORE:
        case AASTORE:
        case BASTORE:
        case CASTORE:
        case SASTORE:
            mv.visitInsn(DUP2);
            if(flagStatic)
                mv.visitLdcInsn(curOwner);
            else
                mv.visitVarInsn(ALOAD,0);
            mv.visitLdcInsn(curObj);
            mv.visitInsn(DUP2_X2);
            mv.visitInsn(POP2);
            mv.visitInsn(POP);
            mv.visitMethodInsn(INVOKESTATIC,"MyTrace","myTraceArrayWrite","(Ljava/lang/Object;Ljava/lang/String;I)V", false);
            break;
        }

        mv.visitInsn(opcode);
    }

}