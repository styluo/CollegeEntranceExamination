package edu.shu.styluo.collegeentranceexamination.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import edu.shu.styluo.collegeentranceexamination.constant.FunType;
import edu.shu.styluo.collegeentranceexamination.customview.CaptionedSquareLayout;

/**
 * Created by 29043 on 2017/4/19.
 */

public class HomeGridViewAdapter extends BaseAdapter {
    private final FunType[] mFunTypes = FunType.values();
    private final Context mContext;

    public HomeGridViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mFunTypes.length;
    }

    @Override
    public FunType getItem(int position) {
        return mFunTypes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CaptionedSquareLayout captionedSquareLayout;
        if (convertView == null) {
            captionedSquareLayout = new CaptionedSquareLayout(mContext);
        } else {
            captionedSquareLayout = (CaptionedSquareLayout) convertView;
        }

        final FunType funType = mFunTypes[position];
        captionedSquareLayout.setImageResource(funType.getFunImageId());
        captionedSquareLayout.getTextView().setText(funType.getFunNameId());

        return captionedSquareLayout;
    }
}
