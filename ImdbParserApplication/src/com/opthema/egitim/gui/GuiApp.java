package com.opthema.egitim.gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import com.opthema.egitim.Starter;
import com.opthema.egitim.model.Director;
import com.opthema.egitim.model.Movie;
import com.opthema.egitim.model.Star;
import com.opthema.egitim.model.Subtitle;
import com.opthema.egitim.model.Writer;
import com.opthema.egitim.service.MovieService;

public class GuiApp {

	private JFrame frame;
	private JTextField textField;
	private JList<String> filmList = new JList<String>();
	private JList directorList = new JList();
	private JList<String> writerList = new JList<String>();
	private JList<String> starList = new JList<String>();
	private JList<String> turkceAltyaziAciklama = new JList<String>();
	MovieService mService = new MovieService();
	private List<Movie> movies;
	Label description = new Label("Description:");
	JList<String> subtitleList = new JList<String>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					GuiApp window = new GuiApp();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		});
	}

	public GuiApp() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1401, 840);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(419, 113, 159, 37);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search Film");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Starter.start(textField.getText());
					DefaultListModel modelForName = new DefaultListModel<>();
					DefaultListModel modelForDirector = new DefaultListModel<>();
					DefaultListModel modelForWriter = new DefaultListModel<>();
					DefaultListModel modelForStar = new DefaultListModel<>();
					DefaultListModel modelForSubtitle = new DefaultListModel<>();
					Movie mv = new Movie();
					movies = mService.getMovies();
					
					
					for (int i = 0; i < movies.size(); i++) {
						modelForName.addElement(movies.get(i).getMovieName());
					}

					filmList.setModel(modelForName);

					
					filmList.addMouseListener(new MouseListener() {
						@Override
						public void mouseClicked(MouseEvent e) {
							description.setText(
							"Description: " + movies.get(filmList.getSelectedIndex()).getMovieDescription());
							
							modelForDirector.removeAllElements();
							List<Director> movieDirectors = movies.get(filmList.getSelectedIndex()).getDirectors();	
								int index = movies.get(filmList.getSelectedIndex()).getDirectors().size();
								for (int j = 0; j < index; j++) {
									modelForDirector.addElement(movieDirectors.get(j).getDirectorName());
								}
								directorList.setModel(modelForDirector);
							
							
							
								modelForWriter.removeAllElements();
								List<Writer> movieWriters = movies.get(filmList.getSelectedIndex()).getWriters();
									int indexForWriter = movies.get(filmList.getSelectedIndex()).getWriters().size();
									for (int j = 0; j < indexForWriter; j++) {
										modelForWriter.addElement(movieWriters.get(j).getWriterName());
									}
									writerList.setModel(modelForWriter);
								
						
									
									modelForStar.removeAllElements();
									List<Star> movieStars = movies.get(filmList.getSelectedIndex()).getStars();
										int indexForStar = movies.get(filmList.getSelectedIndex()).getStars().size();
										for (int j = 0; j < indexForStar; j++) {
													
											modelForStar.addElement(movieStars.get(j).getStarName());
											
										}
										starList.setModel(modelForStar);
										
										
										
										modelForSubtitle.removeAllElements();
										List<Subtitle> movieSubtitles = movies.get(filmList.getSelectedIndex()).getSubtitles();
											int indexForSubtitle = movies.get(filmList.getSelectedIndex()).getSubtitles().size();
											for (int j = 0; j < indexForSubtitle; j++) {			
												modelForSubtitle.addElement(movieSubtitles.get(j).getSubtitle());
			
											}
											subtitleList.setModel(modelForSubtitle);
										
										
										
									//image	
										
										BufferedImage img = null;
										try {
											img = ImageIO.read(new URL(movies.get(filmList.getSelectedIndex()).getMovieImgSrc()));
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										ImageIcon icon = new ImageIcon(img);
										
										JFrame frame = new JFrame();
										frame.getContentPane().setLayout(new FlowLayout());
										frame.setSize(200, 300);
										frame.getDefaultCloseOperation();
										JLabel labelForImage = new JLabel();
										labelForImage.setIcon(icon);
										frame.getContentPane().add(labelForImage);
										frame.setVisible(true);
										frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
										
								
										
										DefaultListModel subtitle = new DefaultListModel<>();
										subtitle.removeAllElements();

										subtitle.addElement(movies.get(filmList.getSelectedIndex()).getMovieOzetForAltyazi());
										turkceAltyaziAciklama.setModel(subtitle);;
										
									
										

							
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub

						}

					});

				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(419, 155, 159, 21);
		frame.getContentPane().add(btnNewButton);

		filmList.setBounds(633, 113, 128, 158);
		frame.getContentPane().add(filmList);

		Label labelTypeAFilm = new Label("Type a film name:");
		labelTypeAFilm.setBounds(419, 85, 159, 37);
		frame.getContentPane().add(labelTypeAFilm);
		
		
		directorList.setBounds(419, 346, 134, 158);
		frame.getContentPane().add(directorList);

		writerList.setBounds(633, 346, 128, 158);
		frame.getContentPane().add(writerList);

		starList.setBounds(851, 346, 134, 158);
		frame.getContentPane().add(starList);

		turkceAltyaziAciklama.setBounds(48, 589, 523, 56);
		frame.getContentPane().add(turkceAltyaziAciklama);
		
		Label label = new Label("Film List:");
		label.setBounds(633, 86, 59, 21);
		frame.getContentPane().add(label);
		
		Label label_1 = new Label("Directors:");
		label_1.setBounds(419, 319, 59, 21);
		frame.getContentPane().add(label_1);
		
		Label label_2 = new Label("Writers:");
		label_2.setBounds(633, 319, 59, 21);
		frame.getContentPane().add(label_2);
		
		Label label_3 = new Label("Stars:");
		label_3.setBounds(851, 319, 59, 21);
		frame.getContentPane().add(label_3);
		
		Label subtitle = new Label("Turkish Description:");
		subtitle.setBounds(48, 562, 112, 21);
		frame.getContentPane().add(subtitle);
		description.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		
		description.setBounds(48, 700, 1500, 70);
		frame.getContentPane().add(description);
		
		
		subtitleList.setBounds(1198, 113, 171, 532);
		frame.getContentPane().add(subtitleList);
		
		Label label_4 = new Label("Subtitles:");
		label_4.setBounds(1188, 86, 59, 21);
		frame.getContentPane().add(label_4);
		
		Label label_5 = new Label("IMDB PARSER");
		label_5.setFont(new Font("Dialog", Font.BOLD, 11));
		label_5.setBounds(633, 10, 186, 21);
		frame.getContentPane().add(label_5);
	}
}
