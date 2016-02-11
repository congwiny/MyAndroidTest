package com.congwiny.rxjavatest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawableUtils;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.congwiny.rxjavatest.bean.Course;
import com.congwiny.rxjavatest.bean.Student;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted!");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError!");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext!");
            }

        };

        //订阅者实现了Observer和Subscription接口的抽象类
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted!");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError!");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext!");
            }

            /**
             * 它会在 subscribe 刚开始，而事件还未发送之前被调用，可以用于做一些准备工作，
             * 例如数据的清零或重置。这是一个可选方法，默认情况下它的实现为空。
             */
            @Override
            public void onStart() {
                Log.e(TAG, "onStart!");
            }

        };

        //subscriber.unsubscribe();

        //Observable 即被观察者，它决定什么时候触发事件以及触发怎样的事件
        //Observable.OnSubscribe 被订阅时的监听，call是回调方法
        //subscriber是具体的订阅者
        /**
         * create() 方法是 RxJava 最基本的创造事件序列的方法。
         */
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.e(TAG, "observable onSubscribe");

                //触发subscribe的onNext和onCompleted
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });

        // observable.subscribe(subscriber);
        /**-------------------------------*/
        /**
         *
         *create() 方法是 RxJava 最基本的创造事件序列的方法。
         * 基于这个方法， RxJava 还提供了一些方法用来快捷创建事件队列，例如：
         * just(T...): 将传入的参数依次发送出来。
         */
        Observable<String> observable2 = Observable.just("Hello", "Hi", "Aloha");
        Subscriber<String> subsciber2 = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted!");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError!");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, s);
            }
        };
        // observable2.subscribe(subsciber2);

        /**
         * 如果我们不关心subscriber是否结束（onComplete())或者发生错误(onError()),
         * subscriber的代码可以简化为:
         */
        Action1<String> subscriber22 = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "Action1:" + s);
            }
        };

        //observable2.subscribe(subscriber22);

        /**
         * 我们直接把创建和订阅连接起来，完整的代码如下。
         */
        Observable.just("Hello RxJava", "I love you", "hahaha").
                subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        //Log.e(TAG, "Action1:" + s);
                    }
                });

        /**
         * from(T[]) / from(Iterable<? extends T>) :
         * 将传入的数组或 Iterable 拆分成具体对象后，依次发送出来。
         */
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable.from(words).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "Action11:" + s);
            }
        });


        /**---------来个完整的用法-------------------
        final BitmapDrawable bitmapDrawable = new BitmapDrawable(
                getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.rx_cartoon));
        final ImageView imageView = (ImageView) findViewById(R.id.iv_test);

        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                subscriber.onNext(bitmapDrawable);

            }
        }).subscribe(new Subscriber<Drawable>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "set Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Drawable drawable) {
                imageView.setImageDrawable(drawable);
            }
        });
         */
        /**
         * 上面的例子并没有什么鸟用，都是在主线程干的。。
         *
         * 在 RxJava 的默认规则中，事件的发出和消费都是在同一个线程的。
         * 也就是说，如果只用上面的方法，实现出来的只是一个同步的观察者模式。
         * 观察者模式本身的目的就是『后台处理，前台回调』的异步机制，
         * 因此异步对于 RxJava 是至关重要的。
         * 而要实现异步，则需要用到 RxJava 的另一个概念： Scheduler 。
         *
         */

        /**
         * Scheduler
         * 在不指定线程的情况下， RxJava 遵循的是线程不变的原则，
         * 即：在哪个线程调用 subscribe()，就在哪个线程生产事件；
         * 在哪个线程生产事件，就在哪个线程消费事件。如果需要切换线程，就需要用到 Scheduler（调度器）。
         */

        //改造上个例子：
        final ImageView imageView = (ImageView) findViewById(R.id.iv_test);

        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getTheme().getDrawable(R.drawable.rx_cartoon);
                subscriber.onNext(drawable);

            }
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Subscriber<Drawable>() {
              @Override
              public void onCompleted() {

              }

              @Override
              public void onError(Throwable e) {
                  Toast.makeText(MainActivity.this, "set Error", Toast.LENGTH_SHORT).show();
              }

              @Override
              public void onNext(Drawable drawable) {
                  imageView.setImageDrawable(drawable);
              }
          });

        /**
         * Rxjava变换
         * Rxjava提供了对事件序列进行变换的支持，这是它的核心功能之一（也就是大多人说RxJava太好用的最大原因）
         * 所谓变换，就是将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列
         */
        //map例子
        Observable.just("images/logo.png")
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        return null;
                    }
                })
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {

                    }
                });

        //如果要打印出每个学生所需要修的所有课程的名称呢？（需求的区别在于，每个学生只有一个名字，但却有多个课程。）
        Student[] students = new Student[10];
        Subscriber<Student> subscriber4 = new Subscriber<Student>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student student) {
                List<Course> courses = student.getCourses();
                for (int i = 0; i < courses.size(); i++) {
                    Course course = courses.get(i);
                    Log.d(TAG, course.getName());
                }
            }

        };
        Observable.from(students)
                .subscribe(subscriber4);
        /**---------------------------------*/
        //flatMap()
        Subscriber<Course> subscriber5 = new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.e(TAG,course.getName());
            }
        };
        Observable.from(students).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.getCourses());
            }
        }).subscribe(subscriber5);

    }

    

}
