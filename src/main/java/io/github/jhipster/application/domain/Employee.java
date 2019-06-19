package io.github.jhipster.application.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * The Employee entity.
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "advisor_id")
    private String advisorId;

    @Column(name = "appointment_id")
    private String appointmentId;

    @Column(name = "room_id")
    private String roomId;

    @Column(name = "token")
    private String token;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "start_date")
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

    public Employee clientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAdvisorId() {
        return advisorId;
    }

    public Employee advisorId(String advisorId) {
        this.advisorId = advisorId;
        return this;
    }

    public void setAdvisorId(String advisorId) {
        this.advisorId = advisorId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Employee appointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
        return this;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getRoomId() {
        return roomId;
    }

    public Employee roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getToken() {
        return token;
    }

    public Employee token(String token) {
        this.token = token;
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Employee endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Employee startDate(Instant startDate) {
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
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Employee{" +
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
