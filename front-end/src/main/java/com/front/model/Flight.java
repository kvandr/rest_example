package com.front.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Objects;


@Data
public class Flight {

    private Long flightId;

    private String airbus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private Date departTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private Date travelTime;

    private Route route;

    private User user;

    public Flight(Long flightId, String airbus, Route route, Date departTime, Date travelTime, User user) {
        this.flightId = flightId;
        this.airbus = airbus;
        this.route = route;
        this.departTime = departTime;
        this.travelTime = travelTime;
        this.user = user;
    }

    public Flight() {
    }

    public Flight(String airbus, Route route, Date departTime, Date travelTime, User user) {
        this.airbus = airbus;
        this.route = route;
        this.departTime = departTime;
        this.travelTime = travelTime;
        this.user = user;
    }

    public String getUserName(){
        return user != null ? user.getUsername() : "<none>";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return flight.departTime.compareTo(departTime) == 0 &&
                flight.travelTime.compareTo(travelTime) == 0 &&
                Objects.equals(airbus, flight.airbus) && Objects.equals(route, flight.route)
                && Objects.equals(user, flight.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airbus, route, departTime, travelTime, route, user);
    }
}
