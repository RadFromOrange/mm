package io.github.jhipster.application.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * The Meeting entity.
 */
@Entity
@Table(name = "VISIOMEETING" )
public class Meeting implements Serializable {

    private static final long serialVersionUID = 1L;


   /* @Column(name = "VMID", nullable = false)
    @SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName = "HIBERNATE_SEQUENCE", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")*/
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "VMID", nullable = false)
    private Long id;

    @Column(name = "ID_CLIENT", nullable = true)
    private String clientId;

    @Column(name = "BRID_NUMBER", nullable = true)
    private String advisorId;

    @Column(name = "VISIOMEETINGID", nullable = false, length = 100)
    private String appointmentId;

    @Column(name = "ROOMID", nullable = false, length = 100)
    private String roomId;

    @Column(name = "ROOM_TOKEN", nullable = true, length = 250)
    private String token;

    @Column(name = "UPDATEDATE", nullable = true)
    private Instant endDate;

    @Column(name = "MEETINGDATE", nullable = true)
    private Instant startDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public Meeting clientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAdvisorId() {
        return advisorId;
    }

    public Meeting advisorId(String advisorId) {
        this.advisorId = advisorId;
        return this;
    }

    public void setAdvisorId(String advisorId) {
        this.advisorId = advisorId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Meeting appointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
        return this;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getRoomId() {
        return roomId;
    }

    public Meeting roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getToken() {
        return token;
    }

    public Meeting token(String token) {
        this.token = token;
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Meeting endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Meeting startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Meeting)) {
            return false;
        }
        return id != null && id.equals(((Meeting) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Meeting{" +
            "id=" + getId() +
            ", clientId='" + getClientId() + "'" +
            ", advisorId='" + getAdvisorId() + "'" +
            ", appointmentId='" + getAppointmentId() + "'" +
            ", roomId='" + getRoomId() + "'" +
            ", token='" + getToken() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", startDate='" + getStartDate() + "'" +
            "}";
    }
}
