package com.diworksdev.webproj3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.diworksdev.webproj3.dto.HelloStrutsDTO;
import com.diworksdev.webproj3.util.DBConnector;

public class HelloStrutsDAO {
	public HelloStrutsDTO select() {
		HelloStrutsDTO dto = new HelloStrutsDTO();
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		if (con == null) {
			dto.setResult("DB接続に失敗しました。");
			return dto;
		}

		String sql = "SELECT * FROM users";
		try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				dto.setResult("MySQL と接続できます。");
			} else {
				dto.setResult("MySQL と接続できません。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			dto.setResult("SQL実行中にエラーが発生しました。");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return dto;
	}
}