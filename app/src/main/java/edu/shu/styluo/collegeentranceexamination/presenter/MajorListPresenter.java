package edu.shu.styluo.collegeentranceexamination.presenter;

import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.async.AsyncOperationListener;
import org.greenrobot.greendao.async.AsyncSession;
import org.greenrobot.greendao.query.Query;

import java.util.List;

import edu.shu.styluo.collegeentranceexamination.data.local.OrmHelper;
import edu.shu.styluo.collegeentranceexamination.data.local.entity.MajorInfo;
import edu.shu.styluo.collegeentranceexamination.greendao.MajorInfoDao;

/**
 * 分离专业列表中业务和视图层次，因为部分activity逻辑较为简单，
 * 时间关系未分离，代码重构可分离
 * author: styluo
 * date: 2017/4/24 14:26
 * e-mail: shu_jiahuili@foxmail.com
 */

public class MajorListPresenter implements MajorListContract.presenter{
    MajorListContract.view mMajorListView;

    private int INIT_OFFSET = 0; // 初始化列表加载开始位置
    private int INIT_LIMIT = 10; // 初始化列表加载个数

    /**
     * presenter保持对view的引用，耦合度是有的，可以通过Dagger2注入形式解除耦合，时间关系直接一点
     * @param majorListView
     */
    public MajorListPresenter(MajorListContract.view majorListView){
        this.mMajorListView = majorListView;
    }

    @Override
    public void start() {

    }

    /**
     * 默认开始加载10条数据
     */
    @Override
    public void initData() {
        MajorInfoDao majorInfoDao = OrmHelper.getInstance().getDaoSession().getMajorInfoDao();

        Query<MajorInfo> query = majorInfoDao.queryBuilder().orderDesc(MajorInfoDao.Properties.Id)
                .offset(INIT_OFFSET).limit(INIT_LIMIT).build();

        AsyncSession asyncSession =  OrmHelper.getInstance().getDaoSession().startAsyncSession();
        asyncSession.setListenerMainThread(new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                List<MajorInfo> majorInfoList = (List<MajorInfo>) operation.getResult();
                mMajorListView.initAdapter(majorInfoList);
            }
        });

        asyncSession.queryList(query);
    }

    /**
     * 刷新新的10条数据
     */
    @Override
    public void refreshData() {
        MajorInfoDao majorInfoDao = OrmHelper.getInstance().getDaoSession().getMajorInfoDao();

        Query<MajorInfo> query = majorInfoDao.queryBuilder().orderDesc(MajorInfoDao.Properties.Id)
                .offset(INIT_OFFSET).limit(INIT_LIMIT).build();

        AsyncSession asyncSession =  OrmHelper.getInstance().getDaoSession().startAsyncSession();
        asyncSession.setListenerMainThread(new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                List<MajorInfo> majorInfoList = (List<MajorInfo>) operation.getResult();
                mMajorListView.getRefershData(majorInfoList);
            }
        });

        asyncSession.queryList(query);
    }

    /**
     * 自动加载更多
     */
    @Override
    public void loadMore(int totalItemCount) {
        MajorInfoDao majorInfoDao = OrmHelper.getInstance().getDaoSession().getMajorInfoDao();

        Query<MajorInfo> query = majorInfoDao.queryBuilder().orderDesc(MajorInfoDao.Properties.Id)
                .offset(totalItemCount).limit(INIT_LIMIT).build();

        AsyncSession asyncSession =  OrmHelper.getInstance().getDaoSession().startAsyncSession();
        asyncSession.setListenerMainThread(new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                List<MajorInfo> majorInfoList = (List<MajorInfo>) operation.getResult();
                mMajorListView.getMoreData(majorInfoList);
            }
        });

        asyncSession.queryList(query);
    }
}
