package com.ensimag.dac.service.api;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.ensimag.dac.service.faults.MyWebFault;

@WebService(name = "HelloWS", targetNamespace = "http://dac.ensimag.com")
public interface IHelloWS {

    /**
     * Just say Hello !
     *
     * @param p_Name who's asking for greetings ?
     * @return greetings !
     */
    String sayHello(@WebParam(name = "Name") String p_Name);

    /**
     * Just say Hello !
     *
     * @param p_Name who's asking for greetings ?
     * @return greetings !
     */
    String sayHelloWithFault(@WebParam(name = "Name") String p_Name) throws MyWebFault;
}
