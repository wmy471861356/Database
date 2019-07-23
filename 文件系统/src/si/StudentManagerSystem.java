/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si;

/**
 *
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 
 
public class StudentManagerSystem {
	public static void main(String[] args) throws IOException {
		Scanner temp = new Scanner(System.in);
		ArrayList<Student> array = new ArrayList<Student>(); //学生信息储存在集合类中
		ArrayList<RStudent> rarray = new ArrayList<RStudent>(); //学生奖励信息储存在集合类中
		loadData(array); //启动应用便开始从硬盘加载数据
                loadRData(rarray); //启动应用便开始从硬盘加载数据
		while (true) {
                        //输入选项
			System.out.println("1 查看学生信息");
			System.out.println("2 录入学生信息");
			System.out.println("3 修改学生信息");
			System.out.println("4 删除学生信息");
			System.out.println("5 退出系统");
			String choice = temp.nextLine();
			//判断
			switch (choice) {
			case "1":
                                System.out.println("查看：指定学生信息/所有学生信息(1/2)");
                                String confirm = temp.nextLine();
                                if(confirm.equals("1") ){
					queryone(array,rarray);
				}else{
					query(array,rarray);
				}
				break;
			case "2":
				add(array,rarray);
				storeData(array);//修改数据之后立即更新硬盘文件
                                storeRData(rarray);//修改数据之后立即更新硬盘文件
				break;
			case "3":
				modify(array,rarray);
				storeData(array);//修改数据之后立即更新硬盘文件
                                storeRData(rarray);//修改数据之后立即更新硬盘文件
				break;
			case "4":
				delete(array,rarray);
				storeData(array);//修改数据之后立即更新硬盘文件
                                storeRData(rarray);//修改数据之后立即更新硬盘文件
				break;
			case "5":
				System.out.println("感谢您的使用，再见！");
				System.exit(0);
			default:
				System.out.println("您的输入有误，请重新选择！");
			}
		}
	}
	private static void loadRData(ArrayList<RStudent> array) throws IOException{
		
		File file= new File("reward.txt");  //初次打开应用会创建文件
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}   
			System.out.println("新建reward.txt成功！");
		}
		//从文件中读取数据进集合
                InputStreamReader read = new InputStreamReader(new FileInputStream(file),"GBK");
		BufferedReader br = new BufferedReader(read);
		String line;
		while((line = br.readLine())!=null){
			String [] str = line.split(" ");
			RStudent s = new RStudent();
			s.setNum(str[0]);
			s.setReward(str[1]);
			array.add(s);
		}
		br.close();
	}
	//储存数据到文件中
	private static void storeRData(ArrayList<RStudent> array) throws IOException{
		File file= new File("reward.txt");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
		}
		//直接将数据全部存储到文件中，不用追加方法，也方便增删查改
		//如何信息量比较大就不适应
                OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file),"GBK");     
		BufferedWriter bw = new BufferedWriter(write);
		//将数据存储进文件
		for(int i=0;i<array.size();i++){
			RStudent s = array.get(i);
			bw.write(s.getNum(),0,s.getNum().length());//导出学号
			bw.write(' ');//间隔不同数据
			bw.write(s.getReward(),0,s.getReward().length());//导出奖励
                        bw.write(' ');//间隔不同数据
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
        private static void loadData(ArrayList<Student> array) throws IOException{
		
		File file= new File("student.txt");  //初次打开应用会创建文件
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}   
			System.out.println("新建student.txt成功！");
		}
		//从文件中读取数据进集合
                InputStreamReader read = new InputStreamReader(new FileInputStream(file),"GBK");
		BufferedReader br = new BufferedReader(read);
		String line;
		while((line = br.readLine())!=null){
			String [] str = line.split(" ");
			Student s = new Student();
			s.setNum(str[0]);
			s.setName(str[1]);
			s.setSex(str[2]);
			s.setAge(str[3]);
                        s.setMajor(str[4]);
			array.add(s);
		}
		br.close();
	}
	//储存数据到文件中
	private static void storeData(ArrayList<Student> array) throws IOException{
		File file= new File("student.txt");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
		}
		//直接将数据全部存储到文件中，不用追加方法，也方便增删查改
		//如何信息量比较大就不适应
                OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file),"GBK");     
		BufferedWriter bw = new BufferedWriter(write);
		//将数据存储进文件
		for(int i=0;i<array.size();i++){
			Student s = array.get(i);
			bw.write(s.getNum(),0,s.getNum().length());//导出学号
			bw.write(' ');//间隔不同数据
			bw.write(s.getName(),0,s.getName().length());//导出姓名
			bw.write(' ');//间隔不同数据
                        bw.write(s.getSex(),0,s.getSex().length());//导出性别
                        bw.write(' ');//间隔不同数据
			bw.write(s.getAge(),0,s.getAge().length());//导出年龄
			bw.write(' ');//间隔不同数据
			bw.write(s.getMajor(),0,s.getMajor().length());//导出专业
                        bw.write(' ');//间隔不同数据
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	//查询学生信息方法
	private static void query(ArrayList<Student> array,ArrayList<RStudent> rarray){
 
		if(array.size() == 0){
			System.out.println("暂时未存储任何学生信息，请重新选择。");
			return;
		}
		System.out.println("学号\t姓名\t性别\t年龄\t专业\t奖项");  
		for(int i=0;i<array.size();i++){
			Student s = array.get(i);
                        RStudent rs = rarray.get(i);
			System.out.println(s.getNum()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t"+s.getMajor()+"\t"+rs.getReward());
		}
	}
        private static void queryone(ArrayList<Student> array,ArrayList<RStudent> rarray){
 
		if(array.size() == 0){
			System.out.println("暂时未存储任何学生信息，请重新选择。");
			return;
		}
                Scanner temp = new Scanner(System.in);
		int index = -1;//查找标志
		System.out.println("请输入需要查看的学生学号：");
		String num = temp.nextLine();
                System.out.println("请输入需要查看的学生姓名：");
		String name = temp.nextLine();
                for(int i=0;i<array.size();i++){
			Student s = array.get(i);
			if(s.getNum().equals(num)&&s.getName().equals(name)){
				index = i;
				break;
			}
		}
		if(index == -1){//未找到学生直接返回
			System.out.println("未找到该学生！");
			return;
		}else{
			System.out.println("学号\t姓名\t性别\t年龄\t专业\t奖项");  
                        Student s = array.get(index);
                        RStudent rs = rarray.get(index);
                        System.out.println(s.getNum()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t"+s.getMajor()+"\t"+rs.getReward());
		}
	}
	//添加学生信息方法
	private static void add(ArrayList<Student> array,ArrayList<RStudent> rarray){
		Scanner temp = new Scanner(System.in);
		String num;
		while(true){
			System.out.println("请输入新同学学号：");
			num = temp.nextLine();
			//判断是否重复学号
			boolean flag1 = false;//默认未重复
			for(int i=0;i<array.size();i++){
				Student s = array.get(i);
				if(s.getNum().equals(num)){
					System.out.println("该学号已经存在，请重新输入！");
					flag1 = true;
					break;
				}
			}
			if(!flag1){
				break;
			}                                                  
		}
                Student s = new Student();
                RStudent rs = new RStudent();
                s.setNum(num);
                rs.setNum(num);
		System.out.println("请输入新同学姓名：");
		String name = temp.nextLine();
                s.setName(name);
                int flag1=0;
                String sex;
                do{
                    if(flag1>0) System.out.println("学生性别不符合要求，请重新输入！");
                    System.out.println("请输入新同学性别：");
                    sex = temp.nextLine();
                    flag1++;
                }while(!sex.equals("男")&&!sex.equals("女"));
                s.setSex(sex);
                int age2;
                String age1;
                int flag2=0;
                do{
                    if(flag2>0) System.out.println("学生年龄不符合要求，请重新输入！");
                    System.out.println("请输入新同学年龄：");
                    age1 = temp.nextLine();
                    age2=Integer.parseInt(age1);
                    flag2++;
                }while(age2<15||age2>45);
                String age=age1;
                s.setAge(age);
		System.out.println("请输入新同学专业：");
		String major = temp.nextLine();
                System.out.println("请输入新同学所获奖项：");
		String reward = temp.nextLine();
		s.setMajor(major);
                rs.setReward(reward);
		array.add(s);
                rarray.add(rs);
		System.out.println("录入学生信息成功！");	
	}
	//修改学生信息方法
	private static void modify(ArrayList<Student> array,ArrayList<RStudent> rarray){
		Scanner temp = new Scanner(System.in);
		int index = -1;//查找标志
		System.out.println("请输入需要修改的学生学号：");
		String num = temp.nextLine();
		for(int i=0;i<array.size();i++){
			Student s1 = array.get(i);
			if(s1.getNum().equals(num)){
				index = i;
				break;
			}
		}
                if(index == -1){//未找到学生直接返回
			System.out.println("未找到相应学号所对应学生！");
			return;
                }else{
                        Student s = array.get(index);
                        RStudent rs = rarray.get(index);
			System.out.println("请输入需要更改的姓名：");
			String name = temp.nextLine();
                        s.setName(name);
			int flag1=0;
                        String sex;
                        do{
                        if(flag1>0) System.out.println("学生性别不符合要求，请重新输入！");
                        System.out.println("请输入需要更改的同学性别：");
                        sex = temp.nextLine();
                        flag1++;
                        }while(!sex.equals("男")&&!sex.equals("女"));
                        s.setSex(sex);
                        int age2;
                        String age1;
                        int flag2=0;
                        do{
                            if(flag2>0) System.out.println("学生年龄不符合要求，请重新输入！");
                            System.out.println("请输入需要更改的同学年龄：");
                            age1 = temp.nextLine();
                            age2=Integer.parseInt(age1);
                            flag2++;
                        }while(age2<15||age2>45);
                        String age=age1;
                        s.setAge(age);
                        System.out.println("请输入需要更改的专业：");
			String major = temp.nextLine();
                        System.out.println("请输入需要更改的奖项：");
			String reward = temp.nextLine();
                        s.setMajor(major);
                        rs.setReward(reward);
			System.out.println("学生信息更新成功！");
		}
		
	}
	//删除学生信息方法
	private static void delete(ArrayList<Student> array,ArrayList<RStudent> rarray){
		Scanner temp = new Scanner(System.in);
		System.out.println("请输入需要删除的学生学号：");
		String num = temp.nextLine();
                System.out.println("请输入需要删除的学生姓名：");
		String name = temp.nextLine();
		for(int i=0;i<array.size();i++){
			Student s = array.get(i);
                        RStudent rs = rarray.get(i);
			if(s.getNum().equals(num)&&s.getName().equals(name)){
				System.out.println("请确认删除该学生信息(Y/N)：");
				System.out.println("学号\t姓名\t年龄\t性别\t专业\t奖项");
				System.out.println(s.getNum()+"\t"+s.getName()+"\t"+s.getAge()+"\t"+s.getSex()+"\t"+s.getMajor()+"\t"+rs.getReward());
				String confirm = temp.nextLine();
				//判断确认删除
				if(confirm.equals("y") || confirm.equals("Y")){
					if(array.remove(s)&&rarray.remove(rs)){
						System.out.println("删除成功！");
						return;
					}
				}else{
					System.out.println("已取消删除！");
					return;
				}
			}
		}
		System.out.println("未找到对应学生，请确认学生信息是否有误!");
	}
 
}

