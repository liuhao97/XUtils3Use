package com.devliu.xutils3use.model;

import java.util.List;

/**
 * Created by liuhao
 * on 2017/3/8
 * use to :
 */
public class DataBean {
    private String title;
    private String title_id;
    private List<DatasBean> datas;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_id() {
        return title_id;
    }

    public void setTitle_id(String title_id) {
        this.title_id = title_id;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }
}
