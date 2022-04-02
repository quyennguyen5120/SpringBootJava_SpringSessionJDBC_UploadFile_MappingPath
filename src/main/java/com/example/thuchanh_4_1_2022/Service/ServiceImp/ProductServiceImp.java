package com.example.thuchanh_4_1_2022.Service.ServiceImp;

import com.example.thuchanh_4_1_2022.Domain.Product;
import com.example.thuchanh_4_1_2022.Dto.ProductDto;
import com.example.thuchanh_4_1_2022.Repository.ProductRepository;
import com.example.thuchanh_4_1_2022.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public void createProduct(Product p, MultipartFile file, HttpSession session) {
        productRepository.createProdut(p, file,session);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteProduct(id);
    }

    @Override
    public void updateProduct(Product p, MultipartFile file, HttpSession session) {
        productRepository.updateProduct(p, file,session);
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getByType(Integer type) {
        return productRepository.getByType(type);
    }

    @Override
    public List<ProductDto> getThongKe(HttpSession session) {
        List<ProductDto> lst= new ArrayList<>();
        ProductDto dto = null;
        for(Product p : Product.Products){
            dto = productRepository.getThongke(p.getId(),session);
            lst.add(dto);
        }
        return lst;
    }

    @Override
    public List<Product> getBySearchText(String textSearch) {
        return productRepository.getBySearchTexxt(textSearch);
    }
}
