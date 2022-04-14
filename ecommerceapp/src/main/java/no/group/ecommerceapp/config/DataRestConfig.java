package no.group.ecommerceapp.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import no.group.ecommerceapp.entity.Country;
import no.group.ecommerceapp.entity.Order;
import no.group.ecommerceapp.entity.Product;
import no.group.ecommerceapp.entity.ProductCategory;
import no.group.ecommerceapp.entity.State;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

	private EntityManager entityManager;
	
	@Value("${allowed.origins}")
	private String[] theAllowedOrigins;
	
	@Autowired
	public DataRestConfig(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		
		HttpMethod[] unsupportedActions = 
					{HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE,HttpMethod.PATCH};
		
		disableHttpMethods(Product.class, config, unsupportedActions);
		disableHttpMethods(ProductCategory.class, config, unsupportedActions);
		disableHttpMethods(Country.class, config, unsupportedActions);
		disableHttpMethods(State.class, config, unsupportedActions);
		disableHttpMethods(Order.class, config, unsupportedActions);

		
		cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);
		
		exposeIds(config);
	}

	private void disableHttpMethods(Class targetClass, RepositoryRestConfiguration config, HttpMethod[] unsupportedActions) {
		config.getExposureConfiguration()
			.forDomainType(targetClass)
			.withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
			.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));
	}

	private void exposeIds(RepositoryRestConfiguration config) {
		
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		
		List<Class> entityClasses = new ArrayList<>();
		
		for(EntityType entityType: entities) {
			entityClasses.add(entityType.getJavaType());
		}
		
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		
		config.exposeIdsFor(domainTypes);
		
	}
	
}
