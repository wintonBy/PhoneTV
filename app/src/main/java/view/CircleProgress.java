package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;

/**
 * Created by Administrator on 2016/1/22.
 */
public class CircleProgress extends View {

    private Paint mPaint;

    private final static int Arc_Num = 10;

    private int lineColor = Color.RED;

    private int lineColor2 = Color.GRAY;

    boolean startAnim = true;

    private long mStartTime;
    private long mPlayTime;
    private long mDuration = 3600;

    private RectF ovel;


    public CircleProgress(Context context){
        super(context);
        init();
    }

    public CircleProgress(Context context,AttributeSet attrs){
        super(context, attrs);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(lineColor);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        ovel = new RectF(0+5,0+5,getRight()-getLeft()-5,getBottom()-getTop()-5);
        for(int i=0;i<10;i++){
            canvas.drawArc(ovel,(-87+getFactor()+i*36),30,false,mPaint);
        }
        canvas.restore();
        if(startAnim){
            postInvalidate();
        }

    }

    private float getFactor() {
        if (startAnim) {
            mPlayTime = AnimationUtils.currentAnimationTimeMillis() - mStartTime;
        }
        float factor = mPlayTime / (float) mDuration;
        return factor % 1f *36*10;
    }
    public void startAnim(){
        mPlayTime = mPlayTime % mDuration;
        mStartTime = AnimationUtils.currentAnimationTimeMillis() - mPlayTime;
        startAnim = true;
        postInvalidate();
    }

    public void stopAnim(){
        startAnim = false;
    }

}
