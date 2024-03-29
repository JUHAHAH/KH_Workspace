package com.project.pjshop.game.model.service;

import static com.project.pjshop.common.JDBCTemplate.close;
import static com.project.pjshop.common.JDBCTemplate.commit;
import static com.project.pjshop.common.JDBCTemplate.getConnection;
import static com.project.pjshop.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.SQLException;

import com.project.pjshop.game.model.dao.GameDAO;

public class GameService {
	private GameDAO dao = new GameDAO();

	public int loot(int memberNo) throws SQLException {
		Connection conn = getConnection();
		
		int result = dao.loot(conn, memberNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
		
		
	}

}
