package controller;


import io.dropwizard.hibernate.UnitOfWork;
import model.Reservation;
import service.ReservationService;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/reservations")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationsController {

    private final ReservationService reservationService;

    public ReservationsController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @POST
    @UnitOfWork
    public Reservation createReservation(@Valid Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @GET
    @UnitOfWork
    public List<Reservation> listReservations() {
        return reservationService.findAllReservations();
    }

}
