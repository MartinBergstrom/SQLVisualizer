package GUI;

import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;

/**
 * Created by Martin on 2017-04-10.
 *
 * This will be the main panel for showing the database contents, in an excel-like format
 */
public class MainPanel extends JPanel{
    private DefaultTableModel model;

    public MainPanel(LayoutManager layout) {
        super(layout);

        String[] column_names = {"Id", "Firstname", "Surname", "City", "Age"};
        Object[][] data = {};

        //Create the model with non-editable cells
        model = new DefaultTableModel(data, column_names){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(30);
        tcm.getColumn(1).setPreferredWidth(90);
        tcm.getColumn(2).setPreferredWidth(110);
        tcm.getColumn(3).setPreferredWidth(90);
        tcm.getColumn(4).setPreferredWidth(40);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    /**
     * Method to format the data sent from our model through the controller to fit our JTable model
     *
     * @param s the string to foramt
     */
    private void formatAndAddDataString(String s){
        model.addRow(FormatData.getFormattedData(s));
    }

    /**
     * Adds all the users from the list into the JTable by formatting
     * the data and adding the rows
     *
     * @param users the list of the users to add
     */
    public void addUsers(List<User> users){
        StringBuilder sb = new StringBuilder();
        for(User u: users){
            sb.append(u.toString());
            formatAndAddDataString(sb.toString());
            sb.setLength(0);
        }
    }

    /**
     * Adds a single new user to the JTable
     *
     * @param u the new user to add into the JTable
     */
    public void addRow(User u){
        formatAndAddDataString(u.toString());
    }

    /**
     * Clears the whole table
     */
    public void clearTable(){
        model.setRowCount(0);
    }
}


