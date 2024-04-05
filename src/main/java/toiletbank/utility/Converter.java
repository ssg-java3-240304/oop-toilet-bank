package toiletbank.utility;

public class Converter {

    public Integer convertToNumber(String value) {
        checkNumber(value);
        checkZero(value);
        return Integer.parseInt(value);
    }

    public Integer[] convertToNumbers(String value) {
        String[] splitValue = value.split("-");
        checkLength(splitValue, 2);

        return new Integer[]{convertToNumber(splitValue[0]), convertToNumber(splitValue[1])};
    }

    private <T> void checkLength(T[] array, int length) {
        if (array.length != length) {
            throw new IllegalArgumentException();
        }
    }

    private void checkNumber(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException();
        }
    }

    private void checkZero(String value) {
        if ((value.charAt(0) == 48) && (value.length() > 1)) {
            throw new IllegalArgumentException();
        }
    }

}
