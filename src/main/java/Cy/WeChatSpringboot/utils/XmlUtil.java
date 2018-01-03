package Cy.WeChatSpringboot.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import Cy.WeChatSpringboot.pojo.users;

public class XmlUtil {
	private static XStream stream=new XStream(new DomDriver());
	
	public static void main(String[] args) {
		users aaUsers=new users();
		aaUsers.setEmail("asdas");
		aaUsers.setMobile("1672316");
		stream.alias("xml", users.class);
		System.out.println(stream.toXML(aaUsers));
		
	}
}
