package lucas.inventory.model;

public class InHouse extends Part{

    //fields
    private int machineId;
    /**
     * Constructor to create new part.
     *
     * @param id    initialize part id
     * @param name  initialize part name
     * @param price initialize part price
     * @param stock initialize part stock
     * @param min   initialize min inventory
     * @param max   initialize max inventory
     * @param machineId initialize machine id
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId)
    {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * @return the machineId
     * */
    public int getMachineId()
    {
        return machineId;
    }
    /**
     * @param machineId the machineId to set
     * */
    public void setMachineId(int machineId)
    {
        this.machineId = machineId;
    }
}
