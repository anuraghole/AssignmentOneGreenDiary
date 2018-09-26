package com.example.anuraghole.onegreendiaryassignment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements CartValueListener{
    Toolbar toolbar;
    Menu customMenu;
    int cartCount = 0;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Product> productList;
    ArrayList<Product> gcartProductList;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Product List");
        setSupportActionBar(toolbar);
        gcartProductList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        productList=makeProductList();
        adapter=new ProductAdapter(this,productList,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setValues(ArrayList<Product> cartProductList) {
        gcartProductList.clear();
        cartCount=cartProductList.size();
        customMenu = updateCart(customMenu, String.valueOf(cartCount));
        gcartProductList.addAll(cartProductList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        customMenu = updateCart(menu, String.valueOf(cartCount));
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                Toast.makeText(this, "cart click", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Menu updateCart(Menu customMenu, String count) {

        MenuItem item = customMenu.findItem(R.id.cart);
        MenuItemCompat.setActionView(item, R.layout.custom_cart_layout);
        RelativeLayout cartView = (RelativeLayout) MenuItemCompat.getActionView(item);

        ImageView ivCart = (ImageView) cartView.findViewById(R.id.imgCart);
        TextView tv = (TextView) cartView.findViewById(R.id.tvCartCount);
        tv.setText(count);

        ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CartListActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelableArrayList("cartList",gcartProductList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return customMenu;
    }


    private ArrayList<Product> makeProductList() {
        ArrayList<Product> productArrayList = new ArrayList<>();
        int price = 1000;
        for (int i = 0; i < 10; i++) {
            price = price + i;
            Product product = new Product();
            product.setName("Product " + i);
            product.setDescription("Description " + i);
            product.setPrice(price);
            Bitmap productImg;
            Uri uri;
            if (i % 2 == 0) {
                productImg = BitmapFactory.decodeResource(getResources(),
                        R.drawable.img4);
               //uri=getImageUri(this,productImg);
                 uri = Uri.parse("android.resource://com.example.anuraghole.onegreendiaryassignment/drawable/img4");


            } else {
                productImg = BitmapFactory.decodeResource(getResources(),
                        R.drawable.img5);
                //uri=getImageUri(this,productImg);
                uri = Uri.parse("android.resource://com.example.anuraghole.onegreendiaryassignment/drawable/img5");


            }
            product.setImage(uri);

            productArrayList.add(product);
        }
        return productArrayList;

    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
