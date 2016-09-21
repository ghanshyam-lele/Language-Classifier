package com.ghanshyam.classifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NaiveBayesClassifier 
{
	HashMap<Category, HashMap<String, Integer>> map;
	
	int totalCount;
	public NaiveBayesClassifier() 
	{
		// TODO Auto-generated constructor stub
		map=new HashMap<Category,HashMap<String,Integer>>();
		totalCount=0;
	}
	public void train(HashMap<Category,URL > trainingSet) throws IOException
	
	{
		Category[] array = new Category[trainingSet.keySet().size()] ;
		trainingSet.keySet().toArray(array);
		HashMap<String, Integer> tempMap;
		for(Category category:array)
		{
			tempMap=new HashMap<String,Integer>();
			int wordcount=0;
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(trainingSet.get(category).openStream(), Charset.forName("UTF-8")));
			Pattern pattern=Pattern.compile("\\w+");
			for(String temp="";temp!=null;temp=bufferedReader.readLine())
			{
				//System.out.print(" "+temp+" ");
				temp=temp.toLowerCase();
				Matcher matcher=pattern.matcher(temp.toLowerCase());
				while(matcher.find())
				{
					tempMap.put(temp=matcher.group(), tempMap.containsKey(temp)? tempMap.get(temp)+1:1);
					wordcount++;
				}
			}
			totalCount+=wordcount;
			category.setWordCount(wordcount);
			map.put(category, tempMap);
			bufferedReader.close();
		}
		//HashMap<String, Integer> map= new HashMap<String,Integer>();
		
		
	}
	class Result
	{
		Category category;
		Double probablity;
		public Result(Category category, Double probablity) {
			super();
			this.category = category;
			this.probablity = probablity;
		}
		public Category getCategory() {
			return category;
		}
		public void setCategory(Category category) {
			this.category = category;
		}
		public Double getProbablity() {
			return probablity;
		}
		public void setProbablity(Double probablity) {
			this.probablity = probablity;
		}
		
		
		
	}
	public ArrayList<Result> predict(String input) 
	{
		
		Category[] array = new Category[map.keySet().size()] ;
		map.keySet().toArray(array);
		String [] inputArray=input.split("\\s+");
		//Result [] resultArray= new Result [array.length];
		ArrayList<Result> resultArray=new ArrayList<Result>();
		
		for(Category category:array)
		{
			int sum=0;
			for(String string:inputArray)
			{
				//System.out.println(string);
				sum+=map.get(category).containsKey(string)? map.get(category).get(string): 0;
			}
			//sum*=10;		
			System.out.println("sum= "+ sum);
			//System.out.println("probab= "+category.getProbability());
			
			//double num=sum/(double)category.getWordCount();
			//System.out.println("num "+num);
			Double numerator=(double) ((double)(sum/(double)category.getWordCount()) * category.getProbability());
			//Double denominator= (double) ((double)sum/(double)totalCount);
			System.out.println("numerator= "+numerator);
			//Double probability=numerator/denominator;
			//System.out.println("probab"+" "+ probability);
			resultArray.add(new Result(category,numerator));
			
		}
		Collections.sort(resultArray, new Comparator<Result>() {

			@Override
			public int compare(Result o1, Result o2) {
				// TODO Auto-generated method stub
				
				return o2.getProbablity().compareTo(o1.getProbablity());
			}
		});
		return resultArray;
		
	}
}
