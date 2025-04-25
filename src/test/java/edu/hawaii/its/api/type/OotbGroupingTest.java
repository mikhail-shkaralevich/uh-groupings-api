package edu.hawaii.its.api.type;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OotbGroupingTest {

    private OotbGrouping grouping;

    @BeforeEach
    public void setUp() {
        grouping = new OotbGrouping();
    }

    @Test
    public void construction() {
        assertNotNull(grouping);
    }

    @Test
    public void name() {
        assertNull(grouping.getName());
        grouping.setName("group1");
        assertThat(grouping.getName(), is("group1"));
    }

    @Test
    public void displayName() {
        assertNull(grouping.getDisplayName());
        grouping.setDisplayName("group1");
        assertThat(grouping.getDisplayName(), is("group1"));
    }

    @Test
    public void extension() {
        assertNull(grouping.getExtension());
        grouping.setExtension("include");
        assertThat(grouping.getExtension(), is("include"));
    }

    @Test
    public void displayExtension() {
        assertNull(grouping.getDisplayExtension());
        grouping.setDisplayExtension("include");
        assertThat(grouping.getDisplayExtension(), is("include"));
    }

    @Test
    public void description() {
        assertNull(grouping.getDescription());
        grouping.setDescription("Description 1");
        assertThat(grouping.getDescription(), is("Description 1"));
    }

    @Test
    public void members() {
        assertNull(grouping.getMembers());
        OotbMember member1 = new OotbMember("Member One", "11111111", "member1");
        OotbMember member2 = new OotbMember("Member Two", "22222222", "member2");
        List<OotbMember> members = Arrays.asList(member1, member2);
        grouping.setMembers(members);
        assertNotNull(grouping.getMembers());
        assertThat(grouping.getMembers().size(), is(2));
        assertThat(grouping.getMembers().get(0).getName(), is("Member One"));
        assertThat(grouping.getMembers().get(1).getName(), is("Member Two"));
    }
}
