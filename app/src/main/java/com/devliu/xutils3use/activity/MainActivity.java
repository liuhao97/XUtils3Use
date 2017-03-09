package com.devliu.xutils3use.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.devliu.xutils3use.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @ViewInject(R.id.tv_demo)
    private TextView tvDemo;

    //默认点击事件
    @Event(type = View.OnClickListener.class, value = R.id.tv_demo)
    private void onClick(View v){
        switch (v.getId()){
            case R.id.tv_demo:
                Toast.makeText(this, "事件注解", Toast.LENGTH_SHORT).show();
                requsetServer();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        x.view().inject(this);

        tvDemo.setText("View 注解");
    }

    private void requsetServer(){
        String urlStr = "http://www.93.gov.cn/93app/data.do";
        //http://www.93.gov.cn/93app/data.do

        RequestParams params = new RequestParams(urlStr);

        //参照后台接口
        params.addQueryStringParameter("channelId", "0");
        params.addQueryStringParameter("startNum", "0");

        //缓存
        //缓存过期时间
        params.setCacheMaxAge(1000*60);
        x.http().get(params, new Callback.CacheCallback<String>(){
            //private String result = null;

            @Override
            public void onSuccess(String s) {
                //if(s != null){
                    //result = s;
                //}
                Log.d(TAG, "onSuccess: "+s);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String s) {
                //默认false不走缓存
                //result = s;
                Toast.makeText(MainActivity.this, "缓存有效", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        /*x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                //Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                //tvDemo.setText(s);
                Log.d(TAG, "onSuccess: "+ s);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });*/
    }

}
