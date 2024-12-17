window.onload = function() {
    var mapContainer = document.getElementById('payment-map');
    if (mapContainer) {
        var mapOption = {
            center: new kakao.maps.LatLng(37.497, 127.036),
            level: 3
        };

        var map = new kakao.maps.Map(mapContainer, mapOption);
    } else {
        console.error('지도 없어요');
    }
};

document.addEventListener("DOMContentLoaded", () => {
    document.querySelector('#kakaoPay').addEventListener('click', function () {
        const productId = document.querySelector('#productId').value;
        const orderId = document.querySelector('#orderId').value;
        const orderDetailId = document.querySelector('#orderDetailId').value;
        const productName = document.querySelector('#productName').value;
        const productPrice = document.querySelector('#productPrice').value;
        const productQuantity = document.querySelector('#productQuantity').value;

        // 서버로 데이터 전송
        $.ajax({
            type: 'POST',
            url: '/kakao/pay',
            contentType: 'application/json',
            data: JSON.stringify({
                fgOrderId: orderId,
                fgPaymentStatus: "결제중",
                fgOrderQuantity: productQuantity,
                fgOrderPerPrice: productPrice,
                fgOrderTax: 0,
                fgOrderUserPoint: 0,
                fgOrderAmount: productPrice * productQuantity,
                fgProductName: productName,
                fgProductId: productId
            }),
            success: function (response) {
                if (response.next_redirect_pc_url) {
                    window.location.href = response.next_redirect_pc_url; // 결제 페이지로 이동
                } else {
                    alert('결제 요청 중 오류가 발생했습니다.');
                }
            },
            error: function (error) {
                console.error('결제 요청 실패:', error);
            }
        });
    });
});





