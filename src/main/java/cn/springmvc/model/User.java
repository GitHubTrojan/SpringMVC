package cn.springmvc.model;

/**
 * Created by Vincent on 2017/2/27.
 * Description Demo POJO for testing.
 */
public class User {
    private int uid;
    private String uname;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    @Override
    public String toString(){
        return "User [uid=" + uid + ", uname=" + uname + "]";
    }
}
