package com.xx.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class FileCreateHelp {
//	//生成的博客在服务器存放的基地址
//	private String blog_path;
//	//博客在服务器存放的基地址对应的在Tomcat配置的用于用户访问的地址
//	private String blog_user_path;
//	//生成的帖子在服务器存放的基地址
//	private String forum_path;
//	//帖子在服务器存放的基地址对应的在Tomcat配置的用于用户访问的地址
//	private String forum_user_path;
//	//服务器域名
//	private String dom_name;
//	//博客模板文件的上半部分地址
//	private String blog_model_up;
//	//博客模板文件的下半部分地址
//	private String blog_model_down;
//	//论坛模板文件的上半部分地址
//	private String forum_model_up;
//	//博客模板文件的下半部分地址
//	private String forum_model_down;
	
	private static Properties pro;	//配置文件
	private static String user_path;	//用户的访问路径
	private static String real_path;	//文件的真实路径
	private static boolean success;	//判断是否成功
	
	
	static{
//		InputStream input = ClassLoader.getSystemResourceAsStream("file.properties");
		InputStream input = FileCreateHelp.class.getClassLoader().getResourceAsStream("file.properties");
		pro = new Properties();
		try {
			pro.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回文件真实的路径
	 * @return
	 */
	public static String getRealPath(){
		return real_path;
	}
	
	/**
	 * 返回用户访问的路径
	 * @return
	 */
	public static String getUserPath(){
		return user_path;
	}
	
	/**
	 * 判断文件是否已被创建
	 * @return	true表示创建成功	false表示创建失败
	 */
	public static boolean isCreate(){
		
		return success;
	}
	
	/**
	 * 
	 * @param inner_id	用户id
	 * @param a_id	文章id
	 * @param passage	文章内容
	 */
	public static void blogCreate(String inner_id, String a_id, String passage){
		
		
		//获取文件存放路径
		String realpath = pro.getProperty("blog_path");
		String model_up = pro.getProperty("blog_model_up");
		String model_down = pro.getProperty("blog_model_down");
		
		//获得用户id用于与 blog_path 组合成新文件的存储目录
		String user_id = inner_id;
		
		//获得文章id用于作为新文件的文件名
		String article_id = a_id;
		//将存储目录与文件名拼接作为该新文件的地址
		String file_name = article_id+".html";
		String file_path = realpath+user_id;
		//判断文件是否存在，不存在则创建
		File new_dir = new File(file_path);
		File new_file = new File(file_path+"/"+file_name);
		if(!new_dir.exists()){
			new_dir.mkdirs();
		}
		
		//文件是否创建成功
		boolean bool = false;
			try {
				if(!new_file.exists()){
					bool = new_file.createNewFile();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//将新文件的地址作为参数调用joinFile方法生成新文件
		boolean flag = false;
		if(bool){
			//blog_model_up(博客模板上半部分), blog_model_down(博客模板下半部分)
			flag = joinFile(model_up, model_down, passage, new_file);
		}
		//判断文件合并是否成功，成功则拼接用户访问的地址赋值给url
		if(flag){
			//文件生成成功，设置文件地址
			user_path = "http://" + pro.getProperty("dom_name")+ pro.getProperty("blog_user_path")+user_id+"/"+file_name;
			real_path = realpath+inner_id+"/"+file_name;
			success = true;
		}else{
			success = false;
		}
		
	}
	
	/**
	 * 生成博客新文件
	 * @param inner_id	发布文章的用户id
	 * @param passage	文章的内容
	 * @param realpath	文章在服务器的真实存储路径
	 * @param model_up	上半部分的模板
	 * @param model_down	下半部分的模板
	 * @return	url 用户查看该文章的路径
	 * 				如果url为null则发布失败
	 */
	public String htmlCreate(String inner_id, String a_id, String model_up, String model_down, String dom_name, String realpath, String passage){
		String url = null;
		
		//获得用户id用于与 blog_path 组合成新文件的存储目录
		String user_id = inner_id;
		
		//获得文章id用于作为新文件的文件名
		String article_id = a_id;
		//将存储目录与文件名拼接作为该新文件的地址
		String file_name = article_id+".html";
		String file_path = realpath+user_id;
		//判断文件是否存在，不存在则创建
		File new_dir = new File(file_path);
		File new_file = new File(file_path+"/"+file_name);
		if(!new_dir.exists()){
			new_dir.mkdirs();
		}
		
		//文件是否创建成功
		boolean bool = false;
			try {
				if(!new_file.exists()){
					bool = new_file.createNewFile();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//将新文件的地址作为参数调用joinFile方法生成新文件
		boolean flag = false;
		if(bool){
			//blog_model_up(博客模板上半部分), blog_model_down(博客模板下半部分)
			flag = joinFile(model_up, model_down, passage, new_file);
		}
		//判断文件合并是否成功，成功则拼接用户访问的地址赋值给url
		if(!flag){
			//如果文件生成失败，返回null
			return null;
		}
		//文件生成成功，返回文件的真实地址
		url = file_path+"/"+file_name;
		return url;
	}
	
	private static boolean joinFile(String model_up_path, String model_down_path, String passage,File new_file_path){
		boolean bool = false;
		
		InputStream in = null;
		OutputStream out = null;
		try {
			//新建对文章模板的上半部分的输入流
			in = new FileInputStream(model_up_path);
			//新建对要生成的新文件的输出流
			out = new FileOutputStream(new_file_path,true);
			//将模板上半部分的内容写入要生成的新文件中
			byte [] b = new byte[1024];
			int len = -1;
			while((len = in.read(b)) != -1){
				out.write(b, 0, len);
			}
			
			//将新文件的文章内容写入要新建的文件中
			byte [] p = passage.getBytes("utf-8");
			out.write(p);
			
			//将之前输入流先关闭
			in.close();
			
			//将模板文件的下半部分写入新文件中
			in = new FileInputStream(model_down_path);
			while((len = in.read(b)) != -1){
				out.write(b, 0, len);
			}
			
			//将bool设置为true，表示新建文件成功
			bool = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				
				if(in != null){
					in.close();
				}
				
				if(out != null){
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return bool;
	}
}
