--***商品テーブル***--
--param
--name:商品名
--stock:在庫数
--category:カテゴリ名
--category_id:categoryとリンクするkey
--price:価格
CREATE TABLE goods(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	stock INT NOT NULL,
	category VARCHAR(100) NOT NULL,
	--category_id INT NOT NULL,
	price INT NOT NULL,
	PRIMARY KEY(id)
	--FOREIGN KEY (category_id) REFERENCES category(id)
);
--商品のカテゴリーテーブル
--param
--name:カテゴリーの名前
CREATE TABLE category(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(45) NOT NULL,
  	PRIMARY KEY (id)
 );

