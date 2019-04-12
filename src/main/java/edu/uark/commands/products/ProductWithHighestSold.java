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
        int count = 10;

        sortProductsBySold(allproducts);

        if(allproducts.size() < 10)
            count = allproducts.size();

        for(int x = 0; x < count; x++)
                topproducts.add(allproducts.get(x));

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
