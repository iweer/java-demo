package cn.iweer.jvm.classpath;

import cn.iweer.jvm.classpath.impl.CompositeEntry;
import cn.iweer.jvm.classpath.impl.DirEntry;
import cn.iweer.jvm.classpath.impl.WildcardEntry;
import cn.iweer.jvm.classpath.impl.ZipEntry;

import java.io.File;
import java.io.IOException;
/**
 * @File : Entry.java
 * @Author : wei.hu
 * @Time : 2022/6/8 15:46
 * @Version : V1.0
 * 类路径接口
 */
public interface Entry {

    byte[] readClass(String className) throws IOException;

    static Entry create(String path) {

        //File.pathSeparator；路径分隔符(win\linux)
        if (path.contains(File.pathSeparator)) {
            return new CompositeEntry(path);
        }

        if (path.endsWith("*")) {
            return new WildcardEntry(path);
        }

        if (path.endsWith(".jar") || path.endsWith(".JAR") ||
                path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }

        return new DirEntry(path);
    }

}
