package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;


/**
 * Created by luk on 2017-05-03.
 */
public class DbHelper {

    private final SessionFactory sessionFactory;
    // A SessionFactory is set up once for an application!

    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups group() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where deprecated='0000-00-00'").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public Groups getGroups(ContactData contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
                                                                               // where id=contact.getId()
        List<GroupData> result = session.createQuery("select groups from ContactData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts getContacts(GroupData group) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
                                                                                         //where group_id=group.getId()
        List<ContactData> result = session.createQuery("select contacts from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);

        /*
    public Groups getGroups(ContactData contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = null;
        List<Integer> group_id = session.createNativeQuery("select group_id from address_in_groups where id=" + contact.getId()).list();
        //session.createQuery(select group from ContactData where id="+contact.getId()).list();
        for (int i = 0; i < group_id.size(); i++) {
            // result = session.createQuery("from GroupData where id=" + group_id.get(i)).list();
           // String id = session.createNativeQuery("select group_id from group_list where group_id=" + group_id.get(i)).toString();
            String name = session.createNativeQuery("select group_name from group_list where group_id=" + group_id.get(i)).toString();
            String header = session.createNativeQuery("select group_header from group_list where group_id=" + group_id.get(i)).toString();
            String footer = session.createNativeQuery("select group_footer from group_list where group_id=" + group_id.get(i)).toString();
            result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
            System.out.println("group_id size "+group_id.size()+" name "+name+" header "+header+" footer "+footer);
        }
        session.getTransaction().commit();
        session.close();
        if (result != null) {
            return new Groups(result);
        } else {
            return null;
        }
    }

    public Contacts getContacts(GroupData group) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = null;
        List<Integer> contact_id = session.createNativeQuery("select id from address_in_groups where group_id=" + group.getId()).list();
        for (int i = 0; i < contact_id.size(); i++) {
           //String id = session.createNativeQuery("select id from addressbook where id=" + contact_id.get(i)).toString();
            String firstname = session.createNativeQuery("select firstname from addressbook where id=" + contact_id.get(i)).toString();
            String lastname = session.createNativeQuery("select lastname from addressbook where id=" + contact_id.get(i)).toString();
            result.add(new ContactData().withFirstname(firstname).withLastname(lastname));

            System.out.println("contact_id  "+contact_id.size()+" name "+firstname+" lastname "+lastname );
        }

        session.getTransaction().commit();
        session.close();
        if (result != null) {
            return new Contacts(result);
        } else {
            return null;
        }
    } */
    }
}
