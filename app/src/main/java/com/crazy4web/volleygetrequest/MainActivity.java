package com.crazy4web.volleygetrequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button btn;
    private TextView txt;
    private Spinner input, input2;
    private RequestQueue mrequestQue;
    private StringRequest mstringRequest;
    private String url
            , search;

    List<String> dataitems = new ArrayList<>();

    String spinnertext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        txt = findViewById(R.id.textView);;


        input = (Spinner) findViewById(R.id.Spinner1);

        input2 = (Spinner) findViewById(R.id.Spinner2);

        //Spinner1

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.endpoints,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        input.setAdapter(adapter);

        input.setOnItemSelectedListener(this);





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                Log.d("input",spinnertext);

               url = "https://learn.operatoroverload.com/rental/"+spinnertext+"";

                sendandreceiveData();


            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnertext = adapterView.getItemAtPosition(i).toString();

        Toast.makeText(getApplicationContext(),spinnertext,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private void sendandreceiveData() {

        mrequestQue = Volley.newRequestQueue(this);
        mstringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                dataitems.add(response);


                txt.setText(response.toString());

                Log.d("response",""+dataitems.get(0));



                for(String items : dataitems){



                        Log.d("item", items);


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                txt.setText("Your EndPoint doesn't Exist");

            }
        });

        mrequestQue.add(mstringRequest);
    }

   }
