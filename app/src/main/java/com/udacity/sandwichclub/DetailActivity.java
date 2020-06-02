package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Log.e("DETAIL ACITIVITY","SEUCCESSFULLY RAN");

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        Intent intent = getIntent();
        if (intent == null) {
            Log.e("ERRORRRRRR","INTENT WAS NULLLL");
            closeOnError();
        }
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            Log.e("ERRORRRRRR","POSITION CLICK NOT AVAILABLE");
            closeOnError();
            return;
        }
        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            Log.e("ERRORRRRRR","SANDWICH WAS NULLLL");
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }
    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }
    private void populateUI(Sandwich sandwich)
    {
        TextView alsoKnownAsTextView = findViewById(R.id.also_known_tv);
        TextView originTextView = findViewById(R.id.origin_tv);
        TextView descTextView = findViewById(R.id.description_tv);
        TextView ingredientsTextView = findViewById(R.id.ingredients_tv);
        TextView mainNameTextView = findViewById(R.id.main_name_tv);
        originTextView.setText(sandwich.getPlaceOfOrigin());
        descTextView.setText(sandwich.getDescription());
        mainNameTextView.setText(sandwich.getMainName());
        if(sandwich.getAlsoKnownAs() != null)
        {
            String alsoKnownAs = sandwich.getAlsoKnownAs().toString();
            alsoKnownAs=alsoKnownAs.replace("[","");
            alsoKnownAs=alsoKnownAs.replace("]","");
            alsoKnownAsTextView.setText(alsoKnownAs);
        }
        else
            alsoKnownAsTextView.setVisibility(View.GONE);

        if(sandwich.getIngredients() != null)
        {
            String ingredients = sandwich.getIngredients().toString();
            ingredients=ingredients.replace("[","");
            ingredients=ingredients.replace("]","");
            ingredientsTextView.setText(ingredients);
        }
        else
            ingredientsTextView.setVisibility(View.GONE);
    }
}
