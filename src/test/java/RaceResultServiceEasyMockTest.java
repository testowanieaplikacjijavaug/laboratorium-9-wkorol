import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.*;


public class RaceResultServiceEasyMockTest {

    private RaceResultService raceResultService;

    private Client client;

    private Message message;

    private Collection<Client> clients;

    @BeforeEach
    public void setUp() {
        client = mock(Client.class);
        message = mock(Message.class);
        clients = mock(Collection.class);

        raceResultService = new RaceResultService();
        raceResultService.setClients(clients);
    }

    @Test
    public void testAddSubscriber() {
        replay(client);
        raceResultService.addSubscriber(client);
        EasyMock.verify(client);
    }

    @Test
    public void testSend() {

        raceResultService.setClients(clients);
        replay(client);
        raceResultService.send(message);
        EasyMock.verify(client);
    }


    @Test
    public void testRemoveSubscriber() {
        replay(client);
        raceResultService.removeSubscriber(client);
        EasyMock.verify(client);
    }

}
