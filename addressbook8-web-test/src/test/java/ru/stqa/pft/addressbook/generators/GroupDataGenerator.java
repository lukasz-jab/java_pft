package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luk on 2017-04-26.
 */
public class GroupDataGenerator {
    @Parameter(names = "-c", description = "Group count")
    private int count;

    @Parameter(names = "-f", description = "Target file")
    private String file;

    @Parameter(names = "-d", description = "File format")
    private String format;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander.newBuilder().addObject(generator).build().usage();
        JCommander.newBuilder().addObject(generator).build().parse(args);
        generator.run();
    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);
        if (format.equals("csv")) {
            saveAsSCV(groups, new File(file));
        } else if (format.equals("xml")) {
            saveAsXML(groups, new File(file));
        } else System.out.println("Unrecognized format: " + format);

    }

    private void saveAsXML(List<GroupData> groups, File file) throws IOException {
        XStream xstream = new XStream();
        //xstream.alias("group", GroupData.class);
        xstream.processAnnotations(GroupData.class);
        String xml = xstream.toXML(groups);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsSCV(List<GroupData> groups, File file) throws IOException {

        Writer writer = new FileWriter(file);
        for (GroupData group : groups) {
            writer.write(group.getName() + "," + group.getHeader() + "," + group.getFooter() + String.format("%n"));
        }
        writer.close();
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName("group nr " + (i + 1)).withHeader("header nr " + (i + 1))
                    .withFooter("footer nr " + (i + 1)));
        }
        return groups;
    }
}
