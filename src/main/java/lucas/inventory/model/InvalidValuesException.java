package lucas.inventory.model;

public class InvalidValuesException extends Exception {
    private int inv;
    private int min;
    private int max;

    /**
     * Constructor to run exception.
     * @param inv Inventory Amount
     * @param min Min amount allowed
     * @param max Max amount allowed
     */
    public InvalidValuesException(int inv, int min, int max) {
        this.inv = inv;
        this.min = min;
        this.max = max;
    }

    /**
     * @return inv
     */
    public int getInv() {
        return inv;
    }

    /**
     * @return min
     */
    public int getMin() {
        return min;
    }

    /**
     *
     * @return max
     */
    public int getMax() {
        return max;
    }

    /**
     * Creates condition to decide which error is being called.
     * @return String based on condition
     */
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
