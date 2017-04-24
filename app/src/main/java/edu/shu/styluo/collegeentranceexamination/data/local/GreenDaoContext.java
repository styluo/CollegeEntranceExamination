package edu.shu.styluo.collegeentranceexamination.data.local;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import edu.shu.styluo.collegeentranceexamination.AppApplication;

/**
 * 自定义GreenDAO数据库文件存储路径,将数据库文件复制到文件存储中
 * author: styluo
 * date: 2017/4/24 10:46
 * e-mail: shu_jiahuili@foxmail.com
 */

public class GreenDaoContext extends ContextWrapper {
    private Context mContext;
    private static String DB_PATH = "/data/data/edu.shu.styluo.collegeentranceexamination/databases/";

    public GreenDaoContext() {
        super(AppApplication.getInstance().getApplicationContext());
        this.mContext = AppApplication.getInstance().getApplicationContext();
    }

    /**
     * 获得数据库路径，如果不存在，则创建对象
     * 如果数据库超过1M，需要使用数据库分割工具，然后在使用大文件数据库拷贝
     * @param dbName
     */
    @Override
    public File getDatabasePath(String dbName) {
        try{
            //判断目录是否存在，不存在则创建
            File dir = new File(DB_PATH);
            if(!dir.exists()){
                dir.mkdirs();
            }

            //判断文件是否存在，不存在则从assets下复制
            File dbf = new File(DB_PATH + dbName);
            if(dbf.exists()){
                return dbf;
            }else{
                copyDataBase(dbName);
                return new File(DB_PATH + dbName);
            }
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  复制数据库文件到默认位置
     * @throws IOException
     */
    private void copyDataBase(String dbName) throws IOException {
        //Open your local db as the input stream
        InputStream myInput = mContext.getAssets().open(dbName);
        // Path to the just created empty db
        String outFileName = DB_PATH + dbName;
        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    /**
     * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
     *
     * @param name
     * @param mode
     * @param factory
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
                                               SQLiteDatabase.CursorFactory factory) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }

    /**
     * Android 4.0会调用此方法获取数据库。
     *
     * @param name
     * @param mode
     * @param factory
     * @param errorHandler
     * @see android.content.ContextWrapper#openOrCreateDatabase(java.lang.String, int,
     * android.database.sqlite.SQLiteDatabase.CursorFactory,
     * android.database.DatabaseErrorHandler)
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory,
                                               DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);

        return result;
    }

}
