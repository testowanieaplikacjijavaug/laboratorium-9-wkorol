import org.easymock.EasyMock;
import org.easymock.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.easymock.EasyMock.*;
import static org.mockito.Mockito.when;

public class MockDifferences {
    List easymock = EasyMock.mock(List.class);
    List mockito = Mockito.mock(List.class);

    //Brak record/replay w Mockito
    //Różnica w składni, co pokazują poniższe przykłady
    //W EasyMock używamy expect oraz andReturn do mockowania, w Mockito kolejno when i thenReturn
    //W mockito możemy użyć "spy" do częściowego mockowania, w EasyMock nie ma takiego rozwiązania.




    @Test
    public void testEasyMock() {
        expect(easymock.get(0)).andStubReturn("one");
        expect(easymock.get(1)).andStubReturn("two");
        easymock.clear();

        replay(easymock);


        verify(easymock);
    }

    @Test
    public void testMockito() {
        when(mockito.get(0)).thenReturn("one");
        when(mockito.get(1)).thenReturn("two");
        verify(mockito);
    }


}
