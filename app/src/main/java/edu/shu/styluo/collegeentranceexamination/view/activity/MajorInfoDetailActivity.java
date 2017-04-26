package edu.shu.styluo.collegeentranceexamination.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.customview.BasePopupWindow;
import edu.shu.styluo.collegeentranceexamination.data.local.entity.MajorInfo;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.CollegeByMajor;
import edu.shu.styluo.collegeentranceexamination.presenter.MajorInfoDetailContract;
import edu.shu.styluo.collegeentranceexamination.presenter.MajorInfoDetailPresenter;
import edu.shu.styluo.collegeentranceexamination.utils.StatusBarLightModeUtils;
import edu.shu.styluo.collegeentranceexamination.view.adapter.MajorDetailPopupRecyclerAdapter;

/**
 * 专业详情界面
 * author: styluo
 * date: 2017/4/26 23:13
 * e-mail: shu_jiahuili@foxmail.com
 */

public class MajorInfoDetailActivity extends AppCompatActivity implements MajorInfoDetailContract.view{

    private String majorId;
    private MajorInfoDetailContract.presenter mMajorInfoDetailPresenter;

    private BasePopupWindow mPopupWindow;
    private RecyclerView mPopupRecyclerView;
    private MajorDetailPopupRecyclerAdapter mMajorDetailPopupRecyclerAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private List<CollegeByMajor.RowsBean> mCollegeList;
    private boolean mIsLoadMore = true;
    private boolean mIsLoading = false;
    private final int PAGE_ITEM = 5;

    @BindView(R.id.tv_majordetail_maincourse)
    TextView mTextViewMainCourse; //主要课程
    @BindView(R.id.tv_majordetail_maingoal)
    TextView mTextViewMainGoal; //专业培养目标
    @BindView(R.id.tv_majordetail_mainability)
    TextView mTextViewMainAbility; //专业培养能力
    @BindView(R.id.btn_majordetail)
    Button mButton;
    @BindView(R.id.sv_majordetail)
    ScrollView mScrollView; //根布局

    private final String NO_DATA = "暂无信息";
    private final String PROMOT_INFO = "无更多数据";

    //数据是否已经获取完成
    private boolean isLocal = false;
    private boolean isRemote = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_majorinfo_detail);

        ButterKnife.bind(this);
        StatusBarLightModeUtils.setStatusBarLightMode(this);

        majorId = getIntent().getStringExtra("majorId");

        mMajorInfoDetailPresenter = new MajorInfoDetailPresenter(this);

        //本地数据库中Id需处理后才能使用到网络请求
        mMajorInfoDetailPresenter.initData(majorId);

        //弹出显示开设大学信息
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //初始化加载变量
                mIsLoading = false;
                mIsLoadMore = true;

                mPopupWindow = new BasePopupWindow(MajorInfoDetailActivity.this);
                View view = LayoutInflater.from(MajorInfoDetailActivity.this).inflate(R.layout.popupwindow_majorinfo_college, null);
                mPopupWindow.setContentView(view);
                mPopupWindow.setWidth(getResources().getDimensionPixelSize(R.dimen.pw_major_detail_lw));
                mPopupWindow.setHeight(getResources().getDimensionPixelSize(R.dimen.pw_major_detail_lh));
                mPopupRecyclerView = ButterKnife.findById(view, R.id.rv_majordetail_college);
                mMajorInfoDetailPresenter.initPopup(majorId);
            }
        });
    }

    @Override
    public void getPopupData(List<CollegeByMajor.RowsBean> collegeList) {
        if(collegeList.size() == 0){
            Snackbar.make(mScrollView, NO_DATA, Snackbar.LENGTH_SHORT).show();
            return;
        }
        mCollegeList = collegeList;
        mMajorDetailPopupRecyclerAdapter = new MajorDetailPopupRecyclerAdapter(mCollegeList);
        mPopupRecyclerView.setAdapter(mMajorDetailPopupRecyclerAdapter);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mPopupRecyclerView.setLayoutManager(mLinearLayoutManager);
        mPopupWindow.showAtLocation(mScrollView, Gravity.CENTER, 0, 0);

        //上滑自动加载
        mPopupRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) mLinearLayoutManager).findLastVisibleItemPosition();
                int totalItemCount = mLinearLayoutManager.getItemCount();
                //lastVisibleItem >= totalItemCount - 2 表示剩下2个item自动加载
                if (lastVisibleItem >= totalItemCount - 2 && dy >= 0) {
                    if(mIsLoadMore && !mIsLoading){
                        //第一页就已经加载完全了，则直接set loadMore为false
                        if(totalItemCount < PAGE_ITEM){
                            mIsLoadMore = false;
                            return;
                        }
                        mIsLoading = true;
                        mMajorInfoDetailPresenter.loadMore(majorId, (totalItemCount / PAGE_ITEM) + 1);
                    }
                }
            }
        });
    }

    /**
     * 防止多次加载同一个数据，设置加载状态
     * @param isLoading
     */
    public void setLoadingState(boolean isLoading){
        mIsLoading = isLoading;
    }

    /**
     * 当获取的数据小于1页数据，就说明已经加载完成了,Ps:小于ITEM_PAGE的数据页要放入List中
     * @param collegeList
     */
    @Override
    public void getLoadMore(List<CollegeByMajor.RowsBean> collegeList) {
        if(collegeList.size() == 0){
            Snackbar.make(mScrollView, PROMOT_INFO, Snackbar.LENGTH_SHORT).show();
            mIsLoadMore = false;
            return;
        }

        for (CollegeByMajor.RowsBean rowsBean : collegeList) {
            mCollegeList.add(rowsBean);
        }
        mMajorDetailPopupRecyclerAdapter.notifyDataSetChanged();

        if(collegeList.size() < PAGE_ITEM){
            Snackbar.make(mScrollView, PROMOT_INFO, Snackbar.LENGTH_SHORT).show();
            mIsLoadMore = false;
            return;
        }
    }

    @Override
    public void setPresenter(MajorInfoDetailContract.presenter presenter) {
        this.mMajorInfoDetailPresenter = presenter;
    }

    /**
     * 初始化本地数据
     * @param majorInfoList
     */
    @Override
    public void getLocalData(List<MajorInfo> majorInfoList) {
        if(majorInfoList.size() == 0){
            return;
        }
        MajorInfo majorInfo = majorInfoList.get(0);
        String mainCourse = majorInfo.getMain_course();
        mTextViewMainCourse.setText( (mainCourse==null) ? NO_DATA : mainCourse);
        String majorGoal = majorInfo.getMajor_goal();
        mTextViewMainGoal.setText( (majorGoal==null) ? NO_DATA : majorGoal);
        String majorAbility = majorInfo.getMajor_ability();
        mTextViewMainAbility.setText( (majorAbility==null) ? NO_DATA : majorAbility);
        isLocal = true;
    }

    @Override
    public ScrollView getRootView() {
        return mScrollView;
    }
}
