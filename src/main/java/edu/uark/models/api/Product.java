package edu.uark.models.api;

import java.time.LocalDateTime;
import java.util.UUID;

import edu.uark.models.entities.ProductEntity;

public class Product
{
	private UUID id;
	private String lookupCode;
	private int count;
	private LocalDateTime createdOn;
	private int sold;

	public int getSold() {
		return this.sold;
	}

	public UUID getId() {
		return this.id;
	}

	public String getLookupCode() {
		return this.lookupCode;
	}

	public int getCount() {
		return this.count;
	}

	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}

	public Product setId(UUID id)
	{
		this.id = id;
		return this;
	}

	public Product setLookupCode(String lookupCode)
	{
		this.lookupCode = lookupCode;
		return this;
	}

	public Product setCount(int count)
	{
		this.count = count;
		return this;
	}

	public Product setCreatedOn(LocalDateTime createdOn)
	{
		this.createdOn = createdOn;
		return this;
	}
	
	public Product()
	{
		this.count = -1;
		this.lookupCode = "";
		this.id = new UUID(0, 0);
		this.createdOn = LocalDateTime.now();
		this.sold = 0;
	}
	
	public Product(ProductEntity productEntity)
	{
		this.id = productEntity.getId();
		this.count = productEntity.getCount();
		this.createdOn = productEntity.getCreatedOn();
		this.lookupCode = productEntity.getLookupCode();
		this.sold = productEntity.getSold();
	}
}
