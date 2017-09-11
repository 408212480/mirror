package com.zxwl.web.bean.api;

import com.zxwl.web.bean.UserInfo;
import com.zxwl.web.bean.common.PagerResult;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by xianghugui on 31/07/2017.
 */
public class UserHomePage {

    private UserInfo userInfo;

    private String allMoney;

    private List<Map>  mapList;

    private List<Map> userVideoList;

    private int allLike;

    private Map userOwnRankandBrokerage;

    public List<Map> getUserVideoList() {
        return userVideoList;
    }

    public void setUserVideoList(List<Map> userVideoList) {
        this.userVideoList = userVideoList;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(String allMoney) {
        this.allMoney = allMoney;
    }

    public List<Map> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map> mapList) {
        this.mapList = mapList;
    }

    @Override
    public String toString() {
        return "UserHomePage{" +
                "userInfo=" + userInfo +
                ", allMoney=" + allMoney +
                ", mapList=" + mapList +
                ", userVideoList=" + userVideoList +
                ", allLike=" + allLike +
                ", userOwnRankandBrokerage=" + userOwnRankandBrokerage +
                '}';
    }

    public Map getUserOwnRankandBrokerage() {
        return userOwnRankandBrokerage;
    }

    public void setUserOwnRankandBrokerage(Map userOwnRankandBrokerage) {
        this.userOwnRankandBrokerage = userOwnRankandBrokerage;
    }

    public int getAllLike() {
        return allLike;
    }

    public void setAllLike(int allLike) {
        this.allLike = allLike;
    }
}


