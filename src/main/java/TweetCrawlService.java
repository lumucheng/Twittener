
import com.google.common.util.concurrent.AbstractScheduledService;
import com.twittener.Manager.TwitterManager;
import java.util.concurrent.TimeUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mucheng
 */
public class TweetCrawlService extends AbstractScheduledService {

    @Override
    protected void runOneIteration() throws Exception {
        
        TwitterManager twitterMgr =  new TwitterManager();
        twitterMgr.getAllTweetsByTopic();
    }

    @Override
    protected Scheduler scheduler() {
        return Scheduler.newFixedRateSchedule(0, 8, TimeUnit.HOURS);
    }
    
    @Override
   protected void startUp()
   {
       System.out.println("\nStarting Scheduler..\n");
   }


   @Override
   protected void shutDown()
   {
       System.out.println("\nShutting Scheduler..\n");
   }

}
