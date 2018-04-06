package cn.zzu.loveinbloom.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import cn.zzu.loveinbloom.MyApplication;
import cn.zzu.loveinbloom.model.dao.bean.AddressBean;
import cn.zzu.loveinbloom.model.dao.bean.UserBean;

/**
 * Created by yangg on 2018/3/19.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {
    //数据库的名子
    private static final String DATA_BASE_NAME = "wuyue.db";
    //数据库的版本号 1
    private static final int DATA_BASE_VERSION = 1;

    public DBHelper(Context context){
        super(context,DATA_BASE_NAME,null,DATA_BASE_VERSION);
    }


    /**
     * 单例设计模式
     * 双重校验,提高效率
     * 如果再方法上枷锁,每次调用都需要排队
     * @return
     */
    private static DBHelper instance;
    public static DBHelper getInstance(){
        if (instance  == null){
            //考虑加锁
            synchronized (DBHelper.class){
                if (instance  == null){
                    instance  =  new DBHelper(MyApplication.getContext());
                    instance.getWritableDatabase();
                }
            }
        }
        return instance;
    }



    //创建数据库 的时候调用
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        //创建数据库表
        try{
            //使用  数据库 开源框架初始化数据库  一个用户表一个地址表
            TableUtils.createTable(connectionSource,UserBean.class);
            TableUtils.createTable(connectionSource,AddressBean.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //更新数据库的时候调用
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
