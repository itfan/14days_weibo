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
	 * ʹ��DOM����XML�ļ�
	 * 
	 * @param is ������
	 * @param elementsTagName
	 * Ԫ�ر�ǩ��,Ӧ��Ϊ����Ԫ�ز��� ,��:<musics><music></music></musics>�е�music
	 * 
	 * @return ��������Ԫ���ı����ݵ��ַ����б�
	 */
	public static List<String> parseXml(InputStream is,String elementsTagName) {
		List<String> list = new ArrayList<String>();

		/* ��XMLת����Document���� */
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
