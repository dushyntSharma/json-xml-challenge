package com.coding.mindtree.services;

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
