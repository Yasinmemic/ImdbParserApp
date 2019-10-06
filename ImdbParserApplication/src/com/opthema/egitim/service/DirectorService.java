package com.opthema.egitim.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.opthema.egitim.model.Director;
import com.opthema.egitim.model.Movie;

public class DirectorService {

	public List<Director> getDirectors(String link) throws ClientProtocolException, IOException {

		List<Director> directors = new ArrayList<Director>();
		Movie mv = new Movie();

		
		
		String url = "https://www.imdb.com/" + link;
		StringBuffer resultForWriters = new StringBuffer();
		resultForWriters = MovieService.dwn.downloadData(url);
		String diretorsTextsStart  = "?ref_=tt_ov_dr\">";
		String directorsTextEnd = "</";

		 
		int directorsTextStartIndex;
		int directosTextEndIndex;
		String resultToString = resultForWriters.toString();
		int resultLenthTrimmed = resultToString.replace(diretorsTextsStart, "").length();
		int fark = resultForWriters.length() - resultLenthTrimmed;
		
		 
		int donguSonu = fark / diretorsTextsStart.length();

		for (int i = 0; i < donguSonu; i++) {
			Director director = new Director();
			directorsTextStartIndex = resultForWriters.indexOf(diretorsTextsStart, 0) + diretorsTextsStart.length();
			directosTextEndIndex = resultForWriters.indexOf(directorsTextEnd, directorsTextStartIndex);
			String directorFound = resultForWriters.substring(directorsTextStartIndex, directosTextEndIndex);
			resultForWriters = resultForWriters.replace((directorsTextStartIndex - diretorsTextsStart.length()), directosTextEndIndex, "");

			if (directorFound.indexOf("<html") == 1) {
				directorFound = "Director is not found! ";
			}
			director.setDirectorName(directorFound);
			directors.add(director);
			
			resultForWriters = resultForWriters.replace((directorsTextStartIndex - diretorsTextsStart.length()), directosTextEndIndex, " ");
			mv.setDirectors(directors);
		
		}
		
		return directors;
	}
}
