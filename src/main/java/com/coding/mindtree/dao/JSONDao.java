package com.coding.mindtree.dao;

import java.sql.SQLException;
import java.util.Set;

import com.coding.mindtree.model.Song;

public interface JSONDao {
	/**
	 * @param id
	 * @param title
	 * @param length
	 * @throws Exception
	 */
	void storeBooks(int id, String title, int length) throws Exception;

	/**
	 * @param id
	 * @param name
	 * @param gender
	 * @throws Exception
	 */
	void storeAuthors(int id, String name, String gender) throws Exception;

	/**
	 * @param songId
	 * @param singerId
	 * @throws SQLException
	 * @throws Exception
	 */
	void storeBooksAuthors(int songId, int singerId) throws SQLException, Exception;

	/**
	 * @return songs
	 * @throws Exception
	 */
	Set<Song> getSongs() throws Exception;

	/**
	 * display songs
	 */
	void displaySongs();

}
