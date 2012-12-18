package com.ensimag.dac.ejb.api;

import javax.ejb.Remote;

@Remote
public interface IMathRemote {

    long add(long p_A, long p_B);

    long multiply(long p_A, long p_B);

}
