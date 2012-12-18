package com.ensimag.dac.message;

import java.io.Serializable;

public class JMSMessage implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 59804226980911037L;

    public String firstName = null;
    public String lastName = null;

    public JMSMessage(final String firstName, final String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
