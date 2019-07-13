package com.javachen.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class Premain {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("agentArgs : " + agentArgs);

        inst.addTransformer(new CallSpy());

        inst.addTransformer(new ClassLogger());

        inst.addTransformer(new ClassFileTransformer() {

            @Override
            public byte[] transform( ClassLoader         loader,
                                     String              className,
                                     Class<?>            classBeingRedefined,
                                     ProtectionDomain    protectionDomain,
                                     byte[]              classfileBuffer)
                    throws IllegalClassFormatException {

//                System.out.println("premain load Class     :" + className);

                if (className.equals("java/lang/Integer")) {
                    CustomClassWriter cr = new CustomClassWriter(classfileBuffer);
                    return cr.addField();
                }
                return classfileBuffer;
            }
        });
    }

}
