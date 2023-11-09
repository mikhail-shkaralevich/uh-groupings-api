package edu.hawaii.its.api.groupings;

import edu.hawaii.its.api.type.GroupingPath;

import java.util.ArrayList;
import java.util.List;

public class GroupingPaths {

    private List<GroupingPath> groupingPaths;
    private String resultCode;

    public GroupingPaths() {
        groupingPaths = new ArrayList<>();
        this.resultCode = "FAILURE";
    }

    public GroupingPaths(List<GroupingPath> groupingPaths) {
        this.groupingPaths = groupingPaths;
        this.resultCode = "SUCCESS";
    }

    public void addGroupingPath(GroupingPath groupingPath) {
        this.groupingPaths.add(groupingPath);
        this.resultCode = "SUCCESS";
    }

    public List<GroupingPath> getGroupingPaths() { return this.groupingPaths; }

    public void setGroupingPaths(List<GroupingPath> groupingPaths) {
        this.groupingPaths = groupingPaths;
        this.resultCode = "SUCCESS";
    }

    public String getResultCode() { return this.resultCode; }
}
