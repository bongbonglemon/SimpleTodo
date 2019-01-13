package com.example.soymilk.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import static com.example.soymilk.simpletodo.MainActivity.*;

public class EditItemActivity extends AppCompatActivity {

    int index;
    String oldItem;
    EditText addNewItem;
    String newItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        index = getIntent().getIntExtra(ITEM_POSITION, 0);
        oldItem = getIntent().getStringExtra(ITEM_TEXT);
        addNewItem = (EditText)findViewById(R.id.newItem);
        addNewItem.setText(oldItem);


    }

    public void saveMethod(View view){
        newItem = addNewItem.getText().toString();
        Intent i = new Intent();
        //pass new info back to MainActivity and update the list there
        i.putExtra(ITEM_POSITION, index);
        i.putExtra(ITEM_TEXT, newItem);
        //set the intent as the result of the activity
        setResult(RESULT_OK, i);

        //MainActivity.listOfItems.set(index, newItem);
        //Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
        finish();
    }
}
