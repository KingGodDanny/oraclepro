package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PhoneDao {

	//필드 
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";
	
	//생성자
	
	//메소드(게터세터)
	
	//메소드(일반)
	
	// 1~2.DB연결 메소드*************************************************
	private void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}
	// ************************************************************

	// 5.자원정리 메소드*********************************************
	private void close() {

		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}
	// ************************************************************************
	
	// 작가 삭제하기*********************************************************
	public int personDelete(int personId) {

		int count = -1;

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " DELETE FROM person ";
			query += " WHERE person_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, personId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[" + count + "건이 삭제되었습니다.] ");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		close();

		return count;

	}
	// ***************************************************************************

	// 작가 수정하기 ***********************************************
	public int personUpdate(PersonVo personVo) {

		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update person ";
			query += " set name = ? , ";
			query += "     hp = ? , ";
			query += "     company = ? ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonId());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[" + count + "건이 수정되었습니다.] ");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.close();

		return count;
	}
	// ******************************************************
	// 작가 등록하기*********************************************
	public int personInsert(PersonVo personVo) {

		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into person ";
			query += " VALUES(seq_person_id.nextval, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[" + count + "건이 등록되었습니다.] ");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.close();

		return count;

	}
	// ********************************************************************************
	
	// 퍼슨리스트 리스트 가져오기****************************************
	public List<PersonVo> getPersonList() { // 리스트 출력 메소드

		// 리스트 생성하기
		List<PersonVo> personList = new ArrayList<PersonVo>();

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select person_id, ";
			query += "        name, ";
			query += "        hp, ";
			query += "        company ";
			query += " from person ";
			query += " order by person_id asc ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				PersonVo personVo = new PersonVo(personId, name, hp, company);

				personList.add(personVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return personList;

	}
	// *******************************************************************************
	
	
	// Search 메소드
		public List<PersonVo> searchList(String searchL) {
			
			// 리스트 생성하기
			List<PersonVo> personList = new ArrayList<PersonVo>();

			this.getConnection();

			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "";
				query += " select person_id, ";
				query += " 		  name, ";
				query += "        hp, ";
				query += "        company ";
				query += " from person ";
				query += " where name || hp || company like ";
				query += "'%" + searchL + "%' ";
				
				pstmt = conn.prepareStatement(query);

				rs = pstmt.executeQuery();

				// 4.결과처리
				while (rs.next()) {
					int personId = rs.getInt("person_id");
					String name = rs.getString("name");
					String hp = rs.getString("hp");
					String company = rs.getString("company");

					PersonVo personVo = new PersonVo(personId, name, hp, company);

					personList.add(personVo);
				}
				
				/* 이렇게 만들수도 있지만 출력메소드가 있으니 있는걸 사용하자!
				for(int i=0; i<personList.size(); i++) {
					PersonVo personVo = personList.get(i);
					System.out.println(personVo.getPersonId() + ".\t" + personVo.getName() + "\t" +
							personVo.getHp() + "\t" + personVo.getCompany());
				}
				*/
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

			this.close();
			
			return personList;
			
			
		}
	
	
	/*
	// Search 메소드
	public void searchList(String searchL) {
		
		// 리스트 생성하기
		List<PersonVo> personList = new ArrayList<PersonVo>();

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select person_id, ";
			query += " 		  name, ";
			query += "        hp, ";
			query += "        company ";
			query += " from person ";
			query += " where name || hp || company like ";
			query += "'%" + searchL + "%' ";
			
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				PersonVo personVo = new PersonVo(personId, name, hp, company);

				personList.add(personVo);
			}
			
			---이렇게 만들수도 있지만 출력메소드가 있으니 있는걸 사용하자!
			for(int i=0; i<personList.size(); i++) {
				PersonVo personVo = personList.get(i);
				System.out.println(personVo.getPersonId() + ".\t" + personVo.getName() + "\t" +
						personVo.getHp() + "\t" + personVo.getCompany());
			}
			
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		
		
	}
	*/
	
}
