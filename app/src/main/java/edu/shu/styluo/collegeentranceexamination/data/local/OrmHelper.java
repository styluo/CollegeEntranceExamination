package edu.shu.styluo.collegeentranceexamination.data.local;

import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.async.AsyncOperationListener;
import org.greenrobot.greendao.async.AsyncSession;
import org.greenrobot.greendao.query.Query;

import edu.shu.styluo.collegeentranceexamination.data.local.entity.MajorInfo;
import edu.shu.styluo.collegeentranceexamination.greendao.DaoMaster;
import edu.shu.styluo.collegeentranceexamination.greendao.DaoSession;
import edu.shu.styluo.collegeentranceexamination.greendao.MajorInfoDao;

/**
 * Orm框架保持单例
 * author: styluo
 * date: 2017/4/24 11:57
 * e-mail: shu_jiahuili@foxmail.com
 */

public class OrmHelper{
    private static OrmHelper instance;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public static OrmHelper getInstance(){
        if(instance == null){
            return new OrmHelper();
        }
        return instance;
    }

    public DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(new GreenDaoContext(), "college_db.sqlite", null);
            SQLiteDatabase db = helper.getWritableDatabase();
            daoMaster = new DaoMaster(db);
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoSession == null) {
            daoSession = getDaoMaster().newSession();
        }
        return daoSession;
    }

    /**
     * 使用样例，操作可放在Presenter中
     * 分页查询测试，测试数据库可用，也可以使用JUnit来进行测试
     * 如果批量查询可以使用AsyncSession进行异步查询
     * Query<MajorInfo> query = majorInfoDao.queryBuilder().orderDesc(MajorInfoDao.Properties.Id)
     *         .offset(offset).limit(limit).build();这是同步查询方法,数据量为0-10可以考虑使用
     * @param offset
     * @param limit
     * @return
     */
    public void queryMajorInfo(int offset, int limit){
        MajorInfoDao majorInfoDao = getDaoSession().getMajorInfoDao();

        Query<MajorInfo> query = majorInfoDao.queryBuilder().orderDesc(MajorInfoDao.Properties.Id)
                .offset(offset).limit(limit).build();

        AsyncSession asyncSession = getDaoSession().startAsyncSession();
        asyncSession.setListenerMainThread(new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                //UI操作
                //可通过presenter传入数据，修改view的UI
                //List<MajorInfo> majorInfoList = (List<MajorInfo>) operation.getResult();
            }
        });

        asyncSession.queryList(query);
    }
}
