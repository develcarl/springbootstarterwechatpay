package org.develcarl.response.body;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author yichen
 * @description
 * @date 2018/8/10
 **/
@JacksonXmlRootElement(namespace = "xml")
public class NotifyOrderResponseBody {

    @JacksonXmlCData
    private String return_code;

    @JacksonXmlCData
    private String return_msg;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
}
