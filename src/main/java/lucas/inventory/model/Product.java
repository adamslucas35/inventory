package lucas.inventory.model; /**
 * Supplied class Product.java
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.Period;

/**
 *
 * @author Adam S Lucas
 */
public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    /**Constructor to create new part.
     * @param id initialize part id
     * @param name initialize part name
     * @param price initialize part price
     * @param stock initialize part stock
     * @param max initialize max inventory
     * @param min initialize min inventory*/
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock()
    {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock)
    {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin()
    {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min)
    {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax()
    {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max)
    {
        this.max = max;
    }

    /**
     * @param part the part to add to associated parts
     * */
    public void addAssociatedPart(Part part)
    {
        for (Part p : Inventory.getAllParts())
        {
            if (part.getId() == p.getId())
            {
                associatedParts.add(p);
            }
        }
    }

    /**
     * Delete selected part.
     * @param selectedAssociatedPart to delete from list
     * */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart)
    {
        for (Part part: getAllAssociatedParts())
        {
            if (part.getId() == selectedAssociatedPart.getId())
            {
                return getAllAssociatedParts().remove(part);
            }
        }
        return false;
    }
    /**
     * To return all items in list.
     * @return associatedParts list
     * */

    public ObservableList<Part> getAllAssociatedParts()
    {
        return associatedParts;
    }

    public void saveParts(ObservableList<Part> assocParts)
    {
        associatedParts = assocParts;
    }

}