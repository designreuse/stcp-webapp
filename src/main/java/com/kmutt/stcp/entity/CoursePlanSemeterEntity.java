package com.kmutt.stcp.entity;

import java.util.ArrayList;
import java.util.List;

public class CoursePlanSemeterEntity {

	// Field //
	private int semeterPlanID;
	private int semeterPlanYear;
	private int semeterPlanPart;
	private List<Course> courseList;

	// Property //
	public int getSemeterPlanID() {
		return semeterPlanID;
	}

	public void setSemeterPlanID(int semeterPlanID) {
		this.semeterPlanID = semeterPlanID;
	}

	public int getSemeterPlanYear() {
		return semeterPlanYear;
	}

	public void setSemeterPlanYear(int semeterPlanYear) {
		this.semeterPlanYear = semeterPlanYear;
	}

	public int getSemeterPlanPart() {
		return semeterPlanPart;
	}

	public void setSemeterPlanPart(int semeterPlanPart) {
		this.semeterPlanPart = semeterPlanPart;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	// Constructor //
	public CoursePlanSemeterEntity() {

		if (ex_courseList == null)
			ex_courseList = getExampleCourseListAll();

	}

	// Method //
	

	// Example Data //
	public static List<Course> ex_courseList;

	public List<CoursePlanSemeterEntity> getListData() {

		List<CoursePlanSemeterEntity> semeterPlanList = new ArrayList<>();

		return semeterPlanList;

	}

	public List<Course> getExampleCourseListAll() {

		List<Course> courseList = new ArrayList<>();

		Course course1 = new Course();
		course1.setId(1);
		course1.setCode("INT 610");
		course1.setName("ระบบสนับสนุนการตัดสินใจ");
		courseList.add(course1);

		Course course2 = new Course();
		course2.setId(2);
		course2.setCode("INT 611");
		course2.setName("การเงินเชิงกลยุทธ์สาหรับเทคโนโลยีสารสนเทศ");
		courseList.add(course2);

		Course course3 = new Course();
		course3.setId(3);
		course3.setCode("INT 613");
		course3.setName("การบริหารความมั่นคงของสารสนเทศ");
		courseList.add(course3);

		Course course4 = new Course();
		course4.setId(4);
		course4.setCode("INT 615");
		course4.setName("การจัดการคุณภาพสารสนเทศ");
		courseList.add(course4);

		Course course5 = new Course();
		course5.setId(5);
		course5.setCode("INT 616");
		course5.setName("การวางแผนทรัพยากรองค์กร");
		courseList.add(course5);

		Course course6 = new Course();
		course6.setId(6);
		course6.setCode("INT 617");
		course6.setName("การยอมรับการใช้งานเทคโนโลยีสารสนเทศ");
		courseList.add(course6);

		Course course7 = new Course();
		course7.setId(7);
		course7.setCode("INT 630");
		course7.setName("เทคโนโลยีฐานข้อมูล");
		courseList.add(course7);

		Course course8 = new Course();
		course8.setId(8);
		course8.setCode("INT 631");
		course8.setName("เทคโนโลยีเชิงอ็อบเจกต์");
		courseList.add(course8);

		Course course9 = new Course();
		course9.setId(9);
		course9.setCode("INT 632");
		course9.setName("ปัญญาประดิษฐ์");
		courseList.add(course9);

		Course course10 = new Course();
		course10.setId(10);
		course10.setCode("INT 633");
		course10.setName("เทคโนโลยีมัลติมีเดีย");
		courseList.add(course10);

		Course course11 = new Course();
		course11.setId(11);
		course11.setCode("INT 635");
		course11.setName("การค้นคืนสารสนเทศ");
		courseList.add(course11);

		Course course12 = new Course();
		course12.setId(12);
		course12.setCode("INT 636");
		course12.setName("ปฏิสัมพันธ์ระหว่างคอมพิวเตอร์กับมนุษย์");
		courseList.add(course12);

		Course course13 = new Course();
		course13.setId(13);
		course13.setCode("INT 637");
		course13.setName("การจัดการความรู้");
		courseList.add(course13);

		Course course14 = new Course();
		course14.setId(14);
		course14.setCode("INT 638");
		course14.setName("การทาเหมืองข้อมูล");
		courseList.add(course14);

		Course course15 = new Course();
		course15.setId(15);
		course15.setCode("INT 640");
		course15.setName("เทคโนโลยีเอ็กซ์เอ็มแอล");
		courseList.add(course15);

		Course course16 = new Course();
		course16.setId(16);
		course16.setCode("INT 641");
		course16.setName("การควบคุมและตรวจสอบเทคโนโลยีสารสนเทศ");
		courseList.add(course16);

		Course course17 = new Course();
		course17.setId(17);
		course17.setCode("INT 650");
		course17.setName("ความมั่นคงของเทคโนโลยีสารสนเทศ");
		courseList.add(course17);

		Course course18 = new Course();
		course18.setId(18);
		course18.setCode("INT 651");
		course18.setName("เทคโนโลยีโทรคมนาคม");
		courseList.add(course18);

		Course course19 = new Course();
		course19.setId(19);
		course19.setCode("INT 652");
		course19.setName("วิศวกรรมอินเทอร์เน็ต");
		courseList.add(course19);

		Course course20 = new Course();
		course20.setId(20);
		course20.setCode("INT 653");
		course20.setName("เทคโนโลยีโมบาย");
		courseList.add(course20);

		Course course21 = new Course();
		course21.setId(21);
		course21.setCode("INT 654");
		course21.setName("การประมวลผลแบบคลาวด์และการประยุกต์");
		courseList.add(course21);

		Course course22 = new Course();
		course22.setId(22);
		course22.setCode("INT 671");
		course22.setName("สัมมนาเชิงปฏิบัติการการจัดการฐานข้อมูลเชิงธุรกิจ");
		courseList.add(course22);

		Course course23 = new Course();
		course23.setId(23);
		course23.setCode("INT 672");
		course23.setName("สัมมนาเชิงปฏิบัติการพัฒนาคลังข้อมูล");
		courseList.add(course23);

		Course course24 = new Course();
		course24.setId(24);
		course24.setCode("INT 673");
		course24.setName("สัมมนาเชิงปฏิบัติการเครือข่ายสาหรับสานักงาน");
		courseList.add(course24);

		Course course25 = new Course();
		course25.setId(25);
		course25.setCode("INT 674");
		course25.setName("สัมมนาเชิงปฏิบัติการเครือข่ายสาหรับองค์กรขนาดใหญ่");
		courseList.add(course25);

		Course course26 = new Course();
		course26.setId(26);
		course26.setCode("INT 675");
		course26.setName("สัมมนาเชิงปฏิบัติการการเขียนโปรแกรมจาวา");
		courseList.add(course26);

		Course course27 = new Course();
		course27.setId(27);
		course27.setCode("INT 676");
		course27.setName("สัมมนาเชิงปฏิบัติการการเขียนโปรแกรมจาวาเซิร์ฟเวอร์ไซต์");
		courseList.add(course27);

		Course course28 = new Course();
		course28.setId(28);
		course28.setCode("INT 677");
		course28.setName("สัมมนาเชิงปฏิบัติการมาตรฐานการให้บริการด้านเทคโนโลยีสารสนเทศ");
		courseList.add(course28);

		Course course29 = new Course();
		course29.setId(29);
		course29.setCode("INT 678");
		course29.setName("สัมมนาเชิงปฏิบัติการคอมพิวเตอร์แบบคลาวด์");
		courseList.add(course29);

		Course course30 = new Course();
		course30.setId(30);
		course30.setCode("SWE 601");
		course30.setName("หลักการวิศวกรรมซอฟต์แวร์");
		courseList.add(course30);

		Course course31 = new Course();
		course31.setId(31);
		course31.setCode("SWE 602");
		course31.setName("การวิเคราะห์และออกแบบระบบขั้นสูง");
		courseList.add(course31);

		Course course32 = new Course();
		course32.setId(32);
		course32.setCode("SWE 603");
		course32.setName("การบริหารโครงการซอฟต์แวร์");
		courseList.add(course32);

		Course course33 = new Course();
		course33.setId(33);
		course33.setCode("SWE 604");
		course33.setName("โครงสร้างและสถาปัตยกรรมซอฟต์แวร์");
		courseList.add(course33);

		Course course34 = new Course();
		course34.setId(34);
		course34.setCode("SWE 605");
		course34.setName("การทวนสอบและการตรวจสอบความสมเหตุสมผลของซอฟต์แวร์");
		courseList.add(course34);

		Course course35 = new Course();
		course35.setId(35);
		course35.setCode("SWE 610");
		course35.setName("การออกแบบซอฟต์แวร์ประเภทฝังตัว");
		courseList.add(course35);

		Course course36 = new Course();
		course36.setId(36);
		course36.setCode("SWE 611");
		course36.setName("การออกแบบปฏิสัมพันธ์");
		courseList.add(course36);

		Course course37 = new Course();
		course37.setId(37);
		course37.setCode("SWE 612");
		course37.setName("การประมว,ผลแบบคลาวด์");
		courseList.add(course37);

		Course course38 = new Course();
		course38.setId(38);
		course38.setCode("SWE 613");
		course38.setName("ระบบแบบกระจายและแบบขนาน");
		courseList.add(course38);

		Course course39 = new Course();
		course39.setId(39);
		course39.setCode("SWE 614");
		course39.setName("การออกแบบซอฟต์แวร์แบบทนทานต่อความบกพร่อง");
		courseList.add(course39);

		Course course40 = new Course();
		course40.setId(40);
		course40.setCode("SWE 615");
		course40.setName("การออกแบบแอนิเมชันและเกม");
		courseList.add(course40);

		Course course41 = new Course();
		course41.setId(41);
		course41.setCode("SWE 616");
		course41.setName("การประมวลผลสาหรับอุปกรณ์เคลื่อนที่และแอปพลิเคชัน");
		courseList.add(course41);

		Course course42 = new Course();
		course42.setId(42);
		course42.setCode("SWE 617");
		course42.setName("การสื่อสารสาหรับวิศวกรซอฟต์แวร์");
		courseList.add(course42);

		Course course43 = new Course();
		course43.setId(43);
		course43.setCode("SWE 630");
		course43.setName("การพัฒนาซอฟต์แวร์แบบอไจล์");
		courseList.add(course43);

		Course course44 = new Course();
		course44.setId(44);
		course44.setCode("SWE 631");
		course44.setName("การพัฒนาซอฟต์แวร์แบบกระจาย");
		courseList.add(course44);

		Course course45 = new Course();
		course45.setId(45);
		course45.setCode("SWE 632");
		course45.setName("ระบบปฏิบัติการและระบบเครือข่าย");
		courseList.add(course45);

		Course course46 = new Course();
		course46.setId(46);
		course46.setCode("SWE 633");
		course46.setName("การพัฒนาโปรแกรมประยุกต์สาหรับองค์กรขนาดใหญ่");
		courseList.add(course46);

		Course course47 = new Course();
		course47.setId(47);
		course47.setCode("SWE 634");
		course47.setName("การบารุงรักษาซอฟต์แวร์");
		courseList.add(course47);

		Course course48 = new Course();
		course48.setId(48);
		course48.setCode("SWE 640");
		course48.setName("การจัดการข้อกาหนดและสิ่งต้องการทางซอฟต์แวร์");
		courseList.add(course48);

		Course course49 = new Course();
		course49.setId(49);
		course49.setCode("SWE 641");
		course49.setName("การจัดการโครงแบบซอฟต์แวร์");
		courseList.add(course49);

		Course course50 = new Course();
		course50.setId(50);
		course50.setCode("SWE 650");
		course50.setName("การปรับปรุงกระบวนการซอฟต์แวร์");
		courseList.add(course50);

		Course course51 = new Course();
		course51.setId(51);
		course51.setCode("SWE 651");
		course51.setName("มาตรวัดผลซอฟต์แวร์");
		courseList.add(course51);

		Course course52 = new Course();
		course52.setId(52);
		course52.setCode("SWE 652");
		course52.setName("การประกันคุณภาพซอฟต์แวร์");
		courseList.add(course52);

		Course course53 = new Course();
		course53.setId(53);
		course53.setCode("SWE 660");
		course53.setName("เครื่องมือสาหรับงานวิศวกรรมซอฟต์แวร์");
		courseList.add(course53);

		Course course54 = new Course();
		course54.setId(54);
		course54.setCode("SWE 661");
		course54.setName("การเขียนโปรแกรมและการพัฒนาบนเว็บ");
		courseList.add(course54);

		Course course55 = new Course();
		course55.setId(55);
		course55.setCode("SWE 670");
		course55.setName("สัมมนาเชิงปฏิบัติการเขียนโปรแกรมและการจัดการฐานข้อมูล");
		courseList.add(course55);

		Course course56 = new Course();
		course56.setId(56);
		course56.setCode("SWE 671");
		course56.setName("สัมมนาเชิงปฏิบัติการทางวิศวกรรมซอฟต์แวร์ 1");
		courseList.add(course56);

		Course course57 = new Course();
		course57.setId(57);
		course57.setCode("SWE 672");
		course57.setName("สัมมนาเชิงปฏิบัติการทางวิศวกรรมซอฟต์แวร์ 2");
		courseList.add(course57);

		Course course58 = new Course();
		course58.setId(58);
		course58.setCode("SWE 690");
		course58.setName("การศึกษาเฉพาะเรื่องทางวิศวกรรมซอฟต์แวร์ 1");
		courseList.add(course58);

		Course course59 = new Course();
		course59.setId(59);
		course59.setCode("SWE 691");
		course59.setName("การศึกษาเฉพาะเรื่องทางวิศวกรรมซอฟต์แวร์ 2");
		courseList.add(course59);

		Course course60 = new Course();
		course60.setId(60);
		course60.setCode("SWE 692");
		course60.setName("การศึกษาเฉพาะเรื่องทางวิศวกรรมซอฟต์แวร์ 3");
		courseList.add(course60);

		Course course61 = new Course();
		course61.setId(61);
		course61.setCode("SWE 700");
		course61.setName("วิทยานิพนธ์");
		courseList.add(course61);

		Course course62 = new Course();
		course62.setId(62);
		course62.setCode("SWE 701");
		course62.setName("การศึกษาโครงการเฉพาะเรื่อง");
		courseList.add(course62);

		Course course63 = new Course();
		course63.setId(63);
		course63.setCode("SWE 702");
		course63.setName("ระเบียบวิธีการวิจัย");
		courseList.add(course63);

		return courseList;

	}

}
