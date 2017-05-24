package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by luk on 2017-05-24.
 */
public class SoapHelper {

    private ApplicationManager app;

    public SoapHelper(ApplicationManager app) {

        this.app = app;
    }

    public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mantisConnectPort = new MantisConnectLocator().getMantisConnectPort(new URL("http://localhost/mantisbt/api/soap/mantisconnect.php"));
        ProjectData[] projectDatas = mantisConnectPort.mc_projects_get_user_accessible("Administrator", "root");
        return Arrays.asList(projectDatas).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mantisConnectPort = new MantisConnectLocator().getMantisConnectPort(new URL("http://localhost/mantisbt/api/soap/mantisconnect.php"));
        String[] issueCategories = mantisConnectPort.mc_project_get_categories("Administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDesciption());
        issueData.setCategory(issueCategories[0]);
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        BigInteger issueId = mantisConnectPort.mc_issue_add("Administrator", "root", issueData);
        IssueData newIssueData = mantisConnectPort.mc_issue_get("Administrator", "root", issueId);
        return new Issue().withId(newIssueData.getId().intValue()).withSummary(newIssueData.getSummary())
                .withDesciption(newIssueData.getDescription())
                .withProject(new Project().withId(newIssueData.getProject().getId().intValue())
                        .withName(newIssueData.getProject().getName()));
    }
}
