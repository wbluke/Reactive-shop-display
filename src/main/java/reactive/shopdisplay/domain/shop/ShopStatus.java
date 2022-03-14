package reactive.shopdisplay.domain.shop;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ShopStatus {

    OPEN("영업중"),
    PREPARING("준비중"),
    CLOSE("영업종료");

    private final String text;

    public boolean isOpen() {
        return this == OPEN;
    }

}
