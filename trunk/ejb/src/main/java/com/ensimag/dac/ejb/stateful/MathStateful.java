package com.ensimag.dac.ejb.stateful;

import javax.ejb.Stateful;

import com.ensimag.dac.ejb.api.IMathRemote;

@Stateful(mappedName = "com.ensimag.ejb.stateless.MathStateful@Remote", description = "Maths stateful", name = "MathStateful")
public class MathStateful implements IMathRemote {

    private long previousResult = 0;

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

}
