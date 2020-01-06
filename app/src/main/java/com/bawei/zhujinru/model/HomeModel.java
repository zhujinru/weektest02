package com.bawei.zhujinru.model;

import com.bawei.zhujinru.contract.HomeContract;
import com.bawei.zhujinru.model.bean.GsonBean;
import com.bawei.zhujinru.model.bean.HomeGson;
import com.bawei.zhujinru.util.NetUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements HomeContract.IModel {
    @Override
    public void getGsonData(IModelCallBack iModelCallBack) {
        NetUtil.getInstance().getApi().liebiao().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GsonBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GsonBean gsonBean) {
                          iModelCallBack.GsonSuccess(gsonBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModelCallBack.GsonFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getHomeData(String category, IModelCallBack iModelCallBack) {

        NetUtil.getInstance().getApi().shanpin(category).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeGson>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeGson homeGson) {
                        iModelCallBack.homeSuccess(homeGson);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModelCallBack.homeFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
