package com.opthema.egitim;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class Starter {

	static DownloaderViaApache dwn = new DownloaderViaApache();
	public static String sendResult;

	public static void start(String text) throws ClientProtocolException, IOException {

		String url = "https://www.imdb.com/find?ref_=nv_sr_fn&q=" + text + "&s=all";
		StringBuffer result = dwn.downloadData(url);

		String sendResultStart = "<h3 class=\"findSectionHeader\"><a name=\"tt\"></a>Titles</h3>";
		String sendResultEnd = "<div class=\"findMoreMatches\">";
		int sendResultStartIndex = result.indexOf(sendResultStart);
		int sendResultEndIndex = result.indexOf(sendResultEnd);
		sendResult = result.substring(sendResultStartIndex, sendResultEndIndex);
		
	}

}
