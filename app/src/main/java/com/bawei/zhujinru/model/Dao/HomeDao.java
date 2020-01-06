package com.bawei.zhujinru.model.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class HomeDao {
    String home;

    @Generated(hash = 123048036)
    public HomeDao(String home) {
        this.home = home;
    }

    @Generated(hash = 1174376228)
    public HomeDao() {
    }

    public String getHome() {
        return this.home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
