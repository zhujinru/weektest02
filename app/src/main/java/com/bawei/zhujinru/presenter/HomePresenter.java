package com.bawei.zhujinru.presenter;

import com.bawei.zhujinru.base.BasePresenter;
import com.bawei.zhujinru.contract.HomeContract;
import com.bawei.zhujinru.model.HomeModel;
import com.bawei.zhujinru.model.bean.GsonBean;
import com.bawei.zhujinru.model.bean.HomeGson;

public class HomePresenter extends BasePresenter<HomeContract.IView> implements HomeContract.IPresenter {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    @Override
    public void getGsonData() {
         homeModel.getGsonData(new HomeContract.IModel.IModelCallBack() {
             @Override
             public void GsonSuccess(GsonBean gsonBean) {
                 view.GsonSuccess(gsonBean);
             }

             @Override
             public void GsonFailure(Throwable throwable) {
                 view.GsonFailure(throwable);
             }

             @Override
             public void homeSuccess(HomeGson homeGson) {
                 view.homeSuccess(homeGson);
             }

             @Override
             public void homeFailure(Throwable throwable) {
                 view.homeFailure(throwable);
             }
         });
    }

    @Override
    public void getHomeData(String category) {
      homeModel.getHomeData(category, new HomeContract.IModel.IModelCallBack() {
          @Override
          public void GsonSuccess(GsonBean gsonBean) {
              view.GsonSuccess(gsonBean);
          }

          @Override
          public void GsonFailure(Throwable throwable) {
              view.GsonFailure(throwable);
          }

          @Override
          public void homeSuccess(HomeGson homeGson) {
              view.homeSuccess(homeGson);
          }

          @Override
          public void homeFailure(Throwable throwable) {
              view.homeFailure(throwable);
          }
      });
    }
}
