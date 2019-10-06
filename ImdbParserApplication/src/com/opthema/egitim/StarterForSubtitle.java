package com.opthema.egitim;


import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class StarterForSubtitle {
	
	public static StringBuffer bf ;
	public StringBuffer editLink(String id) throws ClientProtocolException, IOException {
		
	
		DownloaderViaApache dwn = new DownloaderViaApache();
		 String url = "https://turkcealtyazi.org/mov/" + id + "/";
		StringBuffer resultForSubs = new StringBuffer();
		bf = dwn.downloadData(url);

		
		String subtitleTextStart = "<div class=\"alisim\"><h4>";
		String endTextForSubtitles = "ta-container altsondiv";

		int subtitleTextStartIndex = resultForSubs.indexOf(subtitleTextStart, 0);
		int endTextForSubtitlesIndex = resultForSubs.indexOf(endTextForSubtitles, subtitleTextStartIndex);
		String str = resultForSubs.substring((subtitleTextStartIndex + 1), endTextForSubtitlesIndex + 1);
		System.out.println("                      *****************************             "+str);
	
		return bf;

	}
	
	
	
	

}
