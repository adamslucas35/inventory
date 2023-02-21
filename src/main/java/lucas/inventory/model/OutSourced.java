package lucas.inventory.model;

public class OutSourced extends Part
{
    //fields
    private String companyName;
    /**
     * Constructor to create new part.
     *
     * @param id    initialize part id
     * @param name  initialize part name
     * @param price initialize part price
     * @param stock initialize part stock
     * @param min   initialize min inventory
     * @param max   initialize max inventory
     * @param companyName initialize companyName
     */
    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName)
    {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * @return Gets companyName
     * */
    public String getCompanyName()
    {
        return companyName;
    }
    /**
     * @param companyName sets companyName
     * */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
}
