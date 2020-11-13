package com.hieu.tiki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GridViewActivity extends AppCompatActivity {
    TextView textContext, gia;
    ImageView imageBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        textContext=findViewById(R.id.tvTenSanPham);
        gia = findViewById(R.id.tvGiaSanPham);
        Intent intent=this.getIntent();
        textContext.setText(intent.getStringExtra("noidung"));
        gia.setText(intent.getStringExtra("gia"));
        imageBack = (ImageView)findViewById(R.id.imgBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GridViewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
