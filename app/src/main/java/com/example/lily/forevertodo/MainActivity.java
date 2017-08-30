package com.example.lily.forevertodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> todoItems;
    private ArrayAdapter<String> todoItemsAdapter;
    private ListView listViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readItems();

        listViewItems = (ListView) findViewById(R.id.listViewItems);
        todoItemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
        listViewItems.setAdapter(todoItemsAdapter);

        setupListViewListener();
    }

    private void setupListViewListener() {
        listViewItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(
                            AdapterView<?> adapterView, View item, int pos, long id) {

                        todoItems.remove(pos);
                        todoItemsAdapter.notifyDataSetChanged();

                        writeItems();

                        return true;
                    }
                }
        );
    }

    public void onAddItem(View v) {
        EditText editText =  (EditText) findViewById(R.id.editTextNewItem);
        todoItems.add(editText.getText().toString());
        writeItems();
        editText.setText("");
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            todoItems = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            todoItems = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, todoItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
