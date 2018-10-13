package com.boot.contract.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.time.format.DateTimeFormatter;

import com.boot.contract.model.Contract;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;



public class ContractGenerator {

    final static String SAVE_DIRECTORU="c:/_contract/";
    public static void create(Contract contract,String fileName) throws IOException{
        FileOutputStream out = new FileOutputStream(new File(SAVE_DIRECTORU+"/"+fileName+".docx"));
        XWPFDocument document = new XWPFDocument();

        // 계약서 이름
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(20);
        run.setText("알바 계약서\n");
        run.setUnderline(UnderlinePatterns.DASH);
        run.setBold(true);

        // 사용자 (값)
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run = paragraph.createRun();
        run.setFontSize(17);
        run.setItalic(true);
        run.setText("사용자 (갑)");

        // 테이블 셀 크기 조정
        XWPFTable table = document.createTable(1, 4);
        table.setWidth(9000);
        table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(800));
        table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));
        table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1000));
        table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5200));

        // 사용자 (값) 테이블 1번째 라인
        XWPFTableCell cell = table.getRow(0).getCell(0);
        XWPFParagraph para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        run = para.createRun();
        run.setBold(true);
        run.setFontSize(15);
        run.setText("성명");

        cell = table.getRow(0).getCell(1);
        para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        run = para.createRun();
        run.setFontSize(15);
        run.setText(contract.getEmployerName());

        cell = table.getRow(0).getCell(2);
        para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        run = para.createRun();
        run.setBold(true);
        run.setFontSize(15);
        run.setText("사업종류");

        cell = table.getRow(0).getCell(3);
        para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        run = para.createRun();
        run.setFontSize(15);
        run.setText(contract.getBusinessType());

        // 사용자 (값) 테이블 2번째 라인
        XWPFTableRow row2 = table.createRow();
        cell = row2.getCell(0);
        para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        run = para.createRun();
        run.setBold(true);
        run.setFontSize(15);
        run.setText("사업체명");

        cell = row2.getCell(1);
        para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        run = para.createRun();
        run.setFontSize(15);
        run.setText(contract.getCompanyName());

        cell = row2.getCell(2);
        para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        run = para.createRun();
        run.setBold(true);
        run.setFontSize(15);
        run.setText("소재지");

        cell = row2.getCell(3);
        para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        run = para.createRun();
        run.setFontSize(15);
        run.setText(contract.getCompanyAddress());

        /* ===================================================================== */
        /* ===================================================================== */
        /* ===================================================================== */
        /* ===================================================================== */
        /* ===================================================================== */

        // 사용자 (값)
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run = paragraph.createRun();
        run.setFontSize(17);
        run.setItalic(true);
        run.setText("근로자 (을)");

        // 테이블 셀 크기 조정
        table = document.createTable(1, 4);
        table.setWidth(9000);
        table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(500));
        table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(4000));
        table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1500));
        table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3000));

        // 사용자 (값) 테이블 1번째 라인
        cell = table.getRow(0).getCell(0);
        para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        run = para.createRun();
        run.setBold(true);
        run.setFontSize(15);
        run.setText("성 명");

        cell = table.getRow(0).getCell(1);
        para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        run = para.createRun();
        run.setFontSize(15);
        run.setText(contract.getEmployeeName());

        cell = table.getRow(0).getCell(2);
        para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        run = para.createRun();
        run.setBold(true);
        run.setFontSize(15);
        run.setText("생년월일");

        cell = table.getRow(0).getCell(3);
        para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        run = para.createRun();
        run.setFontSize(15);
        String birth = contract.getEmployeeBirth();
        run.setText(birth.substring(0,2)+"년 "+birth.substring(2,4)+"월 "+birth.substring(4,6)+"일");

        // 사용자 (값) 테이블 2번째 라인
        row2 = table.createRow();
        cell = row2.getCell(0);
        para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        run = para.createRun();
        run.setBold(true);
        run.setFontSize(15);
        run.setText("주소");

        cell = row2.getCell(1);
        para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        run = para.createRun();
        run.setFontSize(15);
        run.setText(contract.getEmployeeAddress());

        cell = row2.getCell(2);
        para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        run = para.createRun();
        run.setBold(true);
        run.setFontSize(15);
        run.setText("주민등록번호");

        cell = row2.getCell(3);
        para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        run = para.createRun();
        run.setFontSize(15);
        run.setText(contract.getPersonalNumber());


        /*======================================================*/
        document.createParagraph();


        table = document.createTable(1, 1);
        table.setWidth(9000);
        cell = table.getRow(0).getCell(0);
        run = cell.getParagraphs().get(0).createRun();
        run.setBold(true);
        run.setText("취업(근로)조건");
        run.addBreak();//run.addTab();
        run.addBreak();//run.addTab();
        run.setBold(false);
        run.setText("   (1)임금:"+contract.getPayKind()+",수당:"+contract.getPay());
        run.addBreak();//run.addTab();
        run.addBreak();//run.addTab();
        run.setText("   (2)근로조건:주"+contract.getDay()+"일/근무"+contract.getHour()+"시간씩");
        run.addBreak();//run.addTab();
        run.addBreak();//run.addTab();
        run.setText("   (3)근로장소:"+contract.getLocation().replaceAll(" ", "."));
        run.addBreak();
        run.addBreak();
        run.setBold(true);
        run.setText("계약기간");
        run.addBreak();//run.addTab();
        run.addBreak();//run.addTab();
        run.setBold(false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        run.setText("   기간:"+contract.getContractDay().format(formatter)+"~"+contract.getExpiredDay().format(formatter));

        document.write(out);
        document.close();
        out.close();
        System.out.println("create_table.docx written successully");
    }

}
