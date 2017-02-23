package com.bruce.chang.testrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //创建一个Observable(被观察者)
    Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            // 发送一个 Hello World 事件
            subscriber.onNext("Hello World!");

            // 事件发送完成
            subscriber.onCompleted();
        }
    });

    // 创建一个Observer
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {
            Log.e(TAG, "complete");
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Log.e(TAG, s);
        }
    };

    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            Log.e(TAG, "complete");
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Log.e(TAG, s);
        }
    };


    // onComplete()
    Action0 onCompleteAction = new Action0() {
        @Override
        public void call() {
            Log.e(TAG, "complete");
        }
    };

    // onNext(T t)
    Action1<String> onNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Log.e(TAG, s);
        }
    };

    // onError(Throwable t)
    Action1<Throwable> onErrorAction = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        observable.subscribe(subscriber);
        observable.subscribe(onNextAction, onErrorAction, onCompleteAction);
    }
}
