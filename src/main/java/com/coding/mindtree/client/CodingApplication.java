package com.coding.mindtree.client;

import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.coding.mindtree.model.Singer;
import com.coding.mindtree.model.Song;
import com.coding.mindtree.services.JSONService;
import com.coding.mindtree.services.JSONServiceImpl;

public class CodingApplication {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("===============WELCOME TO THE JSON/XML APPLICATION=================\n");

		String file = "D:/Script Workspace/CodingChallengeXMLJSON/songs.json";

		Set<Song> songs = readJson(file);

		JSONService jsonTodatabase = new JSONServiceImpl();

		boolean flag = true;
		int choice = 0;
		do {
			// display menu method
			displayMenu();
			System.out.println("Enter your choice");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				// Inserting the json data to database
				try {
					System.out.println("================DATA IS INSERTING TO THE DATABASE==================");
					jsonTodatabase.storeJsonData(songs);

					System.out.println("Json data stored to the database");

				} catch (Exception e) {

					e.printStackTrace();
				}

				break;
			case 2:
				// get the songs and store to the xml file
				try {
					jsonTodatabase.getSongs();
					System.out.println("Data Written to XML File");
				} catch (Exception e1) {

					e1.printStackTrace();
				}
				break;

			case 3:
				// displaying the sorted data using the comparator/comparable
				try {
					System.out.println("===========================");
					readingCtAndCb(file);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				break;

			case 4:
				// displaying the song details
				try {
					jsonTodatabase.displaySongs();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 5:
				// exit
				System.out.println("Thank you");
				flag = false;
				break;

			default:
				System.out.println("Invalid choice!!");
				break;
			}

		} while (flag);
	}

	private static Set<Song> readJson(String file) {

		// Creating a JSONParser object
		JSONParser jsonParser = new JSONParser();
		Set<Song> songs = new HashSet<Song>();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));
			// Retrieving the array
			JSONArray jsonArrayBooks = (JSONArray) jsonObject.get("songs");

			for (Object object : jsonArrayBooks) {

				JSONObject record = (JSONObject) object;

				int songId = Integer.parseInt((String) record.get("sid"));
				String title = (String) record.get("title");
				int length = Integer.parseInt(record.get("length").toString());

				JSONArray jsonArrayAuthors = (JSONArray) record.get("singers");
				Set<Singer> singers = new HashSet<Singer>();
				for (Object object2 : jsonArrayAuthors) {

					JSONObject record2 = (JSONObject) object2;

					int id = Integer.parseInt((String) record2.get("id"));
					String singerName = (String) record2.get("name");
					String gender = (String) record2.get("gender");

					singers.add(new Singer(id, singerName, gender));

				}
				songs.add(new Song(songId, title, length, singers));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return songs;
	}

	private static void readingCtAndCb(String file) {

		// Creating a JSONParser object
		JSONParser jsonParser = new JSONParser();
		List<Song> songs = new LinkedList<Song>();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));
			// Retrieving the array
			JSONArray jsonArrayBooks = (JSONArray) jsonObject.get("songs");

			for (Object object : jsonArrayBooks) {

				JSONObject record = (JSONObject) object;

				int songId = Integer.parseInt((String) record.get("sid"));
				String title = (String) record.get("title");
				int length = Integer.parseInt(record.get("length").toString());

				JSONArray jsonArrayAuthors = (JSONArray) record.get("singers");
				Set<Singer> singers = new HashSet<Singer>();
				for (Object object2 : jsonArrayAuthors) {

					JSONObject record2 = (JSONObject) object2;

					int id = Integer.parseInt((String) record2.get("id"));
					String singerName = (String) record2.get("name");
					String gender = (String) record2.get("gender");

					singers.add(new Singer(id, singerName, gender));

				}
				songs.add(new Song(songId, title, length, singers));

			}
			System.out.println("===================");
			System.out.println("USING THE COMPARABLE TO SORT BASED ON THE LENGTH");
			System.out.println("==========================");
			Collections.sort(songs);
			for (Song sg : songs) {
				System.out.println(sg.getId() + " \t " + sg.getTitle() + " \t " + sg.getLength());

			}
			System.out.println("===================");
			System.out.println("USING THE COMPARATOR TO SORT BASED ON THE TITLE");
			System.out.println("==========================");

			Collections.sort(songs, new Comparator<Song>() {

				@Override
				public int compare(Song o1, Song o2) {
					// TODO Auto-generated method stub
					return o1.getTitle().compareTo(o2.getTitle());
				}

			});

			for (Song bk : songs) {
				System.out.println(bk.getId() + " \t " + bk.getTitle() + " \t " + bk.getLength());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	private static void displayBooks(Set<Song> songs) {
//	for (Song song: songs) {
//		System.out.println(book.getId());
//		System.out.println(book.getTitle());
//		System.out.println(book.getPrice());
//		System.out.println("************************");
//		for (Author author : book.getAuthors()) {
//			System.out.println(author.getId());
//			System.out.println(author.getName());
//
//		}
//	}
//}

	private static void displayMenu() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("1.Add JSON Data to Database");
		System.out.println("2.Writing the data from the database to XML file");
		System.out.println("3.Display Sorted data using the Comaprator/Comparable");
		System.out.println("4.Display Song Details");
		System.out.println("5.Exit");
		System.out.println();

	}

}
