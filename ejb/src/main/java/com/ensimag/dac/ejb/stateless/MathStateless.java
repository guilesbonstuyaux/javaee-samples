package com.ensimag.dac.ejb.stateless;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.LoggerFactory;

import com.ensimag.dac.ejb.api.IMathStatelessRemote;
import com.ensimag.dac.ejb.bean.CalculationsBean;

@Stateless(mappedName = "com.ensimag.ejb.stateless.MathStateless@Remote", description = "Maths stateless",
        name = "MathStateless")
public class MathStateless implements IMathStatelessRemote {

    private long previousResult = 0;

    @PersistenceContext(name="entity_DAC")
    private EntityManager m_EntityManager = null;

    @Resource
    private SessionContext sessionContext;

    public long add(final long p_A, final long p_B) {
        long result = p_A + p_B + this.previousResult;
        this.previousResult = result;

        LoggerFactory.getLogger(this.getClass())
                  .info("Connected user in stateless is {}.", sessionContext.getCallerPrincipal().getName());

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
