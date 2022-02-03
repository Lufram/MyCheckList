package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.db.DbController;

public class TaskActivity extends AppCompatActivity {

    private DbController dbController;
    private ArrayAdapter<String> myAdapter;
    private ListView listViewTasks;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbController = new DbController(this);
        listViewTasks = (ListView) findViewById(R.id.listTasks);
        updateUI();
    }

    @Override
    public boolean onOptionsItemMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu( menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EditText textBox = new EditText (this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("New task")
                .setMessage("What do you want to do next?")
                .setView(textBox)
                .setPositiveButton("Add", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        String task = textBox.getText().toString();
                        dbController.addTask(user_id, task);
                        updateUI();
                    }
                 })
                .setNegativeButton("Cancel" , null)
                .create();
        dialog.show();
        return super.onOptionsItemSelected(item);
    }

    private void updateUI(){

        myAdapter = new ArrayAdapter<>(this, layout.item_task, R.id.task_title, dbController.getAllTask());
        listViewTasks.setAdapter(myAdapter);

    }

    public void deleteTask(View view) {
        View parent = view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String task = taskTextView.getText().toString();
        dbController.deleteTask(task);
        updateUI();
    }


}