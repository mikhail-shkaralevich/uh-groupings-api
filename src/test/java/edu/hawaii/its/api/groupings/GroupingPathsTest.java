package edu.hawaii.its.api.groupings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.hawaii.its.api.type.GroupingPath;

public class GroupingPathsTest {

    private GroupingPaths groupingPaths;
    private GroupingPaths groupingPaths2;

    @BeforeEach
    public void setup() {
        groupingPaths = new GroupingPaths();
        groupingPaths2 = new GroupingPaths(Arrays.asList(new GroupingPath("path:to:grouping")));
    }

    @Test
    public void nullTest() {
        assertNotNull(groupingPaths);
        assertNotNull(groupingPaths2);
    }

    @Test
    public void addGroupingPathTest() {
        groupingPaths.addGroupingPath(new GroupingPath("path:to:grouping"));
        assertTrue(groupingPaths.getGroupingPaths().size() == 1);
        assertEquals("path:to:grouping", groupingPaths.getGroupingPaths().get(0).getPath());
    }

    @Test
    public void getGroupingPathsTest() {
        assertNotNull(groupingPaths.getGroupingPaths());
        assertTrue(groupingPaths.getGroupingPaths().size() == 0);

        assertNotNull(groupingPaths2.getGroupingPaths());
        assertTrue(groupingPaths2.getGroupingPaths().size() > 0);
        assertEquals("path:to:grouping", groupingPaths2.getGroupingPaths().get(0).getPath());
    }

    @Test
    public void setGroupingPathsTest() {
        List<GroupingPath> testGroupingPaths = new ArrayList<>();
        testGroupingPaths.add(new GroupingPath("path:to:grouping"));
        groupingPaths.setGroupingPaths(testGroupingPaths);
        assertNotNull(groupingPaths.getGroupingPaths());
        assertEquals("path:to:grouping", groupingPaths.getGroupingPaths().get(0).getPath());
    }

    @Test
    public void getResultCodeTest() {
        assertEquals("FAILURE", groupingPaths.getResultCode());
        assertEquals("SUCCESS", groupingPaths2.getResultCode());
    }

}
