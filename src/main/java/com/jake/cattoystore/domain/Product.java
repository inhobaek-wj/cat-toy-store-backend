package com.jake.cattoystore.domain;

import java.text.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
