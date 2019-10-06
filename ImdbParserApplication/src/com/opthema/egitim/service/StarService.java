package com.opthema.egitim.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.opthema.egitim.model.Movie;
import com.opthema.egitim.model.Star;

public class StarService {
	

	public List<Star> getStars(String link) throws ClientProtocolException, IOException {
 
		List<Star> stars = new ArrayList<Star>();
		Movie mv = new Movie();
	
		
		String url = "https://www.imdb.com/" + link;
		StringBuffer resultForStars = new StringBuffer();
		resultForStars = MovieService.dwn.downloadData(url);
		String starsTextsStart = "?ref_=tt_ov_st_sm\">";
		String starsTextEnd = "</a>";

		int starsTextStartIndex;
		int starsTextEndIndex;
		String resultToString = resultForStars.toString(); 
		int resultLenthTrimmed = resultToString.replace(starsTextsStart, "").length();
		int fark = resultForStars.length() - resultLenthTrimmed;	
		int donguSonu = fark / starsTextsStart.length();

		for (int i = 0; i < donguSonu-1; i++) {
			Star star = new Star();
	
			starsTextStartIndex = resultForStars.indexOf(starsTextsStart, 0) + starsTextsStart.length();
			starsTextEndIndex = resultForStars.indexOf(starsTextEnd, starsTextStartIndex);
			String starFound = resultForStars.substring(starsTextStartIndex, starsTextEndIndex);
			
			star.setStarName(starFound);
			stars.add(i,star);
			
			resultForStars = resultForStars.replace((starsTextStartIndex - starsTextsStart.length()), starsTextEndIndex, "");	
			
			mv.setStars(stars);
	
		}
		
		return stars;
}
}