package com.example.vincent.crunchtime;

import java.util.HashMap;
import java.util.HashSet;
/**
 * Created by Vincent on 2/5/2016.
 */
public class ExerciseData {
    /* Reps / min per 100 calories. */
    public static final HashMap<Integer, Integer> CALORIES;

    /* Names of all exercises. */
    public static final HashMap<Integer, String> NAMES;

    /* Set of exercises based on reps (as opposed to minutes). */
    public static final HashSet<Integer> REP_EXER;

    public static final int
            PUSHUPS = 0,
            SITUPS = 1,
            SQUATS = 2,
            LEG_LIFTS = 3,
            PLANKS = 4,
            JUMPING_JACKS = 5,
            PULLUPS = 6,
            CYCLING = 7,
            WALKING = 8,
            JOGGING = 9,
            SWIMMING = 10,
            STAIR_CLIMBING = 11,
            ENERGY = 12;

    static {
        NAMES = new HashMap<Integer, String>();
        NAMES.put(PUSHUPS, "Pushups");
        NAMES.put(SITUPS, "Situps");
        NAMES.put(SQUATS, "Squats");
        NAMES.put(LEG_LIFTS, "Leg Lifts");
        NAMES.put(PLANKS, "Planks");
        NAMES.put(JUMPING_JACKS, "Jumping Jacks");
        NAMES.put(PULLUPS, "Pullups");
        NAMES.put(CYCLING, "Cycling");
        NAMES.put(WALKING, "Walking");
        NAMES.put(JOGGING, "Jogging");
        NAMES.put(SWIMMING, "Swimming");
        NAMES.put(STAIR_CLIMBING, "Stair Climbing");
        NAMES.put(ENERGY, "");

        CALORIES = new HashMap<Integer, Integer>();
        CALORIES.put(PUSHUPS, 350);
        CALORIES.put(SITUPS, 200);
        CALORIES.put(SQUATS, 225);
        CALORIES.put(LEG_LIFTS, 25);
        CALORIES.put(PLANKS, 25);
        CALORIES.put(JUMPING_JACKS, 10);
        CALORIES.put(PULLUPS, 100);
        CALORIES.put(CYCLING, 12);
        CALORIES.put(WALKING, 20);
        CALORIES.put(JOGGING, 12);
        CALORIES.put(SWIMMING, 13);
        CALORIES.put(STAIR_CLIMBING, 15);
        CALORIES.put(ENERGY, 100);

        REP_EXER = new HashSet<Integer>();
        REP_EXER.add(PUSHUPS);
        REP_EXER.add(SITUPS);
        REP_EXER.add(SQUATS);
        REP_EXER.add(PULLUPS);
    }
}
