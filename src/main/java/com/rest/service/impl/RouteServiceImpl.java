package com.rest.service.impl;

import com.rest.Exception.NotFoundException;
import com.rest.model.Flight;
import com.rest.model.Route;
import com.rest.repos.FlightRepo;
import com.rest.repos.RouteRepo;
import com.rest.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    FlightRepo flight;
    @Autowired
    RouteRepo routes;

    @Override
    public String addRoute(String DepartPoint, String ArrivalPoint){
        long routeId = 1L;
        List<Route> list = routes.fileLoad();
        Route routeEnd;
        if (!list.isEmpty()) {
            routeEnd = list.get(list.size()-1);
            if (routeEnd != null)
                routeId = routeEnd.getRouteId() + 1;
        }
        try {
            Route route = new Route(DepartPoint, ArrivalPoint);
            route.setRouteId(routeId);
            if (routes.save(route)) return "Successfully added!";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Not added";
    }

    @Override
    public Boolean deleteRoute(Long idRoute){
        try {
            if (routes.getRouteDepart(idRoute) != null) {
                Long routeId = routes.getRouteDepart(idRoute).getRouteId();
                List<Flight> flighted = flight.fileLoad();
                if (routes.delete(routes.getRouteDepart(idRoute))) {

                    for(Flight flight: flighted){
                        if(flight.getRoute().equals(routeId)) {
                            flight.setRoute(0L);
                            flight.setDepartTime(null);
                            flight.setTravelTime(null);
                        }
                    }
                    flight.fileUnload(flighted);
                    return Boolean.TRUE;
                }
                else return Boolean.FALSE;
            }
        }catch (NotFoundException e){
            e.printStackTrace();
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean saveAll(Route route) {
        return routes.save(route);
    }

    @Override
    public Boolean updateRoute(Long id, String departPoint, String arrivalPoint) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {

            Route rUpdate = new Route(routes.getRouteDepart(id).getRouteId(),
                    reader.readLine(),
                    reader.readLine()
            );

            List <Route> list = routes.fileLoad();
            for(Route routeTmp:list){
                if(id.equals(routeTmp.getRouteId())) {
                    list.set(list.indexOf(routeTmp), rUpdate);
                    break;
                }
            }
            routes.fileUnload(list);
            return Boolean.TRUE;
        } catch (NullPointerException | NotFoundException | IOException e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    @Override
    public String viewAll(){
        List<Route> airFl = routes.fileLoad();
        StringBuffer data = new StringBuffer();
        for(Route route:airFl){
            viewContent(route, data);
            data.append("-----------------------------------------\n");
        }

        return data.toString();
    }

    @Override
    public String searchRoute(Long idRoute){
        try {
            Route route = routes.getRouteDepart(idRoute);

            StringBuffer data = new StringBuffer();
            viewContent(route, data);
            data.append("-------------------end-------------------\n");
            return data.toString();
        } catch (NotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String searchRoute(String departPoint, String arrivalPoint){
        List<Route> routeList = routes.fileLoad();
        Pattern p = Pattern.compile(departPoint, Pattern.CASE_INSENSITIVE);
        Matcher m1, m2;

        StringBuffer data = new StringBuffer();
        String departPoints, arrivalPoints;
        for(Route airRoute:routeList){
            departPoints = airRoute.getDepartPoint();
            arrivalPoints = airRoute.getArrivalPoint();
            m1 = p.matcher(departPoints);
            m2 = p.matcher(arrivalPoints);
            if (m1.find() || m2.find()) {
                viewContent(airRoute, data);
                data.append("-----------------------------------------\n");
            }else continue;
        }

        return data.toString();
    }

    private void viewContent(Route route, StringBuffer data) {
        try{
            Long routeId = route.getRouteId();
            String departPoint = route.getDepartPoint();
            String arrivalPoint = route.getArrivalPoint();
            data.append("routeId: ").append(routeId).append("\n");
            data.append("departPoint: ").append(departPoint).append("\n");
            data.append("arrivalPoint: ").append(arrivalPoint).append("\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
