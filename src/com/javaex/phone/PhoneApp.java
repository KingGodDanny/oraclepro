package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList;
		
		System.out.println("***********************************");
		System.out.println("*        전화번호 관리 프로그램          *");
		System.out.println("***********************************");
		
		while(true) {
			System.out.println("");
			System.out.println("1.리스트  2.등록  3.수정  4.삭제  5.검색  6.종료");
			System.out.println("-------------------------------------------");
			System.out.print(">메뉴번호: ");
			int num = sc.nextInt();
			
			if(num == 6) {
				System.out.println("***********************************");
				System.out.println("*        Thank you:)              *");
				System.out.println("***********************************");
				
				break;
				
			} else if(num == 1) {
				System.out.println("<1. 리스트>");
				personList = phoneDao.getPersonList();
				getPersonList(personList);
				
			} else if(num == 2) {
				System.out.println("<2. 등록>");
				System.out.print("이름 > ");
				String name = sc.next();
				System.out.print("휴대전화 > ");
				String hp = sc.next();
				System.out.print("회사번호 > ");
				String company = sc.next();
				sc.nextLine();
				
				
				PersonVo pInsert = new PersonVo(name, hp, company);
				phoneDao.personInsert(pInsert);
				
				
			} else if(num == 3) {
				System.out.println("<3. 수정>");
				System.out.print("번호 > ");
				int personId = sc.nextInt();
				sc.nextLine();
				System.out.print("이름 > ");
				String name = sc.next();
				System.out.print("휴대전화 > ");
				String hp = sc.next();
				System.out.print("회사번호 > ");
				String company = sc.next();
				
				
				PersonVo pUpdate = new PersonVo(personId, name, hp, company);
				phoneDao.personUpdate(pUpdate);
				
				
			} else if(num == 4) {
				System.out.println("<4. 삭제>");
				System.out.print(">번호: ");
				int personId = sc.nextInt();
				
				
				phoneDao.personDelete(personId);
				
				
			} else if(num == 5) {
				System.out.println("<5. 검색>");
				System.out.print("검색어 > ");
				String searchL = sc.next();
				
				List<PersonVo> searchList = phoneDao.searchList(searchL);
				getPersonList(searchList);
				
			} else {
				System.out.println("[다시 입력해주세요.]");
			}
			
			
		}

		
		sc.close();
	}

	
	//Person List 출력메소드
	public static void getPersonList(List<PersonVo> personList) {
		for(int i=0; i<personList.size(); i++) {
			PersonVo personVo = personList.get(i);
			System.out.println(personVo.getPersonId() + ".\t" + personVo.getName() + "\t" +
					personVo.getHp() + "\t" + personVo.getCompany());
		}
		
	}
	
}
