package net.incuventure.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by angulo on 11/23/2016.
 */
@Service("passwordGenerator")
public  class PasswordGenerator {

    private static Logger logger = LoggerFactory.getLogger(PasswordGenerator.class);

    private static SecureRandom random = new SecureRandom();

    public static String nextPassword() {
        logger.info("nextPassword");
        return new BigInteger(130, random).toString(8);
    }

    public static String nextPassword(int passwordLength){
        logger.info("nextPassword",passwordLength);
        return new BigInteger(130, random).toString(passwordLength);
    }
}
