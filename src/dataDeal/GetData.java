package dataDeal;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class GetData {
	static BufferedReader br;
	static Map<String , List> userGoodsMessages;//用户订购信息
	static Map<String , String> goodsMessages;//商品信息
	static Map<String , String> userMessages;//用户信息
	public static void initBaseData() {
		getFromText("goodsMessage");
		getFromText("userMessage");
	}
	private static void getFromText(String name) {
		// TODO Auto-generated method stub
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(System.getProperty("user.dir")+"/dataSource/"+name),"UTF-8"));
			String strLine = "";
			if("goodsMessage".equals(name)){
				goodsMessages = new HashMap<String, String>();
			}else if("userMessage".equals(name)){
				userMessages = new HashMap<String, String>();
			}
			while((strLine = br.readLine()) != null && !"".equals(strLine.trim()) ){
				if(strLine.indexOf("//")==-1){
					strLine = initGoodsMessageData(strLine);
					if ("goodsMessage".equals(name)) {
						goodsMessages.put(strLine.split("##")[0].trim(),strLine);
					}else if("userMessage".equals(name)){
						userMessages.put(strLine.split("##")[0].trim(),strLine);
					}
				}
			}
			br.close();
		} catch (Exception e) {
			//sadasd
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public static void main(String[] args) throws Exception {
		String path = "C:\\Users\\test\\Desktop\\医可诗201904\\";
		File files = new File(path);
		initBaseData();
		File[] fileList = files.listFiles();
		userGoodsMessages = new HashMap<String, List>();
		for (int i = 0; i < fileList.length; i++) {
			getXlsInfo(fileList[i],userGoodsMessages);
		}
		createExcel(path,userGoodsMessages);
	}
	//创建数据
	static void createExcel(String path,Map<String, List> userGoodsMessages) throws Exception{
		FileOutputStream out = null; 
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("代理明细");  //代理货物总结
		HSSFSheet sheet1 = workbook.createSheet("货物明细");  
		try {  
			int i = 1;
			List<String> list;
			for (Entry<String, List> entry : userGoodsMessages.entrySet()) {
				HSSFRow row = sheet.createRow(0);
		    	boolean flag = true;
		    	if(flag){
		    		flag = false;
		    		row.createCell(0).setCellValue("代理");
			    	row.createCell(1).setCellValue("业务单号");
			    	row.createCell(2).setCellValue("收件人姓名");
			    	row.createCell(3).setCellValue("收件人电话");
			    	row.createCell(4).setCellValue("商品名称");
			    	row.createCell(5).setCellValue("商品编码");
			    	row.createCell(6).setCellValue("数量");
			    	row.createCell(7).setCellValue("备注");
			    	row.createCell(8).setCellValue("时间");
			    	row.createCell(9).setCellValue("单价");
			    	row.createCell(10).setCellValue("总价");
		    	}
		    	String[] value ;
			    for (int j = 0; j < (list = (List)entry.getValue()).size(); j++) {
			    	row = sheet.createRow(i++);
			    	value = list.get(j).split(",");
			    	////0代理,1业务单号,2收件人姓名,3收件人电话,4商品名称,5商品编码,6数量,7备注,8时间,9单价,10总价
			    	row.createCell(0).setCellValue(value[0]);
			    	row.createCell(1).setCellValue(value[1]);
			    	row.createCell(2).setCellValue(value[2]);
			    	row.createCell(3).setCellValue(value[3]);
			    	row.createCell(4).setCellValue(value[4]);
			    	row.createCell(5).setCellValue(value[5]);
			    	if(value[6] == null || "".equals(value[6])){
			    		value[6] = "0";
			    	}
			    	row.createCell(6).setCellValue(Integer.parseInt(value[6].replace(".00", "").replace(".0", "").replace(".", "")));
                    row.createCell(7).setCellValue(value[7]);
                    row.createCell(8).setCellValue(value[8]);
                    
                    if(value[5] != null && goodsMessages.get(value[5]) != null ){
                    	row.createCell(9).setCellValue(goodsMessages.get(value[5]).split("##")[5]);
                    	int count = Integer.parseInt(goodsMessages.get(value[5]).split("##")[7])+Integer.parseInt(value[6].replace(".00", "").replace(".0", "").replace(".", ""));
                    	goodsMessages.put(value[5],
                    			goodsMessages.get(value[5]).substring(0,
                    					goodsMessages.get(value[5]).lastIndexOf("##"))+"##"+count);
                    	
                    }
				}
			}
			int row1 = 1;
			HSSFRow row = sheet1.createRow(0);
			row.createCell(0).setCellValue("编码");//编码
			row.createCell(1).setCellValue("货物名称");//货物名称
			row.createCell(2).setCellValue("单价");//单价
			row.createCell(3).setCellValue("数量");//数量
			row.createCell(4).setCellValue("总价");//总价
			row.createCell(5).setCellValue("利润");//利润
			for (Entry<String, String> goods : goodsMessages.entrySet()) {
				row = sheet1.createRow(row1++);
				String goodsStr = goods.getValue();
				row.createCell(0).setCellValue(goodsStr.split("##")[0]);//编码
				row.createCell(1).setCellValue(goodsStr.split("##")[2]);//货物名称
				row.createCell(2).setCellValue(goodsStr.split("##")[5]);//价格
				row.createCell(3).setCellValue(goodsStr.split("##")[7]);//数量
				row.createCell(4).setCellValue(Integer.parseInt(goodsStr.split("##")[5])*Integer.parseInt(goodsStr.split("##")[7]));//价格
				if("6922889700045".equals(goodsStr.split("##")[0])
						||"6922889700083".equals(goodsStr.split("##")[0])
						||"6922889700120".equals(goodsStr.split("##")[0])
						||"6922889700113".equals(goodsStr.split("##")[0])){//茶的利润10块
					row.createCell(5).setCellValue(Integer.parseInt(goodsStr.split("##")[7])*10);
				}else if("6952698413058".equals(goodsStr.split("##")[0])
						||"6952698413058-1".equals(goodsStr.split("##")[0])){//山药粉利润23
					row.createCell(5).setCellValue(Integer.parseInt(goodsStr.split("##")[7])*23);
				}else{
					row.createCell(5).setCellValue(Integer.parseInt(goodsStr.split("##")[7])*Integer.parseInt(goodsStr.split("##")[5])*0.08);
				}
			}
            out = new FileOutputStream(path + System.currentTimeMillis() +".xls");  
            workbook.write(out);  
			workbook.cloneSheet(0);
        } catch (Exception e) {  
            throw e;
        } finally {    
            try {    
                out.close();    
            } catch (IOException e) {    
                e.printStackTrace();  
            }    
        }
	}
	/**
	 * 获取excel数据
	 */
	static List<Object> getXlsInfo(File file ,Map<String , List> userGoodsMessages) throws Exception{
		XSSFWorkbook wbXSS = null;
		HSSFWorkbook wbHSS = null;
		//FileOutputStream fout = new FileOutputStream("/demo.xlsx"); 
		if(file.getName().indexOf(".xlsx")>0){
			try {
				wbXSS = new XSSFWorkbook(new FileInputStream(file));
				XSSFSheet sheet = wbXSS.getSheetAt(0);
				String data ,context;
				List<String> tempList;
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					XSSFRow row = (XSSFRow) sheet.getRow(i);// 获取行对象
					if (row == null) {// 如果为空，不处理
		                continue;
		            }
					if (row.getCell(0) != null && !"".equals(data = row.getCell(0).toString().trim())) {
						//0代理,1业务单号,2收件人姓名,3收件人电话,4商品名称,5商品编码,6数量,7备注,8时间
						DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
						//格式1
						/*Date d = row.getCell(27).getDateCellValue();
						context = row.getCell(0).toString().trim() + "," +row.getCell(1).toString().trim() + ","
	                			+row.getCell(11).toString().trim() + ","+row.getCell(12).toString().trim() + ","
	                			+row.getCell(21).toString().trim() + ","+row.getCell(22).toString().trim() + ","
	                			+row.getCell(25).toString().trim() + ","+row.getCell(26).toString().trim() + ","
	                			+formater.format(d);*/
	                	
	                	//格式2
	                	Date d = row.getCell(15).getDateCellValue();
	                	context = row.getCell(0).toString().trim() + "," +row.getCell(1).toString().trim() + ","
	                			+row.getCell(2).toString().trim() + ","+row.getCell(3).toString().trim() + ","
	                			+row.getCell(8).toString().trim() + ","+row.getCell(9).toString().trim() + ","
	                			+row.getCell(10).toString().trim() + ","+row.getCell(12).toString().trim() + ","
	                			+formater.format(d);
	                	
		                if(userGoodsMessages.get(data) == null || userGoodsMessages.get(data).size() == 0){
		                	tempList = new ArrayList<String>();
		                }else{
		                	tempList = (List)userGoodsMessages.get(data);
		                }
		                
		                tempList.add(context);
		                userGoodsMessages.put(data, tempList);
					}
			    }
				wbXSS.cloneSheet(0);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(file.getName().indexOf(".xls")>0){
			try {
				wbHSS = new HSSFWorkbook(new FileInputStream(file));
				HSSFSheet sheet = wbHSS.getSheetAt(0);
				HSSFRow row = null;
	            //wbHSS.write(fout);  
	            //fout.close();  
				wbHSS.cloneSheet(0);
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
	 * 处理商品信息的数据
	 * @param strLine
	 * @return
	 */
	private static String initGoodsMessageData(String strLine) {
		// TODO Auto-generated method stub
		return strLine;
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
}
