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
	public void deleteDb(long id){
		db.update("DELETE FROM goods WHERE id = ?" , id);
	}
}
