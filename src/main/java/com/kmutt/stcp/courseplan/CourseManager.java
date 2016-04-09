package com.kmutt.stcp.courseplan;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kmutt.stcp.entity.*;
import com.kmutt.stcp.repository.CourseRepository;
import com.kmutt.stcp.repository.SubjectRepository;
import com.kmutt.stcp.web.CoursePlannerController;

public class CourseManager {

	// Field//
	private final Logger logger = LoggerFactory.getLogger(CourseManager.class);
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	private ArrayList<Subject> subjectList;
	private Account student;

	// Constructor//
	public CourseManager(Account student) {
		this.student = student;
	}

	// Method//
	public ArrayList<Subject> getSubjectList() {

		// should get subjectList from Common Entity module.
		// return this.subjectRepository.findAll(); 
		return this.dummySubjectList(this.student);

	}

	public Subject getSubjectByCode(String code) {

		if (this.subjectList == null) {
			this.subjectList = this.getSubjectList();
		}

		Subject resultSubject;

		try {

			//Should change this to get from Common Entity module.
			//resultSubject = this.subjectRepository.findAll().stream()
			resultSubject = this.subjectList.stream()
					.filter(subject -> subject.getSubjectCode().toLowerCase().equals(code.toLowerCase()))
					.findFirst()
					.orElse(null);

		} catch (Exception e) {

			logger.error("Method:getSubjectByCode|Err:" + e.getMessage());
			resultSubject = null;

		}

		return resultSubject;

	}

	public Subject getSubjectByID(int id) {

		if (this.subjectList == null) {
			this.subjectList = this.getSubjectList();
		}

		Subject resultSubject;
		
		try {

			//Should change this to get from Common Entity module.
			//resultSubject = this.subjectRepository.findAll().stream()
			resultSubject = this.subjectList.stream()
					.filter(subject -> subject.getId() == id)
					.findFirst()
					.orElse(null);

		} catch (Exception e) {

			logger.error("Method:getSubjectByID|Err:" + e.getMessage());
			resultSubject = null;
		}

		return resultSubject;

	}

	//Dummy//
	private ArrayList<Subject> dummySubjectList(Account student) {
		
		subjectList = new ArrayList<>();

		Subject subject1 = new Subject();
		subject1.setId(1);
		subject1.setSubjectCode("INT 610");
		subject1.setNameThai("�к�ʹѺʹع��õѴ�Թ�");
		subject1.setCredit("3");
		subject1.setDetailThai("Detail: " + subject1.getNameThai());
		subjectList.add(subject1);

		Subject subject2 = new Subject();
		subject2.setId(2);
		subject2.setSubjectCode("INT 611");
		subject2.setNameThai("����Թ�ԧ���ط������Ѻ෤��������ʹ��");
		subject2.setCredit("3");
		subject2.setDetailThai("Detail: " + subject2.getNameThai());
		subjectList.add(subject2);

		Subject subject3 = new Subject();
		subject3.setId(3);
		subject3.setSubjectCode("INT 613");
		subject3.setNameThai("��ú����ä�����蹤��ͧ���ʹ��");
		subject3.setCredit("3");
		subject3.setDetailThai("Detail: " + subject3.getNameThai());
		subjectList.add(subject3);

		Subject subject4 = new Subject();
		subject4.setId(4);
		subject4.setSubjectCode("INT 615");
		subject4.setNameThai("��èѴ��äس�Ҿ���ʹ��");
		subject4.setCredit("3");
		subject4.setDetailThai("Detail: " + subject4.getNameThai());
		subjectList.add(subject4);

		Subject subject5 = new Subject();
		subject5.setId(5);
		subject5.setSubjectCode("INT 616");
		subject5.setNameThai("����ҧἹ��Ѿ�ҡ�ͧ���");
		subject5.setCredit("3");
		subject5.setDetailThai("Detail: " + subject5.getNameThai());
		subjectList.add(subject5);

		Subject subject6 = new Subject();
		subject6.setId(6);
		subject6.setSubjectCode("INT 617");
		subject6.setNameThai("�������Ѻ�����ҹ෤��������ʹ��");
		subject6.setCredit("3");
		subject6.setDetailThai("Detail: " + subject6.getNameThai());
		subjectList.add(subject6);

		Subject subject7 = new Subject();
		subject7.setId(7);
		subject7.setSubjectCode("INT 630");
		subject7.setNameThai("෤����հҹ������");
		subject7.setCredit("3");
		subject7.setDetailThai("Detail: " + subject7.getNameThai());
		subjectList.add(subject7);

		Subject subject8 = new Subject();
		subject8.setId(8);
		subject8.setSubjectCode("INT 631");
		subject8.setNameThai("෤������ԧ��ͺਡ��");
		subject8.setCredit("3");
		subject8.setDetailThai("Detail: " + subject8.getNameThai());
		subjectList.add(subject8);

		Subject subject9 = new Subject();
		subject9.setId(9);
		subject9.setSubjectCode("INT 632");
		subject9.setNameThai("�ѭ�һ�д�ɰ�");
		subject9.setCredit("3");
		subject9.setDetailThai("Detail: " + subject9.getNameThai());
		subjectList.add(subject9);

		Subject subject10 = new Subject();
		subject10.setId(10);
		subject10.setSubjectCode("INT 633");
		subject10.setNameThai("෤�������ŵ������");
		subject10.setCredit("3");
		subject10.setDetailThai("Detail: " + subject10.getNameThai());
		subjectList.add(subject10);

		Subject subject11 = new Subject();
		subject11.setId(11);
		subject11.setSubjectCode("INT 635");
		subject11.setNameThai("��ä鹤׹���ʹ��");
		subject11.setCredit("3");
		subject11.setDetailThai("Detail: " + subject11.getNameThai());
		subjectList.add(subject11);

		Subject subject12 = new Subject();
		subject12.setId(12);
		subject12.setSubjectCode("INT 636");
		subject12.setNameThai("�������ѹ�������ҧ����������Ѻ������");
		subject12.setCredit("3");
		subject12.setDetailThai("Detail: " + subject12.getNameThai());
		subjectList.add(subject12);

		Subject subject13 = new Subject();
		subject13.setId(13);
		subject13.setSubjectCode("INT 637");
		subject13.setNameThai("��èѴ��ä������");
		subject13.setCredit("3");
		subject13.setDetailThai("Detail: " + subject13.getNameThai());
		subjectList.add(subject13);

		Subject subject14 = new Subject();
		subject14.setId(14);
		subject14.setSubjectCode("INT 638");
		subject14.setNameThai("��÷�����ͧ������");
		subject14.setCredit("3");
		subject14.setDetailThai("Detail: " + subject14.getNameThai());
		subjectList.add(subject14);

		Subject subject15 = new Subject();
		subject15.setId(15);
		subject15.setSubjectCode("INT 640");
		subject15.setNameThai("෤�������硫��������");
		subject15.setCredit("3");
		subject15.setDetailThai("Detail: " + subject15.getNameThai());
		subjectList.add(subject15);

		Subject subject16 = new Subject();
		subject16.setId(16);
		subject16.setSubjectCode("INT 641");
		subject16.setNameThai("��äǺ�����е�Ǩ�ͺ෤��������ʹ��");
		subject16.setCredit("3");
		subject16.setDetailThai("Detail: " + subject16.getNameThai());
		subjectList.add(subject16);

		Subject subject17 = new Subject();
		subject17.setId(17);
		subject17.setSubjectCode("INT 650");
		subject17.setNameThai("������蹤��ͧ෤��������ʹ��");
		subject17.setCredit("3");
		subject17.setDetailThai("Detail: " + subject17.getNameThai());
		subjectList.add(subject17);

		Subject subject18 = new Subject();
		subject18.setId(18);
		subject18.setSubjectCode("INT 651");
		subject18.setNameThai("෤������ä��Ҥ�");
		subject18.setCredit("3");
		subject18.setDetailThai("Detail: " + subject18.getNameThai());
		subjectList.add(subject18);

		Subject subject19 = new Subject();
		subject19.setId(19);
		subject19.setSubjectCode("INT 652");
		subject19.setNameThai("���ǡ����Թ������");
		subject19.setCredit("3");
		subject19.setDetailThai("Detail: " + subject19.getNameThai());
		subjectList.add(subject19);

		Subject subject20 = new Subject();
		subject20.setId(20);
		subject20.setSubjectCode("INT 653");
		subject20.setNameThai("෤����������");
		subject20.setCredit("3");
		subject20.setDetailThai("Detail: " + subject20.getNameThai());
		subjectList.add(subject20);

		Subject subject21 = new Subject();
		subject21.setId(21);
		subject21.setSubjectCode("INT 654");
		subject21.setNameThai("��û����ż�Ẻ���Ǵ���С�û���ء��");
		subject21.setCredit("3");
		subject21.setDetailThai("Detail: " + subject21.getNameThai());
		subjectList.add(subject21);

		Subject subject22 = new Subject();
		subject22.setId(22);
		subject22.setSubjectCode("INT 671");
		subject22.setNameThai("�������ԧ��Ժѵԡ�á�èѴ��ðҹ�������ԧ��áԨ");
		subject22.setCredit("3");
		subject22.setDetailThai("Detail: " + subject22.getNameThai());
		subjectList.add(subject22);

		Subject subject23 = new Subject();
		subject23.setId(23);
		subject23.setSubjectCode("INT 672");
		subject23.setNameThai("�������ԧ��Ժѵԡ�þѲ�Ҥ�ѧ������");
		subject23.setCredit("3");
		subject23.setDetailThai("Detail: " + subject23.getNameThai());
		subjectList.add(subject23);

		Subject subject24 = new Subject();
		subject24.setId(24);
		subject24.setSubjectCode("INT 673");
		subject24.setNameThai("�������ԧ��Ժѵԡ�����͢�������Ѻ�ҹѡ�ҹ");
		subject24.setCredit("3");
		subject24.setDetailThai("Detail: " + subject24.getNameThai());
		subjectList.add(subject24);

		Subject subject25 = new Subject();
		subject25.setId(25);
		subject25.setSubjectCode("INT 674");
		subject25.setNameThai("�������ԧ��Ժѵԡ�����͢�������Ѻͧ��â�Ҵ�˭�");
		subject25.setCredit("3");
		subject25.setDetailThai("Detail: " + subject25.getNameThai());
		subjectList.add(subject25);

		Subject subject26 = new Subject();
		subject26.setId(26);
		subject26.setSubjectCode("INT 675");
		subject26.setNameThai("�������ԧ��Ժѵԡ�á����¹���������");
		subject26.setCredit("3");
		subject26.setDetailThai("Detail: " + subject26.getNameThai());
		subjectList.add(subject26);

		Subject subject27 = new Subject();
		subject27.setId(27);
		subject27.setSubjectCode("INT 676");
		subject27.setNameThai("�������ԧ��Ժѵԡ�á����¹������������������䫵�");
		subject27.setCredit("3");
		subject27.setDetailThai("Detail: " + subject27.getNameThai());
		subjectList.add(subject27);

		Subject subject28 = new Subject();
		subject28.setId(28);
		subject28.setSubjectCode("INT 677");
		subject28.setNameThai("�������ԧ��Ժѵԡ���ҵðҹ�������ԡ�ô�ҹ෤��������ʹ��");
		subject28.setCredit("3");
		subject28.setDetailThai("Detail: " + subject28.getNameThai());
		subjectList.add(subject28);

		Subject subject29 = new Subject();
		subject29.setId(29);
		subject29.setSubjectCode("INT 678");
		subject29.setNameThai("�������ԧ��Ժѵԡ�ä���������Ẻ���Ǵ�");
		subject29.setCredit("3");
		subject29.setDetailThai("Detail: " + subject29.getNameThai());
		subjectList.add(subject29);

		Subject subject30 = new Subject();
		subject30.setId(30);
		subject30.setSubjectCode("SWE 601");
		subject30.setNameThai("��ѡ������ǡ����Ϳ������");
		subject30.setCredit("3");
		subject30.setDetailThai("Detail: " + subject30.getNameThai());
		subjectList.add(subject30);

		Subject subject31 = new Subject();
		subject31.setId(31);
		subject31.setSubjectCode("SWE 602");
		subject31.setNameThai("���������������͡Ẻ�к�����٧");
		subject31.setCredit("3");
		subject31.setDetailThai("Detail: " + subject31.getNameThai());
		subjectList.add(subject31);

		Subject subject32 = new Subject();
		subject32.setId(32);
		subject32.setSubjectCode("SWE 603");
		subject32.setNameThai("��ú������ç��ëͿ������");
		subject32.setCredit("3");
		subject32.setDetailThai("Detail: " + subject32.getNameThai());
		subjectList.add(subject32);

		Subject subject33 = new Subject();
		subject33.setId(33);
		subject33.setSubjectCode("SWE 604");
		subject33.setNameThai("�ç���ҧ���ʶһѵ¡����Ϳ������");
		subject33.setCredit("3");
		subject33.setDetailThai("Detail: " + subject33.getNameThai());
		subjectList.add(subject33);

		Subject subject34 = new Subject();
		subject34.setId(34);
		subject34.setSubjectCode("SWE 605");
		subject34.setNameThai("��÷ǹ�ͺ��С�õ�Ǩ�ͺ�������˵����Ţͧ�Ϳ������");
		subject34.setCredit("3");
		subject34.setDetailThai("Detail: " + subject34.getNameThai());
		subjectList.add(subject34);

		Subject subject35 = new Subject();
		subject35.setId(35);
		subject35.setSubjectCode("SWE 610");
		subject35.setNameThai("����͡Ẻ�Ϳ������������ѧ���");
		subject35.setCredit("3");
		subject35.setDetailThai("Detail: " + subject35.getNameThai());
		subjectList.add(subject35);

		Subject subject36 = new Subject();
		subject36.setId(36);
		subject36.setSubjectCode("SWE 611");
		subject36.setNameThai("����͡Ẻ�������ѹ��");
		subject36.setCredit("3");
		subject36.setDetailThai("Detail: " + subject36.getNameThai());
		subjectList.add(subject36);

		Subject subject37 = new Subject();
		subject37.setId(37);
		subject37.setSubjectCode("SWE 612");
		subject37.setNameThai("��û����,��Ẻ���Ǵ�");
		subject37.setCredit("3");
		subject37.setDetailThai("Detail: " + subject37.getNameThai());
		subjectList.add(subject37);

		Subject subject38 = new Subject();
		subject38.setId(38);
		subject38.setSubjectCode("SWE 613");
		subject38.setNameThai("�к�Ẻ��Ш�����Ẻ��ҹ");
		subject38.setCredit("3");
		subject38.setDetailThai("Detail: " + subject38.getNameThai());
		subjectList.add(subject38);

		Subject subject39 = new Subject();
		subject39.setId(39);
		subject39.setSubjectCode("SWE 614");
		subject39.setNameThai("����͡Ẻ�Ϳ������Ẻ���ҹ��ͤ��������ͧ");
		subject39.setCredit("3");
		subject39.setDetailThai("Detail: " + subject39.getNameThai());
		subjectList.add(subject39);

		Subject subject40 = new Subject();
		subject40.setId(40);
		subject40.setSubjectCode("SWE 615");
		subject40.setNameThai("����͡Ẻ�͹����ѹ�����");
		subject40.setCredit("3");
		subject40.setDetailThai("Detail: " + subject40.getNameThai());
		subjectList.add(subject40);

		Subject subject41 = new Subject();
		subject41.setId(41);
		subject41.setSubjectCode("SWE 616");
		subject41.setNameThai("��û����ż�����Ѻ�ػ�ó�����͹�������ͻ���पѹ");
		subject41.setCredit("3");
		subject41.setDetailThai("Detail: " + subject41.getNameThai());
		subjectList.add(subject41);

		Subject subject42 = new Subject();
		subject42.setId(42);
		subject42.setSubjectCode("SWE 617");
		subject42.setNameThai("��������������Ѻ���ǡëͿ������");
		subject42.setCredit("3");
		subject42.setDetailThai("Detail: " + subject42.getNameThai());
		subjectList.add(subject42);

		Subject subject43 = new Subject();
		subject43.setId(43);
		subject43.setSubjectCode("SWE 630");
		subject43.setNameThai("��þѲ�ҫͿ������Ẻ����");
		subject43.setCredit("3");
		subject43.setDetailThai("Detail: " + subject43.getNameThai());
		subjectList.add(subject43);

		Subject subject44 = new Subject();
		subject44.setId(44);
		subject44.setSubjectCode("SWE 631");
		subject44.setNameThai("��þѲ�ҫͿ������Ẻ��Ш��");
		subject44.setCredit("3");
		subject44.setDetailThai("Detail: " + subject44.getNameThai());
		subjectList.add(subject44);

		Subject subject45 = new Subject();
		subject45.setId(45);
		subject45.setSubjectCode("SWE 632");
		subject45.setNameThai("�к���Ժѵԡ������к����͢���");
		subject45.setCredit("3");
		subject45.setDetailThai("Detail: " + subject45.getNameThai());
		subjectList.add(subject45);

		Subject subject46 = new Subject();
		subject46.setId(46);
		subject46.setSubjectCode("SWE 633");
		subject46.setNameThai("��þѲ�����������ء������Ѻͧ��â�Ҵ�˭�");
		subject46.setCredit("3");
		subject46.setDetailThai("Detail: " + subject46.getNameThai());
		subjectList.add(subject46);

		Subject subject47 = new Subject();
		subject47.setId(47);
		subject47.setSubjectCode("SWE 634");
		subject47.setNameThai("��ú��ا�ѡ�ҫͿ������");
		subject47.setCredit("3");
		subject47.setDetailThai("Detail: " + subject47.getNameThai());
		subjectList.add(subject47);

		Subject subject48 = new Subject();
		subject48.setId(48);
		subject48.setSubjectCode("SWE 640");
		subject48.setNameThai("��èѴ��â�͡�˹������觵�ͧ��÷ҧ�Ϳ������");
		subject48.setCredit("3");
		subject48.setDetailThai("Detail: " + subject48.getNameThai());
		subjectList.add(subject48);

		Subject subject49 = new Subject();
		subject49.setId(49);
		subject49.setSubjectCode("SWE 641");
		subject49.setNameThai("��èѴ����çẺ�Ϳ������");
		subject49.setCredit("3");
		subject49.setDetailThai("Detail: " + subject49.getNameThai());
		subjectList.add(subject49);

		Subject subject50 = new Subject();
		subject50.setId(50);
		subject50.setSubjectCode("SWE 650");
		subject50.setNameThai("��û�Ѻ��ا��кǹ��ëͿ������");
		subject50.setCredit("3");
		subject50.setDetailThai("Detail: " + subject50.getNameThai());
		subjectList.add(subject50);

		Subject subject51 = new Subject();
		subject51.setId(51);
		subject51.setSubjectCode("SWE 651");
		subject51.setNameThai("�ҵ��Ѵ�ūͿ������");
		subject51.setCredit("3");
		subject51.setDetailThai("Detail: " + subject51.getNameThai());
		subjectList.add(subject51);

		Subject subject52 = new Subject();
		subject52.setId(52);
		subject52.setSubjectCode("SWE 652");
		subject52.setNameThai("��û�Сѹ�س�Ҿ�Ϳ������");
		subject52.setCredit("3");
		subject52.setDetailThai("Detail: " + subject52.getNameThai());
		subjectList.add(subject52);

		Subject subject53 = new Subject();
		subject53.setId(53);
		subject53.setSubjectCode("SWE 660");
		subject53.setNameThai("����ͧ�������Ѻ�ҹ���ǡ����Ϳ������");
		subject53.setCredit("3");
		subject53.setDetailThai("Detail: " + subject53.getNameThai());
		subjectList.add(subject53);

		Subject subject54 = new Subject();
		subject54.setId(54);
		subject54.setSubjectCode("SWE 661");
		subject54.setNameThai("�����¹�������С�þѲ�Һ����");
		subject54.setCredit("3");
		subject54.setDetailThai("Detail: " + subject54.getNameThai());
		subjectList.add(subject54);

		Subject subject55 = new Subject();
		subject55.setId(55);
		subject55.setSubjectCode("SWE 670");
		subject55.setNameThai("�������ԧ��Ժѵԡ����¹�������С�èѴ��ðҹ������");
		subject55.setCredit("3");
		subject55.setDetailThai("Detail: " + subject55.getNameThai());
		subjectList.add(subject55);

		Subject subject56 = new Subject();
		subject56.setId(56);
		subject56.setSubjectCode("SWE 671");
		subject56.setNameThai("�������ԧ��Ժѵԡ�÷ҧ���ǡ����Ϳ������ 1");
		subject56.setCredit("3");
		subject56.setDetailThai("Detail: " + subject56.getNameThai());
		subjectList.add(subject56);

		Subject subject57 = new Subject();
		subject57.setId(57);
		subject57.setSubjectCode("SWE 672");
		subject57.setNameThai("�������ԧ��Ժѵԡ�÷ҧ���ǡ����Ϳ������ 2");
		subject57.setCredit("3");
		subject57.setDetailThai("Detail: " + subject57.getNameThai());
		subjectList.add(subject57);

		Subject subject58 = new Subject();
		subject58.setId(58);
		subject58.setSubjectCode("SWE 690");
		subject58.setNameThai("����֡��੾������ͧ�ҧ���ǡ����Ϳ������ 1");
		subject58.setCredit("3");
		subject58.setDetailThai("Detail: " + subject58.getNameThai());
		subjectList.add(subject58);

		Subject subject59 = new Subject();
		subject59.setId(59);
		subject59.setSubjectCode("SWE 691");
		subject59.setNameThai("����֡��੾������ͧ�ҧ���ǡ����Ϳ������ 2");
		subject59.setCredit("3");
		subject59.setDetailThai("Detail: " + subject59.getNameThai());
		subjectList.add(subject59);

		Subject subject60 = new Subject();
		subject60.setId(60);
		subject60.setSubjectCode("SWE 692");
		subject60.setNameThai("����֡��੾������ͧ�ҧ���ǡ����Ϳ������ 3");
		subject60.setCredit("3");
		subject60.setDetailThai("Detail: " + subject60.getNameThai());
		subjectList.add(subject60);

		Subject subject61 = new Subject();
		subject61.setId(61);
		subject61.setSubjectCode("SWE 700");
		subject61.setNameThai("�Է�ҹԾ���");
		subject61.setCredit("3");
		subject61.setDetailThai("Detail: " + subject61.getNameThai());
		subjectList.add(subject61);

		Subject subject62 = new Subject();
		subject62.setId(62);
		subject62.setSubjectCode("SWE 701");
		subject62.setNameThai("����֡���ç���੾������ͧ");
		subject62.setCredit("3");
		subject62.setDetailThai("Detail: " + subject62.getNameThai());
		subjectList.add(subject62);

		Subject subject63 = new Subject();
		subject63.setId(63);
		subject63.setSubjectCode("SWE 702");
		subject63.setNameThai("����º�Ըա���Ԩ��");
		subject63.setCredit("3");
		subject63.setDetailThai("Detail: " + subject63.getNameThai());
		subjectList.add(subject63);

		return subjectList;
	}
}
