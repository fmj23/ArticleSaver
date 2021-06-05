// https://beginnersbook.com/2013/12/java-arraylist/

package com.group15.articlesaver;

import java.util.ArrayList;
import java.util.Random;

public class RandomArticle {
    public static String RandomArticleName() {
        String nextArticle = "";

        ArrayList<String> possibleArticles = new ArrayList<String>();

        possibleArticles.add("Shark");
        possibleArticles.add("United Kingdom");
        possibleArticles.add("Banana");
        possibleArticles.add("Cherry");
        possibleArticles.add("Greece");

        int sizeOfList = possibleArticles.size();
        int articleIndex = 0;

        //Log.i("MyApp", "Size of list = " + possibleArticles.size());

        Random rand = new Random();
        articleIndex = rand.nextInt(sizeOfList);

        //Log.i("MyApp", "Chosen article = " + possibleArticles.get(articleIndex));

        nextArticle = possibleArticles.get(articleIndex);

        return nextArticle;
    }
}
