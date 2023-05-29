package com.example.demo.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.db.Goods;
import com.example.demo.db.GoodsDao;

public class GoodsController {

	private final GoodsDao goodsdao;

	@Autowired
	public GoodsController(GoodsDao goodsdao) {
		this.goodsdao = goodsdao;
	}

	//確認
	@RequestMapping("/complete")
	public String complete(Model model, Form form) {
		model.addAttribute("title", "完了ページ");
		Goods goods = new Goods();
		goods.setName(form.getName());
		goods.setStock(form.getStock());
		goods.setCategory(form.getCategory());
		goodsdao.insertDb(goods);
		return "goods/complete";
	}
}
