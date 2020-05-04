package uk.ac.man.cs.eventlite.entities;

public class Tweet {
	
	public String tweet;
	public String date;
	public String url;

	
	public Tweet(){}
	
	public void setTweet(String tweet)
	{
		this.tweet = tweet;
	}
	
	public void setURL(String url)
	{
		this.url = url;
	}
	
	public void setDate(String date)
	{
		this.date = date;
	}
	
	public String getTweet()
	{
		return this.tweet;
	}
	
	public String getURL()
	{
		return this.url;
	}
	
	public String getDate()
	{
		return this.date;
	}
	
}
