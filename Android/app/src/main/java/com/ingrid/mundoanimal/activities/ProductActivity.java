package com.ingrid.mundoanimal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ingrid.mundoanimal.R;

public class ProductActivity extends AppCompatActivity {

    public static final String PARAM_ID = "paramCategoryId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        int categoryId = getIntent().getIntExtra(PARAM_ID, -1);
        Log.d("category", ""+categoryId);
    }
}