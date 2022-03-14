package reactive.shopdisplay.domain.ad;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class ShopAd {

    private Long id;
    private AdType adType;
    private Long shopNumber;

    @Builder
    private ShopAd(AdType adType, Long shopNumber) {
        this.adType = adType;
        this.shopNumber = shopNumber;
    }

    public boolean isSameShopNumber(Long shopNumber) {
        return Objects.equals(this.shopNumber, shopNumber);
    }

    public void setId(long id) {
        this.id = id;
    }

}
