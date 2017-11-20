package eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.AbstractRaportElementsFactory;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportFooter;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportHeader;

public class XMLReportElementsFactory implements AbstractRaportElementsFactory {
    @Override
    public ReportBody createReportBody() {
        return new XMLReportBody();
    }

    @Override
    public ReportFooter createReportFooter() {
        return new XMLReportFooter();
    }

    @Override
    public ReportHeader createReportHeader() {
        return new XMLReportHeader();
    }
}
