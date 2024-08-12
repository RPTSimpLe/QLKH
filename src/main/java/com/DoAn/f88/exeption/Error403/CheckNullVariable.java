package com.DoAn.f88.exeption.Error403;

public class CheckNullVariable {
    public static boolean checkNullString(String a) {
        return a != null && !a.isEmpty();
    }
    public static <T> boolean checkNullNumber(T a) {
        return !a.equals(null);
    }
    public static Long checkValidateLong(String a) {
        try {
            return Long.valueOf(a);
        }catch (NumberFormatException e) {
            throw new ValidateException("Sai kiểu dữ liệu");
        }
    }
}
