package com.project.pjshop.item.model.service;

import static com.project.pjshop.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.project.pjshop.item.dao.ItemDAO;
import com.project.pjshop.model.dto.Items;

public class ItemService {
	private ItemDAO dao = new ItemDAO();

	public List<Items> selectAll() throws SQLException {
		Connection conn = getConnection();
		
		List<Items> itemList = dao.selectAll(conn);
		
		close(conn);
		
		return itemList;
		
	}

	public int purchase(int memberNo, int itemNo) throws SQLException {
		Connection conn = getConnection();
		
		int result = dao.purchase(conn, memberNo, itemNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
		
		
		
	}
	
}
