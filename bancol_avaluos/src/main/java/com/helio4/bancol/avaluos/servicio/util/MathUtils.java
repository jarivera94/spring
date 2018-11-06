package com.helio4.bancol.avaluos.servicio.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MathUtils {
    public static BigDecimal getBigDecimal(Object value) {
        BigDecimal ret = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                ret = (BigDecimal) value;
            } else if (value instanceof String) {
                ret = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                ret = new BigDecimal(((Number) value).doubleValue());
            } else {
                return null;
            }
        }
        return ret;
    }
    
    public static boolean isEven(int number) {
        return (number & 1) == 0;
    }
}
