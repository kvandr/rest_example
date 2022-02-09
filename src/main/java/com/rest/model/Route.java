package com.rest.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long routeId;
    @Column
    private String departPoint;
    @Column
    private String arrivalPoint;

    public Route() {
    }

    public Route(String departPoint, String arrivalPoint) {
        this.departPoint = departPoint;
        this.arrivalPoint = arrivalPoint;
    }

    public Route(Long routeId,String departPoint, String arrivalPoint) {
        this.routeId = routeId;
        this.departPoint = departPoint;
        this.arrivalPoint = arrivalPoint;
    }
    public Long getRouteId(){
        return this.routeId;
    }
    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getDepartPoint(){
        return this.departPoint;
    }
    public void setDepartPoint(String departPoint) {
        this.departPoint = departPoint;
    }

    public String getArrivalPoint(){
        return this.arrivalPoint;
    }
    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(departPoint, route.departPoint) && Objects.equals(arrivalPoint, route.arrivalPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, departPoint, arrivalPoint);
    }

 }