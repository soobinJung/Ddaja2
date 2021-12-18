package com.bng.ddaja.test.enums;

import java.util.Arrays;
import java.util.function.Predicate;

public enum FaqParam {
    STARTDATE, ENDDATE, SUBJECT, VIEWFLAG, CATEGORY;

    Predicate<String> p = param -> Arrays.stream(values()).anyMatch(e -> e.name().equals(param.toUpperCase()));

    public boolean validateParam(String[] q) {
        return Arrays.stream(q).allMatch(p);
    }

    public Object getBoardWithParam(String[] q) {
        if (validateParam(q)) {
            Arrays.stream(q).forEach(param -> {
                String paramUpper = param.toUpperCase();
                if (paramUpper.equals(STARTDATE.name())) {
                }
            });
        }
        return null;
    }
}
