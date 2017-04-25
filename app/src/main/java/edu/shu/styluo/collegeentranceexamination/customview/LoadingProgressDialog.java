package edu.shu.styluo.collegeentranceexamination.customview;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import edu.shu.styluo.collegeentranceexamination.R;

/**
 * 加载ProgressDialog
 * author: styluo
 * date: 2017/4/25 23:23
 * e-mail: shu_jiahuili@foxmail.com
 */

public class LoadingProgressDialog extends ProgressDialog {
    public LoadingProgressDialog(Context context)
    {
        super(context, R.style.LoadingProgressDialog);
    }

    public LoadingProgressDialog(Context context, int theme)
    {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        init(getContext());
    }

    private void init(Context context)
    {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCanceledOnTouchOutside(false);

        setContentView(R.layout.progress_dialog_loading);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    @Override
    public void show()
    {
        super.show();
    }
}
