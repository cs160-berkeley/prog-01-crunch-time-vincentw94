package com.example.vincent.crunchtime;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Vincent on 2/5/2016.
 */
public class Exercise {
    private static final int ICON_WIDTH = 100, ICON_HEIGHT = 120;

    private int amtPer100;  // reps/min to burn 100 calories
    private int type;
    private boolean isReps;
    private Bitmap icon;

    public Exercise(Context context, int type) {
        this.type = type;
        this.amtPer100 = ExerciseData.CALORIES.get(type);
        this.isReps = ExerciseData.REP_EXER.contains(type);

        int drawableId;
        switch(type) {
            case ExerciseData.CYCLING : drawableId = R.drawable.cycling; break;
            case ExerciseData.JOGGING : drawableId = R.drawable.jogging; break;
            case ExerciseData.JUMPING_JACKS : drawableId = R.drawable.jumpingjacks; break;
            case ExerciseData.LEG_LIFTS : drawableId = R.drawable.leglift; break;
            case ExerciseData.PLANKS : drawableId = R.drawable.plank; break;
            case ExerciseData.PULLUPS : drawableId = R.drawable.pullup; break;
            case ExerciseData.PUSHUPS : drawableId = R.drawable.pushup; break;
            case ExerciseData.SITUPS : drawableId = R.drawable.situp; break;
            case ExerciseData.SQUATS : drawableId = R.drawable.squats; break;
            case ExerciseData.STAIR_CLIMBING : drawableId = R.drawable.stairs; break;
            case ExerciseData.SWIMMING : drawableId = R.drawable.swimming; break;
            case ExerciseData.WALKING : drawableId = R.drawable.walking; break;
            case ExerciseData.ENERGY : drawableId = R.drawable.calorie; break;
            default : throw new IllegalArgumentException("Unknown exercise type");
        }
        Object o = R.drawable.cycling;
        icon = BitmapFactory.decodeResource(context.getResources(), drawableId);
        icon = Bitmap.createScaledBitmap(icon, ICON_WIDTH, ICON_HEIGHT, true);
    }

    public String getName() {
        return ExerciseData.NAMES.get(type);
    }

    public void configure(View target, Exercise ref, int refAmt) {
        TextView title =(TextView) target.findViewById(R.id.title);
        ImageView icon = (ImageView) target.findViewById(R.id.icon);
        EditText amt = (EditText) target.findViewById(R.id.quantity);
        TextView units =(TextView) target.findViewById(R.id.units);

        title.setText(getName());
        icon.setImageBitmap(getIcon());
        if (ref != null)
            amt.setText("" + ref.toOther(this, refAmt));
        else
            amt.setText("" + refAmt);
        units.setText(units());
    }

    public Bitmap getIcon() {
        return icon;
    }

    public int toCalories(int amt) {
        return (int)(100.0 * amt / amtPer100);
    }

    public int toOther(Exercise other, int amt) {
        return amt * other.amtPer100 / amtPer100;
    }

    public String units() {
        if (type == ExerciseData.ENERGY)
            return "calories";
        return ExerciseData.REP_EXER.contains(type) ? "reps" : "min";
    }
}
