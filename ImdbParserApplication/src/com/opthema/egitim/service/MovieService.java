package com.opthema.egitim.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.opthema.egitim.DownloaderViaApache;
import com.opthema.egitim.*;


import com.opthema.egitim.model.*;


public class MovieService {

	static DownloaderViaApache dwn = new DownloaderViaApache();

//	Writer writer;
//	Star star;
//	Director director;
	

	 
	public List<Writer> dre;

	public List<Movie> getMovies() throws ClientProtocolException, IOException {

		

		List<Movie> movies = new ArrayList<Movie>();
	
		String linkStart = "<td class=\"result_text\"> <a href=\"/";
		String linkEnd = "\" >";
		 int loopEnd = (Starter.sendResult.length() - Starter.sendResult.replace(linkStart, "").length()) / linkStart.length();

	
	//	 System.out.println(loopEnd);
	
		for (int i = 0; i < loopEnd; i++) {
			Movie movie = new Movie();
			// link
			int linkStartIndex = Starter.sendResult.indexOf(linkStart, 0) + linkStart.length();
			int linkEndIndex = Starter.sendResult.indexOf(linkEnd, linkStartIndex);
			String link = Starter.sendResult.substring(linkStartIndex, linkEndIndex);

			// id
			String startString = "title/tt";
			String endString = "/?ref_";
			String linkForAltyazi = link;
			int startIndex = linkForAltyazi.indexOf(startString, 0) + startString.length();
			int endIndex = linkForAltyazi.indexOf(endString, startIndex);
			String id = linkForAltyazi.substring(startIndex,endIndex);
			movie.setId(id);
			
			
			

			// name
			int nameStartIndex = linkEndIndex + linkEnd.length();
			String nameEnd = "</a>";
			int nameEndIndex = Starter.sendResult.indexOf(nameEnd, nameStartIndex);
			String name = Starter.sendResult.substring(nameStartIndex, nameEndIndex);
			System.out.println("MovieService çalýþýyor : " + name);
			movie.setMovieName(name);
			

			// year
			int yearStartIndex = nameEndIndex + nameEnd.length();
			String yearEnd = " <";
			int yearEndIndex = Starter.sendResult.indexOf(yearEnd, yearStartIndex);
			String year = Starter.sendResult.substring(yearStartIndex, yearEndIndex);
			movie.setMovieYear(year);
			

			// description 
			String urlForDescription = "https://www.imdb.com/" + link;
			StringBuffer resultForDescription = dwn.downloadData(urlForDescription);
			String resultStart = "<div class=\"summary_text\"";
			String resultEnd = "<";
			int sendResultStartIndex = resultForDescription.indexOf(resultStart) + resultStart.length();
			int sendResultEndIndex = resultForDescription.indexOf(resultEnd, sendResultStartIndex);
			String description = resultForDescription.substring(sendResultStartIndex, sendResultEndIndex).trim();
			description = description.replace(">", " ").trim();
			movie.setMovieDescription(description);	
			

			// image
			String imageUrlStart = "<div class=\"poster\">";
			String imageUrlEnd = "</a> ";
			int imageUrlStartIndex = resultForDescription.indexOf(imageUrlStart) + imageUrlStart.length();
			int imageUrlEndIndex = resultForDescription.indexOf(imageUrlEnd, imageUrlStartIndex);
			String imageUrl = resultForDescription.substring(imageUrlStartIndex, imageUrlEndIndex);
			String tekrarKes = "src=\"";
			String sonunuKes = "/>";
			int imageSrcIndex = imageUrl.indexOf(tekrarKes) + tekrarKes.length();
			int imageSrcEndIndex = imageUrl.indexOf(sonunuKes);
			imageUrl = imageUrl.substring(imageSrcIndex, imageSrcEndIndex);
			imageUrl = imageUrl.replace("\"", "");
			movie.setMovieImgSrc(imageUrl);
			
 
			StringBuffer strBuffer = new StringBuffer();
			strBuffer.append(Starter.sendResult);
			Starter.sendResult = strBuffer.replace(linkStartIndex - 1, linkEndIndex + 1, "").toString();

			StarterForAltyazi subtitle = new StarterForAltyazi();
			String subtitle2 = subtitle.start(link);
			movie.setMovieOzetForAltyazi(subtitle2);
			

			DirectorService dService = new DirectorService();
			List<Director> directors = dService.getDirectors(link);
			movie.setDirectors(directors);

			StarService sService = new StarService();
			List<Star> stars = sService.getStars(link);
			movie.setStars(stars);

			WriterService wService = new WriterService();
			List<Writer> writers = wService.getWriters(link);
			movie.setWriters(writers);

			StarterForSubtitle st = new StarterForSubtitle();
			st.editLink(id);
			SubtitleService subService = new SubtitleService();
			List<Subtitle> subtitles = subService.getSubtitle();
			movie.setSubtitles(subtitles);
			
			for (int k = 0; k < movie.getWriters().size(); k++) {

				System.out.println("Movie Writers: " + movie.getWriters().get(k).getWriterName());

			}

			for (int k = 0; k < movie.getDirectors().size(); k++) {

				System.out.println("Movie Directors: " + movie.getDirectors().get(k).getDirectorName());
			}

			for (int k = 0; k < movie.getStars().size(); k++) {

				System.out.println("Movie Stars: " + movie.getStars().get(k).getStarName());
			}
			
			for (int k = 0; k < movie.getSubtitles().size(); k++) {

				System.out.println("Movie Subtitles: " + movie.getSubtitles().get(k).getSubtitle());

			}
			System.out.println("***********************************************");
			movies.add(movie);
		}

		return movies;
	}

}
