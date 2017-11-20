package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;

public class WordCensor extends SocialChannelDecorator {

    String censor;
    public WordCensor(String censor)
    {
        this.censor = censor;
    }


    public WordCensor(String i, SocialChannel decoratedChannel)
    {
        this.censor= i;
        this.delegate = decoratedChannel;
    }

    @Override
    public void deliverMessage(String message)
    {
        if (message.contains(censor)) {
            StringBuilder builder = new StringBuilder();
            builder.append(message.replace(censor,"###"));
            message = builder.toString();

        }
        delegate.deliverMessage(message);

    }
}
