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

public class ProductWithHighestCount implements ResultCommandInterface<List<Product>>
{
    private ProductRepositoryInterface productRepository;
    private ArrayList<Product> allproducts;

    @Override
    public List<Product> execute()
    {
        List<Product> topproducts = new ArrayList<Product>();

        sortProductsByCount(allproducts);

        int maxcount = allproducts.get(0).getCount();
        for(int x = 0; x < allproducts.size(); x++)
        {
            if(allproducts.get(x).getCount() == maxcount)
            {
                topproducts.add(allproducts.get(x));
            }
        }
        return topproducts;
    }

    public static void sortProductsByCount(List<Product> p)
    {
        Collections.sort(p, new Comparator<Product>()
        {
            @Override
            public int compare(Product o1, Product o2)
            {
                return Integer.compare(o2.getCount(), o1.getCount());
            }
        });
    }

    //Properties
    public ProductWithHighestCount(List<Product> p)
    {
        this.allproducts = new ArrayList<Product>(p);

        this.productRepository = new ProductRepository();
    }
}
