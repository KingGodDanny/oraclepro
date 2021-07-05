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
				String name = sc.next();
				System.out.print("이름 > ");
				String hp = sc.next();
				System.out.print("휴대전화 > ");
				String company = sc.next();
				System.out.print("회사번호 > ");
				sc.nextLine();
				System.out.println("[1건이 등록되었습니다.]");
				
				PersonVo pInsert = new PersonVo(name, hp, company);
				phoneDao.personInsert(pInsert);
				
			} else if(num == 3) {
				System.out.println("<3. 수정>");
			
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
