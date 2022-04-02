package com.example.thuchanh_4_1_2022.Repository;

import com.example.thuchanh_4_1_2022.Domain.Product;
import com.example.thuchanh_4_1_2022.Domain.User;
import com.example.thuchanh_4_1_2022.Dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    @Value("${config.upload_folder}")
    String UPLOAD_FOLDER;

    public List<Product> getAll(){
        return Product.Products;
    }
    public Product findById(Integer id){
        return Product.Products.stream().filter(x->x.getId() == id).findAny().orElse(null);
    }

    public void createProdut(Product p, MultipartFile file, HttpSession session){
        String realativeFilePath = null;
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        String subFolder = month +"_"+year;
        String fullUploadDir = UPLOAD_FOLDER + subFolder;
        File checkDir = new File(fullUploadDir);
        if(checkDir.exists() == true || checkDir.isFile() == true){
            checkDir.mkdir();
        }
        try{
            realativeFilePath = subFolder + Instant.now().getEpochSecond() + file.getOriginalFilename();
            Files.write(Paths.get(UPLOAD_FOLDER + realativeFilePath), file.getBytes());
        }
        catch (Exception e){
            System.out.println(e);
        }
        p.setImage(realativeFilePath);

        int id = Product.Products.size() + 1;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        p.setId(id);
        p.setCraeteTime(new Date(now.getYear(), now.getMonthValue(),now.getDayOfMonth()));
        User currentUser = (User) session.getAttribute("user");
        p.setCreateByUser(currentUser.getUsername());
        Product.Products.add(p);
    }

    public void updateProduct(Product p, MultipartFile file, HttpSession session){
        Product productOld = Product.Products.stream().filter(x->x.getId() == p.getId()).findAny().orElse(null);
        productOld.setName(p.getName());
        productOld.setPrice(p.getPrice());
        productOld.setAmount(p.getAmount());
        productOld.setStatus(p.getStatus());
        productOld.setType(p.getType());
        if(!file.isEmpty()){
            String realativeFilePath = null;
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int year = localDate.getYear();
            int month = localDate.getMonthValue();
            String subFolder = month +"_"+year;
            String fullUploadDir = UPLOAD_FOLDER + subFolder;
            File checkDir = new File(fullUploadDir);
            if(checkDir.exists() == true || checkDir.isFile() == true){
                checkDir.mkdir();
            }
            try{
                realativeFilePath = subFolder + Instant.now().getEpochSecond() + file.getOriginalFilename();
                Files.write(Paths.get(UPLOAD_FOLDER + realativeFilePath), file.getBytes());
            }
            catch (Exception e){
                System.out.println(e);
            }
            User currentUser = (User) session.getAttribute("user");
            productOld.setCreateByUser(currentUser.getUsername());
            productOld.setImage(realativeFilePath);
        }
    }
    public void deleteProduct(Integer id){
        Product productOld = this.findById(id);
        Product.Products.remove(productOld);
    }

    public List<Product> getByType(Integer type){
        return Product.Products.stream().filter(x->x.getType() == type).collect(Collectors.toList());
    }

    public ProductDto getThongke(Integer id,HttpSession session){
        ProductDto dto = new ProductDto();
        dto.setName(this.findById(id).getName());
        List<Product> lstCart = (List<Product>) session.getAttribute("cart");
        Integer totalAmount = 0;
        Double totalPrice = 0D;
        for(Product x : lstCart){
            if(x.getId() == id){
                totalAmount +=1;
                totalPrice += x.getPrice();
            }
        }
        dto.setAmount(totalAmount);
        dto.setPrice(totalPrice);
        return dto;
    }

    public List<Product> getBySearchTexxt(String textSearhc){
        List<Product> getLst = new ArrayList<>();
        if(textSearhc != "")
            getLst = Product.Products.stream().filter(x->x.getName().toLowerCase().equals(textSearhc.toLowerCase(Locale.ROOT))).collect(Collectors.toList());
        else
            getLst = this.getAll();
        return getLst;
    }
}
