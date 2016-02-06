package com.example.vincent.crunchtime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final Integer DEFAULT_EXER = ExerciseData.PUSHUPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout topLeft = (RelativeLayout) findViewById(R.id.activity);
        RelativeLayout topRight = (RelativeLayout) findViewById(R.id.energy);

        Exercise topLeftExer = new Exercise(this, DEFAULT_EXER);
        topLeftExer.configure(topLeft, null, 0);
        topLeft.setTag(topLeftExer);

        Exercise topRightExer = new Exercise(this, ExerciseData.ENERGY);
        topRightExer.configure(topRight, topLeftExer, 0);
        topRight.setTag(topRightExer);

        ArrayList<Exercise> gridExercises = new ArrayList<Exercise>();
        for (int type : ExerciseData.NAMES.keySet()) {
            if (type != DEFAULT_EXER && type != ExerciseData.ENERGY) {
                gridExercises.add(new Exercise(this, type));
            }
        }

        GridView grid = (GridView) findViewById(R.id.grid);
        GridIconAdapter gridAdapter = new GridIconAdapter(this, R.layout.exercise, gridExercises, topLeft);
        grid.setAdapter(gridAdapter);

        EditText topLeftAmt = (EditText) topLeft.findViewById(R.id.quantity);
        EditText topRightAmt = (EditText) topRight.findViewById(R.id.quantity);
        topLeftAmt.addTextChangedListener(new Updater(topLeft, topRight, grid));
        topRightAmt.addTextChangedListener(new Updater(topRight, topLeft, grid));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class Updater implements TextWatcher {
        private View topSrc, topOther;
        private EditText editSrc, editOther;
        private GridView grid;

        public Updater (View topSrc, View topOther, GridView grid) {
            this.topSrc = topSrc;
            this.topOther = topOther;
            this.grid = grid;

            editSrc = (EditText) topSrc.findViewById(R.id.quantity);
            editOther = (EditText) topOther.findViewById(R.id.quantity);

            editSrc.setTag(null);       // tag is not-null when text changed programatically
        }

        public void afterTextChanged(Editable s) {
            // prevent infinite loops: don't update anything else if this EditText was not changed by user
            if (!editSrc.hasFocus() || editSrc.getTag() != null)
                return;

            int amt;
            try {
                amt = Integer.parseInt(editSrc.getText().toString());
            }
            catch (NumberFormatException nfe) {
                return;
            }
            int converted = ((Exercise)topSrc.getTag()).toOther((Exercise)topOther.getTag(), amt);
            editOther.setText("" + converted);

            grid.invalidateViews();
        }
        public void beforeTextChanged (CharSequence s, int start, int count, int after) {}
        public void onTextChanged (CharSequence s, int start, int before, int count) {}
    }
}