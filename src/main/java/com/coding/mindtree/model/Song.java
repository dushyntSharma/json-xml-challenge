package com.coding.mindtree.model;

import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "songs")
public class Song implements Comparable<Song> {
	private int id;
	private String title;
	private int length;
	private Set<Singer> singers;

	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Song(int id, String title, int length, Set<Singer> singers) {
		super();
		this.id = id;
		this.title = title;
		this.length = length;
		this.singers = singers;
	}

	public Song(int id, String title, int length) {
		super();
		this.id = id;
		this.title = title;
		this.length = length;
	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@XmlElement
	public Set<Singer> getSingers() {
		return singers;
	}

	public void setSingers(Set<Singer> singers) {
		this.singers = singers;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", title=" + title + ", length=" + length + ", singers=" + singers + "]";
	}

	@Override
	public int compareTo(Song sg) {
		// TODO Auto-generated method stub
		if (this.length == sg.length)
			return 0;
		else if (this.length > sg.length)
			return 1;
		else
			return -1;
	}

}
