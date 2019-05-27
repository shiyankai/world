package dataDeal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InitData {
	Map<String, String> user ;
	String orderLine = "代理|||业务单号|||收件人姓名|||收件人电话|||收件人手机|||收货人省份|||收货人城市|||收货人县区|||收货人地址|||订单金额|||商品名称|||商品编码|||数量|||备注|||付款时间|||快递公司";
	String[] orderLineMap = orderLine.split("|||");
	public static void main(String[] args) {
		/*String sad = "sadasdasd";
		createFile("dataSource/123",sad);*/
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("大的", "小的");*/
		System.out.println(Integer.parseInt("8.00".replace(".00", "")));
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
				writer.write("\n");
				writer.append("qwee萨达");
				writer.write("\n");
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
