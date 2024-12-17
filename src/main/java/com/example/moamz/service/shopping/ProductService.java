package com.example.moamz.service.shopping;

import com.example.moamz.domain.dto.shopping.*;
import com.example.moamz.mapper.shopping.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    // 상품 리스트
    public List<ProductListMainDTO> showProductList() {
        return productMapper.productList();
    }

    // 결제 상태 업데이트
    public void orderPayment(OrderUpdateDTO orderUpdateDTO) {
        productMapper.updataOrderPaymentStatus(orderUpdateDTO);
    }

    // 픽업 추가
    void addPickup(PickupDTO pickupDTO) {
        productMapper.insertPickup(pickupDTO);
    }
    
    // 제품 이름 조회
    String findProductName(Long fgProductId) {
        return productMapper.selectProductName(fgProductId);
    }

    // 주문 id로 pro_id 검색
    public Long findProductId(Long fgOrderId) {
        return productMapper.selectOrderProductId(fgOrderId);
    }

    // 유저 id로 주문 id 찾기
    public Long findOrderId(Long fgUserCode){
        return productMapper.selectOrderUserCode(fgUserCode);
    }

    // 유저 id로 cartid 찾기
    public Long findCartId(Long fgUserCode){
        return productMapper.selectCartUserCode(fgUserCode);
    }

    // cartId로 cartDetailId 리스트
    public List<CartDTO> showCartList(Long fgCartId) {
        return productMapper.selectCartCartDetail(fgCartId);
    }

    // cartDetail에 insert
    public void addCartDetail(CartDetailDTO cartDetailDTO) {
        productMapper.insertCartDetail(cartDetailDTO);
    }

    // cartDetail에서 delete
    public void removeCartDetail(Long cartDetailId) {
        productMapper.deleteCart(cartDetailId);
    }

    public Long payOrderId(){
        return productMapper.selectOrderId();
    }

    public Long payOrderDetailId(){
        return productMapper.selectOrderDetailId();
    }

    public OrderDTO getOrderByOrderCode(Long orderCode) {
        return productMapper.getOrderByOrderCode(orderCode);
    }

    public OrderDTO getLatestOrder(){
        return productMapper.getLatestOrder();
    }

    public void approveOrder(){
        productMapper.approveOrder();
    }

    public ProductShopDetailDTO showProduct(Long fgProductId) {
        return productMapper.showProduct(fgProductId);
    }
}
