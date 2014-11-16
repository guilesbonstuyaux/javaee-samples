/**
 *
 */
package com.ensimag.dac.service.impl;

import java.net.URL;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.ensimag.dac.service.api.IHelloWS;
import com.ensimag.dac.service.faults.MyWebFault;

/**
 * @author Guillaume Renault
 */
@WebService(endpointInterface = "com.ensimag.dac.service.api.IHelloWS", targetNamespace = "http://dac.ensimag.com")
public class HelloWS extends Service implements IHelloWS {

    protected HelloWS(URL wsdlDocumentLocation, QName serviceName) {
		super(wsdlDocumentLocation, serviceName);
	}

	@Override
    public String sayHello(String p_Name) {

        StringBuilder sb = new StringBuilder();
        sb.append("Hello ");
        sb.append(p_Name);
        sb.append(" !");

        return sb.toString();
    }

    @Override
    public String sayHelloWithFault(@WebParam(name = "Name") String p_Name) throws MyWebFault {
        throw new MyWebFault("Oh la belle faute");
    }

}
