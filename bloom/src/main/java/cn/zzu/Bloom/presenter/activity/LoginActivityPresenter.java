package cn.zzu.Bloom.presenter.activity;

import com.google.gson.Gson;
import cn.zzu.Bloom.MyApplication;
import cn.zzu.Bloom.model.dao.bean.UserBean;
import cn.zzu.Bloom.model.net.bean.ResponseInfo;
import cn.zzu.Bloom.model.net.bean.User;
import cn.zzu.Bloom.presenter.BasePresenter;
import cn.zzu.Bloom.ui.IView;
import cn.zzu.Bloom.utils.PromptManager;
import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;

/**
 * Created by itheima.
 */
public class LoginActivityPresenter extends BasePresenter {
    private IView iView;

    public LoginActivityPresenter(IView iView) {
        this.iView = iView;
    }

    /**
     * 用户登录
     */
    public void login(HashMap<String,String> params){
        // 需要对params做判断
        if(params==null&&params.size()<2)
        {
            failed("参数异常");
            return;
        }
        Call<ResponseInfo> login = responseInfoAPI.login(params);
        login.enqueue(new CallbackAdapter());

    }

    @Override
    protected void failed(String message) {
        PromptManager.closeProgressDialog();
    }

    @Override
    protected void parserData(String data) {
        Gson gson=new Gson();
        User user = gson.fromJson(data, User.class);
        MyApplication.USERID=user.getId();

        // 该用户已经登陆。所有信息需要存储到服务
        // 同样一个用户，三个渠道都有可能，（你的手机应用可能别的用户登录），在本地会存储很多用户信息
        // 数据库中多条记录，只能有一个是被标记为登陆，如果用户点击注销，会一个都没有
        // 必须使用事物

        // Ormlite :AndroidDatabaseConnection
        // 流程：  设置还原点   开启事务       提交        回滚
        // 还原点设置：开始前设置还原点，中间过程也需要设置还原点，但跟业务相关，在手机端遇到的情况非常少

        AndroidDatabaseConnection connection=null;
        Savepoint start=null;
        UserBean userBean=null;
        try {
            connection=new AndroidDatabaseConnection(helper.getWritableDatabase(),true);
            Dao<UserBean, Integer> dao=helper.getDao(UserBean.class);
            // 2、设置还原点
            // 开启事务， 同时设置还原点
            start = connection.setSavePoint("start");

            // 1、dao默认情况下每执行一次操作都是自动提交，所以我们要关闭自动提交机制
            dao.setAutoCommit(connection,false);
            // 注意：事物开启必须在所有操作之前！！！！！！！！！！

            // 3、将数据库所有的用户信息中登陆状态全部设置为未登录
            List<UserBean> userBeanList = dao.queryForAll();
            for (UserBean item:userBeanList) {
                item.setLogin(false);
                dao.update(item);
            }
            // 4、查询数据库中是否有当前已经登陆的信息，如果有改成已经登陆
             userBean=dao.queryForId(user.getId());
            if(userBean!=null)
            {
                userBean.setLogin(true);
                dao.update(userBean);
            }
            // 5、如果没有，新增记录，登陆状态为已经登陆
            else{
                userBean=new UserBean();
                userBean.set_id(user.getId());
                userBean.setPhone(user.getPhone());
                userBean.setName(user.getName());
                userBean.setLogin(true);
                dao.create(userBean);
            }
            // 6、提交事务
            connection.commit(start);
        } catch (SQLException e) {
            e.printStackTrace();
            // 7、回滚事务
            if(connection!=null)
            {
                try {
                    connection.rollback(start);
                    userBean = null;
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        // 修改界面：关闭滚动条，界面跳转到用户信息展示界面
        iView.success(userBean);
    }
}
