package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.BookingFlight;
import it.uniroma2.dicii.bd.model.domain.BookingList;
import it.uniroma2.dicii.bd.model.domain.Passenger;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.*;

public class BookingListProcedureDAO implements GenericProcedureDAO<BookingList> {

    @Override
    public BookingList execute(Object... params) throws DAOException {
        BookingList bookingList = new BookingList();

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call report_prenotazioni()}");
            boolean status = cs.execute();

            if(status) {
                ResultSet rs = cs.getResultSet();
                while (rs.next()) {
                    BookingFlight bookingFlight = new BookingFlight(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4));
                    bookingList.addBookingFlight(bookingFlight);
                }

                int flightIndex = 0;
                status = cs.getMoreResults();
                do {
                    if(status) {
                        rs = cs.getResultSet();
                        while (rs.next()) {
                            Passenger passenger = new Passenger(rs.getString(1), rs.getString(2));
                            bookingList.addPassengerToBookingFlight(passenger, flightIndex++);
                        }
                    }

                    status = cs.getMoreResults();
                } while(status || cs.getUpdateCount() != -1);
            }
        } catch(SQLException e) {
            throw new DAOException("BookingList error: " + e.getMessage());
        }

        return bookingList;
    }

    /**
     * This method attempts to output the contents of a ResultSet in a textual
     * table. It relies on the ResultSetMetaData class, but a fair bit of the
     * code is simple string manipulation.
     */
    static void printResultsTable(ResultSet rs, OutputStream output) throws SQLException {
        // Set up the output stream
        PrintWriter out = new PrintWriter(output);

        // Get some "meta data" (column names, etc.) about the results
        ResultSetMetaData metadata = rs.getMetaData();

        // Variables to hold important data about the table to be displayed
        int numcols = metadata.getColumnCount(); // how many columns
        String[] labels = new String[numcols]; // the column labels
        int[] colwidths = new int[numcols]; // the width of each
        int[] colpos = new int[numcols]; // start position of each
        int linewidth; // total width of table

        // Figure out how wide the columns are, where each one begins,
        // how wide each row of the table will be, etc.
        linewidth = 1; // for the initial '|'.
        for (int i = 0; i < numcols; i++) { // for each column
            colpos[i] = linewidth; // save its position
            labels[i] = metadata.getColumnLabel(i + 1); // get its label
            // Get the column width. If the db doesn't report one, guess
            // 30 characters. Then check the length of the label, and use
            // it if it is larger than the column width
            int size = metadata.getColumnDisplaySize(i + 1);
            if (size == -1)
                size = 30; // Some drivers return -1...
            if (size > 500)
                size = 30; // Don't allow unreasonable sizes
            int labelsize = labels[i].length();
            if (labelsize > size)
                size = labelsize;
            colwidths[i] = size + 1; // save the column the size
            linewidth += colwidths[i] + 2; // increment total size
        }

        // Create a horizontal divider line we use in the table.
        // Also create a blank line that is the initial value of each
        // line of the table
        StringBuffer divider = new StringBuffer(linewidth);
        StringBuffer blankline = new StringBuffer(linewidth);
        for (int i = 0; i < linewidth; i++) {
            divider.insert(i, '-');
            blankline.insert(i, " ");
        }
        // Put special marks in the divider line at the column positions
        for (int i = 0; i < numcols; i++)
            divider.setCharAt(colpos[i] - 1, '+');
        divider.setCharAt(linewidth - 1, '+');

        // Begin the table output with a divider line
        out.println(divider);

        // The next line of the table contains the column labels.
        // Begin with a blank line, and put the column names and column
        // divider characters "|" into it. overwrite() is defined below.
        StringBuffer line = new StringBuffer(blankline.toString());
        line.setCharAt(0, '|');
        for (int i = 0; i < numcols; i++) {
            int pos = colpos[i] + 1 + (colwidths[i] - labels[i].length()) / 2;
            overwrite(line, pos, labels[i]);
            overwrite(line, colpos[i] + colwidths[i], " |");
        }

        // Then output the line of column labels and another divider
        out.println(line);
        out.println(divider);

        // Now, output the table data. Loop through the ResultSet, using
        // the next() method to get the rows one at a time. Obtain the
        // value of each column with getObject(), and output it, much as
        // we did for the column labels above.
        while (rs.next()) {
            line = new StringBuffer(blankline.toString());
            line.setCharAt(0, '|');
            for (int i = 0; i < numcols; i++) {
                Object value = rs.getObject(i + 1);
                if (value != null)
                    overwrite(line, colpos[i] + 1, value.toString().trim());
                overwrite(line, colpos[i] + colwidths[i], " |");
            }
            out.println(line);
        }

        // Finally, end the table with one last divider line.
        out.println(divider);
        out.flush();
    }

    /** This utility method is used when printing the table of results */
    static void overwrite(StringBuffer b, int pos, String s) {
        int slen = s.length(); // string length
        int blen = b.length(); // buffer length
        if (pos + slen > blen)
            slen = blen - pos; // does it fit?
        for (int i = 0; i < slen; i++)
            // copy string into buffer
            b.setCharAt(pos + i, s.charAt(i));
    }
}
