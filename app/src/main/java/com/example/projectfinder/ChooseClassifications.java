package com.example.projectfinder;

import android.os.Bundle;

import com.github.tommykw.tagview.DataTransform;
import com.github.tommykw.tagview.TagView;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ChooseClassifications extends AppCompatActivity {
    private enum Item{
        JAVA("Java",1),
        PYTHON("Python",2),
        PHP("PHP",3),
        CPP("C++",4),
        HASKELL("Haskell",5),
        WEB_DEV("Web development",6),
        ML("Machine Learning",7);


        private final String name;
        private final int id;
        private Item(String s, int i) {
            name = s;
            id = i;
        }

        public String getName() {
            return this.name;
        }

        public int getId() {
            return this.id;

        }
    }
    private ArrayList<String> userInterests = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_chooser2);
        //TODO: Add tags from tag object
        List<Item> list = new ArrayList<>();
        list = Arrays.asList(Item.values());
        TagView<Item> tags = findViewById(R.id.tagview);
        tags.setTags(list, new DataTransform<Item>() {

            @Override
            public String transfer(Item item) {
                return item.getName();
            }
        });
        tags.setClickListener(new TagView.TagClickListener<Item>() {
            @Override
            public void onTagClick(Item item) {
                item.getId();
            }
        });







    }
}