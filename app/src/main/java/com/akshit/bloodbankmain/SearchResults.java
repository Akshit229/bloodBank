package com.akshit.bloodbankmain;

import android.content.Intent;
import android.text.SpannableString;
import android.text.TextUtils;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.akshit.bloodbankmain.Adapters.SearchAdapter;
import com.akshit.bloodbankmain.Models.Donor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity implements SearchAdapter.OnItemClickListener {

  List<Donor> donorList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_results);
    donorList = new ArrayList<>();
    String json;
    String city, blood_group;
    Intent intent = getIntent();
    json = intent.getStringExtra("json");
    city = intent.getStringExtra("city");
    blood_group = intent.getStringExtra("blood_group");
    TextView heading = findViewById(R.id.heading);
    String str = "Donors in " + city + " with blood group " + blood_group;
    heading.setText(str);

    ArrayList<Donor> dataModels = null;
    try {
      dataModels = extractDonorsFromJSON(json);
      if (dataModels != null && dataModels.isEmpty()) {
        heading.setText("No results");
      }else if(dataModels!=null){
        donorList.addAll(dataModels);
      }

      RecyclerView recyclerView = findViewById(R.id.recycler_view);
      LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
      recyclerView.setLayoutManager(layoutManager);

      SearchAdapter adapter = new SearchAdapter(donorList, SearchResults.this);
      adapter.setOnItemClickListener(this);
      recyclerView.setAdapter(adapter);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  private ArrayList<Donor> extractDonorsFromJSON(String jsonResponse) throws JSONException {
    if(TextUtils.isEmpty(jsonResponse)){
      return null;
    }

    ArrayList<Donor> resList = new ArrayList<>();
    JSONObject baseJsonObject = new JSONObject(jsonResponse);
    JSONArray results = baseJsonObject.getJSONArray("result");
    for(int i=0;i<results.length();i++){
      JSONObject currObject = results.getJSONObject(i);
      Donor donor = new Donor();
      donor.setBlood_group(currObject.getString("blood_group"));
      donor.setCity(currObject.getString("city"));
      donor.setName(currObject.getString("name"));
      donor.setPhone_number(currObject.getString("phone_number"));
      donor.setPin_code(currObject.getString("pin_code"));
      resList.add(donor);
    }
    return resList;
  }


  @Override
  public void onClickItem(int positon) {
    Toast.makeText(this, "item clicked at : "+ positon, Toast.LENGTH_LONG).show();
  }
}
