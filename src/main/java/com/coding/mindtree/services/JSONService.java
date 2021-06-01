package com.coding.mindtree.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.util.Set;

import com.coding.mindtree.model.Song;

/**
 * @author SHREEVATSA
 *
 */
public interface JSONService {

	/**
	 * @param songs
	 * @throws Exception
	 */
	void storeJsonData(Set<Song> songs) throws Exception;

	/**
	 * Write to xml file
	 * 
	 * @throws Exception
	 */
	void getSongs() throws Exception;

	/**
	 * display Songs
	 */
	void displaySongs();

}

//
//
//
//public void getSongs() throws Exception {
//
//	Set<Song> songs = jsonTodatabaseDao.getSongs();
//
//	String xmlData = "";
//	String data = "";
//	if (songs != null) {
//		for (Song book : songs) {
//			data = readObjToXml(book) + "\n";
//			xmlData += newFunction(data);
//
//		}
//		System.out.println("***************************");
//		File file = new File("songs.xml");
//		try {
//
//			Writer fw = new FileWriter(file);
//			fw.write(xmlData + "\n</songs>");
//			fw.flush();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(xmlData);
//
//	} else {
//		System.out.println("Data Generation failed");
//	}
//
//}
//
//int count = 0;
//
//private String newFunction(String s) throws IOException {
//	String result = "";
//	String data = "";
//	if (count > 0) {
//		count++;
//
//		BufferedReader br = new BufferedReader(new StringReader(s));
//		System.out.println(br.readLine());
//		while ((data = br.readLine()) != null) {
//			result += data + "\n";
//		}
//
//		return result;
//
//	} else {
//		count++;
//
//		BufferedReader br = new BufferedReader(new StringReader(s));
//		result = br.readLine() + "\n<songs>\n";
//		while ((data = br.readLine()) != null) {
//			result += data + "\n";
//		}
//
//		return s;
//	}
//
//}