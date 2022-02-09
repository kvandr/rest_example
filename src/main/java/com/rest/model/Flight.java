package com.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long flightId;
    @Column
    private String airbus;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private Date departTime;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private Date travelTime;

    @ManyToOne()
    @JoinColumn(name="route_id")
    private Route route;

    public Route getRoutes() {
        return route;
    }


    public Flight(Long flightId, String airbus, Route route, Date departTime, Date travelTime) {
        this.flightId = flightId;
        this.airbus = airbus;
        this.route = route;
        this.departTime = departTime;
        this.travelTime = travelTime;
    }

    public Flight() {
    }

    public Flight(String airbus, Route route, Date departTime, Date travelTime) {
        this.airbus = airbus;
        this.route = route;
        this.departTime = departTime;
        this.travelTime = travelTime;
    }
    public Long getFlightId() {
        return flightId;
    }
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getAirbus() {
        return airbus;
    }
    public void setAirbus(String airbus) {
        this.airbus = airbus;
    }

    public Long getRoute() {
        return route.getRouteId();
    }
    public void setRoute(Route route) {
        this.route = route;
    }

    public Date getDepartTime() {
        return departTime;
    }
    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getTravelTime() {
        return travelTime;
    }
    public void setTravelTime(Date travelTime) {
        this.travelTime = travelTime;
    }


    public String getDepartPoint(){
        return route.getDepartPoint();
    }

    public String getArrivalPoint(){
        return route.getArrivalPoint();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return flight.departTime.compareTo(departTime) == 0 && flight.travelTime.compareTo(travelTime) == 0 && Objects.equals(airbus, flight.airbus) && Objects.equals(route, flight.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airbus, route, departTime, travelTime, route);
    }
}
