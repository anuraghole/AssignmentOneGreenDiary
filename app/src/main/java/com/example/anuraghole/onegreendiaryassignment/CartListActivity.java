package com.example.anuraghole.onegreendiaryassignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartListActivity extends AppCompatActivity {
    ArrayList<Product> cartList;
    RecyclerView recyclerView;
    ProductsInCartAdapter adapter;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        recyclerView=findViewById(R.id.recyclerView);
        cartList = getIntent().getParcelableArrayListExtra("cartList");
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        if (cartList!=null){
            adapter=new ProductsInCartAdapter(this,cartList);
            recyclerView.setAdapter(adapter);
            if (cartList.isEmpty()){
                Toast.makeText(this, "No items in cart", Toast.LENGTH_SHORT).show();
            }

        }

    }
}
