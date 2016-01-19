

class Twittener {
    
    public static void main(String [] args) throws InterruptedException {
        
        TweetCrawlService crawlService = new TweetCrawlService();
        crawlService.startAsync();
//        Thread.sleep(5000);
//        crawlService.stopAsync();
    }
}
