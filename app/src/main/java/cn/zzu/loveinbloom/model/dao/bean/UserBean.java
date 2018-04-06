package cn.zzu.loveinbloom.model.dao.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by yangg on 2018/3/19.
 *  user 用户表
 */

@DatabaseTable(tableName = "t_user")
public class UserBean {
    @DatabaseField(id = true)
    public int _id;       //用户的id
    @DatabaseField()
    public String name;   //名字
    @DatabaseField()
    public float balance; //余额
    @DatabaseField()
    public int discount;//数量
    @DatabaseField()
    public int integral;//积分
    @DatabaseField()
    public String phone;//电话
    @DatabaseField
    public boolean login;//电话



    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public UserBean() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

}
