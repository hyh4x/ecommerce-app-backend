package no.group.ecommerceapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import no.group.ecommerceapp.entity.ProductCategory;

@RepositoryRestResource(collectionResourceRel = "productCategory",
						path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
