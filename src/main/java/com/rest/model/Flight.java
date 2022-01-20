package com.rest.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long flightId;
    private String airbus;
    private Long route;
    private Date departTime;
    private Date travelTime;

    @ManyToOne()
    @JoinColumn(name="id_route")
    private Route routes;


    public Flight(Long flightId, String airbus, Long route, Date departTime, Date travelTime) {
        this.flightId = flightId;
        this.airbus = airbus;
        this.route = route;
        this.departTime = departTime;
        this.travelTime = travelTime;
    }

    public Flight() {
    }

    public Flight(String airbus, Long route, Date departTime, Date travelTime) {
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
        return route;
    }
    public void setRoute(Long route) {
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
        return routes.getDepartPoint();
    }

    public String getArrivalPoint(){
        return routes.getArrivalPoint();
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
