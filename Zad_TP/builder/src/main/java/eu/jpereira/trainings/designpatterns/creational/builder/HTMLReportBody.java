package eu.jpereira.trainings.designpatterns.creational.builder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

import java.util.Iterator;

public class HTMLReportBody implements ReportBody {

	private StringBuffer delegate = new StringBuffer();

	@Override
	public Object getAsString() {
		return this.delegate .toString();
	}

	@Override
	public void addInfo(SaleEntry saleEntry)
	{
		putContent("<span class=\"customerName\">");
		putContent(saleEntry.getCustomer().getName());
		putContent("</span><span class=\"customerPhone\">");
		putContent(saleEntry.getCustomer().getPhone());
		putContent("</span>");
		putContent("<items>");

		Iterator<SoldItem> it = saleEntry.getSoldItems().iterator();
		while ( it.hasNext() ) {
			SoldItem soldEntry= it.next();
			putContent("<item><name>");
			putContent(soldEntry.getName());
			putContent("</name><quantity>");
			putContent(soldEntry.getQuantity());
			putContent("</quantity><price>");
			putContent(soldEntry.getUnitPrice());
			putContent("</price></item>");
		}
		putContent("</items>");

	}


	public void putContent(Object content) {
		this.delegate.append(content);
		
	}

}
