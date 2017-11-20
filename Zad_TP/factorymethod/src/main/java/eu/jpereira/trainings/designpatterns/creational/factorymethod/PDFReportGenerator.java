package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class PDFReportGenerator extends ReportGenerator {
    @Override
    protected Report initiateReport() {
        return new PDFReport();
    }
}
