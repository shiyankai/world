package fileDeal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TextFile {
	private static BufferedReader br;
	/**
	 * 获取文本中数据
	 */
	static List<Object> getTextFileInfo(String path){
		List<Object> outList = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
			String strLine = "";
			outList = new ArrayList<Object>();
			while((strLine = br.readLine()) != null ){
				strLine = initData(strLine);
				outList.add(strLine);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return outList;
	}
	/**
	 * 获取excel数据
	 */
	static List<Object> getXlsInfo(String path) throws Exception{
		XSSFWorkbook wbXSS = null;
		HSSFWorkbook wbHSS = null;
		FileOutputStream fout = new FileOutputStream("/demo.xlsx");  ;
		if(path.indexOf(".xlsx")>0){
			try {
				wbXSS = new XSSFWorkbook(new FileInputStream(new File(path)));
				XSSFSheet sheet = wbXSS.getSheetAt(0);
				XSSFRow row = null;
	            wbXSS.write(fout);  
	            fout.close();  
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(path.indexOf(".xls")>0){
			try {
				wbHSS = new HSSFWorkbook(new FileInputStream(new File(path)));
				HSSFSheet sheet = wbHSS.getSheetAt(0);
				HSSFRow row = null;
	            wbHSS.write(fout);  
	            fout.close();  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}else{
			throw new Exception("文件格式不正确！");
		}
		return null;
	}
	/**
	 * 行数据切换为标准格式
	 */
	static String initData(String strLine){
		String tempStr = "",endStr = "";
		strLine = strLine.replace(" ", " ");
		strLine = strLine.replace("	", " ");
		strLine = strLine.replace("，", ",");
		boolean flag = true;
		for (int i = 0; i < strLine.length(); i++) {
			tempStr = strLine.substring(i, i+1);
			if(" ".equals(tempStr)){
				if(flag){
					endStr += "	";
					flag = false;
				}
			}else{
				endStr += tempStr;
				flag = true;
			}
		}
		return endStr;
	}
	static void createFile(String path , String context){
		path = path.replace("\\", "/");
		path = path.replace(".", "/");
		path = path.replace("_", "/");
		File file = null;
		if(path.indexOf("/")<-1){
			file = new File(path);
		}else{
			String[] pathCol = path.split("/");
			String fileName = pathCol[pathCol.length-1];
			String filePath = path.substring(0,path.indexOf(fileName));
			file = new File(filePath);
			if(!file.exists()){
				file.mkdirs();
			}
			file = new File(path);
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			FileWriter writer;
			try {
				writer = new FileWriter(path, true);
				writer.write(context);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
public static void main(String[] args) throws Exception {
	LineContext lc = new LineContext();
	lc.appenLine("11");
	lc.appenLine("ce");
	System.out.println(lc.toString());
	String basePathAo = "src/com/sitech/ordersvc/person/com.sitech.ordersvc.person.atom";
	String basePathCo = "src/com/sitech/ordersvc/person/";
	String str = "1\n2";
	createFile("sadas//asd//s.txt",str);
		//getTextFileInfo("C:\\Users\\nice\\Desktop\\soapui_pro\\IQpChkProdSvcResAoSvc-soapui-project.xml");
		//getXlsInfo("C:\\Users\\nice\\Desktop\\CRM云化上线代码清单@20170801.xls");
	}

}
