package edu.uark.commands.products;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.controllers.exceptions.NotFoundException;
import edu.uark.controllers.exceptions.UnprocessableEntityException;
import edu.uark.models.api.Product;
import edu.uark.models.entities.ProductEntity;
import edu.uark.models.repositories.ProductRepository;
import edu.uark.models.repositories.interfaces.ProductRepositoryInterface;

public class productTransactionCommand implements ResultCommandInterface <ArrayList<Product>>
{
    private ArrayList<Product> apiProducts;
    private ProductRepositoryInterface productRepository;

    @Override
    public ArrayList<Product> execute()
    {
        for (Product product : (Iterable<Product>) apiProducts)
        {
            product.setCount(product.getCount() - 1); // Product was involved with a transaction so there is 1 less of it

            ProductEntity productEntity = this.productRepository.get(product.getId());

            if (productEntity == null)
            {
                //No record with the associated record ID exists in the database.
                throw new NotFoundException("Product");
            }

            product = productEntity.synchronize(product); //Synchronize any incoming changes for UPDATE to the database.

            productEntity.save(); //Write, via an UPDATE, any changes to the database.
        }

        return apiProducts;
    }

    //Properties
    public productTransactionCommand setProductList(ArrayList<Product> apiProducts)
    {
        this.apiProducts = apiProducts;
        return this;
    }

    public productTransactionCommand setProductRepository(ProductRepositoryInterface productRepository)
    {
        this.productRepository = productRepository;
        return this;
    }

    public productTransactionCommand()
    {
        this.productRepository = new ProductRepository();
    }
}
