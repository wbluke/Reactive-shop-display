package reactive.shopdisplay.domain.shop;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Shop {

    private Long shopNumber;
    private String shopName;
    private ShopStatus shopStatus;

    @Builder
    private Shop(String shopName, ShopStatus shopStatus) {
        this.shopName = shopName;
        this.shopStatus = shopStatus;
    }

    public void setShopNumber(Long shopNumber) {
        this.shopNumber = shopNumber;
    }

}
