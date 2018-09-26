package com.example.anuraghole.onegreendiaryassignment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductsInCartAdapter  extends RecyclerView.Adapter<ProductsInCartAdapter.ProductsInCartViewHolder>{
    private ArrayList<Product> cartProductList;
    private  Context context;

    ProductsInCartAdapter(Context context,ArrayList<Product> cartProductList){
        this.context=context;
        this.cartProductList=cartProductList;
    }

    @NonNull
    @Override
    public ProductsInCartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_in_cart, viewGroup, false);
        return new ProductsInCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsInCartViewHolder holder, int i) {
        Product product=cartProductList.get(i);
        holder.tvName.setText(product.getName());
        holder.tvDescription.setText(product.getDescription());
        holder.tvPrice.setText(context.getResources().getString(R.string.Rs)+" "+product.getPrice());
        Glide.with(context).load(product.getImage()).into(holder.ivProduct);
    }

    @Override
    public int getItemCount() {
        return cartProductList.size();
    }



    static class ProductsInCartViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPrice,tvName, tvDescription;
        private ImageView ivProduct;

        ProductsInCartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct);
        }
    }
}
