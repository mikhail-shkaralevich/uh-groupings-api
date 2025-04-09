package edu.hawaii.its.api.wrapper;

import java.util.List;

import edu.hawaii.its.api.controller.*;
import edu.internet2.middleware.grouperClient.api.GcGetMembers;
import edu.internet2.middleware.grouperClient.ws.WsMemberFilter;
import edu.internet2.middleware.grouperClient.ws.beans.WsGetMembersResults;
import org.apache.commons.logging.*;

/**
 * A wrapper for GcGetMembers. When groupPath(s) are passed, GetMembersCommand on execute fetches, from grouper, results
 * containing all the members of that group at groupPath.
 */
public class GetMembersCommand extends GrouperCommand implements Command<GetMembersResults> {

    private final GcGetMembers gcGetMembers;

    private static final Log logger = LogFactory.getLog(GetMembersCommand.class);

    public GetMembersCommand() {
        this.gcGetMembers = new GcGetMembers();
        this.gcGetMembers.assignContentType("text/x-json"); // Remove after upgrading to Grouper 4
        this.gcGetMembers.assignIncludeSubjectDetail(true);
    }

    @Override
    public GetMembersResults execute() {
        WsGetMembersResults wsGetMembersResults = gcGetMembers.execute();
        return new GetMembersResults(wsGetMembersResults);
    }

    public GetMembersCommand addGroupPaths(List<String> groupPaths) {
        for (String groupPath : groupPaths) {
            gcGetMembers.addGroupName(groupPath);
        }
        return this;
    }

    public GetMembersCommand addGroupPath(String groupPath) {
        gcGetMembers.addGroupName(groupPath);
        return this;
    }

    public GetMembersCommand setPageNumber(Integer pageNumber) {
        gcGetMembers.assignPageNumber(pageNumber);
        return this;
    }

    public GetMembersCommand setPageSize(Integer pageSize) {
        gcGetMembers.assignPageSize(pageSize);
        return this;
    }

    public GetMembersCommand setAscending(boolean isAscending) {
        gcGetMembers.assignAscending(isAscending);
        return this;
    }

    public GetMembersCommand sortBy(String sortString) {
        gcGetMembers.assignSortString(sortString);
        return this;
    }

    public GetMembersCommand owner(String uhIdentifier) {
        gcGetMembers.assignActAsSubject(subjectLookup(uhIdentifier));
        return this;
    }

    public GetMembersCommand addSubjectAttribute(String subjectAttribute) {
        gcGetMembers.addSubjectAttributeName(subjectAttribute);
        return this;
    }

    public GetMembersCommand assignMemberFilter(MemberFilter memberFilter) {
        gcGetMembers.assignMemberFilter(memberFilter.value());
        return this;
    }

    public GetMembersCommand assignOwnerFilter(String filter) {
        WsMemberFilter memberFilter =
                filter.equalsIgnoreCase("All") ? WsMemberFilter.All : WsMemberFilter.Immediate;
        logger.info("Member Filter: " + memberFilter);
        gcGetMembers.assignMemberFilter(memberFilter);
        return this;
    }
}
