package roycurtis;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

/**
 * Created by KJ4IPS on 3/2/2015.
 */
public class EnchantIconsClassTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String className, String newClassName, byte[] byteCode) {
        if(className.equals("net.minecraft.client.renderer.entity.RenderItem") ||
        className.equals("cqh")) {
            boolean obfuscated = className.equals("cqh");
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(byteCode);

            classReader.accept(classNode, 0);

            for(MethodNode method : classNode.methods){
                if(method.name.equals(obfuscated ? "func_180453_a" : "renderItemOverlayIntoGUI")&&
                        method.desc.equals(obfuscated ? "(Lbty;LamjIILjava/lang/String;)V" : "Lnet/minecraft/client/gui/FontRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V"))
                {
                    InsnList injectMe = new InsnList();
                    injectMe.add(new VarInsnNode(Opcodes.ALOAD,0)); //FontRenderer
                    injectMe.add(new VarInsnNode(Opcodes.ALOAD,1)); //ItemStack
                    injectMe.add(new VarInsnNode(Opcodes.ALOAD,2)); //xPos
                    injectMe.add(new VarInsnNode(Opcodes.ALOAD,3)); //yPos
                    injectMe.add(new VarInsnNode(Opcodes.ALOAD,4)); //String to render (usually null)
                    injectMe.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "roycurtis/EnchantIconsRenderer",
                            "handleRender", method.desc,false));
                    method.instructions.insert(injectMe);
                    ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                    classNode.accept(writer);
                    return writer.toByteArray();
                }
            }
        }
        return byteCode;
    }

}
