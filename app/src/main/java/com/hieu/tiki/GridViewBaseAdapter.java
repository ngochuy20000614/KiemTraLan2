package com.hieu.tiki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridViewBaseAdapter extends BaseAdapter {
    private List<GridViewBean> lc;
    private Context context;
    private LayoutInflater layoutInflater;

    public GridViewBaseAdapter(Context context, List<GridViewBean> lc){
        this.context=context;
        this.lc=lc;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return lc.size();
    }

    @Override
    public Object getItem(int position) {
        return lc.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.child,null);
            viewHolder=new ViewHolder();
            viewHolder.image=(ImageView) convertView.findViewById(R.id.imghinhanh);
            viewHolder.ten=(TextView) convertView.findViewById(R.id.tenServices);
            viewHolder.gia=(TextView)convertView.findViewById(R.id.giaServices);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder=(ViewHolder) convertView.getTag();

        GridViewBean gridViewBean =this.lc.get(position);
        viewHolder.image.setImageResource(gridViewBean.getImage());
        viewHolder.ten.setText(gridViewBean.getTen());
        viewHolder.gia.setText(gridViewBean.getGia());
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView ten,mota,gia;
    }
}
