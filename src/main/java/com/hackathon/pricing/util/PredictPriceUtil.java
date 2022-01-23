package com.hackathon.pricing.util;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PredictPriceUtil {

    private static final double MIN_COST = 6.0;
    private static final double NUM_LEN = 9.0;
    private static final double CONSTANT = MIN_COST / NUM_LEN;

    public static int predictPrice(String number) {

        final Collection<Long> duplicationCounts = number.codePoints().mapToObj(Character::toString)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values();

        return specialCalculating(duplicationCounts, number.charAt(0));
    }

    private static int specialCalculating(Collection<Long> values, char firstNumber) {
        int price = 0;
        for (Long position : values) {
            price += position * weight(Math.toIntExact(position));
        }

        if (values.size() == 3 || values.size() == 2) price += (MIN_COST * NUM_LEN);

        price = Math.abs(price - values.size()) * 2 + 2;

        if (firstNumber == '2') price += 60;  // for government numbers


        return price;
    }

    public static double weight(int a) {

        switch (a) {
            case 1:
                return CONSTANT;
            case 2:
                return CONSTANT * 2;
            case 3:
                return CONSTANT * 3;
            case 4:
                return CONSTANT * 25;
            case 5:
                return CONSTANT * 30;
            case 6:
                return CONSTANT * 35;
            case 7:
                return CONSTANT * 40;
            case 8:
                return CONSTANT * 45;
            case 9:
                return CONSTANT * 50;
            default:
                return 0;
        }

    }
}
