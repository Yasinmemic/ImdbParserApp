package com.opthema.egitim;


import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class StarterForAltyazi {
	public static String ozet;
	 static String link;
	

	 
	public String start(String titleId) throws ClientProtocolException, IOException  {
	
		String startString = "title/tt";
		String endString ="/?ref_";
		
		String linkForAltyazi = titleId;
		System.out.println(linkForAltyazi);
		int startIndex = linkForAltyazi.indexOf(startString,0) + startString.length();
		int endIndex = linkForAltyazi.indexOf(endString,startIndex);
		
		String id = linkForAltyazi.substring(startIndex,endIndex);
		
		//System.out.println(id);
		
		String url = "https://turkcealtyazi.org/mov/"+id+"/";
		
		System.out.println(url);
		DownloaderViaApache dwn = new DownloaderViaApache();
		StringBuffer result = dwn.downloadData(url);

		//System.out.println(result);
		String ozetStart = "<div class=\"ozet-goster\" itemprop=\"description\">";
		String ozetEnd = "<a";
		int ozetStartIndex = result.indexOf(ozetStart,0)+ozetStart.length();
		int ozetEndIndex = result.indexOf(ozetEnd,ozetStartIndex)-14;
		
		String ozet = result.substring(ozetStartIndex, ozetEndIndex);
		if(ozet.indexOf("-equiv=\"content-type\" content=\"text/html;") != -1) {
			ozet=" Ozet bulunamadi! ";
		} 
		StarterForAltyazi.ozet = ozet;

		String startSubtitle = "<a itemprop=\"url\" class=\"underline\" ";
		String endSubtitle ="<";
		StringBuffer resultforceviri = dwn.downloadData(url);
		String linkForCeviri = resultforceviri.toString();
		
	
		int subtitleStartIndex = resultforceviri.indexOf(startSubtitle,0) + startSubtitle.length();
		int subtitleEndIndex = resultforceviri.indexOf(endSubtitle,subtitleStartIndex);
		
		String idForCeviri = linkForCeviri.substring(subtitleStartIndex,subtitleEndIndex);
		//System.out.println(idForCeviri);
		
		int titleIndexStart = idForCeviri.indexOf("sub/");
		int titleIndexEnd = idForCeviri.indexOf("\"",titleIndexStart);
		String titleLink = idForCeviri.substring(titleIndexStart+1,titleIndexEnd+1);
		titleLink = "turkcealtyazi.org/s"+titleLink;
		//System.out.println(titleLink);
		if(titleLink.equalsIgnoreCase("turkcealtyazi.org/s")) {
			titleLink = "Turkce Aciklama yok! ";
		}
		titleLink = titleLink.replace("\"", "");
		link = titleLink;
	
			
		return StarterForAltyazi.ozet;
		
	}
	

	
	


}
