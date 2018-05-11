package com.muzi.itemview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ItemGroup itemGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemGroup = findViewById(R.id.itemGrop);
        itemGroup.setOnSelect(new ItemGroup.OnSelect() {
            @Override
            public void onSelect(int position) {
                Toast.makeText(MainActivity.this, "详情-->" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
