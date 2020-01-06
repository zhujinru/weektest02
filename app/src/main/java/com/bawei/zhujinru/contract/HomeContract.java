package com.bawei.zhujinru.contract;

import com.bawei.zhujinru.model.bean.GsonBean;
import com.bawei.zhujinru.model.bean.HomeGson;

public interface HomeContract {
    interface IView{
        void GsonSuccess(GsonBean gsonBean);
        void GsonFailure(Throwable throwable);
        void homeSuccess(HomeGson homeGson);
        void homeFailure(Throwable throwable);
    }
    interface IPresenter{
        void getGsonData();
        void getHomeData(String category);
    }
    interface IModel{
        void getGsonData(IModelCallBack iModelCallBack);
        void getHomeData(String category,IModelCallBack iModelCallBack);
        interface IModelCallBack{
            void GsonSuccess(GsonBean gsonBean);
            void GsonFailure(Throwable throwable);
            void homeSuccess(HomeGson homeGson);
            void homeFailure(Throwable throwable);
        }
    }
}
