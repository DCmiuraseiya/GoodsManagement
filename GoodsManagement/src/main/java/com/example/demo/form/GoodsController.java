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

	@Autowired
	public GoodsController(GoodsDao goodsdao) {

		// TODO 自動生成されたコンストラクター・スタブ
		this.goodsdao = goodsdao;
	}
	

	//確認画面
	@RequestMapping("/confirm")
	 public String confirm(@Validated Form form, BindingResult result, Model model) {
		
		//失敗確認
		if (result.hasErrors()) {
			model.addAttribute("title", "入力ページ");
			//データベースから取得
			List<Goods> list = goodsdao.serchDB();
			//TODO:masterブランチでは下記の関数を使用する
			//List<Goods> list = goodsdao.serchDb();
			//データベースに格納
			model.addAttribute("dbList", list);
			return "goods/input";
		}
		model.addAttribute("title", "confirm");
		return "goods/confirm";
	 }

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id,Model model) {
		Goods goods=goodsdao.serchDbOne(id);
		model.addAttribute("goods",goods);
		return "/goods/edit";
	}
	
	@RequestMapping("/edit/{id}/exe")
    public String editExe(@PathVariable Long id, @Validated Form form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("edit_title", "編集画面");
            model.addAttribute("carender", "カレンダーを入力してください");
            return "diary/edit";
        }
        Goods goods = new Goods();
        goods.setStock(form.getStock());
        goods.setName(form.getName());
        goods.setCategory(form.getCategory());
        goods.setPrice(form.getPrice());
        goodsdao.updateDb(id, goods);
        return "redirect:/index";
	}

}

