package edu.shu.styluo.collegeentranceexamination.data.local.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 专业信息对象
 * author: styluo
 * date: 2017/4/24 11:50
 * e-mail: shu_jiahuili@foxmail.com
 */

@Entity(nameInDb = "MajorInfo", createInDb = false)
public class MajorInfo {
    private String id; //专业代码
    private String major_name; //专业名称
    private String subject_code; //学科代码
    private String degree_level; //学位级别
    private String school_system; //学制
    private String main_course; //主要课程
    private String major_goal; //专业培养目标
    private String major_require; //专业要求
    private String major_ability; //专业培养能力
    private String major_pratice; //专业实践
    private String major_recommend; //专业推荐
    private String major_salary; //专业平均薪水
    private String major_employ; //就业去向
    private String major_develop; //专业发展
    private String major_graduate; //读研深造
    private String major_abroad; //出国深造
    private String major_intro; //专业介绍

    @Generated(hash = 714298037)
    public MajorInfo(String id, String major_name, String subject_code,
            String degree_level, String school_system, String main_course,
            String major_goal, String major_require, String major_ability,
            String major_pratice, String major_recommend, String major_salary,
            String major_employ, String major_develop, String major_graduate,
            String major_abroad, String major_intro) {
        this.id = id;
        this.major_name = major_name;
        this.subject_code = subject_code;
        this.degree_level = degree_level;
        this.school_system = school_system;
        this.main_course = main_course;
        this.major_goal = major_goal;
        this.major_require = major_require;
        this.major_ability = major_ability;
        this.major_pratice = major_pratice;
        this.major_recommend = major_recommend;
        this.major_salary = major_salary;
        this.major_employ = major_employ;
        this.major_develop = major_develop;
        this.major_graduate = major_graduate;
        this.major_abroad = major_abroad;
        this.major_intro = major_intro;
    }

    @Generated(hash = 861691305)
    public MajorInfo() {
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getMajor_name() {
        return this.major_name;
    }
    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }
    public String getSubject_code() {
        return this.subject_code;
    }
    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }
    public String getDegree_level() {
        return this.degree_level;
    }
    public void setDegree_level(String degree_level) {
        this.degree_level = degree_level;
    }
    public String getSchool_system() {
        return this.school_system;
    }
    public void setSchool_system(String school_system) {
        this.school_system = school_system;
    }
    public String getMain_course() {
        return this.main_course;
    }
    public void setMain_course(String main_course) {
        this.main_course = main_course;
    }
    public String getMajor_goal() {
        return this.major_goal;
    }
    public void setMajor_goal(String major_goal) {
        this.major_goal = major_goal;
    }
    public String getMajor_require() {
        return this.major_require;
    }
    public void setMajor_require(String major_require) {
        this.major_require = major_require;
    }
    public String getMajor_ability() {
        return this.major_ability;
    }
    public void setMajor_ability(String major_ability) {
        this.major_ability = major_ability;
    }
    public String getMajor_pratice() {
        return this.major_pratice;
    }
    public void setMajor_pratice(String major_pratice) {
        this.major_pratice = major_pratice;
    }
    public String getMajor_recommend() {
        return this.major_recommend;
    }
    public void setMajor_recommend(String major_recommend) {
        this.major_recommend = major_recommend;
    }
    public String getMajor_salary() {
        return this.major_salary;
    }
    public void setMajor_salary(String major_salary) {
        this.major_salary = major_salary;
    }
    public String getMajor_employ() {
        return this.major_employ;
    }
    public void setMajor_employ(String major_employ) {
        this.major_employ = major_employ;
    }
    public String getMajor_develop() {
        return this.major_develop;
    }
    public void setMajor_develop(String major_develop) {
        this.major_develop = major_develop;
    }
    public String getMajor_graduate() {
        return this.major_graduate;
    }
    public void setMajor_graduate(String major_graduate) {
        this.major_graduate = major_graduate;
    }
    public String getMajor_abroad() {
        return this.major_abroad;
    }
    public void setMajor_abroad(String major_abroad) {
        this.major_abroad = major_abroad;
    }
    public String getMajor_intro() {
        return this.major_intro;
    }
    public void setMajor_intro(String major_intro) {
        this.major_intro = major_intro;
    }
}
