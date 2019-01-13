package com.example.soymilk.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;


/* Stuff learnt:
    1.
    unqualified R class is the class that is generated at compile time by android
    android.R.layout.simple_list_item_1 vs R.id.listview

    2.
    alternative method:
    update list and then notify adapter of change using adapter.notifyDataSetChanged()
    instead of updating list through adapter

    3.
    using Log.i (i stands for information) to debug

    4.
    data persistence using file systems

    5.
    .this refers to the instance of the class while .class refers to the type

    6.
    Could have used .set method from ArrayList class instead to replace item at a speicified position

   Questions:

    1.
   Why is OnLongItemClickListener a boolean?

*/



public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button addButton;
    ListView listView;
    static ArrayList<String> listOfItems;
    ArrayAdapter<String> adapter;

    @Override
    protected void onResume() {
        super.onResume();
        writeItems();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //did not show when coming back from EditItemActivity
        editText = (EditText) findViewById(R.id.editText);
        addButton = (Button) findViewById(R.id.btnAddItem);
        listView = (ListView) findViewById(R.id.lsitems);
        readItems();

        //listOfItems.add("Comb");
        //listOfItems.add("Tree");
        //listOfItems.add("Sky");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfItems);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent openEditItem = new Intent(MainActivity.this, EditItemActivity.class);
                openEditItem.putExtra("index", position);
                startActivity(openEditItem);
            }
        });




        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("MainActivity", "Setting Up OnItemLongClickListener");
                adapter.remove(listOfItems.get(position));
                writeItems();
                return true;
            }
        });
    }

    public void addMethod(View view){
        Toast.makeText(getApplicationContext(), "Item added", Toast.LENGTH_SHORT).show();
        adapter.add(editText.getText().toString());
        writeItems();
        editText.setText("");
    }

    // Persistence using file systems

    private File getDataFile(){
        return new File(getFilesDir(), "todo.txt"); // Creates a new File *instance* from a parent pathname string and a child pathname string.
    }

    private void readItems(){
        try {
            listOfItems = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset())); //read file contents a line at a time and returns a list of Strings
        } catch (IOException e) {
            Log.e("MainActivity", "Error reading file", e);
            listOfItems = new ArrayList<>();

        }
    }

    private void writeItems(){
        try {
            FileUtils.writeLines(getDataFile(), listOfItems); // Writes the toString() value of each item in a collection to the specified File line by line, Overwrites any existing lines
        } catch (IOException e) {
            Log.e("MainActivity", "Error writing file", e);
        }

    }
}
