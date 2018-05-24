package action;

import com.opensymphony.xwork2.ActionSupport;

public class chat extends ActionSupport {
    String toname;
    String fromname;
    String picture;

    public void setToname(String toname) {
        this.toname = toname;
    }

    public String getToname() {
        return toname;
    }

    public void setFromname(String fromname) {
        this.fromname = fromname;
    }

    public String getFromname() {
        return fromname;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
