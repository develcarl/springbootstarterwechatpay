package org.develcarl.response.body;

/**
 * @author yichen
 * @description
 * @date 2018/7/26
 **/
public class ErrorResponseBody {

    public transient static final String ERRCODE = "errcode";

    public transient static final String ERRMSG = "errmsg";

    private String errcode;

    private String errmsg;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
