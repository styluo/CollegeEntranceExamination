package edu.shu.styluo.collegeentranceexamination.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import edu.shu.styluo.collegeentranceexamination.data.local.dao.MajorInfo;

import edu.shu.styluo.collegeentranceexamination.greendao.MajorInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig majorInfoDaoConfig;

    private final MajorInfoDao majorInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        majorInfoDaoConfig = daoConfigMap.get(MajorInfoDao.class).clone();
        majorInfoDaoConfig.initIdentityScope(type);

        majorInfoDao = new MajorInfoDao(majorInfoDaoConfig, this);

        registerDao(MajorInfo.class, majorInfoDao);
    }
    
    public void clear() {
        majorInfoDaoConfig.clearIdentityScope();
    }

    public MajorInfoDao getMajorInfoDao() {
        return majorInfoDao;
    }

}
