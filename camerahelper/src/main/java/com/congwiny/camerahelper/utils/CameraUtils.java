package com.congwiny.camerahelper.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by congwiny on 2016/3/31.
 */
public class CameraUtils {

    public static Observable<Boolean> isCameraEnable(final Context context) {
        return Observable.create(
                new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(Subscriber<? super Boolean> subscriber) {
                        // 检查设备是否提供摄像头
                        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                            // 摄像头存在
                            Camera camera = null;
                            try {
                                //尝试打开摄像头
                                camera = Camera.open();

                                if (camera != null) {
                                    camera.release();
                                    camera = null;
                                }

                                subscriber.onNext(true);
                                subscriber.onCompleted();
                            } catch (Exception e) {
                                subscriber.onError(e);
                            } finally {
                                if (camera != null) {
                                    camera.release();
                                }
                            }
                        } else {
                            // 摄像头不存在
                            subscriber.onNext(false);
                            subscriber.onCompleted();
                        }
                    }
                }

        )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
