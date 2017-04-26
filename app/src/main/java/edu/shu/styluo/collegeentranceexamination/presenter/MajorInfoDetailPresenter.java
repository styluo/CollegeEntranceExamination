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
 * author: styluo
 * date: 2017/4/26 23:32
 * e-mail: shu_jiahuili@foxmail.com
 */

public class MajorInfoDetailPresenter implements MajorInfoDetailContract.presenter{
    private MajorInfoDetailContract.view mMajorInfoDetailView;

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
}
