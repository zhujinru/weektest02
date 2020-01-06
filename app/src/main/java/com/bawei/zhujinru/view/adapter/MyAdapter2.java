package com.bawei.zhujinru.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.zhujinru.R;
import com.bawei.zhujinru.model.bean.HomeGson;
import com.bawei.zhujinru.util.NetUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {

    private List<HomeGson.DataBean> data;

    public MyAdapter2(List<HomeGson.DataBean> data) {

        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.child2, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HomeGson.DataBean dataBean = data.get(position);

        holder.cText.setText(dataBean.getGoods_english_name());
        holder.cName.setText(dataBean.getGoods_name());
        holder.cPrice.setText(dataBean.getCurrency_price() + "");
        NetUtil.getInstance().getPho(dataBean.getGoods_thumb(), holder.cImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.c_text)
        TextView cText;
        @BindView(R.id.c_name)
        TextView cName;
        @BindView(R.id.c_price)
        TextView cPrice;
        @BindView(R.id.c_image)
        ImageView cImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    onItemClickListener onItemClickListener;

    public void setOnItemClickListener(MyAdapter2.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onItemClick(int i);
    }
}
