
import com.twittener.Manager.TwitterManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mucheng
 */
class Twittener {
    
    public static void main(String [] args) {
        
        // Schedule CRON Jobs here
        TwitterManager twitterMgr =  new TwitterManager();
        twitterMgr.getAllTweetsByTopic();
    }
}
