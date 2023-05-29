package com.example.demo.db;

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

	public void insertDb(Goods goods) {
		db.update("INSERT INTO goods (name,stock,category,price) VALUES(?,?,?)", 
				goods.getName(),
				goods.getStock(), 
				goods.getCategoly(),
				goods.getPrice());
	}
	
	public void updateDb(Long id, Goods goods) {
		//コンソールに表示
		System.out.println("編集の実行");
		db.update("UPDATE goods SET name=?, stock=?, categoly=?, price=? WHERE id=?", goods.getName(), goods.getStock(), goods.getCategoly(), goods.getPrice(), id);
	}
}
