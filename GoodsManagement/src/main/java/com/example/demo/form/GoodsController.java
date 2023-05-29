package com.example.demo.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.db.GoodsDao;

@Controller
public class GoodsController {
	private final GoodsDao goodsdao;
	@Autowired
	public GoodsController(GoodsDao goodsdao) {
		
		// TODO 自動生成されたコンストラクター・スタブ
		this.goodsdao=goodsdao;
	}
}
