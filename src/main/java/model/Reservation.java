package model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "reservation")
@NamedQueries(
        {
                @NamedQuery(
                        name = "findAllReservations",
                        query = "SELECT r FROM Reservation r"
                )
        })
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationID;

    @Column(name = "reservationName", nullable = false)
    private String reservationName;

    @Column(name = "date_created", nullable = false)
    private Date date_created;


    public Reservation() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Reservation)) {
            return false;
        }

        Reservation person = (Reservation) o;

        return reservationID == person.reservationID &&
                Objects.equals(reservationName, person.reservationName) &&
                Objects.equals(date_created, person.date_created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationID, reservationName, date_created);
    }
}
