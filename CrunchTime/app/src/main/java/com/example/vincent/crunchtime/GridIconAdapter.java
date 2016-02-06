package com.example.vincent.crunchtime;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vincent on 2/5/2016.
 */
public class GridIconAdapter extends ArrayAdapter<Exercise> {
    Context context;
    int layoutResourceId;
    View topLeft;
    List<Exercise> data;

    public GridIconAdapter(Context context, int layoutResourceId, List<Exercise> data, View topLeft) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.topLeft = topLeft;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // disable editing for reps/min in grid
        ((TextView)convertView.findViewById(R.id.quantity)).setFocusableInTouchMode(false);

        // set reps/min for this exercise
        Exercise exer = data.get(position);
        String refAmtStr = ((EditText)topLeft.findViewById(R.id.quantity)).getText().toString();
        int val = 0;
        try {
            val = Integer.parseInt(refAmtStr);
        } catch (NumberFormatException nfe) {}

        exer.configure(convertView, (Exercise) topLeft.getTag(), val);
        convertView.setTag(exer);

        final ImageView imgView = (ImageView) convertView.findViewById(R.id.icon);
        final View convertViewCpy = convertView;
        final List<Exercise> dataCpy = data;
        final int positionCpy = position;
        imgView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println(event.getAction());
                System.out.println("down = " + MotionEvent.ACTION_DOWN);
                System.out.println("up = " + MotionEvent.ACTION_UP);
                System.out.println("cancel = " + MotionEvent.ACTION_CANCEL);

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
//                        break;
                    case MotionEvent.ACTION_CANCEL:
                        EditText topLeftEdit = (EditText) topLeft.findViewById(R.id.quantity);
                        topLeftEdit.setTag("magic!");   // set tag to non-null to indicate programmatic change

                        Exercise topLeftExer = (Exercise) topLeft.getTag();
                        Exercise currExer = (Exercise) convertViewCpy.getTag();
                        String currVal = ((EditText) convertViewCpy.findViewById(R.id.quantity)).getText().toString();
                        currExer.configure(topLeft, currExer, Integer.parseInt(currVal));
                        topLeftExer.configure(convertViewCpy, currExer, Integer.parseInt(currVal));

                        topLeft.setTag(currExer);
                        convertViewCpy.setTag(topLeftExer);
                        dataCpy.set(positionCpy, topLeftExer);

                        topLeft.invalidate();
                        convertViewCpy.invalidate();
                        topLeftEdit.setTag(null);
                        break;
                }
                return true;
            }
        });
        return convertView;
    }
}
