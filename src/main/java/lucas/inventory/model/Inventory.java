package lucas.inventory.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lucas.inventory.MainApplication;

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
        if (!(partsFilteredSearch.isEmpty()))
        { partsFilteredSearch.clear(); }


//        ObservableList<Part> allParts = getAllParts();
        for (Part part : allParts)
        {
            if (part.getName().toLowerCase().contains(partName.toLowerCase()))
            {
                partsFilteredSearch.add(part);
            }
        }


        return partsFilteredSearch;


    }
    /**Lookup product with name.
     * @param productName to search for part
     * @return product by name*/
    public static ObservableList<Product> lookupProduct(String productName)
    {
        if(!(productsFilteredSearch.isEmpty()))
            productsFilteredSearch.clear();

        for (Product product : allProducts)
        {
            if (product.getName().toLowerCase().contains(productName.toLowerCase()))
            {
                productsFilteredSearch.add(product);
            }
        }
        return productsFilteredSearch;

    }

    /**
     * Function to update part with new information.
     * @param index keeps track of which part is selected
     * @param selectedPart controls which part is updated
     */
    public static void updatePart(int index, Part selectedPart)
    {
        int i = -1;
        for (Part part : Inventory.getAllParts())
        {
            i++;
            if(part.getId() == index)
            {
                Inventory.getAllParts().set(i, selectedPart);
            }
        }

    }

    /**
     * Function to update product with new information.
     * @param index keeps track of which part is selected
     * @param newProduct controls which part is updated
     */
    public static void updateProduct(int index, Product newProduct)
    {
        int i = -1;
        for (Product product : Inventory.getAllProducts())
        {
            i++;
            if(product.getId() == newProduct.getId())
            {
                Inventory.getAllProducts().set(i, newProduct);
            }
        }
    }

    /**
     * Function to delete selected part from list.
     * @param selectedPart controls which part is selected
     * @return boolean
     */
    public static boolean deletePart(Part selectedPart)
    {
        for (Part part : allParts)
        {
            if(selectedPart.getId() == part.getId())
            {
                return Inventory.getAllParts().remove(part);
            }
        }
        return false;
    }

    /**
     * Function to delete selected product from list.
     * @param selectedProduct controls which part is selected
     * @return boolean
     */
    public static boolean deleteProduct(Product selectedProduct)
    {
        for (Product product : Inventory.getAllProducts())
        {
            if (product.getId() == selectedProduct.getId())
            {
                return Inventory.getAllProducts().remove(product);
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
     * Function to add test data to populate table at beginning.
     * */
    private static void addTestData()
    {
        InHouse ih1 = new InHouse(MainApplication.generatePartsID(), "Stuff", 15.00, 5, 0, 10, 0011);
        Inventory.addPart(ih1);
        OutSourced os1 = new OutSourced(MainApplication.generatePartsID(), "Most Stuff", 12.21, 1, 0, 10, "WHEELS");
        Inventory.addPart(os1);
        Product pr1 = new Product(MainApplication.generateProductsID(), "More Stuff", 10.25, 2, 1, 5);
        Inventory.addProduct(pr1);
        Product pr2 = new Product(MainApplication.generateProductsID(), "XYZ Stuff", 15.15, 20, 1, 25);
        Inventory.addProduct(pr2);
        Product pr3 = new Product(MainApplication.generateProductsID(), "Jiffy Lube", 89.15, 10, 1, 20);
        Inventory.addProduct(pr3);
        InHouse ih2 = new InHouse(MainApplication.generatePartsID(), "QRT Stuff", 5.00, 5, 0, 10, 0012);
        Inventory.addPart(ih2);
        OutSourced os2 = new OutSourced(MainApplication.generatePartsID(), "Items", 12.01, 1, 0, 10, "Toy-r-us");
        Inventory.addPart(os2);
        Product pr4 = new Product(MainApplication.generateProductsID(), "Things", 15.11, 2, 1, 5);
        Inventory.addProduct(pr4);



    }

    static {
        addTestData();
    }

}
