package com.devliu.xutils3use.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 1. 类的用途 数据库表跟实体类对象进行关联
 * 2. @author forever
 * 3. @date 2017/3/9 15:10
 * onCreated 当第一次创建表的时候执行sql语句
 */
@Table(name = "Student", onCreated = "")
public class Student {
    /**
     * name = "id",表中的一个字段
     * isId = true,是否是主键
     * autoGen = true, 是否自增长
     * property = "NOT NULL" 添加约束
     */
    @Column(name = "id", isId = true, autoGen = true, property = "NOT NULL")
    private int id;
    @Column(name = "name")
    private String name;

    //无参构造方法如果不添加的话 数据库表创建不成功
    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

