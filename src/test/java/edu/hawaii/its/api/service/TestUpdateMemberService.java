//package edu.hawaii.its.api.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import edu.hawaii.its.api.configuration.SpringBootWebApplication;
//import edu.hawaii.its.api.exception.AccessDeniedException;
//import edu.hawaii.its.api.exception.CommandException;
//import edu.hawaii.its.api.exception.UhMemberNotFoundException;
//import edu.hawaii.its.api.groupings.GroupingMembers;
//import edu.hawaii.its.api.groupings.GroupingReplaceGroupMembersResult;
//import edu.hawaii.its.api.type.OptType;
//import edu.internet2.middleware.grouperClient.ws.GcWebServiceError;
//
//@ActiveProfiles("integrationTest")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest(classes = { SpringBootWebApplication.class })
//public class TestUpdateMemberService {
//
//    @Value("${groupings.api.test.grouping_many}")
//    private String GROUPING;
//
//    @Value("${groupings.api.test.grouping_many_include}")
//    private String GROUPING_INCLUDE;
//
//    @Value("${groupings.api.test.grouping_many_exclude}")
//    private String GROUPING_EXCLUDE;
//
//    @Value("${groupings.api.test.grouping_many_owners}")
//    private String GROUPING_OWNERS;
//
//    @Value("${groupings.api.grouping_admins}")
//    private String GROUPING_ADMINS;
//
//    @Value("${groupings.api.test.admin_user}")
//    private String ADMIN;
//
//    @Value("${groupings.api.success}")
//    private String SUCCESS;
//
//    @Autowired
//    private UpdateMemberService updateMemberService;
//
//    @Autowired
//    private MemberService memberService;
//
//    @Autowired
//    private GroupingAttributeService groupingAttributeService;
//
//    @Autowired
//    private GrouperService grouperService;
//
//    @Autowired
//    private UhIdentifierGenerator uhIdentifierGenerator;
//
//    private List<String> testUids;
//    private List<String> testUhUuids;
//    private Map<String, Boolean> attributeMap = new HashMap<>();
//
//    @BeforeAll
//    public void init() {
//        GroupingMembers testGroupingMembers = uhIdentifierGenerator.getRandomMembers(5);
//        testUids = testGroupingMembers.getUids();
//        testUhUuids = testGroupingMembers.getUhUuids();
//
//        // Initial setting for testing opt-in opt-out related functions in update member service
//        attributeMap.put(OptType.IN.value(), groupingAttributeService.isGroupAttribute(GROUPING, OptType.IN.value()));
//        attributeMap.put(OptType.OUT.value(), groupingAttributeService.isGroupAttribute(GROUPING, OptType.OUT.value()));
//        groupingAttributeService.changeGroupAttributeStatus(GROUPING, ADMIN, OptType.IN.value(), false);
//        groupingAttributeService.changeGroupAttributeStatus(GROUPING, ADMIN, OptType.OUT.value(), false);
//
//        grouperService.removeMember(ADMIN, GROUPING_ADMINS, testUids.get(0));
//        grouperService.removeMembers(ADMIN, GROUPING_INCLUDE, testUids);
//        grouperService.removeMembers(ADMIN, GROUPING_EXCLUDE, testUids);
//    }
//
//    @Test
//    public void addRemoveAdminTest() {
//        // With uh number.
//        assertFalse(memberService.isAdmin(testUhUuids.get(0)));
//        updateMemberService.addAdminMember(ADMIN, testUhUuids.get(0));
//        assertTrue(memberService.isAdmin(testUhUuids.get(0)));
//        updateMemberService.removeAdminMember(ADMIN, testUhUuids.get(0));
//        assertFalse(memberService.isAdmin(testUhUuids.get(0)));
//
//        // With uh uid.
//        assertFalse(memberService.isAdmin(testUids.get(0)));
//        updateMemberService.addAdminMember(ADMIN, testUids.get(0));
//        assertTrue(memberService.isAdmin(testUids.get(0)));
//        updateMemberService.removeAdminMember(ADMIN, testUids.get(0));
//        assertFalse(memberService.isAdmin(testUids.get(0)));
//
//        try {
//            updateMemberService.addAdminMember(ADMIN, "bogus-admin-to-add");
//            fail("Should throw an exception if an invalid adminToAdd is passed.");
//        } catch (UhMemberNotFoundException e) {
//            assertNull(e.getCause());
//        }
//
//        try {
//            updateMemberService.removeAdminMember(ADMIN, "bogus-admin-to-remove");
//            fail("Should throw an exception if an invalid adminToRemove is passed.");
//        } catch (UhMemberNotFoundException e) {
//            assertNull(e.getCause());
//        }
//    }
//
//    @Test
//    public void validateOptInActionForAlreadyOptedUser() {
//        String testUid = testUhUuids.get(0);
//        updateMemberService.addAdminMember(ADMIN, testUid);
//        updateMemberService.addOwnerships(ADMIN, GROUPING, testUids);
//
//        try {
//            updateMemberService.optIn(ADMIN, GROUPING, testUid);
//            updateMemberService.optIn(ADMIN, GROUPING, testUid);
//        } catch (Exception e) {
//            fail("should not throw an exception if user does self opt in when user already opted in");
//        }
//
//        try {
//            updateMemberService.removeIncludeMember(ADMIN, GROUPING, testUid);
//            updateMemberService.optIn(ADMIN, GROUPING, testUid);
//            assertTrue(memberService.isMember(GROUPING_INCLUDE, testUid));
//        } catch (CommandException e) {
//            fail("Should not throw an exception because opt in operation can be executed after removing include member");
//        }
//        updateMemberService.removeIncludeMember(ADMIN, GROUPING, testUid);
//        updateMemberService.removeAdminMember(ADMIN, testUid);
//        updateMemberService.removeOwnerships(ADMIN, GROUPING, testUids);
//    }
//
//    @Test
//    public void validateOptOutActionForAlreadyOptedUser() {
//        String testUid = testUhUuids.get(0);
//        updateMemberService.addAdminMember(ADMIN, testUid);
//        updateMemberService.addOwnerships(ADMIN, GROUPING, testUids);
//        try {
//            updateMemberService.optOut(ADMIN, GROUPING, testUid);
//            updateMemberService.optOut(ADMIN, GROUPING, testUid);
//        } catch (CommandException e) {
//            fail("should not throw an exception if user does self opt out when user already opted out");
//        }
//
//        updateMemberService.removeExcludeMember(ADMIN, GROUPING, testUid);
//
//        try {
//            updateMemberService.removeIncludeMember(ADMIN, GROUPING, testUid);
//            updateMemberService.optOut(ADMIN, GROUPING, testUid);
//            assertFalse(memberService.isMember(GROUPING_EXCLUDE, testUid));
//        } catch (CommandException e) {
//            fail("Should not throw an exception because opt out operation is not executed after removing include member");
//        }
//
//        updateMemberService.removeAdminMember(ADMIN, testUid);
//        updateMemberService.removeOwnerships(ADMIN, GROUPING, testUids);
//    }
//
//    private void addGroupMember(String groupPath, String uhIdentifier) {
//        grouperService.addMember(ADMIN, groupPath, uhIdentifier);
//    }
//
//    private void removeGroupMember(String groupPath, String uhIdentifier) {
//        grouperService.removeMember(ADMIN, groupPath, uhIdentifier);
//    }
//
//}