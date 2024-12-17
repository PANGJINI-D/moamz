$(document).ready(function() {
    // .cart-product-count-btn 클래스에 대해 반복
    $(".cart-product-count-btn").each(function() {
        let count = 1; // 초기 카운트 설정

        const $countDisplay = $(this).find(".cart-product-count-num"); // 카운트 표시 선택
        const $incrementButton = $(this).find(".inc"); // 증가 버튼 선택
        const $decrementButton = $(this).find(".dec"); // 감소 버튼 선택
        const $productWeightDisplay = $(this).closest(".cart-product-detail-product").find(".cart-product-detail-product-intro-text-weight"); // 무게 표시 선택
        const $productPriceDisplay = $(this).closest(".cart-product-detail-product").find(".cart-product-detail-product-intro-text-price"); // 가격 표시 선택
        const id = document.querySelector('#selectedProductName');
        const $hiddenPriceInput = $("#selectedProductPrice"); // Hidden input 값 업데이트
        const $hiddenPriceWeight = $('#selectedProductWeight');
        const $hiddenQuantity = $('#selectedProductQuantity');

        // data-* 속성 값 가져오기
        let productWeight = parseFloat($productWeightDisplay.data("fg-product-weight"));
        let productPrice = parseInt($productPriceDisplay.data("fg-product-price"));

        // 값이 올바르게 가져와졌는지 확인 (디버깅용)
        console.log('Weight:', productWeight);
        console.log('Price:', productPrice);
        console.log('id', id.value);

        // 초기 카운트, 무게, 가격 표시 설정
        updateCartDisplay(count);

        // 증가 버튼 클릭 이벤트
        $incrementButton.click(function() {
            count += 1;
            updateCartDisplay(count);  // 수량 증가 후 디스플레이 업데이트
        });

        // 감소 버튼 클릭 이벤트
        $decrementButton.click(function() {
            if (count > 1) {
                count -= 1;
                updateCartDisplay(count);  // 수량 감소 후 디스플레이 업데이트
            } else {
                alert("수량은 1 이하로 설정할 수 없습니다.");  // 경고창 표시
            }
        });

        // 카운트, 가격, 무게를 업데이트하는 함수
        function updateCartDisplay(count) {
            // 수량에 따른 무게, 가격 계산 후 업데이트
            const totalWeight = (productWeight * count).toFixed(2);  // 무게 계산
            const totalPrice = (productPrice * count);  // 가격 계산 (천 단위 구분)

            // 화면에 표시
            $countDisplay.text(count);  // 카운트 표시 업데이트
            $productWeightDisplay.text(totalWeight + ' kg');  // 무게 업데이트
            $productPriceDisplay.text(totalPrice + '원');  // 가격 업데이트

            $hiddenPriceInput.val(totalPrice);
            $hiddenPriceWeight.val(totalWeight);
            $hiddenQuantity.val(count);
        }
    });
});





// const deleteBtn = document.querySelectorAll('.cart-delete');

// 모든 삭제 버튼에 대해 클릭 이벤트 추가
$('.cart-delete').each(function () {
    let fgCartDetailId = $(this).data('fg-cart-detail-id'); // data-값 가져오기

    $(this).on('click', function () {
        let isConfirmed = confirm("삭제할까요?");
        if (isConfirmed) {
            console.log(fgCartDetailId); // 각 data- 값 출력

            $.ajax({
                url: `http://localhost:9999/shop/cart/delete/` + fgCartDetailId,
                type: 'DELETE',
                success: function () {
                    alert("삭제 완료");
                    location.reload(); // 페이지 새로고침
                },
                error: function () {
                    alert("삭제 실패");
                }
            });
        }
    });
});

document.addEventListener("DOMContentLoaded", function () {
    // 모든 라디오 버튼 가져오기
    const radios = document.querySelectorAll(".cart-radio");
    const form = document.getElementById("cartForm");

    // 선택된 상품 정보를 hidden input에 설정
    radios.forEach((radio) => {
        radio.addEventListener("change", function () {
            // 선택된 라디오 버튼의 값 (상품 ID) 가져오기
            const productId = radio.value;

            // 해당 상품의 숨겨진 데이터를 가져와 hidden input에 설정
            document.getElementById("selectedProductId").value = document.getElementById(`productId-${productId}`).value;
            document.getElementById("selectedProductName").value = document.getElementById(`productName-${productId}`).value;
            document.getElementById("selectedProductPrice").value = document.getElementById(`productPrice-${productId}`).value;
            document.getElementById("selectedProductWeight").value = document.getElementById(`productWeight-${productId}`).value;
            document.getElementById("selectedProductExpTime").value = document.getElementById(`productExpTime-${productId}`).value;
            document.getElementById("selectedProductFileRoot").value = document.getElementById(`productFileRoot-${productId}`).value;
            document.getElementById("selectedProductFileUuid").value = document.getElementById(`productFileUuid-${productId}`).value;
            document.getElementById("selectedProductFileName").value = document.getElementById(`productFileName-${productId}`).value;
        });
    });

    // 폼 전송 시 선택 확인
    form.addEventListener("submit", function (e) {
        // 라디오 버튼이 선택되지 않았을 경우 전송 방지 및 경고 표시
        if (!document.querySelector('input[name="selectProduct"]:checked')) {
            e.preventDefault();
            alert("상품을 선택해주세요.");
        }
    });
});
