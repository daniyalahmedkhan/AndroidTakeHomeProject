package com.daniyalxdubizzle.androidtakehomeproject.utilities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Environment
import android.os.Environment.isExternalStorageRemovable
import android.util.LruCache
import android.widget.ImageView
import com.daniyalxdubizzle.androidtakehomeproject.R
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock


//private lateinit var memoryCache: LruCache<String, Bitmap>
//private val diskCacheLock = ReentrantLock()
//private var diskCacheStarting = true
//private val diskCacheLockCondition: Condition = diskCacheLock.newCondition()


// IMAGE CACHE FRAMEWORK
/*
* Trying to setting up the framework for caching images.
*
* Approach
* 1 - Get the Image ID from API
* 2 - Check with ImageID in Cache If Not Exist Then Load the Image and Store in LruCache with ImageID as key
* 3- For Next Cycle Check Check with ImageID in Cache If Exist Then Load From Cache
* */


class ImageCache {


//    fun initiate() {
//        // Get max available VM memory, exceeding this amount will throw an
//        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
//        // int in its constructor.
//        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
//
//        // Use 1/8th of the available memory for this memory cache.
//        val cacheSize = maxMemory / 8
//
//        memoryCache = object : LruCache<String, Bitmap>(cacheSize) {
//
//            override fun sizeOf(key: String, bitmap: Bitmap): Int {
//                // The cache size will be measured in kilobytes rather than
//                // number of items.
//                return bitmap.byteCount / 1024
//            }
//        }
//        diskCacheLockCondition.signalAll() // Wake any waiting threads
//    }
//
//    fun loadBitmap(resId: Int, mImageView: ImageView, context: Context) {
//        val imageKey: String = resId.toString()
//
//        val bitmap: Bitmap? = getBitmapFromDiskCache(imageKey)?.also {
//            mImageView.setImageBitmap(it)
//        } ?: run {
//            mImageView.setImageResource(R.drawable.ic_search_icon_white)
//            val task = BitmapWorkerTask()
//            task.execute(resId)
//            null
//        }
//    }
//
//    private inner class BitmapWorkerTask : AsyncTask<Int, Unit, Bitmap>() {
//
//        // Decode image in background.
//        override fun doInBackground(vararg params: Int?): Bitmap? {
//            return params[0]?.let { imageId ->
//                decodeSampledBitmapFromResource(resources, imageId, 100, 100)?.also { bitmap ->
//                    addBitmapToCache(imageId.toString(), bitmap)
//                }
//            }
//        }
//
//    }
//
//    fun addBitmapToCache(key: String, bitmap: Bitmap) {
//        // Add to memory cache as before
//        if (getBitmapFromDiskCache(key) == null) {
//            memoryCache.put(key, bitmap)
//        }
//
//        // Also add to disk cache
//        synchronized(diskCacheLock) {
//            memoryCache?.apply {
//                if (!containsKey(key)) {
//                    put(key, bitmap)
//                }
//            }
//        }
//    }
//
//    fun getBitmapFromDiskCache(key: String): Bitmap? =
//        diskCacheLock.withLock {
//            // Wait while disk cache is started from background thread
//            while (diskCacheStarting) {
//                try {
//                    diskCacheLockCondition.await()
//                } catch (e: InterruptedException) {
//                }
//
//            }
//            return@withLock memoryCache?.get(key)
//        }
//
//    fun getDiskCacheDir(context: Context, uniqueName: String): File {
//        // Check if media is mounted or storage is built-in, if so, try and use external cache dir
//        // otherwise use internal cache dir
//        val cachePath =
//            if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
//                || !isExternalStorageRemovable()
//            ) {
//                context.externalCacheDir?.path
//            } else {
//                context.cacheDir.path
//            }
//
//        return File(cachePath + File.separator + uniqueName)
//    }
//
//    @Throws(FileNotFoundException::class)
//    fun decodeSampledBitmapFromResource(
//        context: Context, uri: Uri?,
//        reqWidth: Int, reqHeight: Int
//    ): Bitmap? {
//        val contentResolver = context.contentResolver
//        var inputStream: InputStream? = contentResolver.openInputStream(uri)
//        // First decode with inJustDecodeBounds=true to check dimensions
//        val options = BitmapFactory.Options()
//        options.inJustDecodeBounds = true
//        BitmapFactory.decodeStream(inputStream, null, options)
//
//        // Calculate inSampleSize
//        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
//        // Decode bitmap with inSampleSize set
//        options.inJustDecodeBounds = false
//        inputStream = contentResolver.openInputStream(uri)
//        return BitmapFactory.decodeStream(inputStream, null, options)
//    }
//
//
//    private fun calculateInSampleSize(){
//        InputStream in = conn.getInputStream();
//        var data = Utils.streamToBytes(in);
//        BitmapFactory.Options option = new BitmapFactory.Options();
//        option.inJustDecodeBounds = true;
//        BitmapFactory.decodeByteArray(data, 0, data.length, option);
//        option.inSampleSize = Utils.getBitmapSampleSize(option.outWidth, reqWidth);
//        option.inJustDecodeBounds = false;
//        return BitmapFactory.decodeByteArray(data, 0, data.length, option
//    }

}