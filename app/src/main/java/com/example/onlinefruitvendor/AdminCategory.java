package com.example.onlinefruitvendor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdminCategory extends AppCompatActivity {

    private ImageView bananas, pawpaw, oranges;
    private ImageView avocados, grapes, tangerines;
    private ImageView pineapples, watermelons, fruitSalads;
    private Button checkOrdersBtn, logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        logoutBtn = findViewById(R.id.admin_logout_btn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        checkOrdersBtn = findViewById(R.id.check_orders_btn);
        checkOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, AdminNewOrder.class);
                startActivity(intent);
            }
        });

        bananas = findViewById(R.id.bananas);
        pawpaw = findViewById(R.id.pawpaw);
        oranges = findViewById(R.id.oranges);
        avocados = findViewById(R.id.avocado);
        grapes = findViewById(R.id.grapes);
        tangerines = findViewById(R.id.tangerine);
        pineapples = findViewById(R.id.pineapple);
        watermelons = findViewById(R.id.watermelon);
        fruitSalads = findViewById(R.id.fruitSalad);


        bananas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, AdminAddNewProduct.class);
                intent.putExtra("category", "bananas");
                startActivity(intent);
            }
        });

        pawpaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, AdminAddNewProduct.class);
                intent.putExtra("category", "pawpaw");
                startActivity(intent);
            }
        });

        oranges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, AdminAddNewProduct.class);
                intent.putExtra("category", "oranges");
                startActivity(intent);
            }
        });

        avocados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, AdminAddNewProduct.class);
                intent.putExtra("category", "bananas");
                startActivity(intent);
            }
        });

        grapes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, AdminAddNewProduct.class);
                intent.putExtra("category", "grapes");
                startActivity(intent);
            }
        });
        tangerines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, AdminAddNewProduct.class);
                intent.putExtra("category", "tangerines");
                startActivity(intent);
            }
        });

        pineapples.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, AdminAddNewProduct.class);
                intent.putExtra("category", "pineapples");
                startActivity(intent);
            }
        });

        watermelons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, AdminAddNewProduct.class);
                intent.putExtra("category", "watermelons");
                startActivity(intent);
            }
        });

        fruitSalads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, AdminAddNewProduct.class);
                intent.putExtra("category", "fruit salads");
                startActivity(intent);
            }
        });
    }
}
