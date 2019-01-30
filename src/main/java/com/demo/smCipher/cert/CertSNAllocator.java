package com.demo.smCipher.cert;

import java.math.BigInteger;

public interface CertSNAllocator {
    BigInteger incrementAndGet() throws Exception;
}
