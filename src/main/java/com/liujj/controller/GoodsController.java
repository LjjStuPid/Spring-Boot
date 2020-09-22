package com.liujj.controller;

import com.liujj.dao.GoodsDAO;
import com.liujj.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("goods")
public class GoodsController {

    @Autowired
    private GoodsDAO goodsDAO;

    @GetMapping("save")
    public String save() {
        for (int i = 0; i < 40; i++) {
            Goods goods = new Goods();
            goods.setGoodsId((long) i);
            goods.setGoodsName("shangpin" + i);
            goods.setGoodsType((long) (i + 1));
            goodsDAO.insertSelective(goods);
        }
        return "success";
    }
}
