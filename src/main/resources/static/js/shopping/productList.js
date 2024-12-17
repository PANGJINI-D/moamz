let productPics = document.querySelectorAll(".productList-product-product-box-pic");
let tocart = document.querySelectorAll(".tocart");

productPics.forEach(function (productPic) {
    productPic.addEventListener('mouseover', function () {
        tocart.forEach(function (e) {
            e.style.display = "block"; // 각 tocart 요소에 대해 설정
        });
    });

    productPic.addEventListener('mouseleave', function () {
        tocart.forEach(function (e) {
            e.style.display = "none"; // 각 tocart 요소에 대해 설정
        });
    });
});

// 모든 'tocart' 버튼을 가져옵니다.
const tocartButtons = document.querySelectorAll('.tocart');

tocartButtons.forEach(function (button) {
    // 각 버튼에 클릭 이벤트 리스너 추가
    button.addEventListener('click', () => {
        // 현재 버튼과 연결된 productId를 가져옴
        const productIdInput = button.closest('.productList-product-product-box').querySelector('input[id^="productId-"]');
        const productId = productIdInput ? productIdInput.value : null;

        if (!productId) {
            alert("제품 ID를 찾을 수 없습니다.");
            return;
        }

        // 장바구니에 추가 확인
        if (confirm('장바구니에 추가할까요?')) {
            const cartDetail = {
                fgCartDetailQuantity: 10, // 기본 수량
                fgProductId: productId,
                fgCartId: 1 // 현재 장바구니 ID (예시)
            };

            // 서버로 POST 요청
            fetch('http://localhost:9999/shop/cart/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(cartDetail)
            })
                .then(response => {
                    if (response.ok) {
                        alert("장바구니에 추가 완료");
                    } else {
                        alert("장바구니 추가 실패");
                    }
                })
                .catch(error => {
                    console.error("요청 실패:", error);
                    alert("네트워크 오류로 인해 장바구니 추가 실패");
                });
        } else {
            alert("취소");
        }
    });
});
