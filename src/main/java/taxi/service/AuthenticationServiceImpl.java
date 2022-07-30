package taxi.service;

import taxi.exception.AuthenticationException;
import taxi.lib.Injector;
import taxi.lib.Service;
import taxi.model.Driver;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Injector injector = Injector.getInstance("taxi");
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        System.out.println("AuthenticationServiceImpl.login() was called with params: "
                + "login= " + login + ", password= " +password);
        Optional<Driver> driver = driverService.findByLogin(login);
        if (driver.isPresent()
                && driver.get().getPassword().equals(password)) {
            return driver.get();
        }
        throw new AuthenticationException("Wrong login or password");
    }
}
