package lucas.inventory.model;

public class InvalidValuesException extends Exception {
    private int inv;
    private int min;
    private int max;

    public InvalidValuesException(int inv, int min, int max) {
        this.inv = inv;
        this.min = min;
        this.max = max;
    }

    public int getInv() {
        return inv;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public String getMessage() {
        if (min > max) {
            return "Min amount cannot be greater than max amount.\nPlease check data values.";
        } else if (max < min) {
            return "Max amount cannot be less than min amount.\nPlease check data values.";
        } else if (inv > max || inv < min) {
            return "Inventory amount must be in between min and max value.\nPlease check data values.";
        } else {
            return "Invalid values exception occurred.";
        }
    }
}
