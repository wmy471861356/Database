/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 王萌宇
 */
public class Si {
    static Statement sta=null;
        static String sql=null;  
        static ResultSet rs;
        private static void createTable(){
        try{
            sql="create table students(Sno int primary key,Sname varchar(10),Ssex varchar(6) CHECK (Ssex='男' OR Ssex='女'),Sage int CHECK (Sage>14 AND Sage<46),Sdept varchar(10))";
            sta.execute(sql);  //创建信息表
        }
        catch(SQLException e){
           System.out.println("学生信息表已存在");
        }
        try{
            sql="create table reward(Sno int primary key,Reward varchar(50),constraint fk_Sno foreign key(Sno) references students(Sno))";
            sta.execute(sql);  //创建奖励表
        }
        catch(SQLException e){
            System.out.println("奖励表已存在");
        } 
        //放入原始数据
        sql="insert into students values"+"(20100001,'史玉明','女',20,'计算机'),(20100100,'李明虎','男',21,'机械'),(20100234,'张翔','男',21,'化工')";
        try {
            sta.execute(sql);
            sql="insert into reward values"+"(20100001,'无'),(20100100,'大奖'),(20100234,'完成奖')";
            sta.execute(sql);
            System.out.println("建库完成！");
        } catch (SQLException ex) {
            Logger.getLogger(Si.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	public static void main(String[] args) throws IOException {
            Scanner temp = new Scanner(System.in);
            Connection con=null;
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");//加载JDBC驱动
        }
        catch(Exception e){}
        //连接数据库
        try{
            String uri="jdbc:derby:StudentMessage;create=true";
            con=DriverManager.getConnection(uri);
            sta=con.createStatement();
        }
        catch(Exception e){
            System.out.print("数据库连接失败");
        }
        //createTable();  //建表、放入原始数据
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
		queryone();
	    }else{
		query();
	    }
	        break;
	    case "2":
	        add();		
	        break;
	    case "3":
	        modify();
	        break;
	    case "4":
		delete();
		break;
	    case "5":
		System.out.println("感谢您的使用，再见！");
		System.exit(0);
		default:
		System.out.println("您的输入有误，请重新选择！");
		}
		}
	}
	//查询所有学生信息
	private static void query(){
        try{
            rs=sta.executeQuery("select * from students,reward where students.Sno=reward.Sno");
            while(rs.next()){  
                int number=rs.getInt(1);
                System.out.print(number+"\t");
                String name=rs.getString(2);
                System.out.print(name+"\t");
                String sex=rs.getString(3);
                System.out.print(sex+"\t");
                int age=rs.getInt(4);
                System.out.print(age+"\t");
                String dept=rs.getString(5);
                System.out.print(dept+"\t");
                String reward=rs.getString(7);
                System.out.print(reward+"\t");
                System.out.print("\n");
            }
        }catch(Exception e){
                System.out.println("查询错误");
        }
	}
        //查询指定学生
        private static void queryone(){
                Scanner temp = new Scanner(System.in);
		System.out.println("请输入需要查看的学生学号：");
		String nu = temp.nextLine();
                int num=Integer.parseInt(nu);
                System.out.println("请输入需要查看的学生姓名：");
                String na = temp.nextLine();
            try {
                int flag=0;
                rs=sta.executeQuery("select * from students,reward where students.Sno=reward.Sno AND students.Sno="+num+"");
                while(rs.next()){
                flag=1;
                int number=rs.getInt(1);
                System.out.print(number+"\t");
                String name=rs.getString(2);
                System.out.print(name+"\t");
                String sex=rs.getString(3);
                System.out.print(sex+"\t");
                int age=rs.getInt(4);
                System.out.print(age+"\t");
                String dept=rs.getString(5);
                System.out.print(dept+"\t");
                String reward=rs.getString(7);
                System.out.print(reward+"\t");
                System.out.print("\n");
                }
                if(flag==0){
                    System.out.println("查看失败！");
                    System.out.println("该学生不存在");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Si.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	//添加学生信息
	private static void add(){
		Scanner temp = new Scanner(System.in);
		String nu;
		System.out.println("请输入新同学学号：");
	        nu = temp.nextLine();
                int num;
                num=Integer.parseInt(nu);
            try {
                int flag=0;
                rs=sta.executeQuery("select * from students where Sno="+num+"");
                while(rs.next()){
                    flag=1;
                }
                if(flag==1){
                    System.out.println("该学号已存在！");
                    return;
                }
            } catch (SQLException e) {           
            }
		System.out.println("请输入新同学姓名：");
		String name = temp.nextLine();
                String sex;
                System.out.println("请输入新同学性别：");
                sex = temp.nextLine();
                String ag;
                System.out.println("请输入新同学年龄：");
                ag = temp.nextLine();
                int age=Integer.parseInt(ag);
		System.out.println("请输入新同学专业：");
		String dept = temp.nextLine();
                System.out.println("请输入新同学所获奖项：");
		String reward = temp.nextLine();
            try {
                sql="insert into students values"+"("+num+",'"+name+"','"+sex+"',"+age+",'"+dept+"')";
                sta.execute(sql);
                sql="insert into reward values"+"("+num+",'"+reward+"')";
                sta.execute(sql);
                System.out.println("录入学生信息成功！");	
            } catch (SQLException ex) {
                System.out.println("录入失败！");
                System.out.println("请检查年龄与性别是否符合条件：");
                System.out.println("年龄：15岁-45岁");
                System.out.println("性别：非男即女");
            }
            
	}
	//修改学生信息
	private static void modify(){
		Scanner temp = new Scanner(System.in);
		System.out.println("请输入需要修改的学生学号：");
		String nu = temp.nextLine();
                int num=Integer.parseInt(nu);       
                System.out.println("请输入需要更改的姓名：");
                String name = temp.nextLine();
                //判断学号是否存在
                try {
                    sql="select * from students where Sno="+num+"";
                    rs=sta.executeQuery(sql);
                    int flag=0;
                while(rs.next()){
                    flag=1;
                }
                if(flag==0){
                    System.out.println("该学生不存在！");
                    return;
                }
                } catch (SQLException e) {
                    System.out.println("修改失败！");
                    return;
                }
                System.out.println("请输入需要更改的同学性别：");
                String sex = temp.nextLine();
                System.out.println("请输入需要更改的同学年龄：");
                String ag=temp.nextLine();
                int age=Integer.parseInt(ag);
                System.out.println("请输入需要更改的专业：");
                String dept = temp.nextLine();
                System.out.println("请输入需要更改的奖项：");
	        String reward = temp.nextLine();
            try {
                sql="update students set Sname='"+name+"',Ssex='"+sex+"',Sage="+age+" ,Sdept='"+dept+"'where Sno="+num+"";
                sta.execute(sql);
                sql="update reward set Reward='"+reward+"' where Sno="+num+"";
                sta.execute(sql);
                System.out.println("学生信息更新成功！");
            } catch (SQLException ex) {
                System.out.println("修改失败！");
                System.out.println("请检查年龄与性别是否符合条件：");
                System.out.println("年龄：15岁-45岁");
                System.out.println("性别：非男即女");
            }
	}
	//删除
	private static void delete(){
		Scanner temp = new Scanner(System.in);
		System.out.println("请输入需要删除的学生学号：");
		String nu = temp.nextLine();
                int num=Integer.parseInt(nu);
                System.out.println("请输入需要删除的学生姓名：");
		String na = temp.nextLine();
            try {
                rs=sta.executeQuery("select * from students,reward where students.Sno=reward.Sno AND students.Sno="+num+"");
                int flag=0;
                while(rs.next()){
                flag=1;
                System.out.println("请确认删除该学生信息(Y/N)：");    
                int number=rs.getInt(1);
                System.out.print(number+"\t");
                String name=rs.getString(2);
                System.out.print(name+"\t");
                String sex=rs.getString(3);
                System.out.print(sex+"\t");
                int age=rs.getInt(4);
                System.out.print(age+"\t");
                String dept=rs.getString(5);
                System.out.print(dept+"\t");
                String reward=rs.getString(7);
                System.out.print(reward+"\t");
                System.out.print("\n");
                }
                if(flag==0){
                    System.out.println("删除失败！");
                    System.out.println("该学生不存在");
                    return;
                }
                String confirm = temp.nextLine();
                if(confirm.equals("y") || confirm.equals("Y")){
                    sta.execute("delete from reward where Sno="+num+"");
                    sta.execute("delete from students where Sno="+num+"");
                    
                    System.out.println("删除成功！");
                }
                else{
		    System.out.println("已取消删除！");
		    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Si.class.getName()).log(Level.SEVERE, null, ex);
            }
		
	}
 
}
