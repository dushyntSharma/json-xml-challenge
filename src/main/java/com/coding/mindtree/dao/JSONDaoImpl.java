package com.coding.mindtree.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.coding.mindtree.exceptions.ConnectionFailedException;
import com.coding.mindtree.model.Singer;
import com.coding.mindtree.model.Song;
import com.coding.mindtree.utility.DBConnection;

public class JSONDaoImpl implements JSONDao {

	@Override
	public void storeBooks(int id, String title, int length) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO songs values (?, ?, ?)";
		try {
			connection = DBConnection.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, title);
			preparedStatement.setInt(3, length);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ConnectionFailedException.closeResource(preparedStatement);
				ConnectionFailedException.closeResource(connection);

			} catch (ConnectionFailedException e) {
				e.printStackTrace();

			}
		}

	}

	@Override
	public void storeAuthors(int id, String name, String gender) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO singers values (?, ?, ?)";
		try {
			connection = DBConnection.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, gender);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				ConnectionFailedException.closeResource(preparedStatement);
				ConnectionFailedException.closeResource(connection);

			} catch (ConnectionFailedException e) {
				e.printStackTrace();

			}
		}

	}

	@Override
	public void storeBooksAuthors(int songId, int singerId) throws SQLException, Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO songs_singers(song_id,singer_id) values (?, ?)";
		try {
			connection = DBConnection.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, songId);
			preparedStatement.setInt(2, singerId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				ConnectionFailedException.closeResource(preparedStatement);
				ConnectionFailedException.closeResource(connection);

			} catch (ConnectionFailedException e) {
				e.printStackTrace();

			}
		}

	}

	@Override
	public Set<Song> getSongs() throws Exception {
		// TODO Auto-generated method stub
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		Set<Song> songs = new LinkedHashSet<Song>();
		try {
			String query = "select * from songs";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int songId = rs.getInt("id");
				String songTitle = rs.getString("title");
				int songLength = rs.getInt("length");

				Set<Singer> songSinger = new LinkedHashSet<Singer>();
				String query2 = "SELECT a.id, a.name, a.gender from singers a join songs_singers r where r.song_id="
						+ songId + " and a.id=r.singer_id";
				pstmt2 = con.prepareStatement(query2);
				ResultSet rs2 = pstmt2.executeQuery();
				while (rs2.next()) {
					int singerId = rs2.getInt("id");
					String singerName = rs2.getString("name");
					String gender = rs2.getString("gender");
					songSinger.add(new Singer(singerId, singerName, gender));
				}
				songs.add(new Song(songId, songTitle, songLength, songSinger));

			}

			return songs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return null;
	}

	public void displaySongs() {
		Connection con = DBConnection.getConnection();
		String sql = "select * from songs;";
		Statement st = null;
		ResultSet res = null;
		TreeSet<Song> shop = new TreeSet<Song>();
		HashMap<Integer, String> map = new HashMap<>();
		try {
			st = con.createStatement();
			res = st.executeQuery(sql);
			while (res.next()) {
				int id = res.getInt("id");
				String title = res.getString("title");
				int length = res.getInt("length");
				Song disSong = new Song(id, title, length);

				map.put(disSong.getId(), disSong.getTitle());

			}
			for (Map.Entry m : map.entrySet()) {
				System.out.print("{ " + m.getKey() + ", " + m.getValue() + " } ");
			}
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// close the resources
		finally {
			try {
				ConnectionFailedException.closeResource(res);
				ConnectionFailedException.closeResource(st);
				ConnectionFailedException.closeResource(con);

			} catch (ConnectionFailedException e) {
				e.printStackTrace();

			}

		}
	}

}
