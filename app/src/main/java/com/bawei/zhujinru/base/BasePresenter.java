package com.bawei.zhujinru.base;

public abstract class BasePresenter <V>{
    public V view;

    public BasePresenter() {
        initModel();
    }

    public void attach(V view) {
        this.view = view;
    }
    public void detach(){
        view=null;
    }

    protected abstract void initModel();
}
