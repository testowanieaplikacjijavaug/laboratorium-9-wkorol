import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FriendshipsMongoTest {

    @Mock
    FriendsCollection friendsCollection;

    @InjectMocks
    FriendshipsMongo friendshipsMongo;

    @Test
    public void mockingWorksAsExpected() {
        Person joe = mock(Person.class);
        when(friendsCollection.findByName("Joe")).thenReturn(joe);
        assertEquals(joe.getName(), friendsCollection.findByName("Joe").getName());
    }

    @Test
    public void alexDoesNotHaveFriends() {
        assertThat(friendshipsMongo.getFriendsList("Alex")).isEmpty();


    }

    @Test
    public void joeHas5Friends() {
        List<String> expected = Arrays.asList("Karol","Dawid","Maciej","Tomek","Adam");
        Person joe = mock(Person.class);
        given(friendsCollection.findByName("Joe")).willReturn(joe);
        given(joe.getFriends()).willReturn(expected);
        assertThat(friendshipsMongo.getFriendsList("Joe")).hasSize(5).containsOnly("Karol", "Dawid", "Maciej", "Tomek", "Adam");
        verify(friendsCollection).findByName("Joe");
        verify(joe).getFriends();

    }
}
