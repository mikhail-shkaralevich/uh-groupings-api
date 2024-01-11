package edu.hawaii.its.api.groupings;

import edu.hawaii.its.api.type.GroupingPath;
import edu.hawaii.its.api.wrapper.Group;
import edu.hawaii.its.api.wrapper.GroupAttributeResults;

import java.util.ArrayList;
import java.util.List;

public class GroupingPaths {

    private String resultCode;
    private List<GroupingPath> groupingPaths;


    public GroupingPaths() {
        setGroupingPaths(new ArrayList<>());
        setResultCode("FAILURE");
    }

    public GroupingPaths(GroupAttributeResults groupAttributeResults) {
        setGroupingPaths(groupAttributeResults);
        setResultCode(groupAttributeResults.getResultCode());
    }

    public void setGroupingPaths(List<GroupingPath> groupingPaths) {
        this.groupingPaths = groupingPaths;
    }

    public void setGroupingPaths(GroupAttributeResults groupAttributeResults) {
        this.groupingPaths = new ArrayList<>();
        for (Group group : groupAttributeResults.getGroups()) {
            this.groupingPaths.add(new GroupingPath(group.getGroupPath()));
        }
    }

    public List<GroupingPath> getGroupingPaths() { return this.groupingPaths; }

    public void setResultCode(String resultCode) { this.resultCode = resultCode; }

    public String getResultCode() { return this.resultCode; }

    public void addGroupingPath(GroupingPath path) {
        this.groupingPaths.add(path);
    }
}