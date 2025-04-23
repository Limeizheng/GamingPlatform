package com.cloud.shopping.item.pojo;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "tb_like")
public class Like {
    private String likeId;
    private String img;
    private String skuid;
    private String title;
    private String price;
}
