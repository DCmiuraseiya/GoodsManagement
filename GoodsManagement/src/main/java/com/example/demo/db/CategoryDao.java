package com.example.demo.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao {
	private final JdbcTemplate db;
	
	@Autowired
	public CategoryDao(JdbcTemplate db) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.db = db;
	}
	
	//SELCT処理
		public List<Category> searchDb() {

			//取得
			String sql = "SELECT * FROM category;";

			//データベースから取り出したデータをresultDB1に入れる
			List<Map<String, Object>> result_db = db.queryForList(sql);
			List<Category> categorylist = new ArrayList<Category>();
			//1件ずつピックアップ
			for (Map<String, Object> result : result_db) {

				//データ1件分を1つのまとまりとしたCategory型の「Category」を生成
				Category category = new Category();

				//id,nameのデータをcategoryに移す
				category.setId((int) result.get("id"));
				category.setName((String) result.get("name"));
				//移し替えたデータを持ったCategoryを、resultDB2に入れる
				categorylist.add(category);
			}
			return categorylist;
		}

		public Category searchDbOne(Long id) {
			//取得
			String sql = "SELECT * FROM category WHERE id=" +id;

			//データベースから取り出したデータをresultDB1に入れる
			List<Map<String, Object>> result_db = db.queryForList(sql);
			//1件ずつピックアップ
			//データ1件分を1つのまとまりとしたEntForm型の「entformdb」を生成
			Category category = new Category();

			//id、nameのデータをentformdbに移す
			category.setId((int) result_db.get(0).get("id"));
			category.setName((String) result_db.get(0).get("name"));
	
			//移し替えたデータを持ったentformdbを、resultDB2に入れる
			return category;
		}
		
		public void insertDb(Category category) {
			db.update("INSERT INTO category (name) VALUES(?)",category.getName());
		}
		
		public void deleteDb(long id) {
			db.update("DELETE FROM category WHERE id = ?", id);
		}
		
		public void updateDb(Long id, Category category) {
			//コンソールに表示
			System.out.println("編集の実行");

			db.update("UPDATE category SET name=?, stock=?, category=?, price=? WHERE id=?", category.getName(),  id);
		}
		
		
}
