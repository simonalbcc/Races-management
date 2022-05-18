package Test;

import DataAccess.SingletonConnexion;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SingletonConnexionTest {
    @Test
    void getInstance(){
        assertThrows(SQLException.class, () -> SingletonConnexion.getInstance());
    }
}