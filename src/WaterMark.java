import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPCellEvent;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEvent;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class WaterMark extends PdfPageEventHelper implements PdfPCellEvent {
    
    public Image headerImage;
    public PdfPTable table;
    public PdfGState gstate;
    public PdfTemplate tpl;
    public static BaseFont helv; 
    static PdfPCell cell  = null;
    static String str = "";
    public static void main(String args[]) {
        try {
            Document doc = new Document(PageSize.A4, 0, 0, 0, 0);
            helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("watermarks1.pdf"));
            writer.setSpaceCharRatio(0.02f);
          
            com.lowagie.text.Font f;
    		f = FontFactory.getFont("Courier", 8F);
           // PdfPageEvent pageEvent = new WaterMark();
            //writer.setPageEvent(pageEvent);
            doc.open();
            BufferedReader br=new BufferedReader(new FileReader("rmtapplication_cold_RBC_060210025613_371.txt"));
			 String line=new String();

			 while((line=br.readLine()) !=null)
				  {
					 
						if(line.length()==1 && ((int)(line.charAt(0))==12)) 
						{
							doc.newPage();
							continue;
						}
						if(line.length()==0) line = " ";
						/*
						if(line.length()>0)
						{
							Paragraph p = new Paragraph(line,f);
							p.setLeading(4.8f);
							doc.add(p);						
						}
						*/
						if(line.length()>0)
						{
							str = str + line +"\n";					
						}
						
				  } // End of while loop
			 
		 PdfPTable pTable = new PdfPTable(1);
		 Image headerImage = Image.getInstance("C:\\OSB.TIF");
		 cell = new PdfPCell();
		 
		 WaterMark mark = new WaterMark();
		  
		cell.setCellEvent( mark);
		
		 
		// cell.setImage(Image.getInstance("inv.jpg"));
		 //cell.addElement(Image.getInstance("inv.jpg"));
		 Paragraph p = null;
		
		 p = new Paragraph(str,f);
		 p.setLeading(7f);
		 cell.addElement(p);
		 //cell.setBorder(0);
		 //cell.setPadding(0.5f);
		 //cell.setLeading(.5f,.5f);
		
		 pTable.addCell(cell);
		
		 doc.add(pTable);
		 
		// PdfContentByte cb = writer.getDirectContentUnder();
         //cb.saveState();
         //cb.setColorFill(Color.black);
         //Image headerImage = Image.getInstance("inv.jpg");
        //cb.addImage(headerImage,cell.width(), 0, 0, cell.height(), cell.left(),cell.bottom());
        // headerImage.setAbsolutePosition(0,360);
         //headerImage.scaleAbsolute(600,490);
         //cb.addImage(headerImage);
         //System.out.println(cell.width()+" "+cell.height() +" "+cell.left()+" "+cell.bottom());
         //cb.restoreState();
		 doc.close();
            System.out.println("Finished.");
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    
    public void cellLayout(PdfPCell cell, Rectangle position,
			PdfContentByte[] canvases) {
		PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
		
		try {
			headerImage = Image.getInstance("C:\\OSB.TIF");   
			 cb.saveState();
	         cb.setColorFill(Color.black);
	         System.out.println(cell.width()+" "+cell.height()+" "+ cell.left()+" "+cell.bottom());
	    cb.addImage(headerImage,cell.width(), 0, 0, 470,60,375);
	         // cb.addImage(headerImage);
	         cb.restoreState();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public void onOpenDocument(PdfWriter writer, Document document) {
       
    	try {
        	
            headerImage = Image.getInstance("C:\\OSB.TIF");
            
            table = new PdfPTable(2);
            Phrase p = new Phrase();
            Chunk ck = new Chunk("My Company\n", new Font(Font.TIMES_ROMAN, 16, Font.BOLDITALIC, Color.blue));
            p.add(ck);
            ck = new Chunk("My Address\nMy Address", new Font(Font.HELVETICA, 12, Font.NORMAL, Color.darkGray));
            p.add(ck);
            /*
            table.getDefaultCell().setBackgroundColor(Color.yellow);
            table.getDefaultCell().setBorderWidth(0);
            table.addCell(p);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(new Phrase(new Chunk(headerImage, 0, 0)));
            */
            gstate = new PdfGState();
            gstate.setFillOpacity(0.3f);
            gstate.setStrokeOpacity(0.3f);
            tpl = writer.getDirectContent().createTemplate(100, 100);
            tpl.setBoundingBox(new Rectangle(-20, -20, 100, 100));
            helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
        }
        catch(Exception e) {
            throw new ExceptionConverter(e);
        }
        
    }    
    
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        
       
        cb.saveState();
        
        table.setTotalWidth(document.right() - document.left());
        table.writeSelectedRows(0, -1, document.left(), document.getPageSize().height() - 50, cb);
        String text = "Page " + writer.getPageNumber() + " of ";
        float textSize = helv.getWidthPoint(text, 12);
        float textBase = document.bottom() - 20;
        cb.beginText();
        cb.setFontAndSize(helv, 6);
        if ((writer.getPageNumber() & 1) == 1) {
            cb.setTextMatrix(document.left(), textBase);
            cb.showText(text);
            cb.endText();
            cb.addTemplate(tpl, document.left() + textSize, textBase);
        }
        else {
            float adjust = helv.getWidthPoint("0", 6);
            cb.setTextMatrix(document.right() - textSize - adjust, textBase);
            cb.showText(text);
            cb.endText();
            cb.addTemplate(tpl, document.right() - adjust, textBase);
        }
        cb.saveState();
        /*
        cb.setColorStroke(Color.orange);
        cb.setLineWidth(2);
        cb.rectangle(20, 20, document.getPageSize().width() - 40, document.getPageSize().height() - 40);
        cb.stroke();
        */
        cb.restoreState();
        try {
                //cb.addImage(headerImage, headerImage.width(), 0, 0, headerImage.height(), 600, 825);
        	System.out.println("cell ----> "+cell.width());
        	//cb.addImage(headerImage,cell.width(), 0, 0, cell.height(), cell.left(),cell.bottom());
            }
        catch(Exception e) {
                throw new ExceptionConverter(e);
         }
          cb.restoreState();
    }
    
    public void onStartPage(PdfWriter writer, Document document) {
    	/*
    	try
    	{
    		System.out.println("onStartPage "+writer.getPageNumber());
        if (writer.getPageNumber() < 3) {
        	System.out.println("onStartPage "+writer.getPageNumber());
            PdfContentByte cb = writer.getDirectContentUnder();
            cb.saveState();
            cb.setColorFill(Color.black);
            System.out.println(document.top()+"   "+document.left());
           // headerImage.setAbsolutePosition(0,360);
            //headerImage.scaleAbsolute(600,490);
           // cb.addImage(headerImage,cell.width(), 0, 0, cell.height(), cell.left(),cell.bottom());
           // cb.addImage(headerImage);
            cb.restoreState();
            System.out.println("onStartPage--> After adding image ");
            
        }
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	*/
    }
    
    public void onCloseDocument(PdfWriter writer, Document document) {
       tpl.beginText();
       tpl.setFontAndSize(helv, 6);
       tpl.setTextMatrix(0, 0);
       tpl.showText("" + (writer.getPageNumber() - 1));
       tpl.endText();
    }
}