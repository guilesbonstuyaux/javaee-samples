package com.ensimag.dac.ejb.stateless;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ensimag.dac.ejb.api.IMathStatelessRemote;
import com.ensimag.dac.ejb.bean.CalculationsBean;

@Stateless(mappedName = "com.evangelion.ejb.stateless.MathStateless@Remote", description = "Maths stateless", name = "MathStateless")
public class MathStateless implements IMathStatelessRemote {

    private long previousResult = 0;

    @PersistenceContext
    private EntityManager m_EntityManager = null;

    public long add(final long p_A, final long p_B) {
        long result = p_A + p_B + this.previousResult;
        this.previousResult = result;
        return result;
    }

    public long multiply(final long p_A, final long p_B) {
        long result = p_A * p_B * this.previousResult;
        this.previousResult = result;
        return result;
    }

    public long addAndSave(final long p_A, final long p_B) {
        long result = this.add(p_A, p_B);
        CalculationsBean beanToPersist = new CalculationsBean(p_A, p_B, result);
        this.m_EntityManager.persist(beanToPersist);
        return result;
    }

}
