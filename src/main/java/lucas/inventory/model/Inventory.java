package lucas.inventory.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory
{
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Create new part.
     * Adding new part to observable part list
     * */
    public static void addPart(Part part)
    {
        allParts.add(part);
    }
    /**
     * Create new product.
     * Adding new product to observable part list
     * */
    public static void addProduct(Product product)
    {
        allProducts.add(product);
    }

    /** Lookup part with id.
     * @param partId to search for part
     * @return part by id*/
    public static Part lookupPart(int partId)
    {
        return allParts.get(partId);
    }
    /** Lookup product with id.
     * @param productId to search for product
     * @return product by id*/
    public static Product lookupProduct(int productId)
    {
        int productLoc = 0;
        for (int i = 0; i < allProducts.size(); i++)
        {
            if (allProducts.get(i).getId() == productId)
            {

                productLoc = i;
            }

        }
        return allProducts.get(productLoc);
    }
    /**Lookup part with name.
     * @param partName to search for part
     * @return part by name*/
    public static ObservableList<Part> lookupPart(String partName)
    {
        ObservableList<Part> equalPart = FXCollections.observableArrayList();
        for (int i = 0; i < allParts.size(); i++)
        {
            if (allParts.get(i).getName().equals(partName))
            {
                equalPart.add(allParts.get(i));
            }
        }
        return equalPart;

    }
    /**Lookup product with name.
     * @param productName to search for part
     * @return product by name*/
    public static ObservableList<Product> lookupProduct(String productName)
    {
        ObservableList<Product> equalProduct = FXCollections.observableArrayList();
        for (int i = 0; i < allProducts.size(); i++)
        {
            if (allProducts.get(i).getName().contains(productName))
            {
                equalProduct.add(allProducts.get(i));
            }
        }

        return equalProduct;

    }

    public static void updatePart(int index, Part selectedPart)
    {

    }




    /**
     * @return allParts*/
    public static ObservableList<Part> getAllParts()
    {
        return  allParts;
    }
    /**@return allProducts*/
    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }

    /**
     * function to add test data to populate table at beginning
     * */
    private static void addTestData()
    {
        InHouse ih1 = new InHouse(001, "Stuff", 15.00, 5, 0, 10, 0011);
        Inventory.addPart(ih1);
        Product pr1 = new Product(001, "More Stuff", 10.25, 2, 1, 5);
        Inventory.addProduct(pr1);
        OutSourced os1 = new OutSourced(001, "Most Stuff", 12.21, 1, 0, 10, "WHEELS");
        Inventory.addPart(os1);
    }

    static {
        addTestData();
    }

}
