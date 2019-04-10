package edu.uark.commands.products;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.models.api.Product;
import edu.uark.models.repositories.ProductRepository;
import edu.uark.models.repositories.interfaces.ProductRepositoryInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductWithHighestSold implements ResultCommandInterface<List<Product>>
{
    private ProductRepositoryInterface productRepository;
    private ArrayList<Product> allproducts;

    @Override
    public List<Product> execute()
    {
        List<Product> topproducts = new ArrayList<Product>();

        sortProductsBySold(allproducts);

        int maxcount = allproducts.get(0).getSold();
        for(int x = 0; x < allproducts.size(); x++)
        {
            if(allproducts.get(x).getSold() == maxcount)
            {
                topproducts.add(allproducts.get(x));
            }
        }
        return topproducts;
    }

    public static void sortProductsBySold(List<Product> p)
    {
        Collections.sort(p, new Comparator<Product>()
        {
            @Override
            public int compare(Product o1, Product o2)
            {
                return Integer.compare(o2.getSold(), o1.getSold());
            }
        });
    }

    //Properties
    public ProductWithHighestSold(List<Product> p)
    {
        this.allproducts = new ArrayList<Product>(p);

        this.productRepository = new ProductRepository();
    }
}
