package edu.shu.styluo.collegeentranceexamination.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.utils.SpUtils;
import edu.shu.styluo.collegeentranceexamination.utils.StatusBarLightModeUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import static com.squareup.picasso.MemoryPolicy.NO_CACHE;
import static com.squareup.picasso.MemoryPolicy.NO_STORE;

/**
 *启动页
 * @aythor styluo
 * @Time 2017.4.17
 */
public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.image_entry)
    ImageView mImageViewEntry; //启动页背景

    private static final  int ANIM_TIME = 2000; //启动页背景动画持续时间

    private static final float SCALE_END = 1.15F; //放大到1.15倍

    private static final int[] mImages = {R.drawable.bg_splash_first,
            R.drawable.bg_splash_second};  //启动页随机一个背景


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        StatusBarLightModeUtils.setStatusBarLightMode(this);

        ButterKnife.bind(this);

        checkIsFirstStart();
    }

    /**
     * 检查是否是第一次登录
     * @return
     */
    private void checkIsFirstStart(){
        Random random = new Random(SystemClock.elapsedRealtime());

        Picasso.with(this)
                .load(mImages[random.nextInt(mImages.length)])
                .memoryPolicy(NO_CACHE, NO_STORE)
                .into(mImageViewEntry);

        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        startAnim();
                    }
                });
    }

    /**
     * 开启动画
     * @return
     */
    private void startAnim(){
        boolean isFirstStart = SpUtils.loadIsFirst(this, SpUtils.FIRST_OPEN, true);

        if(isFirstStart){
            SpUtils.saveIsFirst(this, SpUtils.FIRST_OPEN, false);
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);

            finish();
            return;
        }else{
            ObjectAnimator animatorX = ObjectAnimator.ofFloat(mImageViewEntry, "scaleX", 1f, SCALE_END);
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(mImageViewEntry, "scaleY", 1f, SCALE_END);

            AnimatorSet set = new AnimatorSet();
            set.setDuration(ANIM_TIME).play(animatorX).with(animatorY);
            set.start();

            set.addListener(new AnimatorListenerAdapter(){
                @Override
                public void onAnimationEnd(Animator animation){

                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    SplashActivity.this.finish();
                }
            });
        }

    }

    /**
     * 屏蔽物理返回按钮
     * @param keyCode 对应KeyEvent
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}