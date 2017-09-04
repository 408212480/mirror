package com.zxwl.web.bean.api;

import java.util.Arrays;
import java.util.List;

public class SearchUserCharacteristicsJsonBean {

    private String sex;

    private String weight;

    private String height;

    private String age;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SearchUserCharacteristicsJsonBean{" +
                "sex='" + sex + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
