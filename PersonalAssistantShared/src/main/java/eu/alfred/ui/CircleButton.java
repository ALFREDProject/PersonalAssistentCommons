package eu.alfred.ui;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;

import eu.alfred.personalassistant.sharedlibrary.R;

public class CircleButton extends ImageView {

    private static final int PRESSED_COLOR_LIGHTUP = 255 / 25;
    private static final int PRESSED_RING_ALPHA = 0;
    private static final int DEFAULT_PRESSED_RING_WIDTH_DIP = 4;
    private static final int ANIMATION_TIME_ID = 100;

    public int centerY;
    public int centerX;
    private int outerRadius;
    private int pressedRingRadius;
    private Boolean isRed;

    private Paint circlePaint;
    private Paint focusPaint;

    private float animationProgress;

    private int pressedRingWidth;
    private int defaultColor;
    private int pressedColor;
    private ObjectAnimator pressedAnimator;

    private boolean isToggleable;
    private boolean isPressed;
    private boolean hasAnimationEnded;
    private boolean isAnimationRunning;

    private boolean isActive;
    private boolean isAppButton = false;

    private AttributeSet attrs;

    private TransitionDrawable transition;

    private ValueAnimator colorAnimation;

    public CircleButton(Context context) {
        super(context);
        this.attrs = null;
        init(context);
    }

    public CircleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attrs = attrs;
        init(context);
    }

    public CircleButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.attrs = attrs;
        init(context);
    }


    public void ButtonGotPressed(){
        if (getIsToggleable()) {
            if (!isActive()){
                setIsActive(true);
                setPressed(true);
                MakeRed();
                showPressedRing();


                //colorAnimation.start();
            }
            else{
                setIsActive(false);
                setPressed(false);
                MakeBlue();
                hidePressedRing();
                //colorAnimation.reverse();
            }
        }
        else{
            setIsActive(true);
            showPressedRing();
            //colorAnimation.start();
        }
    }

    public void MakeRed(){
        if (!isRed) {
            isRed = true;
            colorAnimation.start();
        }
    }

    public void MakeBlue(){
        if (isRed){
            colorAnimation.reverse();
            isRed = false;
        }
    }

    public void ButtonGotReleased(){
        if (!getIsToggleable())
        {
            setIsActive(false);
            hidePressedRing();
            //colorAnimation.reverse();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawCircle(centerX, centerY, pressedRingRadius + animationProgress, focusPaint);
        //canvas.drawCircle(centerX, centerY, pressedRingRadius + animationProgress, focusPaint);
        if(!isAppButton) {
            canvas.drawCircle(centerX, centerY, outerRadius -
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65,
                            getResources().getDisplayMetrics()), circlePaint);
        } else {
            canvas.drawCircle(centerX, centerY, outerRadius, circlePaint);
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(!isAppButton) {
            centerX = w / 2;
            centerY = h / 2;

            outerRadius = Math.min(w, h) / 2;
            //pressedRingRadius = outerRadius - pressedRingWidth - pressedRingWidth / 2;
            pressedRingRadius = outerRadius -
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65,
                            getResources().getDisplayMetrics()) -
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65,
                            getResources().getDisplayMetrics()) / 2;
        } else {
            Log.i("Posi",centerX+" "+centerY);
            outerRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40,
                    getResources().getDisplayMetrics());
            centerX = w - outerRadius;
            centerY = h - outerRadius;


            //pressedRingRadius = outerRadius - pressedRingWidth - pressedRingWidth / 2;
            pressedRingRadius = outerRadius -
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35,
                            getResources().getDisplayMetrics()) -
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35,
                            getResources().getDisplayMetrics()) / 2;
        }
    }

    public float getAnimationProgress() {
        return animationProgress;
    }

    public void setAnimationProgress(float animationProgress) {
        this.animationProgress = animationProgress;
        this.invalidate();
    }

    //TODO change that
    public boolean getIsToggleable(){
        return true;
    }

    public void setIsToggleable(boolean isToggleable){
        this.isToggleable = isToggleable;
        this.invalidate();
    }

    public void setColor(String color, Context context) {
        switch (color) {
            case("blue"):
            case("bleu"):
            case("blauw"):
                setColor(R.color.AlfredPastelBlue);
                break;
            case("green"):
            case("groen"):
            case("vert"):
                setColor(android.R.color.holo_green_dark);
                break;
            case("grey"):
            case("gris"):
            case("grijs"):
                setColor(android.R.color.darker_gray);
                break;
            case("orange"):
            case("oranje"):
                setColor(android.R.color.holo_orange_dark);
                break;
            case("black"):
            case("noir"):
            case("zwart"):
                setColor(android.R.color.black);
                break;
            case("purple"):
            case("violet"):
            case("paars"):
                setColor(android.R.color.holo_purple);
                break;
        }
        createColorAnimation(context);
    }

    public void setColor(int color) {

        defaultColor = color;
        pressedColor = getHighlightColor(color, PRESSED_COLOR_LIGHTUP);

        circlePaint.setColor(defaultColor);
        focusPaint.setColor(defaultColor);
        focusPaint.setAlpha(PRESSED_RING_ALPHA);

        this.invalidate();
    }

    public void hidePressedRing() {
        pressedAnimator.setFloatValues(pressedRingWidth, 0f);
        pressedAnimator.start();
    }

    public void showPressedRing() {
        pressedAnimator.setFloatValues(animationProgress, pressedRingWidth);
        pressedAnimator.start();
    }

    public void showPressedRing(int ringWidth)
    {
        pressedRingWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, ringWidth, getResources().getDisplayMetrics());
        pressedAnimator.setFloatValues(animationProgress, pressedRingWidth);
        pressedAnimator.start();
    }

    protected void init(Context context) {
        this.setFocusable(true);
        this.setScaleType(ScaleType.CENTER_INSIDE);
        setClickable(true);

        setIsActive(false);
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Paint.Style.FILL);

        focusPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        focusPaint.setStyle(Paint.Style.STROKE);

        pressedRingWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_PRESSED_RING_WIDTH_DIP, getResources()
                .getDisplayMetrics());

        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleButton);
            pressedRingWidth = (int) a.getDimension(R.styleable.CircleButton_cb_pressedRingWidth, pressedRingWidth);
            isAppButton = (boolean) a.getBoolean(R.styleable.CircleButton_cb_isApp, isAppButton);
            a.recycle();
        }

        setColor(R.color.AlfredPastelBlue);
        createColorAnimation(context);

        isRed = false;

        focusPaint.setStrokeWidth(pressedRingWidth);
        pressedAnimator = ObjectAnimator.ofFloat(this, "animationProgress", 0f, 0f);
        pressedAnimator.setDuration(ANIMATION_TIME_ID);
    }

    public void createColorAnimation(Context context) {

        Integer colorTo = context.getResources().getColor(R.color.MicrophoneRed);
        Integer colorFrom = context.getResources().getColor(defaultColor);

        if (colorAnimation!=null) colorAnimation.removeAllUpdateListeners();
        colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                setColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.reverse();
    }

    private int getHighlightColor(int color, int amount) {
        return Color.argb(Math.min(255, Color.alpha(color)), Math.min(255, Color.red(color) + amount),
                Math.min(255, Color.green(color) + amount), Math.min(255, Color.blue(color) + amount));
    }


    public void resetRing(){
        pressedRingWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        hidePressedRing();
    }

    public boolean IsButtonRed(){
        return isRed;
    }

    public void setIsAppButton(boolean isAppButton) {
        this.isAppButton = isAppButton;
    }

    public void StartRecording(boolean isClicked){
        if (!isClicked)
            setPressed(true);

    }

    public void StopRecording(boolean isClicked) {
        if (!isClicked)
            setPressed(false);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}