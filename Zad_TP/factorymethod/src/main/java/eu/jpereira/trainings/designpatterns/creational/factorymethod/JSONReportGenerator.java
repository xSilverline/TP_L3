package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class JSONReportGenerator extends ReportGenerator
{
    @Override
    protected Report initiateReport() {
        return new JSONReport();
    }
}
