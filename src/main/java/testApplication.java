import controller.*;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import model.Person;
import model.Reservation;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import service.PersonService;
import service.ReservationService;

import java.util.Map;

public class testApplication extends Application<testConfiguration> {

    public static void main(final String[] args) throws Exception {
        new testApplication().run(args);
    }


    private final HibernateBundle<testConfiguration> hibernateBundle =
            new HibernateBundle<testConfiguration>(Person.class,Reservation.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(testConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

/*    private final HibernateBundle<testConfiguration> hibernateBundle1 =
            new HibernateBundle<testConfiguration>(Reservation.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(testConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };*/


    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<testConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new MigrationsBundle<testConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(testConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle<testConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(testConfiguration configuration) {
                return configuration.getViewRendererConfiguration();
            }
        });


    }

    @Override
    public void run(testConfiguration configuration, Environment environment) {
        final PersonService personService = new PersonService(hibernateBundle.getSessionFactory());
        final ReservationService reservationService = new ReservationService(hibernateBundle.getSessionFactory());

        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new PeopleController(personService));
        environment.jersey().register(new PersonController(personService));
        environment.jersey().register(new ReservationController(reservationService));
        environment.jersey().register(new ReservationsController(reservationService));
        environment.jersey().register(new HelloController(configuration.getMessage()));
    }

}
