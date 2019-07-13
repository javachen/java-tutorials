package com.javachen.instrument;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author june
 * @createTime 2019-07-11 22:36
 * @see
 * @since
 */
public class CallSpy implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        ClassPool cp = ClassPool.getDefault();
        cp.importPackage("com.javachen.instrument");

        //region filter agent classes
        // we do not want to profile ourselves
        if (className==null || className.startsWith("com/javachen/instrument")) {
            return classfileBuffer;
        }
        //endregion

        //region filter out non-application classes
        // Application filter. Can be externalized into a property file.
        // For instance, profilers use blacklist/whitelist to configure this kind of filters
        if (!className.startsWith("com/javachen")) {
            return classfileBuffer;
        }
        //endregion

        System.out.println("className: "+className);

        try {
            CtClass ct = cp.makeClass(new ByteArrayInputStream(classfileBuffer));

            CtMethod[] declaredMethods = ct.getDeclaredMethods();
            for (CtMethod method : declaredMethods) {
                //region instrument method
                System.out.println("method: "+method.getLongName());
                method.insertBefore("{ Long start = System.currentTimeMillis(); }");
                method.insertAfter("{ System.out.println(\"end time: \"+ System.currentTimeMillis()); }", true);
                //endregion
            }

            return ct.toBytecode();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return classfileBuffer;
    }
}