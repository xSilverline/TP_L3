package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

public interface AbstractRaportElementsFactory {
    public ReportBody createReportBody();

    public ReportFooter createReportFooter();

    public ReportHeader createReportHeader();
}
