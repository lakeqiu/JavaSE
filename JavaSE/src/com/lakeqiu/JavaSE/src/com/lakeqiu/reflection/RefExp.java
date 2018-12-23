package com.lakeqiu.reflection;

/**
 * Created by lakeqiu
 */
public class RefExp {
    private String name;
    private String sno;
    private int grade;

    public RefExp(String name, String sno, int grade) {
        this.name = name;
        this.sno = sno;
        this.grade = grade;
    }

    public RefExp() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if ("lake2".equals(name)){
            return;
        }
        this.name = name;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
