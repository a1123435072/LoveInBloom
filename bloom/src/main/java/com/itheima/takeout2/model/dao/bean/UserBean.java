package com.itheima.takeout2.model.dao.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "t_user")
public class UserBean implements Serializable{
//    _id:主键，如果是主键，并且通过id=true，指定主键， 需要用户手动的指定该id
//            通过generatedId = true  设置为主键时，此时数据库帮我们维护该主键，实现自增长
    @DatabaseField(id = true)
    public int _id;
//    @ForeignCollectionField(eager = true)
//    private ForeignCollection<AddressBean> addressList;
    @DatabaseField()
    public String name;
    @DatabaseField()
    public float balance;
    @DatabaseField()
    public int discount;
    @DatabaseField()
    public int integral;
    @DatabaseField()
    public String phone;
    @DatabaseField
    public boolean login;



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
    //    public ForeignCollection<AddressBean> getAddressList() {
//        return addressList;
//    }
//
//    public void setAddressList(ForeignCollection<AddressBean> addressList) {
//        this.addressList = addressList;
//    }
}
