package com.wasu.winton.phonetv;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * Created by Administrator on 2016/1/21.
 */
public class BaseApplication extends Application {
    protected static ImageLoader imageLoader = ImageLoader.getInstance();

    protected static int MEMORY_CACHE_SIZE = 16 * 1024 * 1024;

    private static BaseApplication mApplciation ;
    @Override
    public void onCreate() {

        super.onCreate();
        mApplciation = this;
        initImageLoader();
    }



    /**
     * 初始化imageLoader
     */
    private void initImageLoader(){
        if(imageLoader.isInited()){
            return;
        }
        synchronized (imageLoader){
            ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(mApplciation)
                    .memoryCacheExtraOptions(480, 800)  //保存文件的最大长宽
                    .threadPoolSize(3)  //线程池大小
                    .threadPriority(Thread.NORM_PRIORITY - 2)
                    .denyCacheImageMultipleSizesInMemory()
                    .diskCacheFileCount(100)
                    .memoryCache(new LRULimitedMemoryCache(MEMORY_CACHE_SIZE))
                    .memoryCacheSize(MEMORY_CACHE_SIZE)
                    .tasksProcessingOrder(QueueProcessingType.LIFO)
                    .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                    .imageDownloader(new BaseImageDownloader(mApplciation,5*1000,30*1000)) //连接超时为5s，读取超时为30s
                    .writeDebugLogs()
                    .build();
            imageLoader.init(configuration);
        }
    }


    public static  BaseApplication getInstance(){
       return mApplciation;
    }

    /**
     * @TODO: 获取imageloader实例
     * @return imageloader
     */
    public static ImageLoader getImageLoader(){
        return imageLoader;
    }
    
    public static DisplayImageOptions getImageOptions(int loadingImageId, int errorImageId) {
        DisplayImageOptions.Builder options = new DisplayImageOptions.Builder();
        if (loadingImageId > 0) {
            options.showImageOnLoading(loadingImageId);
        }
        if (errorImageId > 0) {
            options.showImageForEmptyUri(errorImageId).showImageOnFail(errorImageId);
        }
        // .displayer(new RoundedBitmapDisplayer(20)) //圆角
        return options.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
    }
}
