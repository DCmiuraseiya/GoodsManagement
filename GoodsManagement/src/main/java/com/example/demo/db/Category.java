package com.example.demo.db;

//カテゴリー
public class Category {
	//主キー
    private int id;
    //カテゴリー名
    private String name;
    

    public int getId() {
		return id;
	}

	public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


}
