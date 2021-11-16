package minh.project.piano.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import minh.project.piano.model.PianoKey;

public class PianoView extends View {

    private static final int NUMBER_KEYS = 14;
    private final ArrayList<PianoKey> whites;
    private final ArrayList<PianoKey> blacks;
    private final Paint wPen;
    private final Paint bPen;
    private final Paint downWPen;
    private final Paint downBPen;
    private int keyWidth, keyHeight;
    protected boolean[] pressed;

    public PianoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        whites = new ArrayList<>();
        blacks = new ArrayList<>();

        wPen = new Paint();
        wPen.setStyle(Paint.Style.FILL);
        wPen.setColor(Color.WHITE);

        bPen = new Paint();
        bPen.setStyle(Paint.Style.FILL);
        bPen.setColor(Color.BLACK);

        downWPen = new Paint();
        downWPen.setStyle(Paint.Style.FILL);
        downWPen.setColor(Color.GREEN);

        downBPen = new Paint();
        downBPen.setStyle(Paint.Style.FILL);
        downBPen.setColor(Color.RED);

        pressed = new boolean[300];
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        keyWidth = w/NUMBER_KEYS;
        keyHeight = h;

        int count = 15;
        for(int i=0;i<NUMBER_KEYS;i++){
            int left = i*keyWidth;
            int right = left + keyWidth;

            RectF whiteRect = new RectF(left,0,right,keyHeight);

            whites.add(new PianoKey(i+1,whiteRect,false));

            if(i!=0&&i!=3&&i!=7&&i!=10){
                RectF blackRect = new RectF((float) (i-1)*keyWidth + 0.75f*keyWidth,
                                            0,
                                            (float) i*keyWidth+0.25f*keyWidth,
                                            0.67f*keyHeight);

                blacks.add(new PianoKey(count,blackRect,false));
                count++;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (PianoKey k:whites) {
            canvas.drawRect(k.getRect(),k.isDown() ? downWPen : wPen);
        }

        for(int i=1;i<NUMBER_KEYS;i++){
            canvas.drawLine(i*keyWidth,0,i*keyWidth,keyHeight,bPen);
        }

        for (PianoKey k:blacks) {
            canvas.drawRect(k.getRect(),k.isDown() ? downBPen : bPen);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        int action = event.getAction();
        boolean isDownAction = action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE;

        for(int touchIndex=0; touchIndex<event.getPointerCount();touchIndex++){
            float X = event.getX(touchIndex);
            float Y = event.getY(touchIndex);

            for(PianoKey k:blacks){
                if(k.getRect().contains(X,Y)){
                    disableWhiteDown(k.getSound());
                    k.setDown(isDownAction);
                }
            }

            for(PianoKey k:whites){
                if(k.getRect().contains(X,Y)){
                    k.setDown(isDownAction);
                }
            }
        }

        invalidate();
        return true;
    }

    private void disableWhiteDown(int sound) {
        for(PianoKey k:whites){
            if(k.getSound()==sound-1||k.getSound()==sound+1){
                k.setDown(false);
            }
        }
    }
}
