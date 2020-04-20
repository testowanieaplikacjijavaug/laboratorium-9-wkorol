import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessengerTest {

    @Mock
    Messenger messenger;

    @Mock
    MailServer mailServer;

    @Mock
    Template template;

    @Mock
    TemplateEngine templateEngine;

    @Mock
    Client client;



    @Test
    public void testMocks() {
        messenger = new Messenger(mailServer, templateEngine);

        String message = "asdwef";
        String email = "wiktor@gmail.com";

        when(templateEngine.prepareMessage(template, client)).thenReturn(message);
        when(client.getEmail()).thenReturn(email);

        messenger.sendMessage(client, template);

        verify(templateEngine).prepareMessage(template, client);
        verify(mailServer).send(email, message);

    }


}
