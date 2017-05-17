package edu.shu.styluo.collegeentranceexamination.data.local.entity;

/**
 * 高校查询用实体
 * author: styluo
 * date: 2017/5/17 15:02
 * e-mail: shu_jiahuili@foxmail.com
 */

public class CollegeInfo {
    int id; //高校id
    String collegeName; //高校名称

    public CollegeInfo(int id, String collegeName){
        this.id = id;
        this.collegeName = collegeName;
    }

    public int getId() {
        return id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
