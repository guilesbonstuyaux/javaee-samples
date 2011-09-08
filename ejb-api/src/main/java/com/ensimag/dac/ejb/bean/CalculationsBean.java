package com.ensimag.dac.ejb.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CalculationsBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2827290986889306938L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long firstPart;

    private long secondPart;

    private long result;

    public CalculationsBean(final long firstPart, final long secondPart,
                            final long result) {
        super();

        this.firstPart = firstPart;
        this.secondPart = secondPart;
        this.result = result;
    }

    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getFirstPart() {
        return this.firstPart;
    }

    public void setFirstPart(final long firstPart) {
        this.firstPart = firstPart;
    }

    public long getSecondPart() {
        return this.secondPart;
    }

    public void setSecondPart(final long secondPart) {
        this.secondPart = secondPart;
    }

    public long getResult() {
        return this.result;
    }

    public void setResult(final long result) {
        this.result = result;
    }

}
