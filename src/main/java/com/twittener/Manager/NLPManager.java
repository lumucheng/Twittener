/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twittener.Manager;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

/**
 *
 * @author Mucheng
 */
public class NLPManager {
    
    static StanfordCoreNLP pipeline;
    
     public static void init() {
        pipeline = new StanfordCoreNLP("nlp.properties");
    }

    public static int findSentiment(String tweet) {

        int mainSentiment = 0;
        
        if (tweet != null && tweet.length() > 0) {
            int longest = 0;
            
            Annotation annotation = pipeline.process(tweet);
            
            for (CoreMap sentence : annotation
                    .get(CoreAnnotations.SentencesAnnotation.class)) {
                
                Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                
                String partText = sentence.toString();
                
                if (partText.length() > longest) {
                    mainSentiment = sentiment;
                    longest = partText.length();
                }
            }
        }
        
        // 0: "Very Negative"; 1: "Negative"; 2: "Neutral"; 3: "Positive"; 4: "Very Positive"
        return mainSentiment;
    }
}
