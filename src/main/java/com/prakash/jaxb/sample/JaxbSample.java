package com.prakash.jaxb.sample;

import com.prakash.jaxb.xsd.Inclusions;
import com.prakash.jaxb.xsd.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Praks on 11/7/17.
 */
public class JaxbSample {

    public static void main(String[] args) throws Exception {
        JAXBContext context = JAXBContext.newInstance("com.prakash.jaxb.xsd");
        Unmarshaller unmarshaller = context.createUnmarshaller();

        /* Marshaller Configuration */
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Inclusions rootnode = (Inclusions) unmarshaller.unmarshal(new FileInputStream("src/main/resources/samplexml.xml"));

        System.out.println("-------- Input Xml -----------");
            marshaller.marshal(rootnode,System.out);
        System.out.println("-------- Input Xml - End -----------");

        //Add a new node
        User newUser = new User();
        newUser.setSamaccountname("AddedFromCode");
        newUser.setLastupdatedby("javacode");

        //Add to rootnode
        rootnode.getUser().add(newUser);

        System.out.println("-------- Modified Xml -----------");
        marshaller.marshal(rootnode,System.out);
        System.out.println("-------- Modified Xml - End -----------");

        //Write to a File
        File writeFile = new File("src/main/resources/samplexml_output.xml");
        marshaller.marshal(rootnode,writeFile);

    }
}
