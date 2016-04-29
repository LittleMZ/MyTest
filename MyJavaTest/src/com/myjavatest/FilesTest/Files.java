package com.myjavatest.FilesTest;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by M on 16/4/25.
 * <p>
 * 文件操作的类,实现单个文件复制,文件夹复制等
 */
public class Files {
    //public static ArrayList<File> files = new ArrayList<>();

    /**
     * 复制单个文件
     *
     * @param srcFile  源文件绝对路径
     * @param destFile 目标文件绝对路径
     * @throws IOException
     */
    public static void copyFile(File srcFile, File destFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(srcFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(destFile));

        char[] chars = new char[1024];
        int len;
        while ((len = br.read(chars)) != -1) {
            bw.write(chars, 0, len);
            bw.flush();
        }

        br.close();
        bw.close();
    }

    /**
     * 复制源文件夹下的所有文件夹(包括子目录下的文件夹)至目标文件夹中
     *
     * @param srcFolder  源文件夹绝对路径
     * @param destFolder 目标文件夹绝对路径
     * @throws IOException
     */
    public static void copyFolder(File srcFolder, File destFolder) throws IOException {
        File[] files = srcFolder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                File newFolder = new File(destFolder, file.getName());
                newFolder.mkdir();
                copyFolder(file, newFolder);
            }
        }
    }

    /**
     * 得到文件夹下所有文件(包括子目录下的文件)
     *
     * @param folder 文件夹
     * @return Object数组返回所有文件
     */
    public static File[] getFiles(File folder) {
        ArrayList<File> files = new ArrayList();
        if (folder == null) {
            return null;
        } else if (folder.getName().startsWith(".")) {
            return null;
        } else if (folder.isFile()) {
            //单个文件加入ArrayList
            files.add(folder);
            File[] isFiles = new File[files.toArray().length];
            for (int i = 0; i < files.toArray().length; i++) {
                isFiles[i] = (File) files.toArray()[i];
            }
            return isFiles;
        } else {
            File[] allFiles = folder.listFiles();
            for (File file : allFiles) {
                if (getFiles(file) != null) {
                    for (File file1 : getFiles(file)) {
                        files.add(file1);
                    }
                }
            }
            File[] isFiles = new File[files.toArray().length];
            for (int i = 0; i < files.toArray().length; i++) {
                isFiles[i] = (File) files.toArray()[i];
            }
            return isFiles;
        }

    }

    /**
     * 将源文件夹下的所有文件复制到目标文件夹下
     *
     * @param srcFolder  源文件夹
     * @param destFolder 目标文件夹
     * @throws IOException
     */
    public static void copyAllFiles(File srcFolder, File destFolder) throws IOException {
        if (srcFolder == null || destFolder == null || !srcFolder.exists()) {
            System.out.println("源文件夹不存在!");
            return;
        }

        //创建目标文件夹
        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }

        //创建所有的文件夹
        copyFolder(srcFolder, destFolder);

        File[] files = getFiles(srcFolder);
        String src = srcFolder.getAbsolutePath();
        String dest = destFolder.getAbsolutePath();
        for (File file : files) {
            File fileTemp = file;
            String str = fileTemp.getAbsoluteFile().toString().substring(src.length());
            File destFile = new File(dest + str);
            copyFile(fileTemp, destFile);
        }
    }

}
