package controller;


import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import model.Reservation;
import service.ReservationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/reservation/{reservationId}")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GET
    @UnitOfWork
    public Reservation getReservation(@PathParam("reservationId") LongParam reservationId) {
        return findReservationSafely(reservationId.get());
    }

    private Reservation findReservationSafely(long reservationId) {
        return reservationService.findReservationById(reservationId).orElseThrow(() -> new NotFoundException("No such Reservation."));
    }
}
