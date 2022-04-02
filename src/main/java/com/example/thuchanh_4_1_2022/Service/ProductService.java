package com.example.thuchanh_4_1_2022.Service;

import com.example.thuchanh_4_1_2022.Domain.Product;
import com.example.thuchanh_4_1_2022.Dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ProductService {
    public List<Product> getAll();
    public void createProduct(Product p, MultipartFile file, HttpSession session);
    public void deleteProduct(Integer id);
    public void updateProduct(Product p, MultipartFile file, HttpSession session);
    public Product findById(Integer id);
    public List<Product> getByType(Integer type);
    public List<ProductDto> getThongKe(HttpSession session);
    public List<Product> getBySearchText(String textSearch);

}
