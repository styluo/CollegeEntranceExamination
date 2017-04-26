package edu.shu.styluo.collegeentranceexamination.presenter;

import android.support.design.widget.Snackbar;

import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.async.AsyncOperationListener;
import org.greenrobot.greendao.async.AsyncSession;
import org.greenrobot.greendao.query.Query;

import java.util.List;

import edu.shu.styluo.collegeentranceexamination.data.local.OrmHelper;
import edu.shu.styluo.collegeentranceexamination.data.local.entity.MajorInfo;
import edu.shu.styluo.collegeentranceexamination.data.remote.RetrofitFactory;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.CollegeByMajor;
import edu.shu.styluo.collegeentranceexamination.greendao.MajorInfoDao;
import edu.shu.styluo.collegeentranceexamination.utils.CorrectLocalDataUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: styluo
 * date: 2017/4/26 23:32
 * e-mail: shu_jiahuili@foxmail.com
 */

public class MajorInfoDetailPresenter implements MajorInfoDetailContract.presenter{
    private MajorInfoDetailContract.view mMajorInfoDetailView;
    private final String NETWORK_ERROR = "NetWorkError, Please check the network environment";

    public MajorInfoDetailPresenter(MajorInfoDetailContract.view majorInfoDetailView){
        this.mMajorInfoDetailView = majorInfoDetailView;
    }

    @Override
    public void start() {
    }

    @Override
    public void initData(String majorId) {
        //加载本地数据
        MajorInfoDao majorInfoDao = OrmHelper.getInstance().getDaoSession().getMajorInfoDao();

        Query<MajorInfo> query = majorInfoDao.queryBuilder().where(MajorInfoDao.Properties.Id.eq(majorId)).build();

        AsyncSession asyncSession =  OrmHelper.getInstance().getDaoSession().startAsyncSession();
        asyncSession.setListenerMainThread(new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                List<MajorInfo> majorInfoList = (List<MajorInfo>) operation.getResult();
                mMajorInfoDetailView.getLocalData(majorInfoList);
            }
        });

        asyncSession.queryList(query);

        //加载远程数据（开设高校）,无单独返回相关开设高校接口，如果需求可用现有接口并本地处理数据
        //使用前需要使用工具类处理一下本地Id CorrectLocalDataUtils.correctMajorId(majorId)
    }

    @Override
    public void initPopup(String majorId) {
        //网络请求Id需要修正
        majorId = CorrectLocalDataUtils.correctMajorId(majorId);
        RetrofitFactory.getInstance().getCollegeByMajor(majorId, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CollegeByMajor>() {
                    @Override
                    public void accept(CollegeByMajor collegeByMajor) throws Exception {
                        mMajorInfoDetailView.getPopupData(collegeByMajor.getRows());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Snackbar.make(mMajorInfoDetailView.getRootView(), NETWORK_ERROR, Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void loadMore(String majorId, int page) {
        //网络请求Id修正
        majorId = CorrectLocalDataUtils.correctMajorId(majorId);
        RetrofitFactory.getInstance().getCollegeByMajor(majorId, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CollegeByMajor>() {
                    @Override
                    public void accept(CollegeByMajor collegeByMajor) throws Exception {
                        mMajorInfoDetailView.getLoadMore(collegeByMajor.getRows());
                        mMajorInfoDetailView.setLoadingState(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Snackbar.make(mMajorInfoDetailView.getRootView(), NETWORK_ERROR, Snackbar.LENGTH_SHORT).show();
                    }
                });
    }
}
