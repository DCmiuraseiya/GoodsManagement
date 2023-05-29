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
	
	/*
	public List<EntForm> searchDb() {
		String sql = "SELECT * FROM sample";

		//データベースから取り出したデータをresultDB1に入れる
		List<Map<String, Object>> resultDb1 = db.queryForList(sql);

		//画面に表示しやすい形のList(resultDB2)を用意
		List<EntForm> resultDb2 = new ArrayList<EntForm>();

		//1件ずつピックアップ
		for (Map<String, Object> result1 : resultDb1) {

			//データ1件分を1つのまとまりとしたEntForm型の「entformdb」を生成
			EntForm entformdb = new EntForm();

			//id、nameのデータをentformdbに移す
			entformdb.setId((int) result1.get("id"));
			entformdb.setName((String) result1.get("name"));
			entformdb.setEmail((String) result1.get("email"));
			entformdb.setNickname((String) result1.get("nickname"));
			entformdb.setMessage((String) result1.get("message"));
			//移し替えたデータを持ったentformdbを、resultDB2に入れる
			resultDb2.add(entformdb);
		}
		*/
}
