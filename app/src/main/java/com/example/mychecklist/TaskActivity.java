package com.example.mychecklist;

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
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mychecklist.db.DbController;

public class TaskActivity extends AppCompatActivity {

    private DbController dbController;
    private ListView listViewTasks;
    private int sessionId;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_task);
        sessionId = getIntent().getIntExtra("session",0);
        dbController = new DbController(this,sessionId);
        listViewTasks = (ListView) findViewById(R.id.listTasks);
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu( menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EditText textBox = new EditText (this);
        textBox.setHint("¿Qué quieres hacer?");
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Nueva Tarea")
                .setView(textBox)
                .setPositiveButton("Añadir", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        String task = textBox.getText().toString();
                        dbController.addTask( sessionId , task);
                        Toast.makeText(getApplicationContext(),"Tarea añadida con éxito",Toast.LENGTH_LONG).show();
                        updateUI();
                    }

                 })
                .setNegativeButton("Cancelar" , null)
                .create();

        dialog.show();
        return super.onOptionsItemSelected(item);
    }

    private void updateUI(){
        if (dbController.regLength() == 0) {
            listViewTasks.setAdapter(null);
        }  else {
            // Adapter para rellenar un ListView
            ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, R.layout.item_task, R.id.task_title, dbController.getAllTask());
            listViewTasks.setAdapter(myAdapter);
        }
    }

    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = parent.findViewById(R.id.task_title);
        String task = taskTextView.getText().toString();
        dbController.deleteTask(task);
        Toast.makeText(this,"Tarea finalizada",Toast.LENGTH_LONG).show();
        updateUI();
    }
}