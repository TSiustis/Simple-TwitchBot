package siustis.teodor.twitchbot;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import static java.nio.charset.StandardCharsets.*;

import org.jibble.pircbot.*;
public class TwitchBot extends PircBot {
	private static final Set<String> BLACKLIST = new HashSet<String>(Arrays.asList(
		     new String[] {""}
		));

	

	File log = new File("log.txt");
	Date date = new Date();


	

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
	SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");
	String time = timeFormat.format(date);
	String formattedDate = sdf.format(date);
	
	String channel,user;
	int i = 0;
	int idx = 0;
	public TwitchBot(String botName,String channel){
		this.setName(botName);
		this.setLogin(botName);
		this.channel = channel;
		this.isConnected();
		
	}
	@Override
	public void onMessage(String channel, String sender, String login, String hostname, String message){
		//current game
		if(message.equalsIgnoreCase("!game"))
			sendMessage(channel, "The current game is " + Main.currentGame);
		//times out people using blacklisted words
                if(BLACKLIST.contains(message.toLowerCase()))
			sendMessage(channel,"/timeout " + sender + "10");
		if(message.equalsIgnoreCase("!time"))
			sendMessage(channel,time);
                //logs chat lines
		try{
				    if(!log.exists())
				        log.createNewFile();
				    

				    FileWriter fileWriter = new FileWriter(log, true);

				    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				    bufferedWriter.write(formattedDate + " " + sender + ": " + message + System.lineSeparator());
				 
				    bufferedWriter.close();

				    System.out.println("Done");
				} catch(IOException e) {
				    System.out.println("COULD NOT LOG!!");
				    e.printStackTrace();
				}
		
		
		
	}
	@Override
	public void onConnect(){
		joinChannel(channel);
		super.onConnect();
	}
	@Override
	public void onJoin(String channel,String sender, String login,String hostname){
		System.out.println(login + "joined " + channel);
		super.onJoin(channel, sender, login, hostname);
		
		return;
	}
	
	@Override
    protected void onUserList(String channel, User[] users) {
        for (User user : users) {
            System.out.println(user);
        }
        super.onUserList(channel, users);
    }
    //sends message from an array of strings at random to chat
	public void sendRandom(String channel,String[] messages){
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
  		  @Override
  		  public void run() {
  			int idx = new Random().nextInt(messages.length);
  		   sendMessage(channel, messages[idx]);
  		    idx = new Random().nextInt(messages.length);
  		  }
  		}, 1*5*1000, 1*5*1000);

	
	

	}
		



