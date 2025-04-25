package edu.hawaii.its.api.type;

import java.util.List;
import java.util.Map;

public class OotbActiveProfile {
    private String uid;
    private String uhUuid;
    private List<String> authorities;
    private Map<String, String> attributes;
    private List<OotbGrouping> ootbGroupings;

    public List<OotbGrouping> getGroupings() { return ootbGroupings; }

    public void setGroupings(List<OotbGrouping> ootbGroupings) { this.ootbGroupings = ootbGroupings; }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUhUuid() {
        return uhUuid;
    }

    public void setUhUuid(String uhUuid) {
        this.uhUuid = uhUuid;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}