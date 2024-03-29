package com.project.pjshop.game.model.dao;

import static com.project.pjshop.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.project.pjshop.member.model.dao.MemberDAO;

public class GameDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public GameDAO() {
		try {
			prop = new Properties();
			
			String filePath = MemberDAO.class.getResource("/com/project/pjshop/sql/game.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public int loot(Connection conn, int memberNo) throws SQLException {
		int result = 0;
		
		try {
			String sql = prop.getProperty("loot");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		
		}
		
		return result;
		
	}

	
	
	
	
	
	
}
