package com.coding.mindtree.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.coding.mindtree.dao.JSONDao;
import com.coding.mindtree.dao.JSONDaoImpl;
import com.coding.mindtree.model.Singer;
import com.coding.mindtree.model.Song;

public class JSONServiceImpl implements JSONService {

	JSONDao jsonTodatabaseDao = new JSONDaoImpl();
	List<Singer> authorsService = new LinkedList<Singer>();

	List<Singer> authorsServiceNew = new LinkedList<Singer>();

	@Override
	public void storeJsonData(Set<Song> songs) throws Exception {
		// TODO Auto-generated method stub
		for (Song song : songs) {
			for (Singer singer : song.getSingers()) {
				boolean flag = checkList(singer.getId());
				if (flag) {
					continue;
				} else {
					authorsService.add(new Singer(singer.getId(), singer.getName(), singer.getGender()));
				}
			}
		}

		insertSongs(songs);
		insertSingers(authorsService);
		insertSongSingers(songs);

	}

	private boolean checkList(int i) {

		boolean flag = false;

		Iterator<Singer> itr = authorsService.iterator();
		while (itr.hasNext()) {
			if (itr.next().getId() == i) {
				flag = true;
				break;
			} else {
				flag = false;
			}
		}

		return flag;

	}

	private void insertSongSingers(Set<Song> songs) throws SQLException, Exception {
		for (Song song : songs) {
			for (Singer singer : song.getSingers()) {
				int songId = song.getId();
				int singerId = singer.getId();
				jsonTodatabaseDao.storeBooksAuthors(songId, singerId);
			}
		}

	}

	private void insertSingers(List<Singer> authorsService) throws Exception {

		for (Singer author : authorsService) {
			int id = author.getId();
			String name = author.getName();
			String gender = author.getGender();
			jsonTodatabaseDao.storeAuthors(id, name, gender);
		}

	}

	private void insertSongs(Set<Song> songs) throws Exception {

		for (Song book : songs) {
			int id = book.getId();
			String title = book.getTitle();
			int length = book.getLength();

			jsonTodatabaseDao.storeBooks(id, title, length);
		}
	}

	public void getSongs() throws Exception {

		Set<Song> songs = jsonTodatabaseDao.getSongs();

		String xmlData = "";
		if (songs != null) {
			String replaceString = "";
			for (Song book : songs) {
				xmlData = readObjToXml(book) + "\n";
				replaceString += xmlData.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");

			}
			System.out.println("***************************");
			String xmlfinal = "<songs>\n" + replaceString + "\n</songs>";
			File file = new File("songs.xml");
			try {

				Writer fw = new FileWriter(file);
				fw.write(xmlfinal);
				fw.flush();

			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(xmlfinal);

		} else {
			System.out.println("Data Generation failed");
		}

	}

	private String readObjToXml(Song song) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Song.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter sw = new StringWriter();

			marshaller.marshal(song, sw);
			String bookData = sw.toString();
			return bookData;
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void displaySongs() {
		// TODO Auto-generated method stub

		try {
			jsonTodatabaseDao.displaySongs();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
