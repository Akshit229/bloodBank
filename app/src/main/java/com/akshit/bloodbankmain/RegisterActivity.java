package com.akshit.bloodbankmain;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.akshit.bloodbankmain.Utils.Endpoints;
import com.akshit.bloodbankmain.Utils.VolleySingleton;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

  private EditText nameEt, cityEt, bloodGroupEt, passwordEt, mobileEt;
  private Button submitButton;
  private Toolbar main_toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    nameEt = findViewById(R.id.name);
    cityEt = findViewById(R.id.city);
    bloodGroupEt = findViewById(R.id.blood_group);
    passwordEt = findViewById(R.id.password);
    mobileEt = findViewById(R.id.number);
    main_toolbar = findViewById(R.id.main_toolbar);
    submitButton = findViewById(R.id.submit_button);
    submitButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        String name, city, blood_group, password, mobile;
        name = nameEt.getText().toString();
        city = cityEt.getText().toString();
        blood_group = bloodGroupEt.getText().toString();
        password = passwordEt.getText().toString();
        mobile = mobileEt.getText().toString();
        if (isValid(name, city, blood_group, password, mobile)) {
          register(name, city, blood_group, password, mobile);
        }
      }
    });

    setUpToolBar();

  }

  private void setUpToolBar(){
    setSupportActionBar(main_toolbar);
    ActionBar ab = getSupportActionBar();
    ab.setTitle("");
    ab.setDisplayHomeAsUpEnabled(true);
  }


  private void register(final String name, final String city, final String blood_group, final String password,
      final String mobile) {
      StringRequest stringRequest = new StringRequest(Method.POST, Endpoints.register_url, new Listener<String>() {
      @Override
      public void onResponse(String response) {
          try {
            JSONObject object = new JSONObject(response);
            if (!object.getBoolean("error")) {
              PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("city", city).apply();
              PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("number", mobile).apply();
              Toast.makeText(RegisterActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
              startActivity(new Intent(RegisterActivity.this, MainActivity.class));
              RegisterActivity.this.finish();
            }else{
              Toast.makeText(RegisterActivity.this, object.getString("message"), Toast.LENGTH_SHORT).show();
            }
          } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(RegisterActivity.this, "JSON error", Toast.LENGTH_SHORT).show();
          }
      }
    }, new ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Toast.makeText(RegisterActivity.this, "Something went wrong:(", Toast.LENGTH_SHORT).show();
//        Log.d("VOLLEY", error.getMessage());
      }
    }){
      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("city", city);
        params.put("blood_group", blood_group);
        params.put("password", password);
        params.put("pin_code", "my_pin");
        params.put("phone_number", mobile);
        return params;
      }
    };
    VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    stringRequest.setRetryPolicy(new RetryPolicy() {
      @Override
      public int getCurrentTimeout() {
        return 50000;
      }

      @Override
      public int getCurrentRetryCount() {
        return 50000;
      }

      @Override
      public void retry(VolleyError error) throws VolleyError {

      }
    });
  }


  private boolean isValid(String name, String city, String blood_group, String password,
      String mobile) {
    List<String> valid_blood_groups = new ArrayList<>();
    valid_blood_groups.add("A+");
    valid_blood_groups.add("A-");
    valid_blood_groups.add("B+");
    valid_blood_groups.add("B-");
    valid_blood_groups.add("AB+");
    valid_blood_groups.add("AB-");
    valid_blood_groups.add("O+");
    valid_blood_groups.add("O-");
    if (name.isEmpty()) {
      showMessage("Name is empty");
      return false;
    } else if (city.isEmpty()) {
      showMessage("City name is required");
      return false;
    } else if (!valid_blood_groups.contains(blood_group)) {
      showMessage("Blood group invalid choose from " + valid_blood_groups);
      return false;
    } else if (mobile.length() != 10) {
      showMessage("Invalid mobile number, number should be of 10 digits");
      return false;
    }
    return true;
  }


  private void showMessage(String msg) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
  }


}
