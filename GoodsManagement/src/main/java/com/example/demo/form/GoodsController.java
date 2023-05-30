package com.example.demo.form;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.db.Goods;
import com.example.demo.db.GoodsDao;

@Controller

public class GoodsController {
	private final GoodsDao goodsdao;
	private String sort = "name";
	private String sortName = "";
	private Boolean sortOrder = false;
	private int min;
	private String page;

	@Autowired
	public GoodsController(GoodsDao goodsdao) {
		sort = "id ASC";
		this.goodsdao = goodsdao;
		min = 0;
		page = "first";
	}

	@RequestMapping("/index")
	public String top(Model model, Form form) {
		model.addAttribute("title", "商品管理ページ");
		List<Goods> list = goodsdao.sortpageDb(sort, min);
		
		if(goodsdao.sortpageDb(sort, min).size()<goodsdao.MAX_CONTENTS) page = "end";
		else if (min == 0) page="first";
		else page="";
		
		model.addAttribute("dbList", list);
		model.addAttribute("page", page);
		return "index";
	}

	@RequestMapping("/page/{change}")
	public String pageChange(@PathVariable String change) {
		if (goodsdao.getMaxFlag() == false &&
			Integer.parseInt(change) == goodsdao.MAX_CONTENTS) 
				min += goodsdao.MAX_CONTENTS;
		
		if (Integer.parseInt(change) == -goodsdao.MAX_CONTENTS) {
			min -= goodsdao.MAX_CONTENTS;
		}
		
		if (min < 0) min = 0;
		
		return "redirect:/index";
	}

	@RequestMapping("/sort/{name}")
	public String sortIndex(@PathVariable String name) {
		if (sortName.equals(name))
			sortOrder = !sortOrder;
		else
			sortOrder = true;
		sortName = name;
		sort = sortName + (sortOrder ? " ASC" : " DESC");

		return "redirect:/index";
	}

	//確認
	@RequestMapping("/complete")
	public String complete(Model model, Form form) {
		model.addAttribute("title", "登録完了ページ");
		Goods goods = new Goods();
		goods.setName(form.getName());
		goods.setStock(Integer.parseInt(form.getStock()));
		goods.setCategory(form.getCategory());
		goods.setPrice(Integer.parseInt(form.getPrice()));
		goodsdao.insertDb(goods);
		return "goods/complete";
	}

	//確認画面
	@RequestMapping("/confirm")
	public String confirm(@Validated Form form, BindingResult result, Model model) {

		//失敗確認
		if (result.hasErrors()) {
			model.addAttribute("title", "商品管理ページ");
			List<Goods> list = goodsdao.sortpageDb(sort, min);
			
			if(goodsdao.sortpageDb(sort, min).size()<goodsdao.MAX_CONTENTS) page = "end";
			else if (min == 0) page="first";
			else page="";
			
			model.addAttribute("dbList", list);
			model.addAttribute("page", page);
			return "/index";
		}
		model.addAttribute("title", "confirm");
		return "goods/confirm";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model, @Validated Form form) {
		Goods goods = goodsdao.searchDbOne(id);
		model.addAttribute("goods", goods);
		return "/goods/edit";
	}

	@RequestMapping("/edit/{id}/exe")
	public String editExe(@PathVariable Long id, @Validated Form form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			Goods goods = goodsdao.searchDbOne(id);
			model.addAttribute("goods", goods);
			return "/goods/edit";
		}
		Goods goods = new Goods();
		goods.setStock(Integer.parseInt(form.getStock()));
		goods.setName(form.getName());
		goods.setCategory(form.getCategory());
		goods.setPrice(Integer.parseInt(form.getPrice()));
		goodsdao.updateDb(id, goods);
		return "redirect:/index";

	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		goodsdao.deleteDb(id);
		return "redirect:/index";
	}
}