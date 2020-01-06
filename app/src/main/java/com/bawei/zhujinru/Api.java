package com.bawei.zhujinru;

import com.bawei.zhujinru.model.bean.GsonBean;
import com.bawei.zhujinru.model.bean.HomeGson;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("category")
    Observable<GsonBean> liebiao();
    @GET("shopByCategory")
    Observable<HomeGson> shanpin(@Query("category") String category);
}
