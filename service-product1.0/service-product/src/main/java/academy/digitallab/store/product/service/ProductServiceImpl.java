package academy.digitallab.store.product.service;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl  implements ProductService{

   // @Autowired //injection dependency

    private final ProductRepository productRepository;

    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        System.out.println(product);
        product.setStatus("create");
        product.setCreateAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productDb = getProduct(product.getId());
        if(null == productDb){
            return null;
        }
        productDb.setName(product.getName());
        productDb.setDescription((product.getDescription()));
        productDb.setCategory(product.getCategory());
        productDb.setPrice(product.getPrice());

        return productRepository.save(productDb);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDb = getProduct(id);
        if(null == productDb){
            return null;
        }
        productDb.setStatus("DELETED");

        return productRepository.save(productDb);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quabtity) {
        Product productDb = getProduct(id);
        if(null == productDb){
            return null;
        }
        Double stock = productDb.getStock() + quabtity;
        productDb.setStock(stock);

        return productRepository.save(productDb);
    }
}
