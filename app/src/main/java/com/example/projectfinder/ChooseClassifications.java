package com.example.projectfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.github.tommykw.tagview.DataTransform;
import com.github.tommykw.tagview.TagView;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;



public class ChooseClassifications extends AppCompatActivity implements View.OnClickListener {


    StringBuilder sb = new StringBuilder();
    private HashMap<Integer, String> ha = new HashMap<Integer, String>();

    public void initHash(){
        ha.put(1,"Machine Learning");
        ha.put(2,"Python");
        ha.put(3,"Javascript");
        ha.put(4,"Web Development");
        ha.put(5,"Data Science");
        ha.put(6,"Deep learning");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_chooser2);
        initHash();


        Button button1  = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2  = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3  = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4  = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);
        Button button5  = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(this);
        Button button6  = (Button)findViewById(R.id.button6);
        button6.setOnClickListener(this);
         //TODO: Add tags from tag object

    }

    public String getUserSelection() {
        return sb.toString();
    }



    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.done:
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", this.getUserSelection());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                break;
            case R.id.button1:
                sb.append(ha.get(1)).append(",");
                break;
            case R.id.button2:
                sb.append(ha.get(2)).append(",");
                break;
            case R.id.button3:
                sb.append(ha.get(3)).append(",");
                break;
            case R.id.button4:
                sb.append(ha.get(4)).append(",");
                break;
            case R.id.button5:
                sb.append(ha.get(5)).append(",");
                break;
            case R.id.button6:
                sb.append(ha.get(6)).append(",");
                break;

        }

    }
}