package com.example.thuchanh_4_1_2022.Controller;

import com.example.thuchanh_4_1_2022.Domain.Product;
import com.example.thuchanh_4_1_2022.Domain.User;
import com.example.thuchanh_4_1_2022.Dto.ProductDto;
import com.example.thuchanh_4_1_2022.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ProductService productService;


    @GetMapping("/index")
    public String redirectIndex(Model model){
        model.addAttribute("Products",productService.getAll());
        return "adminview";
    }

    @GetMapping("/redirectCreate")
    public String redirectCreate(Model model){
        model.addAttribute("product", new Product());
        return "createForm";
    }
    @PostMapping("/create")
    public String create(Product product, @RequestParam("file")MultipartFile file, HttpSession session){
        productService.createProduct(product,file, session);
        return "redirect:/admin/index";
    }

    @GetMapping("/getProduct/{id}")
    public String getProduct(Model model, @PathVariable("id") Integer id){
        Product getProduct = productService.findById(id);
        if(getProduct == null){
            return "redirect:/admin/index";
        }
        model.addAttribute("product",getProduct);
        return "productdetails";
    }

    @PostMapping("/updateProduct")
    public  String updateProduct(Product product, @RequestParam("file") MultipartFile file, HttpSession session){
        productService.updateProduct(product,file,session);
        return "redirect:/admin/index";
    }

    @GetMapping("removeProduct/{id}")
    public String removeProduct(Model model, @PathVariable("id") Integer id){
        productService.deleteProduct(id);
        return "redirect:/admin/index";
    }

    @GetMapping("/thongke")
    public String thongke(HttpSession session,Model model){
        List<ProductDto> lstDto = productService.getThongKe(session);
        Double sumPrice = lstDto.stream().mapToDouble(o -> o.getPrice()).sum();
        model.addAttribute("lstDto",lstDto);
        model.addAttribute("sumPrice",sumPrice);
        return "thongke";
    }
}
