package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
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
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander.newBuilder().addObject(generator).build().parse(args);
        generator.run();
    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);
        save(groups, new File(file));
    }

    private void save(List<GroupData> groups, File file) throws IOException {

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
