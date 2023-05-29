package com.example.demo.db;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsDao {

	private final JdbcTemplate db;
	
	@Autowired
	public GoodsDao(JdbcTemplate db) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.db=db;
	}
  
  //SELCT処理
	public List<Goods> serchDB(){
		//取得
		String sql = "SELECT * FROM goods;";
		
		//データベースから取り出したデータをresultDB1に入れる
		List<Map<String, Object>> result_db = db.queryForList(sql);
		List<Goods> goodslist = new ArrayList<Goods>();
		//1件ずつピックアップ
		for (Map<String, Object> result : result_db) {

			//データ1件分を1つのまとまりとしたEntForm型の「entformdb」を生成
			Goods goods = new Goods();

			//id、nameのデータをentformdbに移す
			goods.setId((int)result.get("id"));
			goods.setName((String) result.get("name"));
			goods.setStock((int) result.get("stock"));
			goods.setCategory((String) result.get("category"));
			goods.setPrice((int) result.get("price"));
			//移し替えたデータを持ったentformdbを、resultDB2に入れる
			goodslist.add(goods);
		}
		return goodslist;
	}

	public void insertDb(Goods goods) {
		db.update("INSERT INTO goods (name,stock,category,price) VALUES(?,?,?)", 
				goods.getName(),
				goods.getStock(), 
				goods.getCategory(),
				goods.getPrice());
	}
	
	public void updateDb(Long id, Goods goods) {
		//コンソールに表示
		System.out.println("編集の実行");
		db.update("UPDATE goods SET name=?, stock=?, categoly=?, price=? WHERE id=?", goods.getName(), goods.getStock(), goods.getCategory(), goods.getPrice(), id);
	}
}
