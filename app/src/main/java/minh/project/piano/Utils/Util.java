package minh.project.piano.Utils;

import android.text.TextPaint;

import java.util.HashMap;

import minh.project.piano.R;

public class Util {

    public final static String[] notenames = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};

    private static HashMap<Integer, Integer> PianoKey;

    public static int maxTextSize(String text, int maxWidth) {
        TextPaint paint = new TextPaint();
        for (int textSize = 10;; ++textSize) {
            paint.setTextSize(textSize);
            if (paint.measureText(text) > maxWidth) return textSize - 1;
        }
    }

    public static HashMap<Integer,Integer> getPianoKeyMap(){
        if(PianoKey==null) {
            PianoKey = new HashMap<>();

            PianoKey.put(48, R.raw.c3);
            PianoKey.put(50, R.raw.c4);
            PianoKey.put(52, R.raw.d3);
            PianoKey.put(53, R.raw.d4);
            PianoKey.put(55, R.raw.e3);
            PianoKey.put(57, R.raw.e4);
            PianoKey.put(59, R.raw.f3);
            PianoKey.put(60, R.raw.f4);
            PianoKey.put(62, R.raw.a3);
            PianoKey.put(64, R.raw.a4);
            PianoKey.put(65, R.raw.b3);
            PianoKey.put(67, R.raw.db3);
            PianoKey.put(69, R.raw.db4);
            PianoKey.put(71, R.raw.eb3);

            PianoKey.put(49, R.raw.eb4);
            PianoKey.put(51, R.raw.gb3);
            PianoKey.put(54, R.raw.gb4);
            PianoKey.put(56, R.raw.ab3);
            PianoKey.put(58, R.raw.ab4);
            PianoKey.put(61, R.raw.bb3);
            PianoKey.put(63, R.raw.bb4);
            PianoKey.put(66, R.raw.f2);
            PianoKey.put(68, R.raw.e2);
            PianoKey.put(70, R.raw.g3);
        }

        return PianoKey;
    }

}
