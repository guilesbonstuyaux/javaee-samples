package com.ensimag.dac.ejb.stateless;

import com.ensimag.dac.ejb.api.IMathStatelessRemote;
import com.ensimag.dac.ejb.bean.CalculationsBean;
import org.ow2.util.log.LogFactory;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "com.evangelion.ejb.stateless.MathStateless@Remote", description = "Maths stateless",
        name = "MathStateless")
public class MathStateless implements IMathStatelessRemote {

    private long previousResult = 0;

    @PersistenceContext
    private EntityManager m_EntityManager = null;

    @Resource
    private SessionContext sessionContext;

    public long add(final long p_A, final long p_B) {
        long result = p_A + p_B + this.previousResult;
        this.previousResult = result;

        LogFactory.getLog(this.getClass())
                  .info("Connected user in stateless is {0}.", sessionContext.getCallerPrincipal().getName());

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
