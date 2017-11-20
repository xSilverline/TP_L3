package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class XMLReportGenerator extends ReportGenerator {
    @Override
    protected Report initiateReport()
    {
        return new XMLReport();
    }
}
