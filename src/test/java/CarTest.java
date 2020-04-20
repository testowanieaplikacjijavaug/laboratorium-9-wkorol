
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CarTest {

    private Car myFerrari;

    @BeforeEach
    public void setUp(){
        myFerrari = mock(Car.class);
    }

    @Test
    public void test_instance_car(){
        assertTrue(myFerrari instanceof Car);
    }


    //Sprawdza czy potrzebuje paliwa
    @Test
    public void test_default_behavior_needsFuel(){
        assertFalse(myFerrari.needsFuel(), "New test double should return False as boolean");
    }

    //Sprawdza czy 0.0 temperatura silnika
    @Test
    public void test_default_behavior_temperature(){
        assertEquals(0.0, myFerrari.getEngineTemperature(), "New test double should return 0.0");
    }

    //When
    @Test
    public void test_stubbing_mock(){
        when(myFerrari.needsFuel()).thenReturn(true);
        assertTrue(myFerrari.needsFuel());
    }

    //Wyjątek
    @Test
    public void test_exception(){
        when(myFerrari.needsFuel()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> {
            myFerrari.needsFuel();
        });
    }

    @Test
    public void testVerification(){
        myFerrari.driveTo("Kartuzy");
        myFerrari.needsFuel();

        verify(myFerrari).driveTo("Kartuzy");
        verify(myFerrari).needsFuel();
        assertFalse(myFerrari.needsFuel());
    }

    @Test
    public void testEngineTemperature() {
        when(myFerrari.getEngineTemperature()).thenReturn(15.2);
        double temperature = myFerrari.getEngineTemperature();
        verify(myFerrari).getEngineTemperature();
        assertThat(myFerrari.getEngineTemperature(), is(15.2));
    }

    @Test
    public void testDriveTo_IllegalArgumentException() {

        doThrow(IllegalArgumentException.class).when(myFerrari).driveTo(null);

    }

    @Test
    public void testDriveTo_DoAnswer() {
        doAnswer((i)  -> {
            assertEquals(1, i.getArguments().length);
            return null;
        }).when(myFerrari).driveTo(anyString());
        myFerrari.driveTo("Kartuzy");

    }



    @AfterEach
    public void tearDown(){
        myFerrari = null;
    }

}
