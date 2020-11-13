package com.hieu.tiki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    GridView gridView;
    GridViewBaseAdapter gridViewBaseAdapter;
    List<GridViewBean> lc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView=findViewById(R.id.gridView);
        lc=getListData();
        gridViewBaseAdapter=new GridViewBaseAdapter(this,lc);
        gridView.setAdapter(gridViewBaseAdapter);
        registerForContextMenu(this.gridView);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("FazMall"));
        tabLayout.addTab(tabLayout.newTab().setText("Free Shipping"));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GridViewBean gridViewBean =(GridViewBean) getListData().get(position);
                Intent intent=new Intent(MainActivity.this, GridViewActivity.class);
                intent.putExtra("noidung", gridViewBean.getTen());
                intent.putExtra("gia",gridViewBean.getGia());
                startActivity(intent);
            }
        });
    }

    private List<GridViewBean> getListData() {
        List<GridViewBean> gridViewBeanList =new ArrayList<>();
        GridViewBean gridViewBean1 =new GridViewBean(R.drawable.phone2,"Điện thoại Nokia","đ20.000.000đ");
        GridViewBean gridViewBean3 =new GridViewBean(R.drawable.vivo,"Điện thoại Samsung","đ20.000.000");
        GridViewBean gridViewBean4 =new GridViewBean(R.drawable.oppo,"Điện thoại LG","đ7.000.000đ");
        GridViewBean gridViewBean5 =new GridViewBean(R.drawable.phone2,"Điện thoại Xiaomi","đ20.000.000đ");
        GridViewBean gridViewBean6 =new GridViewBean(R.drawable.iphone,"Điện thoại Realme","đ4.000.000đ");
        GridViewBean gridViewBean7 =new GridViewBean(R.drawable.oppo,"Điện thoại Oppo","đ5.500.000đ");

        gridViewBeanList.add(gridViewBean1);
        gridViewBeanList.add(gridViewBean3);
        gridViewBeanList.add(gridViewBean4);
        gridViewBeanList.add(gridViewBean5);
        gridViewBeanList.add(gridViewBean6);
        gridViewBeanList.add(gridViewBean7);
        return gridViewBeanList;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Chọn hành động");
        menu.add(0,1,0,"Xóa");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final GridViewBean gridViewBeanSelected =(GridViewBean) this.getListData().get(info.position);
        if(item.getItemId() == 1){
            // Ask before deleting.
            new AlertDialog.Builder(this)
                    .setMessage(gridViewBeanSelected.getTen()+". Bạn có muốn xoá?")
                    .setCancelable(false)
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            deleteItem(info.position);
                        }
                    })
                    .setNegativeButton("Không", null)
                    .show();
        }
        else
            return false;
        return true;
    }
    private void deleteItem(int item)  {
        lc.remove(item);
        gridViewBaseAdapter.notifyDataSetChanged();
    }
}