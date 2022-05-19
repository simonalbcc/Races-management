package Test;

import DataAccess.SingletonConnexion;
import org.junit.jupiter.api.Test;

import Exception.DataException;

import static org.junit.jupiter.api.Assertions.*;

class SingletonConnexionTest {
    @Test
    void getInstance(){
        assertThrows(DataException.class , () -> SingletonConnexion.getInstance());
    }
}