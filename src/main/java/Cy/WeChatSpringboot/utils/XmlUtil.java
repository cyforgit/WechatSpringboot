package Cy.WeChatSpringboot.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import Cy.WeChatSpringboot.pojo.users;

public class XmlUtil {
	private static XStream stream = new XStream(new DomDriver());
	public XmlUtil() {
		System.out.println("init");
	}
	public static void main(String[] args) {
		XStream.setupDefaultSecurity(stream);
		String[] allowclasses=new String[] {"Cy.WeChatSpringboot.pojo.users"};
		stream.allowTypes(allowclasses);
		users aaUsers=new users();
		users bbUsers=new users();
		aaUsers.setEmail("asdas");
		aaUsers.setMobile("1672316");
		stream.alias("xml", users.class);
		System.out.println(stream.toXML(aaUsers));
		String teString=stream.toXML(aaUsers);
		bbUsers=(users) stream.fromXML(teString);
		System.out.println(bbUsers.toString());
	}
}
