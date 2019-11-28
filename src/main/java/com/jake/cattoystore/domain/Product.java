package com.jake.cattoystore.domain;

import java.text.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Product {

    @Getter
    private String name;

    @Getter
    private String maker;

    private Integer price;

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl != null ? imageUrl : "";
    }

    // second, I create function to avoid syntax error.
    public String getPriceWithComma() {
        // return null;

        // third, implement what really works.
        return NumberFormat.getInstance().format(price);
    }

    public void changeImageUrl(String newImageUrl) {
        this.imageUrl = newImageUrl;
    }
}
