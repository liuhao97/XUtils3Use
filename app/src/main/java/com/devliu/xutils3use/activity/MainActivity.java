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
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 注解的方式加载布局
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @ViewInject(R.id.tv_demo)
    private TextView tvDemo;

    /**
     * 1，方法必须私有限定,
     * 2，方法参数形式必须和type对应的Listener接口一致.
     * 3，注解参数value支持数组: value={id1, id2, id3}
     * type默认View.OnClickListener.class，故此处可以简化不写，@Event(R.id.bt_main)
     * 默认的是单击事件
     *
     * @Event({R.id.tv, R.id.iv, R.id.bt_add, R.id.bt_find, R.id.bt_update, R.id.bt_delete})
     */
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
        /**
         *   初始化注解
         */
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

    private void showImage() {
        ImageOptions options = new ImageOptions
                .Builder()
                .setCircular(true)
                .setCrop(true)
                .setSize(100, 100)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .build();

        /*x.image().bind(iv,
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489035859303&di=a701b850fecd73173a020305d46eec77&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F1f178a82b9014a90e7eb9d17ac773912b21bee47.jpg",
                options);*/
    }

    /**
     * db.save(students);
     *
     * Student student = db.findFirst(Student.class);
     * List<Student> studentList = db.findAll(Student.class);
     *
     * //第一种方式 更新第一个对象的名字
     * Student student = db.findFirst(Student.class);
     * student.setName("wowo");
     * db.update(student, "name");
     * //第二种方式
     * WhereBuilder b = WhereBuilder.b();
     * b.and("id", "=", student.getId());
     * KeyValue value = new KeyValue("name", "sisi");
     * db.update(Student.class, b, value);
     * //第三种方式
     * student.setName("xixi");
     * db.saveOrUpdate(student);
     *
     * //删除表中的所有数据
     * //  db.delete(Student.class);
     * //根据条件删除表中的数据
     * WhereBuilder b = WhereBuilder.b();
     * b.and("id", ">", "5");
     * b.and("id", "<", "8");
     * db.delete(Student.class, b);
     */

    /**
     * params.addBodyParameter("channelId", "0");
     * params.addParameter("startNum", "0");
     * params.addHeader("head", "android");
     */

}
