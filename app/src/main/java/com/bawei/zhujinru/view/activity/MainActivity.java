package com.bawei.zhujinru.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.zhujinru.R;
import com.bawei.zhujinru.base.BaseActivity;
import com.bawei.zhujinru.contract.HomeContract;
import com.bawei.zhujinru.database.DaoMaster;
import com.bawei.zhujinru.database.DaoSession;
import com.bawei.zhujinru.database.GsonDaoDao;
import com.bawei.zhujinru.database.HomeDaoDao;
import com.bawei.zhujinru.model.Dao.GsonDao;
import com.bawei.zhujinru.model.Dao.HomeDao;
import com.bawei.zhujinru.model.bean.GsonBean;
import com.bawei.zhujinru.model.bean.HomeGson;
import com.bawei.zhujinru.presenter.HomePresenter;
import com.bawei.zhujinru.util.NetUtil;
import com.bawei.zhujinru.view.adapter.MyAdapter1;
import com.bawei.zhujinru.view.adapter.MyAdapter2;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeContract.IView {

    @BindView(R.id.rec1)
    RecyclerView rec1;
    @BindView(R.id.rec2)
    RecyclerView rec2;
    private GsonDaoDao gsonDaoDao;
    private HomeDaoDao homeDaoDao;

    @Override
    protected HomePresenter providerPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initData() {
        if (NetUtil.getInstance().hasNet(this)) {
            mPresenter.getHomeData("生活");
            mPresenter.getGsonData();
        }else {
            GsonDao unique = gsonDaoDao.queryBuilder().unique();
            String s = unique.getS();
            GsonBean gsonBean = new Gson().fromJson(s, GsonBean.class);
            List<String> category = gsonBean.getCategory();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rec1.setLayoutManager(linearLayoutManager);
            MyAdapter1 myAdapter1 = new MyAdapter1(category);
            rec1.setAdapter(myAdapter1);
            myAdapter1.setOnItemClickListener(new MyAdapter1.onItemClickListener() {
                @Override
                public void onItemClick(int i) {
                    List<HomeDao> list = homeDaoDao.queryBuilder().list();
                    HomeDao homeDao = list.get(i);
                    String home = homeDao.getHome();
                    HomeGson homeGson = new Gson().fromJson(home, HomeGson.class);
                    List<HomeGson.DataBean> data = homeGson.getData();
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                    rec2.setLayoutManager(gridLayoutManager);
                    rec2.setAdapter(new MyAdapter2(data));
                }
            });
        }
 }

    @Override
    protected void initView() {
        DaoSession daoSession = DaoMaster.newDevSession(this, "app.db");
        gsonDaoDao = daoSession.getGsonDaoDao();
        homeDaoDao = daoSession.getHomeDaoDao();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void GsonSuccess(GsonBean gsonBean) {
        List<String> category = gsonBean.getCategory();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rec1.setLayoutManager(linearLayoutManager);
        MyAdapter1 myAdapter1 = new MyAdapter1(category);
        rec1.setAdapter(myAdapter1);
        myAdapter1.setOnItemClickListener(new MyAdapter1.onItemClickListener() {
            @Override
            public void onItemClick(int i) {
                Toast.makeText(MainActivity.this, i+"", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(category.get(i));
            }
        });
        String s = new Gson().toJson(gsonBean);
        GsonDao gsonDao = new GsonDao(s);
        gsonDaoDao.insert(gsonDao);
    }

    @Override
    public void GsonFailure(Throwable throwable) {
        Log.e("xxx",throwable.getMessage());
    }

    @Override
    public void homeSuccess(HomeGson homeGson) {
        List<HomeGson.DataBean> data = homeGson.getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rec2.setLayoutManager(gridLayoutManager);
        rec2.setAdapter(new MyAdapter2(data));
        String str = new Gson().toJson(homeGson);
        HomeDao homeDao = new HomeDao(str);
        homeDaoDao.insert(homeDao);
    }

    @Override
    public void homeFailure(Throwable throwable) {
        Log.e("xxx",throwable.getMessage());
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
@Subscribe
    public void onEven(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        mPresenter.getHomeData(s);
    }
}
