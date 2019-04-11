package com.lijing.dev.storage

import android.os.Environment
import com.lijing.dev.utils.ContextUtils
import java.io.File

/**
 * 文件相关的工具类
 *
 * @author lijing
 */
object FileUtils {

    /**
     * 内部存储相关
     */
    object InternalStorage {

        /**
         * 内部存储根目录
         * /data/data/#pageage name#/
         */
        val rootDir: File?
            get() = filesDir?.parentFile

        /**
         * 内部存储文件目录
         * /data/data/#pageage name#/files
         */
        val filesDir: File?
            get() = ContextUtils.getContext()?.filesDir

        /**
         * 内部存储缓存目录
         * /data/data/#pageage name#/cache
         *
         * @return
         */
        val cacheDir: File?
            get() = ContextUtils.getContext()?.cacheDir

        /**
         * 内部存储的自定义目录
         * /data/data/#pageage name#/files/suffix
         */
        fun getCustomDir(suffix: String): File {
            return File(filesDir, suffix)
        }
    }

    /**
     * 外部存储相关
     */
    object ExternalStorage {

        /**
         * 外部存储的文件目录
         * /storage/sdcard
         */
        public val SD_ROOT_DIR = Environment.getExternalStorageDirectory()

        /**
         * 手机图片的文件目录
         * /storage/sdcard/Pictures
         */
        public val PICTURES_DIR = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)

        /**
         * 是否能使用外部存储
         *
         * @return
         */
        public fun usable(): Boolean {
            return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        }

        /**
         * 外包存储的自定义目录
         * /storage/sdcard/suffix
         *
         * @param suffix
         * @return
         */
        public fun getCustomDir(suffix: String): File? {
            return if (usable()) {
                File(SD_ROOT_DIR, suffix)
            } else null
        }

    }

    /**
     * 删除某个文件
     *
     * @param fileName
     * @return
     */
    public fun deleteFile(fileName: String): Boolean {
        val file = File(fileName)
        if (file.exists() && file.isFile) {
            return file.delete()
        }

        if (file.exists() && file.isDirectory) {
            deleteDirectory(fileName)
        }

        return false
    }

    /**
     * 删除文件目录
     *
     * @param fileName
     * @return
     */
    public fun deleteDirectory(fileName: String): Boolean? {
        val file = File(fileName)
        if (!file.exists() || !file.isDirectory) {
            return false
        }

        var res = true
        val files = file.listFiles()
        for (childFile in files) {
            if (childFile.isFile) {
                // 单个文件直接删除
                res = res && childFile.delete()
            }
            if (childFile.isDirectory) {
                res = res && deleteDirectory(childFile.absolutePath)!!
            }
        }
        return res
    }

}
