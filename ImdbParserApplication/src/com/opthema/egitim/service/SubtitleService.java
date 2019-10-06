package com.opthema.egitim.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.opthema.egitim.StarterForSubtitle;
import com.opthema.egitim.model.Movie;
import com.opthema.egitim.model.Subtitle;

public class SubtitleService {
	
	StringBuffer sbt = StarterForSubtitle.bf;

	public List<Subtitle> getSubtitle() throws ClientProtocolException, IOException {
		List<Subtitle> subtitles = new ArrayList<Subtitle>();
		Movie mv = new Movie();
		StringBuffer buffer = new StringBuffer();
 
		buffer = sbt;

		String subTextStart = "href=\"/sub/";
		String subTextEnd = "title";
		String resultToString = buffer.toString();
		int resultLenthTrimmed = resultToString.replace(subTextStart, "").length();
		int fark = buffer.length() - resultLenthTrimmed;
		int donguSonu = fark / subTextStart.length();

		for (int i = 0; i < donguSonu; i++) {
			
			Subtitle subtitle = new Subtitle();
			int subTextIndex = buffer.indexOf(subTextStart, 0) + subTextStart.length();
			int subTextEndIndex = buffer.indexOf(subTextEnd, subTextIndex);
			String subtitleFound = buffer.substring(subTextIndex, subTextEndIndex);
			subtitleFound = subtitleFound.replace("\"", "");

			subtitle.setSubtitle(subtitleFound);
			subtitles.add(i, subtitle);
			buffer = buffer.replace((subTextIndex - subTextStart.length()), subTextEndIndex, " ");
			mv.setSubtitles(subtitles);
		}

		return subtitles;
	}

}
