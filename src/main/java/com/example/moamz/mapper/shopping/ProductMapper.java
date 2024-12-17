package com.example.moamz.mapper.shopping;

import com.example.moamz.domain.dto.shopping.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    // 상품리스트
    List<ProductListMainDTO> productList();


    // 결제 상태 업데이트
    void updataOrderPaymentStatus(OrderUpdateDTO orderUpdateDTO);

    // 픽업 일정 추가
    void insertPickup(PickupDTO pickupDTO);

    // 제품이름 조회
    String selectProductName(Long fgProductId);

    // 주문 id로 pro_id 검색
    Long selectOrderProductId(Long fgOrderId);

//    <!--    유저코드로 orderId 찾기-->
    Long selectOrderUserCode(Long fgUserCode);

    // 유저코드로 cartId 찾기
    Long selectCartUserCode(Long fgUserCode);

    // cartId로 cartDetailId 리스트
    List<CartDTO> selectCartCartDetail(Long fgCartId);

    // cartDetail에 insert
    void insertCartDetail(CartDetailDTO cartDetailDTO);

    // cartDetail에서 delete
    void deleteCart(Long fgCartDetailId);

//    orderId
    Long selectOrderId();

    Long selectOrderDetailId();

    void insertOrder(OrderDTO order); // FG_ORDER 데이터 삽입
    void insertOrderDetail(OrderDetailDTO orderDetail); // FG_ORDER_DETAIL 데이터 삽입
    Long getBusinessIdByProductId(Long productId); // 상품 ID로 사업자 ID 조회
    String getProductNameByProductId(Long productId); // 상품 ID로 상품 이름 조회

    OrderDTO getOrderByOrderCode(Long orderCode);

    OrderDTO getLatestOrder();

    void approveOrder();

    ProductShopDetailDTO showProduct(Long fgProductId);
}
