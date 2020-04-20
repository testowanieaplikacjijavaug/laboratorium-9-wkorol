
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

public class RaceResultServiceMockitoTest {

    private RaceResultService raceResultService;
    private Client client;
    private Message message;
    private Collection<Client> clients;

    @BeforeEach
    public void setUp() {
        client = mock(Client.class);
        message = mock(Message.class);
        clients = spy(new HashSet<>());
        raceResultService = new RaceResultService();
        raceResultService.setClients(clients);
    }

    @Test
    public void testAddSubscriber() {
        raceResultService.addSubscriber(client);
        assertThat(clients).hasSize(1).contains(client);
        verify(clients).add(client);
    }


    @Test
    public void testSend() {
        clients.add(client);
        raceResultService.setClients(clients);

        raceResultService.send(message);

        verify(client).receive(message);
    }

    @Test
    public void testRemoveSubscriber() {
        raceResultService.addSubscriber(client);
        raceResultService.removeSubscriber(client);

        assertThat(clients).isEmpty();
        verify(clients).remove(client);

    }


}