/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si;
import java.lang.String;
/**
 *
 * @author dmt
 */
public class Student {
	private String num;
	private String name;
	private String  sex;
	private String age;
        private String major;
	public Student() {
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
        public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMajor() {
		return major;
	}
        public void setMajor(String major) {
		this.major = major;
	}
	
}


