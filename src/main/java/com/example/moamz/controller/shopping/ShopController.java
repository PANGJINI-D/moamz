package com.example.moamz.controller.shopping;

import com.example.moamz.domain.dto.shopping.CartDTO;
import com.example.moamz.domain.dto.shopping.CartDetailDTO;
import com.example.moamz.domain.dto.shopping.ProductShopDetailDTO;
import com.example.moamz.domain.dto.shopping.ProductListMainDTO;
import com.example.moamz.service.shopping.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {
    private final ProductService productService;

    @GetMapping("/list")
    public String productList(Model model){
        List<ProductListMainDTO> productList = productService.showProductList();
        model.addAttribute("productList", productList);

        return "shopping/productList";
    }

    @GetMapping("/detail/{fgProductId}")
    public String productDetail(@PathVariable("fgProductId") Long fgProductId, Model model){
        ProductShopDetailDTO prodcutDetail = productService.showProduct(fgProductId);

        model.addAttribute("prodcutDetail", prodcutDetail);

        return "shopping/productDetail";
    }

    @GetMapping("/cart")
    public String cart(Model model){
        Long sampleUserCode = 1L;
        Long sampleCartCode = productService.findCartId(sampleUserCode);
        List<CartDTO> cartLists = productService.showCartList(sampleCartCode);

//        for(CartDTO cartDTO : cartLists){
//            cartDTO.getE
//        }

        model.addAttribute("cartLists", cartLists);

        return "shopping/cart";
    }

    @PostMapping("/payment")
    public String processPayment(@RequestParam("productId") Long productId,
                                 @RequestParam("productName") String productName,
                                 @RequestParam("productPrice") Integer productPrice,
                                 @RequestParam("productWeight") Double productWeight,
                                 @RequestParam("productQuantity") int productQuantity,
                                 @RequestParam("productExpTime") LocalDateTime productExpTime,
                                 @RequestParam("productFileRoot") String productFileRoot,
                                 @RequestParam("productFileUuid") String productFileUuid,
                                 @RequestParam("productFileName") String productFileName,
                                 RedirectAttributes redirectAttributes) {
        // RedirectAttributes로 데이터 전달
        System.out.println("productId = " + productId);

       Long orderId = productService.payOrderId();
       Long orderDetailId = productService.payOrderDetailId();

        redirectAttributes.addFlashAttribute("orderId", orderId);
        redirectAttributes.addFlashAttribute("orderDetailId", orderDetailId);
        redirectAttributes.addFlashAttribute("productId", productId);
        redirectAttributes.addFlashAttribute("productName", productName);
        redirectAttributes.addFlashAttribute("productPrice", productPrice);
        redirectAttributes.addFlashAttribute("productQuantity", productQuantity);
        redirectAttributes.addFlashAttribute("productWeight", productWeight);
        redirectAttributes.addFlashAttribute("productExpTime", productExpTime);
        redirectAttributes.addFlashAttribute("productFileRoot", productFileRoot);
        redirectAttributes.addFlashAttribute("productFileUuid", productFileUuid);
        redirectAttributes.addFlashAttribute("productFileName", productFileName);

        return "redirect:/shop/payment";
    }


    @GetMapping("/payment")
    public String getPaymentPage(Model model) {
        // 플래시 속성에서 데이터 가져오기 (Redirect 이후)
        if (!model.containsAttribute("productId")) {
            model.addAttribute("productId", "No Data");
        }
        return "shopping/payment";
    }



    @GetMapping("/storeList")
    public String shoplist(Model model){
        return "shopping/storeList";
    }

    @PostMapping("/cart/add")
    public ResponseEntity<String> addToCart(@RequestBody CartDetailDTO cartDetailDTO) {
        try {
            productService.addCartDetail(cartDetailDTO); // 서비스 호출
            System.out.println("굿 cartDetailDTO = " + cartDetailDTO + "굿");
            return ResponseEntity.ok("장바구니에 추가 완료");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("배드 cartDetailDTO = " + cartDetailDTO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("장바구니 추가 실패: " + e.getMessage());
        }
    }


}















