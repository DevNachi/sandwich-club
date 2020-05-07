package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
     Sandwich tastySandwich=new Sandwich();
      //Declaring localVariables
        String sandwichName="", sandwichDescription="", sandwichOrigin="",imageSrc="";
        List<String> ingredients= new ArrayList<>();
        List<String> nickname= new ArrayList<>();

      //Extract data from JSON
      try{

          JSONObject sandwichClub = new JSONObject(json);
          JSONObject Sanwich = sandwichClub.getJSONObject("name");

          sandwichName = Sanwich.getString("mainName");
          sandwichDescription = sandwichClub.getString("description");



          JSONArray jaAlsoKnownAs = Sanwich.getJSONArray("alsoKnownAs");
          for (int i = 0; i < jaAlsoKnownAs.length(); i++) {
              String strAlsoKnownAs = jaAlsoKnownAs.getString(i);
              nickname.add(strAlsoKnownAs);
          }

          sandwichOrigin = sandwichClub.getString("placeOfOrigin");
          imageSrc = sandwichClub.getString("image");


          JSONArray jaIngredients = sandwichClub.getJSONArray("ingredients");
          for (int i = 0; i < jaIngredients.length(); i++) {
              String strIngredients = jaIngredients.getString(i);
              ingredients.add(strIngredients);
          }
      }catch (JSONException e) {
          e.printStackTrace();
      }

        //Store JSON values into new Sandwitch object
        tastySandwich.setMainName(sandwichName);
        tastySandwich.setIngredients(ingredients);
        tastySandwich.setAlsoKnownAs(nickname);
        tastySandwich.setDescription(sandwichDescription);
        tastySandwich.setImage(imageSrc);
        tastySandwich.setPlaceOfOrigin(sandwichOrigin);



        return tastySandwich;
    }
}
