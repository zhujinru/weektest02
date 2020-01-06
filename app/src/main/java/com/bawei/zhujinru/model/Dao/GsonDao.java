package com.bawei.zhujinru.model.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GsonDao {
    String s;

    @Generated(hash = 2106166150)
    public GsonDao(String s) {
        this.s = s;
    }

    @Generated(hash = 683220977)
    public GsonDao() {
    }

    public String getS() {
        return this.s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
