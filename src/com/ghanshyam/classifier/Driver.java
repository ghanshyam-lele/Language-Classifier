package com.ghanshyam.classifier;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class Driver 
{
	public static void main(String[] args) throws IOException 
	{
		Category englishCategory =new Category("English", 0, (double) 0.333);
		Category frenchCategory =new Category("French", 0, (double) 0.333);
		Category germanCategory =new Category("German", 0, (double) 0.333);
		
		HashMap<Category, URL> map= new HashMap<Category, URL>();
		//map.put(englishCategory, Driver.class.getResource("/datasets/training.language.en.txt"));
		//map.put(frenchCategory, Driver.class.getResource("/datasets/training.language.fr.txt"));
		//map.put(germanCategory, Driver.class.getResource("/datasets/training.language.de.txt"));
		map.put(englishCategory, Driver.class.getResource("/datasets/eng_news_2015_100K-sentences.txt"));
		map.put(frenchCategory, Driver.class.getResource("/datasets/fra_news_2010_100K-sentences.txt"));
		map.put(germanCategory, Driver.class.getResource("/datasets/deu_news_2015_100K-sentences.txt"));
		NaiveBayesClassifier bayesClassifier=new NaiveBayesClassifier();
		
		
		
		try 
		{
			System.out.println("Training started...");
			long start=System.currentTimeMillis();
			bayesClassifier.train(map);
			long end=System.currentTimeMillis();
			System.out.println("trainng ended time taken= "+(end-start));
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		String exampleEn = "goodbye";
        Result outputEn = bayesClassifier.predict(exampleEn.toLowerCase()).get(0);
        System.out.format("The sentense \"%s\" was classified as \"%s\".%n", exampleEn, outputEn.getCategory().getCategoryName());
        
        String exampleFr = "Au Revoir";
        Result outputFr = bayesClassifier.predict(exampleFr.toLowerCase()).get(0);
        System.out.format("The sentense \"%s\" was classified as \"%s\".%n", exampleFr, outputFr.getCategory().getCategoryName());
        
        String exampleDe = "Auf Wiedersehen";
        Result outputDe = bayesClassifier.predict(exampleDe.toLowerCase()).get(0);
        System.out.format("The sentense \"%s\" was classified as \"%s\".%n", exampleDe, outputDe.getCategory().getCategoryName());*/
		
		Scanner scanner =new Scanner(System.in);
	
		do
		{
			System.out.println("enter string");
			System.out.println("The language is "+bayesClassifier.predict(scanner.nextLine().toLowerCase()).get(0).getCategory().getCategoryName());
			System.out.println("Continue? (y/n)");
		}while(!scanner.nextLine().equals("n"));
		
		scanner.close();
		
	}
}
