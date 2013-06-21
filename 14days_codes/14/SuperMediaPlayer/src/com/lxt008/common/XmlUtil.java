package com.lxt008.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlUtil {
	
	/**
	 * 使用DOM解析XML文件
	 * 
	 * @param is 输入流
	 * @param elementsTagName
	 * 元素标签名,应该为二级元素才行 ,如:<musics><music></music></musics>中的music
	 * 
	 * @return 包含二级元素文本内容的字符串列表
	 */
	public static List<String> parseXml(InputStream is,String elementsTagName) {
		List<String> list = new ArrayList<String>();

		/* 将XML转换成Document对象 */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(is);
			NodeList nodeList = doc.getElementsByTagName(elementsTagName);
			int len = nodeList.getLength();
			for (int i = 0; i < len; i++) {
				String content = nodeList.item(i).getChildNodes().item(0)
						.getNodeValue();				
				list.add(content);
			}
		} catch (ParserConfigurationException e) {		
			e.printStackTrace();
		} catch (SAXException e) {		
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}

		return list;
	}

}
