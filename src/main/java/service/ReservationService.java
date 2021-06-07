package service;

import io.dropwizard.hibernate.AbstractDAO;
import model.Reservation;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class ReservationService extends AbstractDAO<Reservation> {

    public ReservationService(SessionFactory factory) {
        super(factory);
    }

    public Optional<Reservation> findReservationById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Reservation createReservation(Reservation reservation) {
        return persist(reservation);
    }

    @SuppressWarnings("unchecked")
    public List<Reservation> findAllReservations() {
        return list((Query<Reservation>) namedQuery("findAllReservations"));
    }
}
