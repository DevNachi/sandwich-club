package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    //
    ImageView ingredientsIv;
    TextView setNickname;
    TextView setIngredients;
    TextView setOrigin;
    TextView setDescription;

    String nickname, origin, description, ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);
        setNickname=findViewById(R.id.knownAs);
        setIngredients=findViewById(R.id.ingredients);
        setOrigin=findViewById(R.id.origin);
        setDescription=findViewById(R.id.description);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        assert intent != null;
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
        nickname=sandwich.getAlsoKnownAs().toString();
        ingredients=sandwich.getIngredients().toString();
        origin=sandwich.getPlaceOfOrigin();
        description=sandwich.getDescription();

        populateUI();
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        setNickname.setText(nickname.substring(1,nickname.length()-1));
        setIngredients.setText(ingredients.substring(1,nickname.length()-1));
        setOrigin.setText(origin);
        setDescription.setText(description);
    }
}

