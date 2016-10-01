package siustis.teodor.twitchbot;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.jibble.pircbot.*;
public class Main {
	static Timer timer = new Timer();
	public static String currentGame = "Hearthstone";
	static String channel = "#channel"; // channel name must start with '#'
	static String[] messages = {""}; // your list of messages
        static int idx = new Random().nextInt(messages.length);
	
	
    public static void main(String []args)throws Exception{
    	
    	//list of users you want to connect
    	String[] users = {""};
    	
        TwitchBot[] array =new TwitchBot[users.length];
    	array[0] = new TwitchBot(user,channel);
    	array[0].setVerbose(true);
    	array[0].connect("irc.twitch.tv",6667,oauth);
        //sends random messages to chat
       for(TwitchBot bots :array){
    	   bots.sendRandom(channel, messages);
       }
  		
    
    }
    
}
