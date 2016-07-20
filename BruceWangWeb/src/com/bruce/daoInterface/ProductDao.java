package com.bruce.daoInterface;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;

import com.bruce.model.PageBean;
import com.bruce.model.Product;



public interface ProductDao extends BaseManager<Product, Long>{
	//@Cacheable(value="BaseCache")
	public List<Product> retrieve(PageBean pageBean,String brand);
	public Long count();
	public void updateProduct(Product product);
	public Product searchUni(Long id,String firstName);
}
