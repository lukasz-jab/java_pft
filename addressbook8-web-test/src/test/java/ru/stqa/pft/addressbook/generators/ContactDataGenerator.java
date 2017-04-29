package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luk on 2017-04-28.
 */
public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    private int count;

    @Parameter(names = "-f", description = "Target file")
    private String file;

    @Parameter(names = "-d", description = "File format")
    private String format;


    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander.newBuilder().addObject(generator).build().usage();
        JCommander.newBuilder().addObject(generator).build().parse(args);
        generator.run();

    }

    private static void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(contact.getFirstName() + "," + contact.getLastName() + "," + contact.getAddress()
                    + "," + contact.getHomePhone() + "," + contact.getMobilePhone() + "," + contact.getWorkPhone()
                    + "," + contact.getPhoto() + String.format("\n"));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname("Name" + String.valueOf((int) (Math.random() * 10)))
                    .withLastname("Lastname" + String.valueOf((int) (Math.random() * 10)))
                    .withAddress("Address " + ((int) (Math.random() * 10))).withHomePhone(String.valueOf((int) (Math.random() * 1000000)))
                    .withMobilePhone((String.valueOf((int) (Math.random() * 1000000)))).withWorkPhone((String.valueOf((int) (Math.random() * 1000000))))
                    .withPhoto(new File("src/test/resources/s.jpg")));

        }
        return contacts;
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCSV(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXML(contacts, new File(file));
        } else System.out.println("Unrecognized format: " + format);

    }

    private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        //xstream.alias("contact", ContactData.class);
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }
}