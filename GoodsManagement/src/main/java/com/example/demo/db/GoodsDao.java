package com.example.demo.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsDao {
	public final int MAX_CONTENTS=10;
	private final JdbcTemplate db;
	private boolean maxFlag=false;
	public boolean getMaxFlag() {
		return maxFlag;
	}
	
	@Autowired
	public GoodsDao(JdbcTemplate db) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.db = db;
	}

	public void deleteDb(long id) {
		db.update("DELETE FROM goods WHERE id = ?", id);
	}

	//SELCT処理
	public List<Goods> searchDb() {

		//取得
		String sql = "SELECT * FROM goods;";

		//データベースから取り出したデータをresultDB1に入れる
		List<Map<String, Object>> result_db = db.queryForList(sql);
		List<Goods> goodslist = new ArrayList<Goods>();
		//1件ずつピックアップ
		for (Map<String, Object> result : result_db) {

			//データ1件分を1つのまとまりとしたGoods型の「goods」を生成
			Goods goods = new Goods();

			//id,name,stock,category,priceのデータをgoodsに移す
			goods.setId((int) result.get("id"));
			goods.setName((String) result.get("name"));
			goods.setStock((int) result.get("stock"));
			goods.setCategory((String) result.get("category"));
			goods.setPrice((int) result.get("price"));
			//移し替えたデータを持ったgoodsを、resultDB2に入れる
			goodslist.add(goods);
		}
		return goodslist;
	}

	public Goods searchDbOne(Long id) {
		//取得
		String sql = "SELECT * FROM goods WHERE id=" +id;

		//データベースから取り出したデータをresultDB1に入れる
		List<Map<String, Object>> result_db = db.queryForList(sql);
		//1件ずつピックアップ
		//データ1件分を1つのまとまりとしたEntForm型の「entformdb」を生成
		Goods goods = new Goods();

		//id、nameのデータをentformdbに移す
		goods.setId((int) result_db.get(0).get("id"));
		goods.setName((String) result_db.get(0).get("name"));
		goods.setStock((int) result_db.get(0).get("stock"));
		goods.setCategory((String) result_db.get(0).get("category"));
		goods.setPrice((int) result_db.get(0).get("price"));
		//移し替えたデータを持ったentformdbを、resultDB2に入れる
		return goods;
	}

	public void insertDb(Goods goods) {
		db.update("INSERT INTO goods (name,stock,category,price) VALUES(?,?,?,?)",
				goods.getName(),
				goods.getStock(),
				goods.getCategory(),
				goods.getPrice());
	}

	public void updateDb(Long id, Goods goods) {
		//コンソールに表示
		System.out.println("編集の実行");

		db.update("UPDATE goods SET name=?, stock=?, category=?, price=? WHERE id=?", 
				goods.getName(), 
				goods.getStock(), 
				goods.getCategory(), 
				goods.getPrice(), id);
	}
	
	public List<Goods> sortDb(String sort){
		//取得
				String sql = "SELECT * FROM goods ORDER BY "+sort;

				//データベースから取り出したデータをresultDB1に入れる
				List<Map<String, Object>> result_db = db.queryForList(sql);
				List<Goods> goodslist = new ArrayList<Goods>();
				//1件ずつピックアップ
				for (Map<String, Object> result : result_db) {

					//データ1件分を1つのまとまりとしたGoods型の「goods」を生成
					Goods goods = new Goods();

					//id,name,stock,category,priceのデータをgoodsに移す
					goods.setId((int) result.get("id"));
					goods.setName((String) result.get("name"));
					goods.setStock((int) result.get("stock"));
					goods.setCategory((String) result.get("category"));
					goods.setPrice((int) result.get("price"));
					//移し替えたデータを持ったgoodsを、resultDB2に入れる
					goodslist.add(goods);
				}
				return goodslist;
	}
	public List<Goods> sortpageDb(String sort,int min){
		//取得
				String sql = "SELECT * FROM goods ORDER BY "+sort+
						" LIMIT "+MAX_CONTENTS+ " OFFSET "+min;

				//データベースから取り出したデータをresultDB1に入れる
				List<Map<String, Object>> result_db = db.queryForList(sql);
				
				if(result_db.size()!=MAX_CONTENTS)maxFlag=true;
				else maxFlag=false;
				
				List<Goods> goodslist = new ArrayList<Goods>();
				//1件ずつピックアップ
				for (Map<String, Object> result : result_db) {

					//データ1件分を1つのまとまりとしたGoods型の「goods」を生成
					Goods goods = new Goods();

					//id,name,stock,category,priceのデータをgoodsに移す
					goods.setId((int) result.get("id"));
					goods.setName((String) result.get("name"));
					goods.setStock((int) result.get("stock"));
					goods.setCategory((String) result.get("category"));
					goods.setPrice((int) result.get("price"));
					//移し替えたデータを持ったgoodsを、resultDB2に入れる
					goodslist.add(goods);
				}
				return goodslist;
	}
}