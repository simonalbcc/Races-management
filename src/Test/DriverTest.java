package Test;

import Model.Driver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {
    private Driver firstDriver;
    private Driver secondDriver;

    @BeforeEach
    void setUp(){
        firstDriver = new Driver(15,null,null,null,null,null,true,null,null);
        secondDriver = new Driver(15,null,null,null,null,null,false,null,null);
    }

    @Test
    void getNumber() {
        assertEquals(15,firstDriver.getNumber());
        assertNotEquals(150,firstDriver.getNumber());
    }

    @Test
    void setNumber() {
        secondDriver.setNumber(19);
        assertEquals(19,secondDriver.getNumber());
        assertNotEquals(250,secondDriver.getNumber());
    }

    @Test
    void isHasRenewedCommitmentContract() {
        assertTrue(firstDriver.getHasRenewedCommitmentContract());
        assertFalse(secondDriver.getHasRenewedCommitmentContract());
    }
}