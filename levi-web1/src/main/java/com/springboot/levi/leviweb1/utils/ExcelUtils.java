package com.springboot.levi.leviweb1.utils;

import com.springboot.levi.leviweb1.model.ColumnError;
import com.springboot.levi.leviweb1.model.ObjectKit;
import com.springboot.levi.leviweb1.model.RowError;
import jxl.*;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.lang.Boolean;
import java.lang.Number;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-07-22 11:55
 */
public class ExcelUtils {


    private static final Integer MAX_SHEET_SIZE = 65535;
    private static final String DEFAULT_TIME_SEC_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * @MethodName  : listToExcel
     * @Description : 导出Excel（也可以导出到浏览器，可自定义工作表大小）
     * @param list      数据源
     * @param fieldMap      类的英文属性和Excel中的中文列名的对应关系
     * 如果需要的是引用对象的属性，则英文属性使用类似于EL表达式的格式
     * 如：list中存放的都是student，student中又有college属性，而我们需要学院名称，则可以这样写
     * fieldMap.put("college.collegeName","学院名称")
     * @param sheetName 工作表的名称
     * @param sheetSize 每个工作表中记录的最大个数
     * @param out       导出流
     */
    public static <T> void listToExcel(List<T> list, LinkedHashMap<String,String> fieldMap, String sheetName, int sheetSize, OutputStream out, List<RowError> rowErrors)  {
        if(list==null  || list.size() == 0){
            return;
        }
        if(sheetName == null || sheetName.equals("")) {
            sheetName = "sheet";
        }
        if(sheetSize > MAX_SHEET_SIZE || sheetSize < 1){
            sheetSize = MAX_SHEET_SIZE;
        }
        Map<Integer, List<ColumnError>> rowColumnErrsMap = new HashMap<>();
        if(rowErrors != null) {
            for (RowError re : rowErrors) {
                if(re == null) {
                    continue;
                }
                Integer inxKey = re.getRow() - 1;
                List<ColumnError> columnErrors = rowColumnErrsMap.get(inxKey); //-1 to index
                if(columnErrors == null) {
                    rowColumnErrsMap.put(inxKey, columnErrors = new ArrayList<>());
                }
                columnErrors.addAll(re.getColumnErrList());
            }
        }

        //创建工作簿并发送到OutputStream指定的地方
        WritableWorkbook wwb;
        try {
            wwb = Workbook.createWorkbook(out);
            //因为2003的Excel一个工作表最多可以有65536条记录，除去列头剩下65535条
            //所以如果记录太多，需要放到多个工作表中，其实就是个分页的过程
            //1.计算一共有多少个工作表
            double sheetNum = Math.ceil(((double)list.size()) / sheetSize);

            //2.创建相应的工作表，并向其中填充数据
            for(int i=0; i<sheetNum; i++){
                if(1==sheetNum){
                    //如果只有一个工作表的情况
                    WritableSheet sheet=wwb.createSheet(sheetName, i);
                    fillSheet(sheet, list, fieldMap, 0, list.size()-1, rowColumnErrsMap);
                }else{
                    //有多个工作表的情况
                    WritableSheet sheet=wwb.createSheet(sheetName+(i+1), i);

                    //获取开始索引和结束索引
                    int firstIndex=i*sheetSize;
                    int lastIndex=(i+1)*sheetSize-1>list.size()-1 ? list.size()-1 : (i+1)*sheetSize-1;
                    //填充工作表
                    fillSheet(sheet, list, fieldMap, firstIndex, lastIndex, rowColumnErrsMap);
                }
            }
            wwb.write();
            wwb.close();
        } catch (IllegalArgumentException | IllegalAccessException | WriteException | IOException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> void listToExcel (List<T> list , LinkedHashMap<String,String> fieldMap, String sheetName, OutputStream out) {
        listToExcel(list, fieldMap, sheetName, out, null);
    }

    /**
     * @MethodName  : listToExcel
     * @Description : 导出Excel（也可以导出到浏览器，工作表大小为2003支持的最大值）
     * @param list      数据源
     * @param fieldMap      类的英文属性和Excel中的中文列名的对应关系
     * @param out       导出流
     */
    public static <T> void listToExcel (List<T> list , LinkedHashMap<String,String> fieldMap, String sheetName, OutputStream out, List<RowError> rowErrors) {
        listToExcel(list, fieldMap, sheetName, MAX_SHEET_SIZE, out, rowErrors);
    }

    public static <T> void listToExcel (String fileName, List<T> list, LinkedHashMap<String,String> fieldMap, String sheetName, HttpServletResponse response) {
        listToExcel(fileName, list, fieldMap, sheetName, MAX_SHEET_SIZE, response, null);
    }


    public static <T> void listToExcel (String fileNamePerfix, List<T> list, LinkedHashMap<String,String> fieldMap, String sheetName, int sheetSize, HttpServletResponse response) {
        listToExcel(fileNamePerfix, list, fieldMap, sheetName, sheetSize, response, null);
    }

    /**
     * @MethodName  : listToExcel
     * @param fileNamePerfix 文件名前缀
     * @Description : 导出Excel（导出到浏览器，可以自定义工作表的大小）
     * @param list      数据源
     * @param fieldMap      类的英文属性和Excel中的中文列名的对应关系
     * @param sheetSize    每个工作表中记录的最大个数
     * @param response  使用response可以导出到浏览器
     */
    public static <T> void listToExcel (String fileNamePerfix, List<T> list, LinkedHashMap<String,String> fieldMap, String sheetName, int sheetSize, HttpServletResponse response, List<RowError> rowErrors) {
        //设置默认文件名为前缀+当前时间：年月日时分秒
        String fileName = fileNamePerfix + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        //设置response头信息
        response.reset();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment; filename=" + tryTransEncodingByRequest(fileName) + ".xls" );

        //创建工作簿并发送到浏览器
        try {
            OutputStream out=response.getOutputStream();
            listToExcel(list, fieldMap, sheetName, sheetSize, out, rowErrors);
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());

            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @MethodName  : listToExcel
     * @Description : 导出Excel（导出到浏览器，工作表的大小是2003支持的最大值）
     * @param list      数据源
     * @param fieldMap      类的英文属性和Excel中的中文列名的对应关系
     * @param response  使用response可以导出到浏览器
     */
    public static <T> void listToExcel (String fileName, List<T> list, LinkedHashMap<String,String> fieldMap, String sheetName, HttpServletResponse response, List<RowError> rowErrors) {
        listToExcel(fileName, list, fieldMap, sheetName, MAX_SHEET_SIZE, response, rowErrors);
    }


    //EXCEL TO ENTITY LIST
    public static <T> List<T> excelToList(InputStream in, String sheetName, Class<T> entityClass, LinkedHashMap<String, String> fieldMap, String[] uniqueFields) {
        return excelToList(in, sheetName, entityClass, fieldMap, uniqueFields, null);
    }

    public static <T> List<T> excelToList(InputStream in, String sheetName, Class<T> entityClass, LinkedHashMap<String, String> fieldMap, String[] uniqueFields, List<RowError> rowErrors) {
        try {
            Workbook wb = Workbook.getWorkbook(in);
            return excelToList(wb, sheetName, entityClass, fieldMap, uniqueFields, rowErrors);
        } catch (IOException | BiffException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T>  List<T> excelToList(InputStream in, Class<T> entityClass, LinkedHashMap<String, String> fieldMap, String[] uniqueFields) {
        return excelToList(in, entityClass, fieldMap, uniqueFields, null);
    }

    public static <T>  List<T> excelToList(InputStream in, Class<T> entityClass, LinkedHashMap<String, String> fieldMap, String[] uniqueFields, List<RowError> rowErrors) {
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(in);
        } catch (BiffException | IOException e) {
            throw new RuntimeException(e);
        }
        List<T> list = new ArrayList<>();
        for(String sheetName : workbook.getSheetNames()) {
            list.addAll(excelToList(workbook, sheetName, entityClass, fieldMap, uniqueFields, rowErrors));
        }
        return list;
    }

    public static Workbook getWorkbook(InputStream in) {
        try {
            return Workbook.getWorkbook(in);
        } catch (BiffException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> sheetToList(Workbook wb, int sheetIndex, Class<T> entityClass, LinkedHashMap<String, String> fieldMap, String[] uniqueFields) {
        return sheetToList(wb, sheetIndex, entityClass, fieldMap, uniqueFields, null);
    }

    public static <T> List<T> sheetToList(Workbook wb, int sheetIndex, Class<T> entityClass, LinkedHashMap<String, String> fieldMap, String[] uniqueFields, List<RowError> rowErrors) {
        return excelToList(() -> wb == null ? null : wb.getSheet(sheetIndex), entityClass, fieldMap, uniqueFields, rowErrors);
    }

    public static <T> List<T> excelToList(Workbook wb, String sheetName, Class<T> entityClass, LinkedHashMap<String, String> fieldMap, String[] uniqueFields, List<RowError> rowErrors) {
        return excelToList(() -> wb == null ? null : wb.getSheet(sheetName), entityClass, fieldMap, uniqueFields, rowErrors);
    }

    private static <T> List<T> excelToList(Supplier<Sheet> sheetSupplier, Class<T> entityClass, LinkedHashMap<String, String> fieldMap, String[] uniqueFields, List<RowError> rowErrors) {
        return excelToList(sheetSupplier == null ? null : sheetSupplier.get(), entityClass, fieldMap, uniqueFields, rowErrors);
    }

    private static <T> List<T> excelToList(Sheet sheet, Class<T> entityClass, LinkedHashMap<String, String> fieldMap, String[] uniqueFields, List<RowError> rowErrors) {
        List<T> resultList = new ArrayList<T>();
        if(sheet == null) return resultList;
        try {
            //获取工作表的有效行数
            int realRows=0;
            for(int i=0;i<sheet.getRows();i++){
                int nullCols=0;
                for(int j=0; j<sheet.getColumns(); j++){
                    Cell currentCell=sheet.getCell(j,i);
                    if(currentCell==null || "".equals(currentCell.getContents())){
                        nullCols++;
                    }
                }
                if(nullCols == sheet.getColumns()){
                    break;
                } else {
                    realRows++;
                }
            }
            //如果Excel中没有数据则提示错误
            if(realRows <= 1){
                throw new RuntimeException("Excel文件中没有任何数据");
            }
            Cell[] firstRow=sheet.getRow(0);
            int cols = sheet.getColumns();
            String[] excelFieldNames=new String[firstRow.length];
            //获取Excel中的列名
            for(int i = 0; i < firstRow.length; i++){
                excelFieldNames[i]=firstRow[i].getContents().toString().trim();
            }

            //判断需要的字段在Excel中是否都存在
            boolean isExist=true;
            List<String> excelFieldList=Arrays.asList(excelFieldNames);
            for(String cnName : fieldMap.values()){
                if(!excelFieldList.contains(cnName)){
                    isExist=false;
                    break;
                }
            }
            //如果有列名不存在，则抛出异常，提示错误
            if(!isExist){
                throw new RuntimeException("Excel中缺少必要的字段，或字段名称有误");
            }
            Map<String, Integer> colMap=new HashMap<>(10);
            for(int i=0; i<excelFieldNames.length; i++){
                colMap.put(excelFieldNames[i], firstRow[i].getColumn());
            }

            if(null != uniqueFields) {
                //判断是否有重复行
                //1.获取uniqueFields指定的列
                Cell[][] uniqueCells=new Cell[uniqueFields.length][];
                for(int i=0;i<uniqueFields.length;i++){
                    int col=colMap.get(uniqueFields[i]);
                    uniqueCells[i]=sheet.getColumn(col);
                }
                //2.从指定列中寻找重复行
                for(int i=1;i<realRows;i++){
                    int nullCols=0;
                    for(int j=0;j<uniqueFields.length;j++){
                        String currentContent=uniqueCells[j][i].getContents();
                        Cell sameCell=sheet.findCell(currentContent,
                                uniqueCells[j][i].getColumn(),
                                uniqueCells[j][i].getRow()+1,
                                uniqueCells[j][i].getColumn(),
                                uniqueCells[j][realRows-1].getRow(),
                                true);
                        if(sameCell!=null){
                            nullCols++;
                        }
                    }
                    if(nullCols == uniqueFields.length){
                        // get reduplicate content
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < uniqueFields.length; j++) {
                            String currentContent = uniqueCells[j][i].getContents();
                            stringBuilder.append("|" + currentContent);
                        }
                        throw new RuntimeException("Excel中有重复行，请检查,content=" + stringBuilder.toString());
                    }
                }
            }
            //将sheet转换为list
            for(int i=1;i<realRows;i++){
                //新建要转换的对象
                T entity=entityClass.newInstance();
                RowError rowError = new RowError(i + 1);
                //给对象中的字段赋值
                for(Map.Entry<String, String> entry : fieldMap.entrySet()){
                    String cnNormalName=entry.getValue(); //获取中文字段名
                    String enNormalName=entry.getKey(); //获取英文字段名
                    int col=colMap.get(cnNormalName); //根据中文字段名获取列号

                    try {
                        setFieldValueByName(enNormalName, sheet.getCell(col, i), entity); //给对象赋值
                    } catch (RuntimeException e) {
                        throw e;
                    }
                }

                if(!rowError.getColumnErrList().isEmpty()) {
                    rowErrors.add(rowError);
                }
                resultList.add(entity);
            }
        } catch(InstantiationException | IllegalArgumentException | IllegalAccessException e){
            throw new RuntimeException(e);
        }
        return resultList;
    }

    /*<-------------------------辅助的私有方法----------------------------------------------->*/
    private static void setFieldValueByName(String fieldName, Cell cell, Object o) throws IllegalArgumentException, IllegalAccessException {
        Field field = ReflectionUtils.findField(o.getClass(), fieldName);
        if(field != null && cell != null){
            field.setAccessible(true);
            Object value = tryMatchTypedValue(cell, field.getType());
            if (value != null) {
                field.set(o, value);
            } else {
                try {
                    value = cellToValue(cell.getContents().trim(), field.getType());
                    if (value != null) {
                        field.set(o, value);
                    }
                } catch (Exception e) {
                    throw e;
                }
            }
        } else {
            throw new RuntimeException(o.getClass().getSimpleName() + "类不存在字段名 "+fieldName);
        }
    }

    /*
        public static final CellType EMPTY = new CellType("Empty");
        public static final CellType LABEL = new CellType("Label");
        public static final CellType NUMBER = new CellType("Number");  interface NumberCell
        public static final CellType BOOLEAN = new CellType("Boolean");
        public static final CellType ERROR = new CellType("Error");
        public static final CellType DATE = new CellType("Date"); interface DateCell
     */
    private static Object tryMatchTypedValue(Cell cell, Class<?> fieldType) {
        if(fieldType == null || cell == null) {
            return null;
        }
        boolean cellIsNumber = cell instanceof NumberCell;
        Double numberCellValue = !cellIsNumber ?  null : ((NumberCell)cell).getValue();
        if(cellIsNumber) {
            if (((Integer.TYPE == fieldType) || (Integer.class == fieldType)) && cellIsNumber) {
                return numberCellValue.intValue();
            } else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
                return numberCellValue.longValue();
            } else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
                return numberCellValue.floatValue();
            } else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
                return numberCellValue.shortValue();
            } else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
                return numberCellValue;
            } else {
                return null;
            }
        } else if(Date.class == fieldType && cell instanceof DateCell) {
            return ((DateCell) cell).getDate();
        } else if((Boolean.TYPE == fieldType || Boolean.class == fieldType) && cell instanceof BooleanCell) {
            return ((BooleanCell) cell).getValue();
        } else {
            return null;
        }
    }

    private static Object cellToValue(String strExp, Class<?> fieldType) throws RuntimeException {
        if(fieldType == null) {
            return null;
        }
        boolean empty = strExp == null || strExp.length() < 1;
        try {
            if (String.class == fieldType) {
                return strExp;
            } else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
                return empty ? 0 : tryParse(strExp, e -> Integer.parseInt(e), "integer");
            } else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
                return empty ? 0L : tryParse(strExp, e -> Long.valueOf(e), "long");
            } else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
                return empty ? 0f : tryParse(strExp, e -> Float.valueOf(e), "float");
            } else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
                return empty ? (short)0 : tryParse(strExp, e -> Short.valueOf(e), "short");
            } else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
                return empty ? 0d : tryParse(strExp, e -> Double.valueOf(e), "double");
            } else if (Character.TYPE == fieldType) {
                return empty ? null : tryParse(strExp, e -> e.charAt(0), "char");
            } else if (Boolean.TYPE == fieldType || Boolean.class == fieldType) {
                return empty ? null : tryParse(strExp, e -> Boolean.valueOf(e), "boolean");
            } else if (Date.class == fieldType) {
                return empty ? null : tryParse(strExp, ex -> {
                    try {
                        return new SimpleDateFormat(DEFAULT_TIME_SEC_FORMAT).parse(ex);
                    } catch (ParseException e) {
                        throw new RuntimeException("can't parse to 'time'.");
                    }
                }, "time");
            } else {
                throw new RuntimeException("not support '" + fieldType.getSimpleName() + "' value parse.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private static <String, R> R tryParse(String val, Function<String, R> f, String type) throws RuntimeException {
        try {
            return f.apply(val);
        } catch (Exception e) {
            throw new RuntimeException("can't parse to '" + type + "'.");
        }
    }

    /**
     * @MethodName  : setColumnAutoSize
     * @Description : 设置工作表自动列宽和首行加粗
     * @param ws
     */
    private static void setColumnAutoSize(WritableSheet ws,int extraWith){
        //获取本列的最宽单元格的宽度
        for(int i=0;i<ws.getColumns();i++){
            int colWith=0;
            for(int j=0;j<ws.getRows();j++){
                String content=ws.getCell(i,j).getContents().toString();
                int cellWith=content.length();
                if(colWith<cellWith){
                    colWith=cellWith;
                }
            }
            //设置单元格的宽度为最宽宽度+额外宽度
            ws.setColumnView(i, colWith+extraWith);
        }
    }


    static WritableCellFormat RED_BORDER_WARNING_FORMAT = new WritableCellFormat();
    static {
        try {
            RED_BORDER_WARNING_FORMAT.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM, Colour.RED);
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
    private static <T> void fillSheet(WritableSheet sheet, List<T> list, Map<String,String> fieldMap, int firstIndex,
                                      int lastIndex, Map<Integer, @NotNull List<ColumnError>> rowColumnErrsMap) throws WriteException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        //定义存放Header字段名和对象字段名的数组
        String[] enFields = new String[fieldMap.size()];
        String[] cnFields = new String[fieldMap.size()];
        //填充数组
        int count = 0;
        for(Map.Entry<String,String> entry : fieldMap.entrySet()){
            cnFields[count] = entry.getValue();
            enFields[count] = entry.getKey();
            count++;
        }
        //填充表头
        for(int i=0;i<cnFields.length;i++){
            Label label = new Label(i, 0, cnFields[i]);
            sheet.addCell(label);
        }
        //填充内容
        int rowNo = 1;
        for(int index = firstIndex; index <= lastIndex; index++){
            //获取单个对象
            T item=list.get(index);
            Set<Integer> columnsErrInxSet = new HashSet<>();
            List<ColumnError> columnErrors = rowColumnErrsMap.get(index + 1); //skip header row.
            if(!CollectionUtils.isEmpty(columnErrors)) {
                Collections.sort(columnErrors, Comparator.comparingInt(ColumnError::getInx));
                StringBuilder errHint = new StringBuilder();
                for(ColumnError ce : columnErrors) {
                    if(errHint.length() > 0) {
                        errHint.append('\n');
                    }
                    columnsErrInxSet.add(ce.getInx() - 1);
                    errHint.append("Column " + excelColIndexToStr(ce.getInx()) + " " + ce.getErr());
                }
                sheet.addCell(writeRecordTypedValue(errHint, enFields.length, rowNo));
            }
            for(int i = 0; i < enFields.length; i++){
                Object objValue = ObjectKit.pathFieldValue(enFields[i], item);
                WritableCell writableCell = writeRecordTypedValue(objValue, i, rowNo);
                if(columnsErrInxSet.contains(i)) {
                    writableCell.setCellFormat(RED_BORDER_WARNING_FORMAT);
                }
                sheet.addCell(writableCell);
            }

            rowNo++;
        }
        //设置自动列宽
        setColumnAutoSize(sheet, 5);
    }

    private static String excelColIndexToStr(int columnIndex) {
        if (columnIndex <= 0) {
            return null;
        }
        String columnStr = "";
        columnIndex--;
        do {
            if (columnStr.length() > 0) {
                columnIndex--;
            }
            columnStr = ((char) (columnIndex % 26 + (int) 'A')) + columnStr;
            columnIndex = (int) ((columnIndex - columnIndex % 26) / 26);
        } while (columnIndex > 0);
        return columnStr;
    }

    static DateFormat DATE_FORMAT = new DateFormat(DEFAULT_TIME_SEC_FORMAT);
    static WritableCellFormat DATE_CELL_FORMAT = new WritableCellFormat(DATE_FORMAT);

    private static WritableCell writeRecordTypedValue(Object value, int col, int row) {
        if(value == null) {
            return new Label(col, row, "");
        } else if(value instanceof Date) {
            return new DateTime(col, row, (Date)value, DATE_CELL_FORMAT);
        } else if(value instanceof Number) {
            return new jxl.write.Number(col, row, ((Number) value).doubleValue());
        } else if(value instanceof Boolean) {
            return new jxl.write.Boolean(col, row, (Boolean)value);
        } else {
            return new Label(col, row, format(value));
        }
    }

    private static String format(Object o) {
        if(o == null) {
            return "";
        }
        if(o instanceof Date) {
            return new SimpleDateFormat(DEFAULT_TIME_SEC_FORMAT).format((Date)o);
        } else {
            return o.toString();
        }
    }

    //US-ASCII
    //ISO-8859-1
    //UTF-8
    //UTF-16BE
    //UTF-16LE
    //UTF-16
    private static String tryTransEncodingByRequest(String s) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if(requestAttributes == null) {
            return s;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        if(request == null) {
            return s;
        }
        final String userAgent = request.getHeader("User-Agent");
        if(userAgent == null || userAgent.isEmpty()) {
            return s;
        }
        if(userAgent.contains("MSIE") || userAgent.contains("rv:11") || userAgent.contains("Edge") || userAgent.contains("Node")) {
            try {
                return URLEncoder.encode(s, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return s;
            }
        } else if(userAgent.contains("Mozilla")) {
            try {
                return new String(s.getBytes("UTF-8"), "ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                return s;
            }
        } else {
            return s; //URLEncoder.encode(s, "UTF-8");
        }
    }
}
