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
		ArrayList<Student> array = new ArrayList<Student>(); //ѧ����Ϣ�����ڼ�������
		ArrayList<RStudent> rarray = new ArrayList<RStudent>(); //ѧ��������Ϣ�����ڼ�������
		loadData(array); //����Ӧ�ñ㿪ʼ��Ӳ�̼�������
                loadRData(rarray); //����Ӧ�ñ㿪ʼ��Ӳ�̼�������
		while (true) {
                        //����ѡ��
			System.out.println("1 �鿴ѧ����Ϣ");
			System.out.println("2 ¼��ѧ����Ϣ");
			System.out.println("3 �޸�ѧ����Ϣ");
			System.out.println("4 ɾ��ѧ����Ϣ");
			System.out.println("5 �˳�ϵͳ");
			String choice = temp.nextLine();
			//�ж�
			switch (choice) {
			case "1":
                                System.out.println("�鿴��ָ��ѧ����Ϣ/����ѧ����Ϣ(1/2)");
                                String confirm = temp.nextLine();
                                if(confirm.equals("1") ){
					queryone(array,rarray);
				}else{
					query(array,rarray);
				}
				break;
			case "2":
				add(array,rarray);
				storeData(array);//�޸�����֮����������Ӳ���ļ�
                                storeRData(rarray);//�޸�����֮����������Ӳ���ļ�
				break;
			case "3":
				modify(array,rarray);
				storeData(array);//�޸�����֮����������Ӳ���ļ�
                                storeRData(rarray);//�޸�����֮����������Ӳ���ļ�
				break;
			case "4":
				delete(array,rarray);
				storeData(array);//�޸�����֮����������Ӳ���ļ�
                                storeRData(rarray);//�޸�����֮����������Ӳ���ļ�
				break;
			case "5":
				System.out.println("��л����ʹ�ã��ټ���");
				System.exit(0);
			default:
				System.out.println("������������������ѡ��");
			}
		}
	}
	private static void loadRData(ArrayList<RStudent> array) throws IOException{
		
		File file= new File("reward.txt");  //���δ�Ӧ�ûᴴ���ļ�
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}   
			System.out.println("�½�reward.txt�ɹ���");
		}
		//���ļ��ж�ȡ���ݽ�����
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
	//�������ݵ��ļ���
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
		//ֱ�ӽ�����ȫ���洢���ļ��У�����׷�ӷ�����Ҳ������ɾ���
		//�����Ϣ���Ƚϴ�Ͳ���Ӧ
                OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file),"GBK");     
		BufferedWriter bw = new BufferedWriter(write);
		//�����ݴ洢���ļ�
		for(int i=0;i<array.size();i++){
			RStudent s = array.get(i);
			bw.write(s.getNum(),0,s.getNum().length());//����ѧ��
			bw.write(' ');//�����ͬ����
			bw.write(s.getReward(),0,s.getReward().length());//��������
                        bw.write(' ');//�����ͬ����
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
        private static void loadData(ArrayList<Student> array) throws IOException{
		
		File file= new File("student.txt");  //���δ�Ӧ�ûᴴ���ļ�
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}   
			System.out.println("�½�student.txt�ɹ���");
		}
		//���ļ��ж�ȡ���ݽ�����
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
	//�������ݵ��ļ���
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
		//ֱ�ӽ�����ȫ���洢���ļ��У�����׷�ӷ�����Ҳ������ɾ���
		//�����Ϣ���Ƚϴ�Ͳ���Ӧ
                OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file),"GBK");     
		BufferedWriter bw = new BufferedWriter(write);
		//�����ݴ洢���ļ�
		for(int i=0;i<array.size();i++){
			Student s = array.get(i);
			bw.write(s.getNum(),0,s.getNum().length());//����ѧ��
			bw.write(' ');//�����ͬ����
			bw.write(s.getName(),0,s.getName().length());//��������
			bw.write(' ');//�����ͬ����
                        bw.write(s.getSex(),0,s.getSex().length());//�����Ա�
                        bw.write(' ');//�����ͬ����
			bw.write(s.getAge(),0,s.getAge().length());//��������
			bw.write(' ');//�����ͬ����
			bw.write(s.getMajor(),0,s.getMajor().length());//����רҵ
                        bw.write(' ');//�����ͬ����
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	//��ѯѧ����Ϣ����
	private static void query(ArrayList<Student> array,ArrayList<RStudent> rarray){
 
		if(array.size() == 0){
			System.out.println("��ʱδ�洢�κ�ѧ����Ϣ��������ѡ��");
			return;
		}
		System.out.println("ѧ��\t����\t�Ա�\t����\tרҵ\t����");  
		for(int i=0;i<array.size();i++){
			Student s = array.get(i);
                        RStudent rs = rarray.get(i);
			System.out.println(s.getNum()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t"+s.getMajor()+"\t"+rs.getReward());
		}
	}
        private static void queryone(ArrayList<Student> array,ArrayList<RStudent> rarray){
 
		if(array.size() == 0){
			System.out.println("��ʱδ�洢�κ�ѧ����Ϣ��������ѡ��");
			return;
		}
                Scanner temp = new Scanner(System.in);
		int index = -1;//���ұ�־
		System.out.println("��������Ҫ�鿴��ѧ��ѧ�ţ�");
		String num = temp.nextLine();
                System.out.println("��������Ҫ�鿴��ѧ��������");
		String name = temp.nextLine();
                for(int i=0;i<array.size();i++){
			Student s = array.get(i);
			if(s.getNum().equals(num)&&s.getName().equals(name)){
				index = i;
				break;
			}
		}
		if(index == -1){//δ�ҵ�ѧ��ֱ�ӷ���
			System.out.println("δ�ҵ���ѧ����");
			return;
		}else{
			System.out.println("ѧ��\t����\t�Ա�\t����\tרҵ\t����");  
                        Student s = array.get(index);
                        RStudent rs = rarray.get(index);
                        System.out.println(s.getNum()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t"+s.getMajor()+"\t"+rs.getReward());
		}
	}
	//���ѧ����Ϣ����
	private static void add(ArrayList<Student> array,ArrayList<RStudent> rarray){
		Scanner temp = new Scanner(System.in);
		String num;
		while(true){
			System.out.println("��������ͬѧѧ�ţ�");
			num = temp.nextLine();
			//�ж��Ƿ��ظ�ѧ��
			boolean flag1 = false;//Ĭ��δ�ظ�
			for(int i=0;i<array.size();i++){
				Student s = array.get(i);
				if(s.getNum().equals(num)){
					System.out.println("��ѧ���Ѿ����ڣ����������룡");
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
		System.out.println("��������ͬѧ������");
		String name = temp.nextLine();
                s.setName(name);
                int flag1=0;
                String sex;
                do{
                    if(flag1>0) System.out.println("ѧ���Ա𲻷���Ҫ�����������룡");
                    System.out.println("��������ͬѧ�Ա�");
                    sex = temp.nextLine();
                    flag1++;
                }while(!sex.equals("��")&&!sex.equals("Ů"));
                s.setSex(sex);
                int age2;
                String age1;
                int flag2=0;
                do{
                    if(flag2>0) System.out.println("ѧ�����䲻����Ҫ�����������룡");
                    System.out.println("��������ͬѧ���䣺");
                    age1 = temp.nextLine();
                    age2=Integer.parseInt(age1);
                    flag2++;
                }while(age2<15||age2>45);
                String age=age1;
                s.setAge(age);
		System.out.println("��������ͬѧרҵ��");
		String major = temp.nextLine();
                System.out.println("��������ͬѧ�����");
		String reward = temp.nextLine();
		s.setMajor(major);
                rs.setReward(reward);
		array.add(s);
                rarray.add(rs);
		System.out.println("¼��ѧ����Ϣ�ɹ���");	
	}
	//�޸�ѧ����Ϣ����
	private static void modify(ArrayList<Student> array,ArrayList<RStudent> rarray){
		Scanner temp = new Scanner(System.in);
		int index = -1;//���ұ�־
		System.out.println("��������Ҫ�޸ĵ�ѧ��ѧ�ţ�");
		String num = temp.nextLine();
		for(int i=0;i<array.size();i++){
			Student s1 = array.get(i);
			if(s1.getNum().equals(num)){
				index = i;
				break;
			}
		}
                if(index == -1){//δ�ҵ�ѧ��ֱ�ӷ���
			System.out.println("δ�ҵ���Ӧѧ������Ӧѧ����");
			return;
                }else{
                        Student s = array.get(index);
                        RStudent rs = rarray.get(index);
			System.out.println("��������Ҫ���ĵ�������");
			String name = temp.nextLine();
                        s.setName(name);
			int flag1=0;
                        String sex;
                        do{
                        if(flag1>0) System.out.println("ѧ���Ա𲻷���Ҫ�����������룡");
                        System.out.println("��������Ҫ���ĵ�ͬѧ�Ա�");
                        sex = temp.nextLine();
                        flag1++;
                        }while(!sex.equals("��")&&!sex.equals("Ů"));
                        s.setSex(sex);
                        int age2;
                        String age1;
                        int flag2=0;
                        do{
                            if(flag2>0) System.out.println("ѧ�����䲻����Ҫ�����������룡");
                            System.out.println("��������Ҫ���ĵ�ͬѧ���䣺");
                            age1 = temp.nextLine();
                            age2=Integer.parseInt(age1);
                            flag2++;
                        }while(age2<15||age2>45);
                        String age=age1;
                        s.setAge(age);
                        System.out.println("��������Ҫ���ĵ�רҵ��");
			String major = temp.nextLine();
                        System.out.println("��������Ҫ���ĵĽ��");
			String reward = temp.nextLine();
                        s.setMajor(major);
                        rs.setReward(reward);
			System.out.println("ѧ����Ϣ���³ɹ���");
		}
		
	}
	//ɾ��ѧ����Ϣ����
	private static void delete(ArrayList<Student> array,ArrayList<RStudent> rarray){
		Scanner temp = new Scanner(System.in);
		System.out.println("��������Ҫɾ����ѧ��ѧ�ţ�");
		String num = temp.nextLine();
                System.out.println("��������Ҫɾ����ѧ��������");
		String name = temp.nextLine();
		for(int i=0;i<array.size();i++){
			Student s = array.get(i);
                        RStudent rs = rarray.get(i);
			if(s.getNum().equals(num)&&s.getName().equals(name)){
				System.out.println("��ȷ��ɾ����ѧ����Ϣ(Y/N)��");
				System.out.println("ѧ��\t����\t����\t�Ա�\tרҵ\t����");
				System.out.println(s.getNum()+"\t"+s.getName()+"\t"+s.getAge()+"\t"+s.getSex()+"\t"+s.getMajor()+"\t"+rs.getReward());
				String confirm = temp.nextLine();
				//�ж�ȷ��ɾ��
				if(confirm.equals("y") || confirm.equals("Y")){
					if(array.remove(s)&&rarray.remove(rs)){
						System.out.println("ɾ���ɹ���");
						return;
					}
				}else{
					System.out.println("��ȡ��ɾ����");
					return;
				}
			}
		}
		System.out.println("δ�ҵ���Ӧѧ������ȷ��ѧ����Ϣ�Ƿ�����!");
	}
 
}

