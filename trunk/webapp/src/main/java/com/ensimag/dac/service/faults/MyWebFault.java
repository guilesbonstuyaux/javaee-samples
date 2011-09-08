package com.ensimag.dac.service.faults;

import javax.xml.ws.WebFault;

/**
 * Created by IntelliJ IDEA.
 * User: guillaume
 * Date: 11/04/11
 * Time: 16:24
 * To change this template use File | Settings | File Templates.
 */
@WebFault(name = "MyFault")
public class MyWebFault extends Exception {
    public MyWebFault(String faultString) {
        super(faultString);
    }
}
