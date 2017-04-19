package edu.shu.styluo.collegeentranceexamination.customview;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * 自定义正方形ImageView，用于CaptionedSquareImageView
 * Created by 29043 on 2017/4/19.
 */

public class SquareImageView extends AppCompatImageView {
    private boolean mSquare = true;

    public SquareImageView(Context context){
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    /**
     * 控制是否显示为Square形状ImageView
     * @param square 是否为Square
     */
    public void setSquare(boolean square){
        if(mSquare != square){
            mSquare = square;
            requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(mSquare) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
        }
    }
}

