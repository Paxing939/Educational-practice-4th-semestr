package com.company;

import javax.swing.*;
import java.awt.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.TableCellEditor;

class Controller extends AbstractCellEditor implements TableCellEditor {
    final public static String MESSAGE = "Please, write correct formula, such as \"мин\" or \"макс\"(yyyy-mm-dd,A4,B2), or \"=yyyy-mm-dd+-int\" or \"=CELL+-int\"!";
    final public static String MESSAGE_WRONG_DATE = "Please, write correct date in format yyyy-mm-dd!";
    final public static String MESSAGE_WRONG_CELLS_FORMAT = "Please, write correct cell format yyyy-mm-dd!";
    final public static String MESSAGE_CYCLE_REFERENCE = "Cycle references are not acceptable!";
    final public static String MESSAGE_WRONG_FORMULA_FORMAT = "Wrong format of formula!";
    final public static String MESSAGE_WRONG_MAX_MIN_FORMAT = "Wrong max or min format!";
    final public static String MESSAGE_OUT_OF_RANGE = "Out of range!";
    final public static WrongFormatException EXCEPTION = new WrongFormatException(MESSAGE);
    final public static WrongFormatException EXCEPTION_WRONG_DATE_FORMAT = new WrongFormatException(MESSAGE_WRONG_DATE);
    final public static WrongFormatException EXCEPTION_WRONG_CELLS_FORMAT = new WrongFormatException(MESSAGE_WRONG_CELLS_FORMAT);
    final public static WrongFormatException EXCEPTION_CYCLE_REFERENCE = new WrongFormatException(MESSAGE_CYCLE_REFERENCE);
    final public static WrongFormatException EXCEPTION_WRONG_FORMULA_FORMAT = new WrongFormatException(MESSAGE_WRONG_FORMULA_FORMAT);
    final public static WrongFormatException EXCEPTION_WRONG_MAX_MIN_FORMAT = new WrongFormatException(MESSAGE_WRONG_MAX_MIN_FORMAT);
    final public static IndexOutOfBoundsException EXCEPTION_OUT_OF_RANGE = new IndexOutOfBoundsException(MESSAGE_OUT_OF_RANGE);

    final static Pattern CELL = Pattern.compile("[A-Z][1-9]+[0-9]*");

    private final JTextField editor;
    private MyCell currentValue;
    private JTable table;
    int curRow, curCol;
    boolean notifier = false;
    boolean needToNotify = false;


    public Controller() {
        super();
        editor = new JTextField();
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        currentValue = (MyCell) value;
        this.table = table;
        curCol = column;
        curRow = row;

        if (currentValue != null) {
            editor.setText(currentValue.getCurrentFormula());
            needToNotify = true;
        } else {
            editor.setText("");
            needToNotify = false;
        }
        return this.editor;
    }

    public Object getCellEditorValue() {
        MyCell date = currentValue;
        try {
            date = parseDate(editor.getText());
            if (currentValue != null)
                date.setUsingDependencies(currentValue.getUsingDependencies());
            currentValue = date;
            table.getModel().setValueAt(date, curRow, curCol);
            if (needToNotify)
                notifyTable();
        } catch (
                WrongFormatException exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage());
        } catch (
                NumberFormatException exc) {
            JOptionPane.showMessageDialog(null, MESSAGE);
        }

        return date;
    }

    private void findDates(String cell, String str, ArrayList<MyCell> container) {
        int begin = 3;
        if (begin + 10 > str.length() - 1) {
            return;
        }
        while (begin != str.length() - 10) {
            cell = str.substring(begin, begin + 10);
            if (isValidDate(cell)) {
                container.add(parseFullDate(cell));
            }
            begin++;
        }
    }

    private boolean minOrMax(String str) throws WrongFormatException {
        boolean isMin;
        if (str.charAt(2) == 'и' && str.charAt(3) == 'н') {
            isMin = true;
        } else if (str.charAt(2) == 'а' && str.charAt(3) == 'к' && str.charAt(4) == 'с') {
            isMin = false;
        } else {
            throw EXCEPTION;
        }
        String cells = "";
        if (isMin) {
            if (str.charAt(4) != '(') {
                throw EXCEPTION_WRONG_MAX_MIN_FORMAT;
            }
        } else {
            if (str.charAt(5) != '('){
                throw EXCEPTION_WRONG_MAX_MIN_FORMAT;
            }
        }

        return isMin;
    }

    private MyCell parseMaxMin(String str) throws WrongFormatException, NumberFormatException {
        ArrayList<MyCell> container = new ArrayList<>();

        boolean isMin = minOrMax(str);

        String cell = "";
        findDates(cell, str, container);

        cell = str.substring(str.indexOf(cell) + cell.length());
        Matcher m = CELL.matcher(cell);
        while (m.find()) {
            MyCell myCell = parseCellDate(m.group());
            if (myCell == null) {
                throw EXCEPTION_WRONG_DATE_FORMAT;
            }
            container.add(myCell);
        }
        if (cell.charAt(cell.length() - 1) != ')' || container.size() == 0) {
            throw EXCEPTION_WRONG_MAX_MIN_FORMAT;
        }

        if (isMin) {
            return Collections.min(container);
        } else {
            return Collections.max(container);
        }
    }

    private MyCell parseFormula(String str) throws WrongFormatException, NumberFormatException {
        MyCell first;
        int second;
        boolean isMinus;
        if (str.charAt(1) >= '1' && str.charAt(1) <= '9') {
            String usualDate = str.substring(1, 11);
            if (isValidDate(usualDate)) {
                first = parseFullDate(usualDate);
                try {
                    second = Integer.parseInt(str.substring(12));
                } catch (StringIndexOutOfBoundsException exp){
                    throw EXCEPTION_WRONG_FORMULA_FORMAT;
                }
                if (str.charAt(11) == '+')
                    isMinus = false;
                else if (str.charAt(11) == '-')
                    isMinus = true;
                else throw EXCEPTION_WRONG_FORMULA_FORMAT;
                if (isMinus)
                    second *= -1;
                first.add(Calendar.DAY_OF_MONTH, second);
                return first;
            } else {
                throw EXCEPTION_WRONG_DATE_FORMAT;
            }
        } else if (str.charAt(1) >= 'A' && str.charAt(1) <= 'Z') {
            int ind = str.indexOf('-');
            isMinus = true;
            if (ind == -1) {
                ind = str.indexOf('+');
                isMinus = false;
            }
            if (ind == -1)
                throw EXCEPTION_WRONG_FORMULA_FORMAT;
            String cell = str.substring(1, ind);
            Matcher m = CELL.matcher(cell);
            if (m.matches()) {
                first = parseCellDate(cell);
                if (first == null)
                    throw EXCEPTION_WRONG_DATE_FORMAT;
                second = Integer.parseInt(str.substring(cell.length() + 2));
                if (isMinus) {
                    second *= -1;
                }
                first.add(Calendar.DAY_OF_MONTH, second);
                return first;
            } else throw EXCEPTION_WRONG_CELLS_FORMAT;
        } else throw EXCEPTION;
    }

    private MyCell parseFullDate(String str) throws NumberFormatException {
        return new MyCell(Integer.parseInt(str.substring(0, 4)), Integer.parseInt(str.substring(5, 7)) - 1,
                Integer.parseInt(str.substring(8, 10)));
    }

    private MyCell parseUsualDate(String str) throws NumberFormatException, WrongFormatException {
        if (isValidDate(str)) {
            return parseFullDate(str);
        } else {
            throw EXCEPTION_WRONG_DATE_FORMAT;
        }
    }

    private MyCell parseCellDate(String str) throws NumberFormatException, WrongFormatException {
        int curCol = str.charAt(0) - 'A' + 1;
        int curRow = Integer.parseInt(str.substring(1)) - 1;
        if (curRow >= table.getRowCount() || curCol >= table.getColumnCount()) {
            throw EXCEPTION_OUT_OF_RANGE;
        }

        if (!notifier) {
            if (curRow == this.curRow && curCol == this.curCol) {
                throw EXCEPTION_CYCLE_REFERENCE;
            }
        }
        if (table.getValueAt(curRow, curCol) == null) {
            throw EXCEPTION;
        }
        MyCell cellDate = (MyCell) table.getValueAt(curRow, curCol);
        if (cellDate == null) {
            throw EXCEPTION;
        }
        MyCell date = new MyCell(cellDate);
        if (!notifier) {
            cellDate.addUsingDependency(this.curRow, this.curCol);
        }

        return date;
    }

    private MyCell parseDate(String str) throws NumberFormatException, WrongFormatException {
        MyCell resultCell = currentValue;
        if (str.length() == 0) {
            return resultCell;
        }

        if (str.length() >= 5) {
            if (str.charAt(0) == '=') {
                if (str.charAt(1) == 'м') {
                    resultCell = parseMaxMin(str);
                } else {
                    resultCell = parseFormula(str);
                }
            } else {
                resultCell = parseUsualDate(str);
            }
            resultCell.setCurrentFormula(str);
        } else {
            throw EXCEPTION_WRONG_FORMULA_FORMAT;
        }

        return resultCell;
    }

    private void notifyCell(MyCell cell, int i, int j) throws WrongFormatException {
        HashSet<int[]> dependencies = cell.getUsingDependencies();
        MyCell tempCell = cell;
        cell = parseDate(cell.getCurrentFormula());
        cell.setUsingDependencies(tempCell.getUsingDependencies());
        table.setValueAt(cell, i, j);
        for (var elem : dependencies) {
            notifyCell((MyCell) table.getModel().getValueAt(elem[0], elem[1]), elem[0], elem[1]);
        }
    }

    public void notifyTable() throws WrongFormatException, NumberFormatException {
        notifier = true;
        MyCell cell;
        HashSet<int[]> dependencies = currentValue.getUsingDependencies();
        for (var elem : dependencies) {
            cell = (MyCell) table.getModel().getValueAt(elem[0], elem[1]);
            notifyCell(cell, elem[0], elem[1]);
        }
        notifier = false;
    }

    private static boolean isValidDate(String text) {
        if (text == null || !text.matches("\\d{4}-[01]\\d-[0-3]\\d")) {
            return false;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            df.parse(text);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }
}
