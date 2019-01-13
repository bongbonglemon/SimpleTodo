package com.example.soymilk.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditItemActivity extends AppCompatActivity {

    int index;
    EditText addNewItem;
    String newItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        index = getIntent().getIntExtra("index", 0);
        addNewItem = (EditText)findViewById(R.id.newItem);

    }

    public void saveMethod(View view){
        newItem = addNewItem.getText().toString();
        MainActivity.listOfItems.remove(index);
        MainActivity.listOfItems.add(index, newItem);
        Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
        finish();
    }
}
