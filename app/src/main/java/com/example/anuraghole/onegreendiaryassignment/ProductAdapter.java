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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    ArrayList<Product> productList;
    Context context;
    CartValueListener cartValueListener;
    ArrayList<Product> cartProductList = new ArrayList<>();

    public ProductAdapter(Context context, ArrayList<Product> productList, CartValueListener cartValueListener) {
        this.productList = productList;
        this.context = context;
        this.cartValueListener = cartValueListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_product, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final Product product = productList.get(position);
        holder.tvName.setText(product.getName());
        holder.tvDescription.setText(product.getDescription());
        int price=product.getPrice();
        holder.tvPrice.setText(context.getResources().getString(R.string.Rs)+" "+price);
        Glide.with(context).load(product.getImage()).into(holder.ivProduct);
        holder.tvAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart(product);
            }
        });


    }

    private void addToCart(Product product) {
        cartProductList.add(product);
        cartValueListener.setValues(cartProductList);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPrice, tvAddToCart, tvName, tvDescription;
        private ImageView ivProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvAddToCart = itemView.findViewById(R.id.tvAddToCart);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct);
        }
    }
}
