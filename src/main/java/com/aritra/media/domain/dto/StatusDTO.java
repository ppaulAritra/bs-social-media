package com.aritra.media.domain.dto;

/**
 * @author Aritra Paul
 * @created_on 10/3/20 at 12:04 AM
 * @project socialmedia
 */

public class StatusDTO {
    private Long id;
    private String status;
    private String privacy;
    private Long locationId;
    private Long userId;
    private String userName;
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "StatusDTO{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", privacy='" + privacy + '\'' +
                ", locationId='" + locationId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
