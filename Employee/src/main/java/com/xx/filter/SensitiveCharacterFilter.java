package com.xx.filter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
//import javax.servlet.http.HttpServletResponse;

import com.xx.utils.FileCreateHelp;



/**
 * Servlet Filter implementation class SensitiveCharacterFilter
 */
public class SensitiveCharacterFilter implements Filter {
	private List<String> dirty;
	
    /**
     * Default constructor. 
     */
    public SensitiveCharacterFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse rep = (HttpServletResponse) response;
		
		request = new MyDirtyCharacterRequest(req,this.dirty);
		System.out.println("过滤器");
		
		
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("我被创建了");
		Properties pro = new Properties();
		InputStream input = FileCreateHelp.class.getClassLoader().getResourceAsStream("file.properties");
		
		FileReader fr = null;
		try {
			pro.load(input);
			fr = new FileReader(pro.getProperty("dirty"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		BufferedReader bf = new BufferedReader(fr);
		this.dirty = new ArrayList<String>();
		try {
			String word = null;
			while((word = bf.readLine()) != null){
				dirty.add(word);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				try {
					if(bf != null){
						bf.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		int i = 0;
		while(++i < dirty.size()){
			
			System.out.print(dirty.get(i) + "  ");
			
			if(i%10 == 0){
				System.out.println();
			}
		}
		System.out.println(dirty.size());
	}

}

/*
Request包装类：
*/
class MyDirtyCharacterRequest extends HttpServletRequestWrapper{
	
//	private List<String> dirty = new ArrayList<String>();
	private HttpServletRequest request;
	private List<String> dirty;
	
	public MyDirtyCharacterRequest(HttpServletRequest request, List<String> dirty) {
		super(request);
		this.request = request;
		this.dirty = dirty;
		System.out.println("构造");
	}

	@Override
	public String getParameter(String name) {
		try{
			
			String value = this.request.getParameter(name);
			
			if(value==null){
				return null;
			}
			
			System.out.println("dirty:"+dirty);
			System.out.println("我是过滤器啊*****************************");
			for(String s : dirty){
				if(value.contains(s)){
					value = value.replace(s, "***");
				}
			}
			return value;
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
}

