package lucas.inventory.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory
{
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> partsFilteredSearch = FXCollections.observableArrayList();
    private static ObservableList<Product> productsFilteredSearch = FXCollections.observableArrayList();

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
        for (Part part : Inventory.getAllParts())
        {
            if (part.getId() == partId)
            {
                return part;
            }
        }
    return null;
    }

    /** Lookup product with id.
     * @param productId to search for productlogi
     * @return product by id*/
    public static Product lookupProduct(int productId)
    {
        for (Product product : Inventory.getAllProducts())
        {
            if (productId == product.getId())
            {
                return product;
            }
        }
        return null;
    }
    /**Lookup part with name.
     * @param partName to search for part
     * @return part by name*/
    public static ObservableList<Part> lookupPart(String partName)
    {
        if(!(Inventory.getPartsFilteredSearch().isEmpty()))
            Inventory.getPartsFilteredSearch().clear();

        for (Part part : Inventory.getAllParts())
        {
            if(part.getName().contains(partName))
            {
                Inventory.getPartsFilteredSearch().add(part);
            }
        }
        if (Inventory.getPartsFilteredSearch().isEmpty())
            return Inventory.getAllParts();
        else
            return Inventory.getPartsFilteredSearch();
    }
    /**Lookup product with name.
     * @param productName to search for part
     * @return product by name*/
    public static ObservableList<Product> lookupProduct(String productName)
    {
        if(!(Inventory.getProductsFilteredSearch().isEmpty()))
            Inventory.getProductsFilteredSearch().clear();
        for (Product product : Inventory.getAllProducts())
        {
            if (product.getName().contains((productName)))
            {
                Inventory.getProductsFilteredSearch().add(product);
            }
        }
        if (Inventory.getProductsFilteredSearch().isEmpty())
            return Inventory.getAllProducts();
        else
            return getProductsFilteredSearch();
    }

    public static void updatePart(int index, Part selectedPart)
    {

        for (Part part : Inventory.getAllParts())
        {
            if(part.getId() == selectedPart.getId())
            {
                Inventory.getAllParts().set(index, selectedPart);
            }
        }

    }

    public static void updateProduct(int index, Product newProduct)
    {
        for (Product product : Inventory.getAllProducts())
        {
            if(product.getId() == newProduct.getId())
            {
                Inventory.getAllProducts().set(index, newProduct);
            }
        }
    }

    public static boolean deletePart(Part selectedPart)
    {
        for (Part part : Inventory.getAllParts())
        {
            if (part.getId() == selectedPart.getId())
            {
                return Inventory.deletePart(selectedPart);
            }
        }
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct)
    {
        for (Product product : Inventory.getAllProducts())
        {
            if (product.getId() == selectedProduct.getId())
            {
                return Inventory.deleteProduct(selectedProduct);
            }
        }
        return false;
    }


    /**
     * @return allParts
     * */
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

    public static ObservableList<Part> getPartsFilteredSearch() {
        return partsFilteredSearch;
    }

    public static ObservableList<Product> getProductsFilteredSearch() {
        return productsFilteredSearch;
    }
}
