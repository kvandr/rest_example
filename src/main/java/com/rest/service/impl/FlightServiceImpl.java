package com.rest.service.impl;

import com.rest.Exception.NotFoundException;
import com.rest.model.Flight;
import com.rest.repos.FlightRepo;
import com.rest.repos.RouteRepo;
import com.rest.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FlightServiceImpl implements FlightService{
    @Autowired
    FlightRepo flight;

    @Autowired
    RouteRepo route;

    @Override
    public String addFlight(String airbus, Long route, String departTime, String travelTime) {
        long id = 1L;
        List<Flight> list = flight.fileLoad();
        Flight flightEnd;
        if (!list.isEmpty()) {
            flightEnd = list.get(list.size()-1);
            if (flightEnd != null)
                id = flightEnd.getFlightId() + 1;
        }
        try{
            Date departTimes = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz").parse(departTime);
            Date travelTimes = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz").parse(travelTime);
            Flight flightier = new Flight(id, airbus, route, departTimes, travelTimes);
            if (flight.save(flightier)) return "Flight added.";
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        return "Not added!";
    }

    @Override
    public Boolean saveAll(Flight flights) {
        return flight.save(flights);
    }

    @Override
    public Boolean updateFlight(Long idFlight, String airbus, Long route, String departTime,String travelTime) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
        try {

            Flight fUpdate = new Flight(idFlight, airbus, route,
                    formatter.parse(departTime),
                    formatter.parse(travelTime)
            );

            List <Flight> list = flight.fileLoad();
            for(Flight flightTmp:list){
                if(Objects.equals(flightTmp.getFlightId(), flight.findFlight(idFlight, route).getFlightId())) {
                    list.set(list.indexOf(flightTmp), fUpdate);
                    break;
                }
            }
            flight.fileUnload(list);
            return Boolean.TRUE;
        } catch (NullPointerException | NotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean deleteFlight(Long idFlight){
        try {
            flight.delete(flight.findFlight(idFlight));
            return Boolean.TRUE;
        } catch (NotFoundException e){
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean deleteFlight(List<Flight> flightList){
        try {
            for(Flight flights:flightList)
                flight.delete(flight.findFlight(flights.getFlightId()));
            return Boolean.TRUE;
        } catch (NotFoundException e){
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    @Override
    public String viewAll(){
        List<Flight> airFl = flight.fileLoad();
        StringBuffer data = new StringBuffer();
        for(Flight flights:airFl){
            viewContent(flights, data);
            data.append("-----------------------------------------\n");
        }

        return data.toString();
    }

    @Override
    public String searchFlight(Long idFlight){
        try {
            Flight flighty = flight.findFlight(idFlight);

            StringBuffer data = new StringBuffer();
            viewContent(flighty, data);
            data.append("-------------------end-------------------\n");
            return data.toString();
        } catch (NotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String searchFlight(String airbuses){
        List<Flight> flightList = flight.fileLoad();
        Pattern p = Pattern.compile(airbuses, Pattern.CASE_INSENSITIVE);
        Matcher m1, m2, m3, m4;

        StringBuffer data = new StringBuffer();
        String airbus, routes;
        String departTime, travelTime;
        for(Flight airFl:flightList){
            try {
                airbus = airFl.getAirbus();
                departTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz").format(airFl.getDepartTime());
                travelTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz").format(airFl.getTravelTime());
                routes = route.getRouteDepart(airFl.getRoute()).getDepartPoint();
                m1 = p.matcher(airbus);
                m2 = p.matcher(departTime);
                m3 = p.matcher((CharSequence) travelTime);
                m4 = p.matcher(routes);
                if (m1.find() || m2.find() || m3.find() || m4.find()) {
                    viewContent(airFl, data);
                    data.append("-----------------------------------------\n");
                }else continue;
            } catch (NotFoundException e) {
                e.printStackTrace();
                continue;
            }
        }

        return data.toString();
    }

    private void viewContent(Flight flight, StringBuffer data) {
        try{
            Long flightId = flight.getFlightId();
            String airbus = flight.getAirbus();
            Date departTime = flight.getDepartTime();
            Date travelTime = flight.getTravelTime();
            Long route = flight.getRoute();
            data.append("flightId: ").append(flightId).append("\n");
            data.append("airbus: ").append(airbus).append("\n");
            data.append("route: ").append(route).append("\n");
            data.append("departTime: ").append(departTime).append("\n");
            data.append("travelTime: ").append(travelTime).append("\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
