package com.demo.bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author ylx
 * Created by fuzamei on 2018/4/25.
 */
public class DecimalUtil {

    private DecimalUtil(){
        throw new AssertionError("instaniation is not permitted");
    }

    /**
     * 乘法精确计算
     * @param x 乘数
     * @param y 乘数
     * @param scale 小数点后保留位数
     * @param strategy 采用四舍五入等策略
     * @return
     */
    public static final double multiply(double x, double y, int scale, RoundingMode strategy){
        BigDecimal xDdecimal = new BigDecimal(String.valueOf(x));
        BigDecimal yDdecimal = new BigDecimal(String.valueOf(y));
        BigDecimal one = new BigDecimal("1");
        BigDecimal divide = xDdecimal.multiply(yDdecimal).divide(one, scale, strategy);
        return divide.doubleValue();
    }

    /**
     * 多个数乘法精确计算
     * @param scale 小数点后保留位数
     * @param strategy 采用四舍五入等策略
     * @param xs 多个乘数相乘
     * @return
     */
    public static final double mMultiply(int scale, RoundingMode strategy,double... xs){
        if (xs.length == 0){
            throw new RuntimeException("计算必须填入参数");
        }
        BigDecimal result = new BigDecimal("1");
        for (double x : xs) {
            result = result.multiply(new BigDecimal(String.valueOf(x)));
        }
        BigDecimal one = new BigDecimal("1");
        BigDecimal divide = result.divide(one, scale, strategy);
        return divide.doubleValue();
    }

    /**
     * 减法精确计算
     * @param x 被减数
     * @param y 减数
     * @param scale 小数点后保留位数
     * @param strategy 采用四舍五入等策略
     * @return
     */
    public static final double subtract(double x, double y, int scale, RoundingMode strategy){
        BigDecimal xDdecimal = new BigDecimal(String.valueOf(x));
        BigDecimal yDdecimal = new BigDecimal(String.valueOf(y));
        BigDecimal one = new BigDecimal("1");
        BigDecimal divide = xDdecimal.subtract(yDdecimal).divide(one, scale, strategy);
        return divide.doubleValue();
    }

    public static final double add(double x, double y, int scale, RoundingMode strategy){
        BigDecimal xDdecimal = new BigDecimal(String.valueOf(x));
        BigDecimal yDdecimal = new BigDecimal(String.valueOf(y));
        BigDecimal one = new BigDecimal("1");
        BigDecimal divide = xDdecimal.add(yDdecimal).divide(one, scale, strategy);
        return divide.doubleValue();
    }

    public static final double mAdd(int scale, RoundingMode strategy,double...xs){
        if (xs.length == 0){
            throw new RuntimeException("计算必须填入参数");
        }
        BigDecimal result = new BigDecimal("0");
        for (double x : xs) {
            result = result.add(new BigDecimal(String.valueOf(x)));
        }
        BigDecimal one = new BigDecimal("1");
        BigDecimal divide = result.divide(one, scale, strategy);
        return divide.doubleValue();
    }
    public static final double divide(double x, double y, int scale, RoundingMode strategy){
        BigDecimal xDdecimal = new BigDecimal(String.valueOf(x));
        BigDecimal yDdecimal = new BigDecimal(String.valueOf(y));
        BigDecimal divide = xDdecimal.divide(yDdecimal, scale, strategy);
        return divide.doubleValue();
    }

}
