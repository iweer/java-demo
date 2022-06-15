package cn.iweer.jvm;

import cn.iweer.jvm.classpath.Classpath;

/**
 * @File : Main.java
 * @Author : wei.hu
 * @Time : 2022/6/8 15:29
 * @Version : V1.0
 */
public class Main {

    public static void main(String[] args) {
        Cmd cmd = Cmd.parse(args);
        if (!cmd.ok || cmd.helpFlag) {
            System.out.println("Usage: <main class> [-options] class [args...]");
            return;
        }
        if (cmd.versionFlag) {
            System.out.println("java version \"1.8.0\"");
            return;
        }
        startJVM(cmd);
    }

    private static void startJVM(Cmd cmd) {
        System.out.println(cmd.jre + cmd.classpath);
        // /Users/mac/Library/Java/JavaVirtualMachines/openjdk-15.0.1/Contents/Home/Users/mac/www/demo/demo-jvm/target/classes
        Classpath cp = new Classpath(cmd.jre, cmd.classpath);
        System.out.printf("classpath：%s class：%s args：%s\n", cp, cmd.getMainClass(), cmd.getAppArgs());
        // classpath：cn.iweer.jvm.classpath.Classpath@6477463f class：cn.iweer.jvm.Main args：null
        //获取className
        String className = cmd.getMainClass().replace(".", "/");
        System.out.println("className:" + className);  // cn/iweer/jvm/Main
        try {
            byte[] classData = cp.readClass(className);
            System.out.println("classData：");
            for (byte b : classData) {
                //16进制输出
                System.out.print(String.format("%02x", b & 0xff) + " ");
            }
        } catch (Exception e) {
            System.out.println("Could not find or load main class " + cmd.getMainClass());
            e.printStackTrace();
        }
    }

}
