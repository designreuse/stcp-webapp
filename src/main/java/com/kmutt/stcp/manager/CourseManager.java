package com.kmutt.stcp.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kmutt.stcp.service.CoursePlannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kmutt.stcp.entity.*;
import com.kmutt.stcp.repository.SubjectRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("courseManager")
public class CourseManager {

    // Field//
    private final Logger logger = LoggerFactory.getLogger(CourseManager.class);

    @Autowired
	private CoursePlannerService coursePlannerService;

	@Autowired
    private SubjectRepository subjectRepository;

    private List<Subject> subjectList;
    private Account student;

    // Constructor//
    public CourseManager() { }
    public CourseManager(Account student) {
        this.student = student;
    }

    // Method//
	public List<Subject> testCourseManager() {
        return coursePlannerService.getSubjectListTest();
	}

    public List<Subject> getSubjectList() {

        // TODO: should get subjectList from Common Entity module.
        //this.subjectList = this.subjectRepository.findAll();
        this.subjectList = this.dummySubjectList(this.student);
        return this.subjectList;

    }

    public Subject getSubjectByCode(String code) {

        if (this.subjectList == null) {
            this.subjectList = this.getSubjectList();
        }

        Subject resultSubject;

        try {

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

	public List<Subject> searchSubject(String criteria){
		
		if (this.subjectList == null) {
			this.subjectList = this.getSubjectList();
		}

		List<Subject> resultSubject  = new ArrayList<Subject>();

		try {
			
			if(criteria != null && !criteria.isEmpty()) {

				//Should change this to get from Common Entity module.
				// TODO: resultSubject = this.subjectRepository.findAll().stream()
				resultSubject = this.subjectList.stream()
						.filter(subject -> {													
							if (subject.getSubjectCode().toLowerCase().contains(criteria.toLowerCase())
									|| subject.getNameThai().toLowerCase().contains(criteria.toLowerCase())) {
								return true;														
							} else {														
								return false;														
							}
						}).collect(Collectors.toList());
			} else {
				
				resultSubject = this.subjectList;
				
			}
				
			
		} catch (Exception e) {

			logger.error("Method:searchSubject|Err:" + e.getMessage());
			resultSubject = new ArrayList<Subject>();

		}

		return resultSubject;

	}
	
	//Dummy//
private List<Subject> dummySubjectList(Account student) {
		
		subjectList = new ArrayList<>();

        Subject subject1 = new Subject();
        subject1.setId(1);
        subject1.setSubjectCode("INT 610");
        subject1.setNameThai("ระบบสนับสนุนการตัดสินใจ");
        subject1.setCredit("3");
        subject1.setDetailThai("Detail: " + subject1.getNameThai());
        subjectList.add(subject1);

        Subject subject2 = new Subject();
        subject2.setId(2);
        subject2.setSubjectCode("INT 611");
        subject2.setNameThai("การเงินเชิงกลยุทธ์สาหรับเทคโนโลยีสารสนเทศ");
        subject2.setCredit("3");
        subject2.setDetailThai("Detail: " + subject2.getNameThai());
        subjectList.add(subject2);

        Subject subject3 = new Subject();
        subject3.setId(3);
        subject3.setSubjectCode("INT 613");
        subject3.setNameThai("การบริหารความมั่นคงของสารสนเทศ");
        subject3.setCredit("3");
        subject3.setDetailThai("Detail: " + subject3.getNameThai());
        subjectList.add(subject3);

        Subject subject4 = new Subject();
        subject4.setId(4);
        subject4.setSubjectCode("INT 615");
        subject4.setNameThai("การจัดการคุณภาพสารสนเทศ");
        subject4.setCredit("3");
        subject4.setDetailThai("Detail: " + subject4.getNameThai());
        subjectList.add(subject4);

        Subject subject5 = new Subject();
        subject5.setId(5);
        subject5.setSubjectCode("INT 616");
        subject5.setNameThai("การวางแผนทรัพยากรองค์กร");
        subject5.setCredit("3");
        subject5.setDetailThai("Detail: " + subject5.getNameThai());
        subjectList.add(subject5);

        Subject subject6 = new Subject();
        subject6.setId(6);
        subject6.setSubjectCode("INT 617");
        subject6.setNameThai("การยอมรับการใช้งานเทคโนโลยีสารสนเทศ");
        subject6.setCredit("3");
        subject6.setDetailThai("Detail: " + subject6.getNameThai());
        subjectList.add(subject6);

        Subject subject7 = new Subject();
        subject7.setId(7);
        subject7.setSubjectCode("INT 630");
        subject7.setNameThai("เทคโนโลยีฐานข้อมูล");
        subject7.setCredit("3");
        subject7.setDetailThai("Detail: " + subject7.getNameThai());
        subjectList.add(subject7);

        Subject subject8 = new Subject();
        subject8.setId(8);
        subject8.setSubjectCode("INT 631");
        subject8.setNameThai("เทคโนโลยีเชิงอ็อบเจกต์");
        subject8.setCredit("3");
        subject8.setDetailThai("Detail: " + subject8.getNameThai());
        subjectList.add(subject8);

        Subject subject9 = new Subject();
        subject9.setId(9);
        subject9.setSubjectCode("INT 632");
        subject9.setNameThai("ปัญญาประดิษฐ์");
        subject9.setCredit("3");
        subject9.setDetailThai("Detail: " + subject9.getNameThai());
        subjectList.add(subject9);

        Subject subject10 = new Subject();
        subject10.setId(10);
        subject10.setSubjectCode("INT 633");
        subject10.setNameThai("เทคโนโลยีมัลติมีเดีย");
        subject10.setCredit("3");
        subject10.setDetailThai("Detail: " + subject10.getNameThai());
        subjectList.add(subject10);

        Subject subject11 = new Subject();
        subject11.setId(11);
        subject11.setSubjectCode("INT 635");
        subject11.setNameThai("การค้นคืนสารสนเทศ");
        subject11.setCredit("3");
        subject11.setDetailThai("Detail: " + subject11.getNameThai());
        subjectList.add(subject11);

        Subject subject12 = new Subject();
        subject12.setId(12);
        subject12.setSubjectCode("INT 636");
        subject12.setNameThai("ปฏิสัมพันธ์ระหว่างคอมพิวเตอร์กับมนุษย์");
        subject12.setCredit("3");
        subject12.setDetailThai("Detail: " + subject12.getNameThai());
        subjectList.add(subject12);

        Subject subject13 = new Subject();
        subject13.setId(13);
        subject13.setSubjectCode("INT 637");
        subject13.setNameThai("การจัดการความรู้");
        subject13.setCredit("3");
        subject13.setDetailThai("Detail: " + subject13.getNameThai());
        subjectList.add(subject13);

        Subject subject14 = new Subject();
        subject14.setId(14);
        subject14.setSubjectCode("INT 638");
        subject14.setNameThai("การทาเหมืองข้อมูล");
        subject14.setCredit("3");
        subject14.setDetailThai("Detail: " + subject14.getNameThai());
        subjectList.add(subject14);

        Subject subject15 = new Subject();
        subject15.setId(15);
        subject15.setSubjectCode("INT 640");
        subject15.setNameThai("เทคโนโลยีเอ็กซ์เอ็มแอล");
        subject15.setCredit("3");
        subject15.setDetailThai("Detail: " + subject15.getNameThai());
        subjectList.add(subject15);

        Subject subject16 = new Subject();
        subject16.setId(16);
        subject16.setSubjectCode("INT 641");
        subject16.setNameThai("การควบคุมและตรวจสอบเทคโนโลยีสารสนเทศ");
        subject16.setCredit("3");
        subject16.setDetailThai("Detail: " + subject16.getNameThai());
        subjectList.add(subject16);

        Subject subject17 = new Subject();
        subject17.setId(17);
        subject17.setSubjectCode("INT 650");
        subject17.setNameThai("ความมั่นคงของเทคโนโลยีสารสนเทศ");
        subject17.setCredit("3");
        subject17.setDetailThai("Detail: " + subject17.getNameThai());
        subjectList.add(subject17);

        Subject subject18 = new Subject();
        subject18.setId(18);
        subject18.setSubjectCode("INT 651");
        subject18.setNameThai("เทคโนโลยีโทรคมนาคม");
        subject18.setCredit("3");
        subject18.setDetailThai("Detail: " + subject18.getNameThai());
        subjectList.add(subject18);

        Subject subject19 = new Subject();
        subject19.setId(19);
        subject19.setSubjectCode("INT 652");
        subject19.setNameThai("วิศวกรรมอินเทอร์เน็ต");
        subject19.setCredit("3");
        subject19.setDetailThai("Detail: " + subject19.getNameThai());
        subjectList.add(subject19);

        Subject subject20 = new Subject();
        subject20.setId(20);
        subject20.setSubjectCode("INT 653");
        subject20.setNameThai("เทคโนโลยีโมบาย");
        subject20.setCredit("3");
        subject20.setDetailThai("Detail: " + subject20.getNameThai());
        subjectList.add(subject20);

        Subject subject21 = new Subject();
        subject21.setId(21);
        subject21.setSubjectCode("INT 654");
        subject21.setNameThai("การประมวลผลแบบคลาวด์และการประยุกต์");
        subject21.setCredit("3");
        subject21.setDetailThai("Detail: " + subject21.getNameThai());
        subjectList.add(subject21);

        Subject subject22 = new Subject();
        subject22.setId(22);
        subject22.setSubjectCode("INT 671");
        subject22.setNameThai("สัมมนาเชิงปฏิบัติการการจัดการฐานข้อมูลเชิงธุรกิจ");
        subject22.setCredit("3");
        subject22.setDetailThai("Detail: " + subject22.getNameThai());
        subjectList.add(subject22);

        Subject subject23 = new Subject();
        subject23.setId(23);
        subject23.setSubjectCode("INT 672");
        subject23.setNameThai("สัมมนาเชิงปฏิบัติการพัฒนาคลังข้อมูล");
        subject23.setCredit("3");
        subject23.setDetailThai("Detail: " + subject23.getNameThai());
        subjectList.add(subject23);

        Subject subject24 = new Subject();
        subject24.setId(24);
        subject24.setSubjectCode("INT 673");
        subject24.setNameThai("สัมมนาเชิงปฏิบัติการเครือข่ายสาหรับสานักงาน");
        subject24.setCredit("3");
        subject24.setDetailThai("Detail: " + subject24.getNameThai());
        subjectList.add(subject24);

        Subject subject25 = new Subject();
        subject25.setId(25);
        subject25.setSubjectCode("INT 674");
        subject25.setNameThai("สัมมนาเชิงปฏิบัติการเครือข่ายสาหรับองค์กรขนาดใหญ่");
        subject25.setCredit("3");
        subject25.setDetailThai("Detail: " + subject25.getNameThai());
        subjectList.add(subject25);

        Subject subject26 = new Subject();
        subject26.setId(26);
        subject26.setSubjectCode("INT 675");
        subject26.setNameThai("สัมมนาเชิงปฏิบัติการการเขียนโปรแกรมจาวา");
        subject26.setCredit("3");
        subject26.setDetailThai("Detail: " + subject26.getNameThai());
        subjectList.add(subject26);

        Subject subject27 = new Subject();
        subject27.setId(27);
        subject27.setSubjectCode("INT 676");
        subject27.setNameThai("สัมมนาเชิงปฏิบัติการการเขียนโปรแกรมจาวาเซิร์ฟเวอร์ไซต์");
        subject27.setCredit("3");
        subject27.setDetailThai("Detail: " + subject27.getNameThai());
        subjectList.add(subject27);

        Subject subject28 = new Subject();
        subject28.setId(28);
        subject28.setSubjectCode("INT 677");
        subject28.setNameThai("สัมมนาเชิงปฏิบัติการมาตรฐานการให้บริการด้านเทคโนโลยีสารสนเทศ");
        subject28.setCredit("3");
        subject28.setDetailThai("Detail: " + subject28.getNameThai());
        subjectList.add(subject28);

        Subject subject29 = new Subject();
        subject29.setId(29);
        subject29.setSubjectCode("INT 678");
        subject29.setNameThai("สัมมนาเชิงปฏิบัติการคอมพิวเตอร์แบบคลาวด์");
        subject29.setCredit("3");
        subject29.setDetailThai("Detail: " + subject29.getNameThai());
        subjectList.add(subject29);

        Subject subject30 = new Subject();
        subject30.setId(30);
        subject30.setSubjectCode("SWE 601");
        subject30.setNameThai("หลักการวิศวกรรมซอฟต์แวร์");
        subject30.setCredit("3");
        subject30.setDetailThai("Detail: " + subject30.getNameThai());
        subjectList.add(subject30);

        Subject subject31 = new Subject();
        subject31.setId(31);
        subject31.setSubjectCode("SWE 602");
        subject31.setNameThai("การวิเคราะห์และออกแบบระบบขั้นสูง");
        subject31.setCredit("3");
        subject31.setDetailThai("Detail: " + subject31.getNameThai());
        subjectList.add(subject31);

        Subject subject32 = new Subject();
        subject32.setId(32);
        subject32.setSubjectCode("SWE 603");
        subject32.setNameThai("การบริหารโครงการซอฟต์แวร์");
        subject32.setCredit("3");
        subject32.setDetailThai("Detail: " + subject32.getNameThai());
        subjectList.add(subject32);

        Subject subject33 = new Subject();
        subject33.setId(33);
        subject33.setSubjectCode("SWE 604");
        subject33.setNameThai("โครงสร้างและสถาปัตยกรรมซอฟต์แวร์");
        subject33.setCredit("3");
        subject33.setDetailThai("Detail: " + subject33.getNameThai());
        subjectList.add(subject33);

        Subject subject34 = new Subject();
        subject34.setId(34);
        subject34.setSubjectCode("SWE 605");
        subject34.setNameThai("การทวนสอบและการตรวจสอบความสมเหตุสมผลของซอฟต์แวร์");
        subject34.setCredit("3");
        subject34.setDetailThai("Detail: " + subject34.getNameThai());
        subjectList.add(subject34);

        Subject subject35 = new Subject();
        subject35.setId(35);
        subject35.setSubjectCode("SWE 610");
        subject35.setNameThai("การออกแบบซอฟต์แวร์ประเภทฝังตัว");
        subject35.setCredit("3");
        subject35.setDetailThai("Detail: " + subject35.getNameThai());
        subjectList.add(subject35);

        Subject subject36 = new Subject();
        subject36.setId(36);
        subject36.setSubjectCode("SWE 611");
        subject36.setNameThai("การออกแบบปฏิสัมพันธ์");
        subject36.setCredit("3");
        subject36.setDetailThai("Detail: " + subject36.getNameThai());
        subjectList.add(subject36);

        Subject subject37 = new Subject();
        subject37.setId(37);
        subject37.setSubjectCode("SWE 612");
        subject37.setNameThai("การประมว,ผลแบบคลาวด์");
        subject37.setCredit("3");
        subject37.setDetailThai("Detail: " + subject37.getNameThai());
        subjectList.add(subject37);

        Subject subject38 = new Subject();
        subject38.setId(38);
        subject38.setSubjectCode("SWE 613");
        subject38.setNameThai("ระบบแบบกระจายและแบบขนาน");
        subject38.setCredit("3");
        subject38.setDetailThai("Detail: " + subject38.getNameThai());
        subjectList.add(subject38);

        Subject subject39 = new Subject();
        subject39.setId(39);
        subject39.setSubjectCode("SWE 614");
        subject39.setNameThai("การออกแบบซอฟต์แวร์แบบทนทานต่อความบกพร่อง");
        subject39.setCredit("3");
        subject39.setDetailThai("Detail: " + subject39.getNameThai());
        subjectList.add(subject39);

        Subject subject40 = new Subject();
        subject40.setId(40);
        subject40.setSubjectCode("SWE 615");
        subject40.setNameThai("การออกแบบแอนิเมชันและเกม");
        subject40.setCredit("3");
        subject40.setDetailThai("Detail: " + subject40.getNameThai());
        subjectList.add(subject40);

        Subject subject41 = new Subject();
        subject41.setId(41);
        subject41.setSubjectCode("SWE 616");
        subject41.setNameThai("การประมวลผลสาหรับอุปกรณ์เคลื่อนที่และแอปพลิเคชัน");
        subject41.setCredit("3");
        subject41.setDetailThai("Detail: " + subject41.getNameThai());
        subjectList.add(subject41);

        Subject subject42 = new Subject();
        subject42.setId(42);
        subject42.setSubjectCode("SWE 617");
        subject42.setNameThai("การสื่อสารสาหรับวิศวกรซอฟต์แวร์");
        subject42.setCredit("3");
        subject42.setDetailThai("Detail: " + subject42.getNameThai());
        subjectList.add(subject42);

        Subject subject43 = new Subject();
        subject43.setId(43);
        subject43.setSubjectCode("SWE 630");
        subject43.setNameThai("การพัฒนาซอฟต์แวร์แบบอไจล์");
        subject43.setCredit("3");
        subject43.setDetailThai("Detail: " + subject43.getNameThai());
        subjectList.add(subject43);

        Subject subject44 = new Subject();
        subject44.setId(44);
        subject44.setSubjectCode("SWE 631");
        subject44.setNameThai("การพัฒนาซอฟต์แวร์แบบกระจาย");
        subject44.setCredit("3");
        subject44.setDetailThai("Detail: " + subject44.getNameThai());
        subjectList.add(subject44);

        Subject subject45 = new Subject();
        subject45.setId(45);
        subject45.setSubjectCode("SWE 632");
        subject45.setNameThai("ระบบปฏิบัติการและระบบเครือข่าย");
        subject45.setCredit("3");
        subject45.setDetailThai("Detail: " + subject45.getNameThai());
        subjectList.add(subject45);

        Subject subject46 = new Subject();
        subject46.setId(46);
        subject46.setSubjectCode("SWE 633");
        subject46.setNameThai("การพัฒนาโปรแกรมประยุกต์สาหรับองค์กรขนาดใหญ่");
        subject46.setCredit("3");
        subject46.setDetailThai("Detail: " + subject46.getNameThai());
        subjectList.add(subject46);

        Subject subject47 = new Subject();
        subject47.setId(47);
        subject47.setSubjectCode("SWE 634");
        subject47.setNameThai("การบารุงรักษาซอฟต์แวร์");
        subject47.setCredit("3");
        subject47.setDetailThai("Detail: " + subject47.getNameThai());
        subjectList.add(subject47);

        Subject subject48 = new Subject();
        subject48.setId(48);
        subject48.setSubjectCode("SWE 640");
        subject48.setNameThai("การจัดการข้อกาหนดและสิ่งต้องการทางซอฟต์แวร์");
        subject48.setCredit("3");
        subject48.setDetailThai("Detail: " + subject48.getNameThai());
        subjectList.add(subject48);

        Subject subject49 = new Subject();
        subject49.setId(49);
        subject49.setSubjectCode("SWE 641");
        subject49.setNameThai("การจัดการโครงแบบซอฟต์แวร์");
        subject49.setCredit("3");
        subject49.setDetailThai("Detail: " + subject49.getNameThai());
        subjectList.add(subject49);

        Subject subject50 = new Subject();
        subject50.setId(50);
        subject50.setSubjectCode("SWE 650");
        subject50.setNameThai("การปรับปรุงกระบวนการซอฟต์แวร์");
        subject50.setCredit("3");
        subject50.setDetailThai("Detail: " + subject50.getNameThai());
        subjectList.add(subject50);

        Subject subject51 = new Subject();
        subject51.setId(51);
        subject51.setSubjectCode("SWE 651");
        subject51.setNameThai("มาตรวัดผลซอฟต์แวร์");
        subject51.setCredit("3");
        subject51.setDetailThai("Detail: " + subject51.getNameThai());
        subjectList.add(subject51);

        Subject subject52 = new Subject();
        subject52.setId(52);
        subject52.setSubjectCode("SWE 652");
        subject52.setNameThai("การประกันคุณภาพซอฟต์แวร์");
        subject52.setCredit("3");
        subject52.setDetailThai("Detail: " + subject52.getNameThai());
        subjectList.add(subject52);

        Subject subject53 = new Subject();
        subject53.setId(53);
        subject53.setSubjectCode("SWE 660");
        subject53.setNameThai("เครื่องมือสาหรับงานวิศวกรรมซอฟต์แวร์");
        subject53.setCredit("3");
        subject53.setDetailThai("Detail: " + subject53.getNameThai());
        subjectList.add(subject53);

        Subject subject54 = new Subject();
        subject54.setId(54);
        subject54.setSubjectCode("SWE 661");
        subject54.setNameThai("การเขียนโปรแกรมและการพัฒนาบนเว็บ");
        subject54.setCredit("3");
        subject54.setDetailThai("Detail: " + subject54.getNameThai());
        subjectList.add(subject54);

        Subject subject55 = new Subject();
        subject55.setId(55);
        subject55.setSubjectCode("SWE 670");
        subject55.setNameThai("สัมมนาเชิงปฏิบัติการเขียนโปรแกรมและการจัดการฐานข้อมูล");
        subject55.setCredit("3");
        subject55.setDetailThai("Detail: " + subject55.getNameThai());
        subjectList.add(subject55);

        Subject subject56 = new Subject();
        subject56.setId(56);
        subject56.setSubjectCode("SWE 671");
        subject56.setNameThai("สัมมนาเชิงปฏิบัติการทางวิศวกรรมซอฟต์แวร์ 1");
        subject56.setCredit("3");
        subject56.setDetailThai("Detail: " + subject56.getNameThai());
        subjectList.add(subject56);

        Subject subject57 = new Subject();
        subject57.setId(57);
        subject57.setSubjectCode("SWE 672");
        subject57.setNameThai("สัมมนาเชิงปฏิบัติการทางวิศวกรรมซอฟต์แวร์ 2");
        subject57.setCredit("3");
        subject57.setDetailThai("Detail: " + subject57.getNameThai());
        subjectList.add(subject57);

        Subject subject58 = new Subject();
        subject58.setId(58);
        subject58.setSubjectCode("SWE 690");
        subject58.setNameThai("การศึกษาเฉพาะเรื่องทางวิศวกรรมซอฟต์แวร์ 1");
        subject58.setCredit("3");
        subject58.setDetailThai("Detail: " + subject58.getNameThai());
        subjectList.add(subject58);

        Subject subject59 = new Subject();
        subject59.setId(59);
        subject59.setSubjectCode("SWE 691");
        subject59.setNameThai("การศึกษาเฉพาะเรื่องทางวิศวกรรมซอฟต์แวร์ 2");
        subject59.setCredit("3");
        subject59.setDetailThai("Detail: " + subject59.getNameThai());
        subjectList.add(subject59);

        Subject subject60 = new Subject();
        subject60.setId(60);
        subject60.setSubjectCode("SWE 692");
        subject60.setNameThai("การศึกษาเฉพาะเรื่องทางวิศวกรรมซอฟต์แวร์ 3");
        subject60.setCredit("3");
        subject60.setDetailThai("Detail: " + subject60.getNameThai());
        subjectList.add(subject60);

        Subject subject61 = new Subject();
        subject61.setId(61);
        subject61.setSubjectCode("SWE 700");
        subject61.setNameThai("วิทยานิพนธ์");
        subject61.setCredit("3");
        subject61.setDetailThai("Detail: " + subject61.getNameThai());
        subjectList.add(subject61);

        Subject subject62 = new Subject();
        subject62.setId(62);
        subject62.setSubjectCode("SWE 701");
        subject62.setNameThai("การศึกษาโครงการเฉพาะเรื่อง");
        subject62.setCredit("3");
        subject62.setDetailThai("Detail: " + subject62.getNameThai());
        subjectList.add(subject62);

        Subject subject63 = new Subject();
        subject63.setId(63);
        subject63.setSubjectCode("SWE 702");
        subject63.setNameThai("ระเบียบวิธีการวิจัย");
        subject63.setCredit("3");
        subject63.setDetailThai("Detail: " + subject63.getNameThai());
        subjectList.add(subject63);

		return subjectList;
	}
}
