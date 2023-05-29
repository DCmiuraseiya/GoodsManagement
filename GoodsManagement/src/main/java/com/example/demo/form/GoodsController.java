package com.example.demo.form;

import com.example.demo.db.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.db.GoodsDao;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GoodsController {
	private final GoodsDao goodsdao;
	@Autowired
	public GoodsController(GoodsDao goodsdao) {
		this.goodsdao=goodsdao;
	}
    @RequestMapping("/index")
    public String top(Model model, Form form) {
        model.addAttribute("title", "トップページ");
        List<Goods> list = goodsdao.searchDb();
        model.addAttribute("dbList", list);
        return "index";
    }
}
