package com.twittener.Manager;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import java.util.List;

public class NLPManager {
    
    static StanfordCoreNLP pipeline;
    
    public static void init() {
        pipeline = new StanfordCoreNLP("nlp.properties");
    }

    public static int getSentiment(String tweet) {

        int mainSentiment = 0;
        
        if (tweet != null && tweet.length() > 0) {
            int longest = 0;
            
            Annotation annotation = pipeline.process(tweet);
            
            for (CoreMap sentence : annotation
                    .get(CoreAnnotations.SentencesAnnotation.class)) {
                
                Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                // tree.pennPrint();
                
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
    
    public static void filterTweet(String tweet, String keyword) {
        Annotation annotation = pipeline.process(tweet);
        
        List<CoreMap> sentences = annotation.get(SentencesAnnotation.class);
        
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
                
                // NOTE: maybe can check if sentence contains certain keyword & etc.
                
                // text of the token
                String word = token.get(TextAnnotation.class);

                // POS tag of the token
                String pos = token.get(PartOfSpeechAnnotation.class);

                // NER label of the token
                String ne = token.get(NamedEntityTagAnnotation.class);
                
                System.out.println("Word " + word);
                System.out.println("POS " + pos);
                System.out.println("NER " + ne);
                System.out.println("-----------");
            }
        }
    }
}
