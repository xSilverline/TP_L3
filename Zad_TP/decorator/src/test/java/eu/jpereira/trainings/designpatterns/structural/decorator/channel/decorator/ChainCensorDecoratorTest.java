package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelBuilder;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelProperties;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelPropertyKey;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.spy.TestSpySocialChannel;

public class ChainCensorDecoratorTest extends AbstractSocialChanneldDecoratorTest
{
    @Test
    public void ChainWithOtherTwoDecorators()
    {
        SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

        SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);

        SocialChannel channel = builder.
                with(new WordCensor("this")).
                with(new MessageTruncator(10)).
                with(new URLAppender("http://jpereira.eu")).
                getDecoratedChannel(props);
        channel.deliverMessage("this is a message");

        TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
        assertEquals("### is ... http://jpereira.eu", spyChannel.lastMessagePublished());
    }

    @Test
    public void testChainTwoDecoratorsWithoutBuilder() {

        SocialChannel channel = new TestSpySocialChannel();

        SocialChannel urlAppenderChannel = new URLAppender("http://jpereira.eu", channel);

        //Now create a truncator
        SocialChannel messageTruncatorChannel = new MessageTruncator(10, urlAppenderChannel);

        SocialChannel messageCensorChannel = new WordCensor("this",messageTruncatorChannel);

        messageCensorChannel.deliverMessage("this is a message");
        // Spy channel. Should get the one created before
        TestSpySocialChannel spy = (TestSpySocialChannel)channel;
        assertEquals("### is ... http://jpereira.eu", spy.lastMessagePublished());
    }
}
