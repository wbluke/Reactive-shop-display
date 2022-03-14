package reactive.shopdisplay.domain.ad;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AdType {

    A("A 타입"),
    B("B 타입"),
    C("C 타입");

    private final String text;

}
