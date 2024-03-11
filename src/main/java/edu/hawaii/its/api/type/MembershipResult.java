package edu.hawaii.its.api.type;

public class MembershipResult {

    private String path;
    private String name;
    private String description;
    private boolean isSelfOpted = false;
    private boolean isOptInEnabled = false;
    private boolean isOptOutEnabled = false;

    // Constructor.
    public MembershipResult() {
        // Empty.
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSelfOpted() {
        return isSelfOpted;
    }

    public boolean isOptInEnabled() {
        return isOptInEnabled;
    }

    public boolean isOptOutEnabled() {
        return isOptOutEnabled;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSelfOpted(boolean isSelfOpted) {
        this.isSelfOpted = isSelfOpted;
    }

    public void setOptInEnabled(boolean isOptInEnabled) {
        this.isOptInEnabled = isOptInEnabled;
    }

    public void setOptOutEnabled(boolean isOptOutEnabled) {
        this.isOptOutEnabled = isOptOutEnabled;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isSelfOpted=" + isSelfOpted +
                ", isOptInEnabled=" + isOptInEnabled +
                ", isOptOutEnabled=" + isOptOutEnabled +
                '}';
    }
}
