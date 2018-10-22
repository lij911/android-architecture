package com.lijing.dev.utils;

import android.os.Environment;

import java.io.File;

import static android.os.Environment.MEDIA_MOUNTED;

/**
 * 文件相关的工具类
 *
 * @author lijing
 */
public class FileUtils {

    /**
     * 内部存储相关
     */
    public static class InternalStorage {

        /**
         * 内部存储根目录
         * /data/data/#pageage name#/files
         */
        public static File getRootDir() {
            return ContextUtils.getContext().getFilesDir();
        }

        /**
         * 内部存储缓存目录
         * /data/data/#pageage name#/cache
         *
         * @return
         */
        public static File getCacheDir() {
            return ContextUtils.getContext().getCacheDir();
        }

        /**
         * 内部存储的自定义目录
         * /data/data/#pageage name#/files/suffix
         */
        public static File getCustomDir(String suffix) {
            return new File(getRootDir(), suffix);
        }
    }

    /**
     * 外部存储相关
     */
    public static class ExternalStorage {

        /**
         * 外部存储的文件目录
         * /storage/sdcard
         */
        public static final File SD_ROOT_DIR = Environment.getExternalStorageDirectory();

        /**
         * 手机图片的文件目录
         * /storage/sdcard/Pictures
         */
        public static final File PICTURES_DIR = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        /**
         * 是否能使用外部存储
         *
         * @return
         */
        public static boolean useable() {
            return Environment.getExternalStorageState().equals(MEDIA_MOUNTED);
        }

        /**
         * 外包存储的自定义目录
         * /storage/sdcard/suffix
         *
         * @param suffix
         * @return
         */
        public static File getCustomDir(String suffix) {
            return new File(SD_ROOT_DIR, suffix);
        }

    }

    /**
     * 删除某个文件
     *
     * @param fileName
     * @return
     */
    public static Boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            return file.delete();
        }

        if (file.exists() && file.isDirectory()) {
            deleteDirectory(fileName);
        }

        return false;
    }

    /**
     * 删除文件目录
     *
     * @param fileName
     * @return
     */
    public static Boolean deleteDirectory(String fileName) {
        File file = new File(fileName);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }

        boolean res = true;
        File[] files = file.listFiles();
        for (File childFile : files) {
            if (childFile.isFile()) {
                // 单个文件直接删除
                res = res && childFile.delete();
            }
            if (childFile.isDirectory()) {
                res = res && deleteDirectory(childFile.getAbsolutePath());
            }
        }
        return res;
    }

}
