package edu.shu.styluo.collegeentranceexamination.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import edu.shu.styluo.collegeentranceexamination.data.local.entity.MajorInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MajorInfo".
*/
public class MajorInfoDao extends AbstractDao<MajorInfo, Void> {

    public static final String TABLENAME = "MajorInfo";

    /**
     * Properties of entity MajorInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", false, "ID");
        public final static Property Major_name = new Property(1, String.class, "major_name", false, "MAJOR_NAME");
        public final static Property Subject_code = new Property(2, String.class, "subject_code", false, "SUBJECT_CODE");
        public final static Property Degree_level = new Property(3, String.class, "degree_level", false, "DEGREE_LEVEL");
        public final static Property School_system = new Property(4, String.class, "school_system", false, "SCHOOL_SYSTEM");
        public final static Property Main_course = new Property(5, String.class, "main_course", false, "MAIN_COURSE");
        public final static Property Major_goal = new Property(6, String.class, "major_goal", false, "MAJOR_GOAL");
        public final static Property Major_require = new Property(7, String.class, "major_require", false, "MAJOR_REQUIRE");
        public final static Property Major_ability = new Property(8, String.class, "major_ability", false, "MAJOR_ABILITY");
        public final static Property Major_pratice = new Property(9, String.class, "major_pratice", false, "MAJOR_PRATICE");
        public final static Property Major_recommend = new Property(10, String.class, "major_recommend", false, "MAJOR_RECOMMEND");
        public final static Property Major_salary = new Property(11, String.class, "major_salary", false, "MAJOR_SALARY");
        public final static Property Major_employ = new Property(12, String.class, "major_employ", false, "MAJOR_EMPLOY");
        public final static Property Major_develop = new Property(13, String.class, "major_develop", false, "MAJOR_DEVELOP");
        public final static Property Major_graduate = new Property(14, String.class, "major_graduate", false, "MAJOR_GRADUATE");
        public final static Property Major_abroad = new Property(15, String.class, "major_abroad", false, "MAJOR_ABROAD");
        public final static Property Major_intro = new Property(16, String.class, "major_intro", false, "MAJOR_INTRO");
    }


    public MajorInfoDao(DaoConfig config) {
        super(config);
    }
    
    public MajorInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MajorInfo entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String major_name = entity.getMajor_name();
        if (major_name != null) {
            stmt.bindString(2, major_name);
        }
 
        String subject_code = entity.getSubject_code();
        if (subject_code != null) {
            stmt.bindString(3, subject_code);
        }
 
        String degree_level = entity.getDegree_level();
        if (degree_level != null) {
            stmt.bindString(4, degree_level);
        }
 
        String school_system = entity.getSchool_system();
        if (school_system != null) {
            stmt.bindString(5, school_system);
        }
 
        String main_course = entity.getMain_course();
        if (main_course != null) {
            stmt.bindString(6, main_course);
        }
 
        String major_goal = entity.getMajor_goal();
        if (major_goal != null) {
            stmt.bindString(7, major_goal);
        }
 
        String major_require = entity.getMajor_require();
        if (major_require != null) {
            stmt.bindString(8, major_require);
        }
 
        String major_ability = entity.getMajor_ability();
        if (major_ability != null) {
            stmt.bindString(9, major_ability);
        }
 
        String major_pratice = entity.getMajor_pratice();
        if (major_pratice != null) {
            stmt.bindString(10, major_pratice);
        }
 
        String major_recommend = entity.getMajor_recommend();
        if (major_recommend != null) {
            stmt.bindString(11, major_recommend);
        }
 
        String major_salary = entity.getMajor_salary();
        if (major_salary != null) {
            stmt.bindString(12, major_salary);
        }
 
        String major_employ = entity.getMajor_employ();
        if (major_employ != null) {
            stmt.bindString(13, major_employ);
        }
 
        String major_develop = entity.getMajor_develop();
        if (major_develop != null) {
            stmt.bindString(14, major_develop);
        }
 
        String major_graduate = entity.getMajor_graduate();
        if (major_graduate != null) {
            stmt.bindString(15, major_graduate);
        }
 
        String major_abroad = entity.getMajor_abroad();
        if (major_abroad != null) {
            stmt.bindString(16, major_abroad);
        }
 
        String major_intro = entity.getMajor_intro();
        if (major_intro != null) {
            stmt.bindString(17, major_intro);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MajorInfo entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String major_name = entity.getMajor_name();
        if (major_name != null) {
            stmt.bindString(2, major_name);
        }
 
        String subject_code = entity.getSubject_code();
        if (subject_code != null) {
            stmt.bindString(3, subject_code);
        }
 
        String degree_level = entity.getDegree_level();
        if (degree_level != null) {
            stmt.bindString(4, degree_level);
        }
 
        String school_system = entity.getSchool_system();
        if (school_system != null) {
            stmt.bindString(5, school_system);
        }
 
        String main_course = entity.getMain_course();
        if (main_course != null) {
            stmt.bindString(6, main_course);
        }
 
        String major_goal = entity.getMajor_goal();
        if (major_goal != null) {
            stmt.bindString(7, major_goal);
        }
 
        String major_require = entity.getMajor_require();
        if (major_require != null) {
            stmt.bindString(8, major_require);
        }
 
        String major_ability = entity.getMajor_ability();
        if (major_ability != null) {
            stmt.bindString(9, major_ability);
        }
 
        String major_pratice = entity.getMajor_pratice();
        if (major_pratice != null) {
            stmt.bindString(10, major_pratice);
        }
 
        String major_recommend = entity.getMajor_recommend();
        if (major_recommend != null) {
            stmt.bindString(11, major_recommend);
        }
 
        String major_salary = entity.getMajor_salary();
        if (major_salary != null) {
            stmt.bindString(12, major_salary);
        }
 
        String major_employ = entity.getMajor_employ();
        if (major_employ != null) {
            stmt.bindString(13, major_employ);
        }
 
        String major_develop = entity.getMajor_develop();
        if (major_develop != null) {
            stmt.bindString(14, major_develop);
        }
 
        String major_graduate = entity.getMajor_graduate();
        if (major_graduate != null) {
            stmt.bindString(15, major_graduate);
        }
 
        String major_abroad = entity.getMajor_abroad();
        if (major_abroad != null) {
            stmt.bindString(16, major_abroad);
        }
 
        String major_intro = entity.getMajor_intro();
        if (major_intro != null) {
            stmt.bindString(17, major_intro);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public MajorInfo readEntity(Cursor cursor, int offset) {
        MajorInfo entity = new MajorInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // major_name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // subject_code
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // degree_level
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // school_system
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // main_course
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // major_goal
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // major_require
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // major_ability
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // major_pratice
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // major_recommend
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // major_salary
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // major_employ
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // major_develop
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // major_graduate
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // major_abroad
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16) // major_intro
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MajorInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setMajor_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSubject_code(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDegree_level(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSchool_system(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setMain_course(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setMajor_goal(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setMajor_require(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setMajor_ability(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setMajor_pratice(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setMajor_recommend(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setMajor_salary(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setMajor_employ(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setMajor_develop(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setMajor_graduate(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setMajor_abroad(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setMajor_intro(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(MajorInfo entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(MajorInfo entity) {
        return null;
    }

    @Override
    public boolean hasKey(MajorInfo entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
