package com.opthema.egitim.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.opthema.egitim.model.Movie;
import com.opthema.egitim.model.Writer;

public class WriterService {

	 
	 List<Writer> writers;
	public List<Writer> getWriters(String link) throws ClientProtocolException, IOException {
 
		writers= new ArrayList<Writer>();	
		Movie mv = new Movie();
	
		String url = "https://www.imdb.com/" + link;
		StringBuffer resultForWriters = new StringBuffer();
		resultForWriters = MovieService.dwn.downloadData(url);
		
		String writersTextsStart = "?ref_=tt_ov_wr\">";
		String writersTextEnd = "<";

		
		
		String resultToString = resultForWriters.toString();
		int resultLenthTrimmed = resultToString.replace(writersTextsStart, "").length();
		int fark = resultForWriters.length() - resultLenthTrimmed;
		
		
		int donguSonu = fark / writersTextsStart.length();

		for (int i = 0; i < donguSonu; i++) {
			Writer writer = new Writer();
			int writersTextStartIndex = resultForWriters.indexOf(writersTextsStart, 0) + writersTextsStart.length();
			int writersTextEndIndex  = resultForWriters.indexOf(writersTextEnd, writersTextStartIndex);
			String writerFound = resultForWriters.substring(writersTextStartIndex, writersTextEndIndex);
			writer.setWriterName(writerFound);
			writers.add(i,writer);
			
			resultForWriters = resultForWriters.replace((writersTextStartIndex - writersTextsStart.length()), writersTextEndIndex, " ");
			mv.setWriters(writers);
	
		}
		return writers;

}}
