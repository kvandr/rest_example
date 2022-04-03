package com.front.model;


import lombok.Data;

import java.util.Objects;


@Data
public class Route {


    private Long routeId;

    private String departPoint;

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