import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//import static com.sun.jndi.ldap.LdapDnsProviderService.service;
import static org.junit.jupiter.api.Assertions.*;

public class JUnitTest {
    private cAutomatZNapojami service;

    @Test
    public void sprawdzMiejsceNULL_mscWiekszyZero_throwsANullPointerException(){
    //given
        cAutomatZNapojami a = new cAutomatZNapojami();
        int msc = a.getLarge()+1;
    //when
  //      boolean result = service.sprawdzMiejsceNULL(5);
    //then
       Assertions.assertThrows(NullPointerException.class, () -> service.sprawdzMiejsceNULL(msc));
    }
    @Test
    public void sprawdzMiejsceNULL_mscZTowarem_true(){
    //given
        cAutomatZNapojami a = new cAutomatZNapojami();
        int msc = 1;
    //when
        boolean result = a.sprawdzMiejsceNULL(msc);
    //then
        Assertions.assertEquals(true, result);
    }
    @Test
    public void sprawdzMiejsceNULL_mscZTowarem_false(){
    //given
        cAutomatZNapojami a = new cAutomatZNapojami();
        int msc = 7;
    //when
        boolean result = a.sprawdzMiejsceNULL(msc);
    //then
        Assertions.assertEquals(false, result);

    }


    @Test
    public void sprawdzMiejsceZero_mscWiekszyZero_throwsNullPointerException(){
        //given
        cAutomatZNapojami a = new cAutomatZNapojami();
        int msc = a.getLarge()+1;
        //when

        //then
        Assertions.assertThrows(NullPointerException.class, () -> service.sprawdzMiejsceNULL(msc));
    }
    @Test
    public void sprawdzMiejsceZero_mscZTowaremIIloscia_true(){
        //given
        cAutomatZNapojami a = new cAutomatZNapojami();
        int msc = 1;
        //when
        boolean result = a.sprawdzMiejsceZero(msc);
        //then
        Assertions.assertEquals(true, result);
    }
    @Test
    public void sprawdzMiejsceZero_mscZTowaremIZero_false(){
        //given
        cAutomatZNapojami a = new cAutomatZNapojami();
        int msc = 2;
        //when
        boolean result = a.sprawdzMiejsceZero(msc);
        //then
        Assertions.assertEquals(false, result);
    }
    @Test
    public void sprawdzMiejsceZero_mscBezTowaru_throwsNullPointerException(){
        //given
        cAutomatZNapojami a = new cAutomatZNapojami();
        int msc = 7;
        //when

        //then
        Assertions.assertThrows(NullPointerException.class, () -> service.sprawdzMiejsceZero(msc));
    }


    @Test
    public void czyWIndexieTablicy_wieksze_false(){
        //given
        cAutomatZNapojami a = new cAutomatZNapojami();
        int msc = a.getLarge()+1;
        //when
        boolean result = a.czyWIndexieTablicy(msc);
        //then
        Assertions.assertEquals(false, result);
    }
    @Test
    public void czyWIndexieTablicy_mniejsze_false(){
        //given
        cAutomatZNapojami a = new cAutomatZNapojami();
        int msc = -1;
        //when
        boolean result = a.czyWIndexieTablicy(msc);
        //then
        Assertions.assertEquals(false, result);
    }




}
