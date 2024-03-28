package com.project.pjshop.item.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.project.pjshop.common.JDBCTemplate.*;
import com.project.pjshop.member.model.dao.MemberDAO;
import com.project.pjshop.model.dto.Items;

public class ItemDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public ItemDAO() {
		try {
			prop = new Properties();
			
			String filePath = MemberDAO.class.getResource("/com/project/pjshop/sql/items.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public List<Items> selectAll(Connection conn) throws SQLException {
		List<Items> itemList = new ArrayList<Items>();
		
		try {
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Items item = new Items(
						rs.getInt("ITEM_NO"),
						rs.getString("ITEM_NAME"),
						rs.getString("ITEM_INFO"),
						rs.getInt("ITEM_PRICE"),
						rs.getString("ITEM_IMAGE"),
						rs.getString("UPLOAD_DATE"),
						rs.getInt("MEMBER_NO")
						);
				
				itemList.add(item);
				
			}
			
			
		} finally {
			close(stmt);
				
		}
		
		return itemList;
	}

	public int purchase(Connection conn, int memberNo, int itemNo) throws SQLException {
		int result = 0;
		
		try {
			String sql = prop.getProperty("purchase");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, itemNo);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		
		}
		
		return result;

	}

}
