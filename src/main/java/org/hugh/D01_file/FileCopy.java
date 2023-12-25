package org.hugh.D01_file;


//import org.junit.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.Objects;
/**
 * @author adward
 * @date 2023/1/15 12:35
 */
public class FileCopy {
    public static void main(String[] args) throws IOException {
        String p = Objects.requireNonNull(FileCopy.class.getResource(".")).getPath();
        File file = new File("/Users/adward/development/demos/JavaDemos/javaDemos/src/main/java/org/hugh/D01_file/f1/01.txt");
        File file2 = new File("/Users/adward/development/demos/JavaDemos/javaDemos/src/main/java/org/hugh/D01_file/f2/01.txt");
        checkAndTouchFile(file);
        // checkAndTouchFile(file2);
        System.out.println();
        // copyFileUsingChannel(file, file2);
        copyFileUsingJava7Files(file, file2);
    }

    private static void checkAndTouchFile(File file) {
        if (!file.exists()) {
            if(!file.getParentFile().exists()) {
                boolean mkdirs = file.getParentFile().mkdirs();
                if (mkdirs){
                    System.out.println("ok");
                }
            }
            try {
                boolean a = file.createNewFile();
                if (a){
                    System.out.println("ok");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void copyFileUsingChannel(File source, File dest) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }finally{
            sourceChannel.close();
            destChannel.close();
        }
    }
    //更多请阅读：https://www.yiibai.com/java/java-copy-file.html
   // @Test
    public static void copyFileUsingJava7Files(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }
    //更多请阅读：https://www.yiibai.com/java/java-copy-file.html



}
