package com.ensimag.dac.ejb.api;

import javax.ejb.Remote;

@Remote
public interface IMathStatelessRemote extends IMathRemote {

    long addAndSave(final long p_A, final long p_B);

}
