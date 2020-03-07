import org.junit.jupiter.api.BeforeAll;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class GiveMeMoneyTest {
    static GiveMeMoney skarbona;

    @BeforeAll
    static void init(){
        skarbona = new GiveMeMoney();
    }
    @org.junit.jupiter.api.Test
    void addAmount() {
        //GIVEN - mamy już obiekt testowany - skarbona
        //WHEN
        skarbona.addAmount(new BigDecimal("1.00"));
        //THAT
        assertEquals(new BigDecimal("1.00"), skarbona.getAmount());

        //WHEN
        skarbona.addAmount(new BigDecimal("10"));
        //THAT
        assertEquals(new BigDecimal("11.00"), skarbona.getAmount());

        //WHEN
        skarbona.addAmount(new BigDecimal("-1"));
        //THAT
        assertEquals(new BigDecimal("11.00"), skarbona.getAmount());
    }

    @org.junit.jupiter.api.Test
    void getAmountAsString() {

    }
}