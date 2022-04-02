package com.example.thuchanh_4_1_2022.Controller;

import com.example.thuchanh_4_1_2022.Domain.Product;
import com.example.thuchanh_4_1_2022.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/index")
    public String redirectProduct(Model model){
        model.addAttribute("Products",productService.getAll());
        return "productview";
    }

    @GetMapping("/getByType/{type}")
    public String redirectGetByType(Model model,@PathVariable Integer type){
        model.addAttribute("Products",productService.getByType(type));
        return "productview";
    }

    @GetMapping("/redirectCart")
    public  String redirectDoHang(HttpSession session,Model model){
        List<Product> lstPro =(List<Product> ) session.getAttribute("cart");
        model.addAttribute("lstPro",lstPro);
        return "cart";
    }

    @GetMapping("/addCart/{id}")
    public  String redirectDoHang(HttpSession session, @PathVariable Integer id){
        List<Product> lstProduct = (List<Product>) session.getAttribute("cart");
        if(lstProduct == null){
            lstProduct = new ArrayList<>();
            lstProduct.add(productService.findById(id));
        }
        else if(lstProduct != null){
            lstProduct.add(productService.findById(id));
        }
        session.setAttribute("cart", lstProduct);
        return "redirect:/product/index";
    }

    @PostMapping("/Search/")
    public String searchByText(Model model,@RequestParam("textSearch") String textSearch){
        model.addAttribute("Products",productService.getBySearchText(textSearch));
        return "productview";
    }

}
