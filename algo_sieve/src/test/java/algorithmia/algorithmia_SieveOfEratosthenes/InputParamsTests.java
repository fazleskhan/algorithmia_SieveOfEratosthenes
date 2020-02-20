package algorithmia.algorithmia_SieveOfEratosthenes;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class InputParamsTests {

    @Test
    public void zeroLastNumberTest(){
        InputParams target = new InputParams(0);
        assertThat(target.toString(),equalTo("InputParams{lastNumber=0}"));
    }

    @Test
    public void nullLastNumberTest(){
        InputParams target = new InputParams(null);
        assertThat(target.toString(),equalTo("InputParams{lastNumber=null}"));
    }

}
