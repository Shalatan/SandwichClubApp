package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils
{
    public static Sandwich parseSandwichJson(String json)
    {
        Sandwich sandwich = new Sandwich();
        ArrayList<String> alsoKnownAsList = new ArrayList<>();
        ArrayList<String> ingredientsList = new ArrayList<>();
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonName = jsonObject.getJSONObject("name");
            String mainName = jsonName.getString("mainName");
            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            String description = jsonObject.getString("description");
            String image = jsonObject.getString("image");
            JSONArray alsoKnownAs = jsonObject.getJSONArray("alsoKnownAs");
            for (int i = 0; i < alsoKnownAs.length(); i++)
            {
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            }
            JSONArray ingredients = jsonObject.getJSONArray("ingredients");
            for (int i = 0; i < ingredients.length(); i++)
            {
                ingredientsList.add(ingredients.getString(i));
            }
            Log.e("SANDWICH Name",description);
            sandwich.setMainName(mainName);
            sandwich.setDescription(description);
            sandwich.setPlaceOfOrigin(placeOfOrigin);
            sandwich.setAlsoKnownAs(alsoKnownAsList);
            sandwich.setIngredients(ingredientsList);
            sandwich.setImage(image);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("SANDWICH DETAIL","SANDWICH DETAIL SENT");
        return sandwich;
    }
}
