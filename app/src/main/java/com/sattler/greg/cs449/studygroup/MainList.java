package com.sattler.greg.cs449.studygroup;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sattler.greg.cs449.studygroup.db.CreateTaskActivity;
import com.sattler.greg.cs449.studygroup.db.TaskContract;
import com.sattler.greg.cs449.studygroup.db.TaskDBHelper;



class task{
    String name="";
    String className="";
    int dateM=0;
    int dateD=0;
    int dateY=0;



    boolean completed=false;
    boolean started=false;

    public task(){}
    public task(task t, String n, String cn, int M, int D, int Y){
        t.name=n;
        t.className=cn;
        t.dateM=M;
        t.dateD=D;
        t.dateY=Y;
    }

    void startTask(task t){
        if (t.started==true){
            t.started=false;
        }
        else;
        {
            t.started = true;
        }
    }

    void completeTask(task t){
        if (t.completed==true){
            t.completed=false;
        }
        else;
        {
            t.completed = true;
        }
    }

}


public class MainList extends AppCompatActivity {

    //    private ListAdapter listAdapter;
    private TaskDBHelper helper;
    private void updateUI() {
        helper = new TaskDBHelper(MainList.this);
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor cursor = sqlDB.query(TaskContract.TABLE,
                new String[]{TaskContract.Columns._ID, TaskContract.Columns.TASK},
                null,null,null,null,null);

//        listAdapter = new SimpleCursorAdapter(
//                this,
//                R.layout.task_view,
//                cursor,
//                new String[]{TaskContract.Columns.TASK},
//                new int[]{R.id.taskTextView},
//                0
//        );
//        ListView listView = (ListView) findViewById(R.id.expandableListView);
//        listView.setAdapter(listAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        updateUI();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                Intent intent = new Intent(this, CreateTaskActivity.class);
                startActivityForResult(intent, 1);
                return true;

            default:
                return false;
        }
    }
    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView taskTextView = (TextView) v.findViewById(R.id.textViewName);
        String task = taskTextView.getText().toString();

        String sql = String.format("DELETE FROM %s WHERE %s = '%s'",
                TaskContract.TABLE,
                TaskContract.Columns.TASK,
                task);


        helper = new TaskDBHelper(MainList.this);
        SQLiteDatabase sqlDB = helper.getWritableDatabase();
        sqlDB.execSQL(sql);
        updateUI();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {

            if (resultCode == RESULT_OK) {
                //do stuff
                updateUI();
            }
            else if (resultCode == RESULT_CANCELED) {
                //forget it
            }
        }
    }
}
