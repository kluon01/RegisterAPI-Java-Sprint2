package edu.uark.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uark.commands.products.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uark.models.api.Product;

@RestController
@RequestMapping(value = "/api/product")
public class ProductRestController
{
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Product> getProducts()
	{
		return (new ProductsQuery()).execute();
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable UUID productId)
	{
		return (new ProductQuery()).
			setProductId(productId).
			execute();
	}

	@RequestMapping(value = "/byLookupCode/{productLookupCode}", method = RequestMethod.GET)
	public Product getProductByLookupCode(@PathVariable String productLookupCode)
	{
		return (new ProductByLookupCodeQuery()).
			setLookupCode(productLookupCode).
			execute();
	}

	@RequestMapping(value = "/byHighestSold", method = RequestMethod.GET)
	public List<Product> getProductWithHighestSold()
	{
		return (new ProductWithHighestSold(getProducts())).
				execute();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Product createProduct(@RequestBody Product product)
	{
		return (new ProductCreateCommand()).
			setApiProduct(product).
			execute();
	}
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
	public Product updateProduct(@PathVariable UUID productId, @RequestBody Product product)
	{
		return (new ProductUpdateCommand()).
			setProductId(productId).
			setApiProduct(product).
			execute();
	}

	@RequestMapping(value = "/updateProducts", method = RequestMethod.PUT)
	public ArrayList<Product> productTransaction(@RequestBody ArrayList<Product> products)
	{
		return (new productTransactionCommand()).
				setProductList(products).
				execute();
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable UUID productId)
	{
		(new ProductDeleteCommand()).
			setProductId(productId).
			execute();
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Successful test. (ProductRestController)";
	}
}
