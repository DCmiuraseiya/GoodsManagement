package com.example.demo.form;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.db.Category;
import com.example.demo.db.CategoryDao;
import com.example.demo.db.Goods;
import com.example.demo.db.GoodsDao;

@Controller

public class GoodsController {
	private final GoodsDao goodsdao;
	private final CategoryDao categorydao;
	private String sort = "name";
	private String sortName = "";
	private Boolean sortOrder = false;

	@Autowired
	public GoodsController(GoodsDao goodsdao, CategoryDao categorydao) {
		sort = "id ASC";
		this.goodsdao = goodsdao;
		this.categorydao = categorydao;
	}

	@RequestMapping("/index")
	public String top(Model model, Form form) {
		model.addAttribute("title", "商品管理ページ");
		List<Goods> list = goodsdao.sortDb(sort);
		model.addAttribute("dbList", list);
		List<Category> categorylist = categorydao.searchDb();
		model.addAttribute("categoryList", categorylist);
		//プルダウンの初期値を設定する場合は指定
		//model.addAttribute("selectedValue", "01");
		return "index";
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
		//goodsテーブルに今回選択したものをINSERT
		Goods goods = new Goods();
		goods.setName(form.getName());
		goods.setStock(Integer.parseInt(form.getStock()));
		goods.setCategory(form.getCategory());
		goods.setPrice(Integer.parseInt(form.getPrice()));
		goodsdao.insertDb(goods);
		
		//カテゴリテーブルに今回選択したカテゴリがあるかチェックして無い場合INSERT
		if(categorydao.searchDbOne(form.getCategory()) == null) {
			Category c = new Category();
			c.setName(form.getCategory());
			categorydao.insertDb(c);
		}
		
		return "goods/complete";
	}

	//確認画面
	@RequestMapping("/confirm")
	public String confirm(@Validated Form form, BindingResult result, Model model) {

		//失敗確認
		if (result.hasErrors()) {
			model.addAttribute("title", "入力ページ");
			//商品データベースから取得
			List<Goods> list = goodsdao.searchDb();
			//カテゴリーデータベースから取得
			List<Category> categorylist = categorydao.searchDb();
			//商品データベースに格納
			model.addAttribute("dbList", list);
			//カテゴリデータベースに格納
			model.addAttribute("categoryList", categorylist);
			return "index";
		}
		model.addAttribute("title", "confirm");
		return "goods/confirm";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model, Form form) {
		Goods goods = goodsdao.searchDbOne(id);
		model.addAttribute("goods", goods);
		return "/goods/edit";
	}

	@RequestMapping("/edit/{id}/exe")
	public String editExe(@PathVariable Long id, @Validated Form form, BindingResult result, Model model) {
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

	//////////////////////////////////////////////////////////////////////////
	//							カテゴリ処理								//
	//////////////////////////////////////////////////////////////////////////
	
	//確認
	@RequestMapping("/complete/category")
	public String completecategory(Model model, Form form) {
		model.addAttribute("title", "登録完了ページ");
		Category category = new Category();
		category.setName(form.getName());
		categorydao.insertDb(category);
		return "category/complete";
	}

	//確認画面
	@RequestMapping("/confirm/category")
	public String confirmcategory(@Validated Form form, BindingResult result, Model model) {

		//失敗確認
		if (result.hasErrors()) {
			model.addAttribute("title", "入力ページ");
			//データベースから取得
			List<Category> list = categorydao.searchDb();

			//データベースに格納
			model.addAttribute("dbList", list);

			return "index";
		}
		model.addAttribute("title", "confirm");
		return "category/confirm";
	}
	
	//編集処理
	@RequestMapping("/edit/category/{id}")
	public String editcategory(@PathVariable Long id, Model model, Form form) {
		Category category = categorydao.searchDbOne(id);
		model.addAttribute("category", category);
		return "category/edit";
	}
	
	//更新処理
	@RequestMapping("/edit/category/{id}/exe")
	public String editcategoryExe(@PathVariable Long id, @Validated Form form, BindingResult result, Model model) {
		Category category = new Category();
		category.setName(form.getName());
		categorydao.updateDb(id, category);
		return "redirect:/index";

	}
	
	//削除処理
	@RequestMapping("/delete/category/{id}")
	public String deletecategory(@PathVariable Long id) {
		categorydao.deleteDb(id);
		return "redirect:/index";
	}
	
	//編集処理
		@RequestMapping("test")
		public String editcategory( Model model, Form form) {
			model.addAttribute("title", "商品管理ページ");
			List<Goods> list = goodsdao.sortDb(sort);
			model.addAttribute("dbList", list);
			List<Category> categorylist = categorydao.searchDb();
			model.addAttribute("categoryList", categorylist);
			//TODO:カテゴリテーブルからの値を入れてselectに挿入
			return "category/test";
		}
	
}
