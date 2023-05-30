package com.example.demo.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Form {
    private int id;
    @Size(min=1, max=20, message="1～20文字以内にしてください")
    private String name;
    
    @Min(1)
    @Pattern(regexp = "^[0-9]+$", message = "数値を入力してください")
    private String stock ;
    

	@Size(min=1, max=20, message="1～20文字以内にしてください")
    private String category;
    
    @Min(1)
    @Pattern(regexp = "^[0-9]+$", message = "数値を入力してください")
    private String price;
    
    public void setStock(String stock) {
		this.stock = stock;
	}

	public void setPrice(String price) {
		this.price = price;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getStock() {
		return stock;
	}

	public String getPrice() {
		return price;
	}

	public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
