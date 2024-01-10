package edu.hawaii.its.api.groupings;

import edu.hawaii.its.api.type.GroupingPath;
import edu.hawaii.its.api.wrapper.GetGroupsResults;
import edu.hawaii.its.api.wrapper.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupingPaths {

    private String resultCode;
    private List<GroupingPath> groupingPaths;


    public GroupingPaths() {
        setGroupingPaths(new ArrayList<>());
        setResultCode("FAILURE");
    }

    public GroupingPaths(GetGroupsResults getGroupsResults) {
        setGroupingPaths(getGroupsResults);
        setResultCode(getGroupsResults.getResultCode());
    }

    public void setGroupingPaths(List<GroupingPath> groupingPaths) {
        this.groupingPaths = groupingPaths;
    }

    public void setGroupingPaths(GetGroupsResults getGroupsResults) {
        this.groupingPaths = new ArrayList<>();
        for (Group group : getGroupsResults.getGroups()) {
            this.groupingPaths.add(new GroupingPath(group.getGroupPath()));
        }

    }

    public List<GroupingPath> getGroupingPaths() { return this.groupingPaths; }

    public void setResultCode(String resultCode) { this.resultCode = resultCode; }

    public String getResultCode() { return this.resultCode; }
}