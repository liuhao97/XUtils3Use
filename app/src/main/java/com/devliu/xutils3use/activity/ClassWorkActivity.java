package com.devliu.xutils3use.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.devliu.xutils3use.R;
import com.devliu.xutils3use.adapter.ExpandLvAdapter;
import com.devliu.xutils3use.model.ResponseBean;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by liuhao
 * on 2017/3/8
 * use to : 今天作业, expandableListView练习
 */

@ContentView(R.layout.activity_class_work)
public class ClassWorkActivity extends AppCompatActivity {
    @ViewInject(R.id.ptr_expand)
    private PullToRefreshExpandableListView ptrExpandLv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        initViews();

        requestServer();
    }

    private void initViews() {

        ptrExpandLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ExpandableListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ExpandableListView> pullToRefreshBase) {
                ptrExpandLv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrExpandLv.onRefreshComplete();
                    }
                }, 2000);
            }
        });


    }

    private void requestServer(){
        String url = "http://mock.eolinker.com/success/rq7m6GNqurs93zYkEANkY8Z4358Aihf1";
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                ResponseBean response = gson.fromJson(s, ResponseBean.class);
                ExpandLvAdapter adapter = new ExpandLvAdapter(response.getData(), ClassWorkActivity.this);

                ExpandableListView refreshableView = ptrExpandLv.getRefreshableView();
                refreshableView.setAdapter(adapter);
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
        });
    }
}
