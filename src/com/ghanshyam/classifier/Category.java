package com.ghanshyam.classifier;

public class Category 
{
	String categoryName;
	int wordCount;
	Double probability;
	
	
	public Category(String categoryName, int wordCount, Double probability) {
		super();
		this.categoryName = categoryName;
		this.wordCount = wordCount;
		this.probability = probability;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getWordCount() {
		return wordCount;
	}
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}
	public Double getProbability() {
		return probability;
	}
	public void setProbability(Double probability) {
		this.probability = probability;
	}
	
	
}
