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
	private String sort="name";
	private String sortName="";
	private Boolean sortOrder=false;
	@Autowired
	public GoodsController(GoodsDao goodsdao) {
		sort="id ASC";
		this.goodsdao=goodsdao;
	}
    @RequestMapping("/index")
    public String top(Model model, Form form) {
        model.addAttribute("title", "商品管理ページ");
        List<Goods> list = goodsdao.sortDb(sort);
        model.addAttribute("dbList", list);
        return "index";
    }
    
    @RequestMapping("/sort/{name}")
    public String sortIndex(@PathVariable String name) {
    	if(sortName.equals(name))sortOrder=!sortOrder;
    	else sortOrder=true;
    	sortName=name;
    	sort=sortName+(sortOrder?" ASC":" DESC");
    	
    	return "redirect:/index";
    }
	//確認
	@RequestMapping("/complete")
	public String complete(Model model, Form form) {
		model.addAttribute("title", "登録完了ページ");
		Goods goods = new Goods();
		goods.setName(form.getName());
		goods.setStock(form.getStock());
		goods.setCategory(form.getCategory());
		goods.setPrice(form.getPrice());
		goodsdao.insertDb(goods);
		return "goods/complete";
	}
	

	//確認画面
	@RequestMapping("/confirm")
	 public String confirm(@Validated Form form, BindingResult result, Model model) {
		
		//失敗確認
		if (result.hasErrors()) {
			model.addAttribute("title", "入力ページ");
			//データベースから取得
			List<Goods> list = goodsdao.searchDb();
			
			//データベースに格納
			model.addAttribute("dbList", list);
			return "goods/input";
		}
		model.addAttribute("title", "confirm");
		return "goods/confirm";
	 }

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id,Model model,Form form) {
		Goods goods=goodsdao.searchDbOne(id);
		model.addAttribute("goods",goods);
		return "/goods/edit";
	}
	
	@RequestMapping("/edit/{id}/exe")
    public String editExe(@PathVariable Long id, @Validated Form form, BindingResult result, Model model) {
        Goods goods = new Goods();
        goods.setStock(form.getStock());
        goods.setName(form.getName());
        goods.setCategory(form.getCategory());
        goods.setPrice(form.getPrice());
        goodsdao.updateDb(id, goods);
        return "redirect:/index";

	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		goodsdao.deleteDb(id);
		return "redirect:/index";
	}
}

