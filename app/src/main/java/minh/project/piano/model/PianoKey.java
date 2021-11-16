package minh.project.piano.model;

import android.graphics.RectF;

public class PianoKey {
    private int sound;
    private RectF rect;
    private boolean isDown;

    public PianoKey(int sound, RectF rect, boolean isDown) {
        this.sound = sound;
        this.rect = rect;
        this.isDown = isDown;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public RectF getRect() {
        return rect;
    }

    public void setRect(RectF rect) {
        this.rect = rect;
    }

    public boolean isDown() {
        return isDown;
    }

    public void setDown(boolean down) {
        isDown = down;
    }
}
