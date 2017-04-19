package edu.shu.styluo.collegeentranceexamination.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;

/**
 * 自定义Layout用于GridView中显示
 * Created by 29043 on 2017/4/19.
 */

public class CaptionedSquareLayout extends FrameLayout implements View.OnLayoutChangeListener{

    private Drawable mDrawable;
    private TextView mTextView;
    private SquareImageView mImageView;
    int mScrimColor;

    public CaptionedSquareLayout(Context context) {
        super(context);
        init(context);
    }

    public CaptionedSquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CaptionedSquareLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 外部获取SqureView
     * @return 返回SquareView
     */
    public SquareImageView getImageView() {
        return mImageView;
    }

    /**
     * 外部获取TextView
     * @return 返回TextView
     */
    public TextView getTextView() {
        return mTextView;
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (v.getVisibility() != VISIBLE) {
            return;
        }
        final int height = bottom - top;
        final int width = right - left;
        if (height == 0 || width == 0) {
            return;
        }
        updateBlur();
    }

    /**
     * picasso处理可能导致模糊化处理消失
     * @param drawableResourceId 资源索引
     */
    public void setImageResource(@DrawableRes int drawableResourceId) {
        mDrawable = getResources().getDrawable(drawableResourceId);
        mImageView.setImageResource(drawableResourceId);
        /*Picasso.with(getContext())
                .load(drawableResourceId)
                .memoryPolicy(NO_CACHE, NO_STORE)
                .into(mImageView);*/

        updateBlur();
    }

    /**
     * 模糊处理 API >= 17 才能进行模糊处理, 这里可能引发OOM
     * @return
     */
    @TargetApi(17)
    private void updateBlur() {
        if(Build.VERSION.SDK_INT >= 17){
            if (!(mDrawable instanceof BitmapDrawable)) {
                return;
            }
            final int textViewHeight = mTextView.getHeight();
            if (textViewHeight == 0) {
                return;
            }

            // Determine the size of the TextView compared to the height of the ImageView
            final float ratio = (float) textViewHeight / mImageView.getHeight();

            // Get the Bitmap
            final BitmapDrawable bitmapDrawable = (BitmapDrawable) mDrawable;
            final Bitmap originalBitmap = bitmapDrawable.getBitmap();

            // Calculate the height as a ratio of the Bitmap
            int height = (int) (ratio * originalBitmap.getHeight());

            // The y position is the number of pixels height represents from the bottom of the Bitmap
            final int y = originalBitmap.getHeight() - height;

            final Bitmap portionToBlur = Bitmap.createBitmap(originalBitmap, 0, y, originalBitmap.getWidth(), height);
            final Bitmap blurredBitmap = portionToBlur.copy(Bitmap.Config.ARGB_8888, true);

            // Use RenderScript to blur the pixels
            RenderScript rs = RenderScript.create(getContext());
            ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            Allocation tmpIn = Allocation.createFromBitmap(rs, portionToBlur);
            Allocation tmpOut = Allocation.createFromBitmap(rs, blurredBitmap);
            theIntrinsic.setRadius(25f);
            theIntrinsic.setInput(tmpIn);
            theIntrinsic.forEach(tmpOut);
            tmpOut.copyTo(blurredBitmap);
            new Canvas(blurredBitmap).drawColor(mScrimColor);

            // Create the new bitmap using the old plus the blurred portion and display it
            final Bitmap newBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
            final Canvas canvas = new Canvas(newBitmap);
            canvas.drawBitmap(blurredBitmap, 0, y, new Paint());

            mImageView.setImageBitmap(newBitmap);
        }else {
            //TODO nothing

        }
    }

    /**
     * 初始化控件
     * @param context 上下文
     * @return
     */
    private void init(Context context) {
        inflate(context, R.layout.layout_captioned, this);
        mTextView = ButterKnife.findById(this, R.id.tv_home_item);
        mImageView = ButterKnife.findById(this, R.id.si_home_item);
        mScrimColor = getResources().getColor(R.color.grid_item_scrim);

        mTextView.addOnLayoutChangeListener(this);
    }
}
