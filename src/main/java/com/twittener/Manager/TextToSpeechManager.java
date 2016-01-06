/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twittener.Manager;

import com.memetix.mst.language.SpokenDialect;
import com.memetix.mst.speak.Speak;
import com.twittener.DAO.TweetDAO;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author Lu
 */
public class TextToSpeechManager {

    private static final String CLIENT_ID = "Twitter_Radio";
    private static final String CLIENT_SECRET = "MNezcfeVh7hz8HVOaeAKiIQeQATDmlpv5IlmFflyy6k=";
    private String workingDir = "";
    
    public TextToSpeechManager() {
        Speak.setClientId(CLIENT_ID);
        Speak.setClientSecret(CLIENT_SECRET);
        workingDir = System.getProperty("user.dir");
    }

    public void convert(String text, Long tweetId) {
        try {
            
            String fileName = tweetId + ".wav";
            String dirName = workingDir + File.separator + "tweet_files"  + File.separator;
            //String dirName = "/etc/tweet_files/";
            
            String wavUrl = Speak.execute(text, SpokenDialect.ENGLISH_UNITED_STATES);
            final URL waveUrl = new URL(wavUrl);
            
            final HttpURLConnection urlConnection = (HttpURLConnection) waveUrl.openConnection();
            
            InputStream bufferedIn = new BufferedInputStream(urlConnection.getInputStream());
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
            
            new File(dirName).mkdirs();
            
            File file = new File(dirName + File.separator + fileName);
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, file);
            audioInputStream.close();
        } 
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(TweetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
