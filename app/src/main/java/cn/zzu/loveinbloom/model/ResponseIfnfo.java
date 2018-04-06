package cn.zzu.loveinbloom.model;

/**
 * Created by yangg on 2018/3/19.
 * 这只一个 model 用来存储访问返回的数据 中的在值
 */

public class ResponseIfnfo {
    /**
     *  {  这是服务器返回的 json 字符串
     *   "code": "0", 如果服务器返回的是0 时候说明返回的数据成功了
     *   "data": "{……}"
     */

    public   String code;
    public String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
