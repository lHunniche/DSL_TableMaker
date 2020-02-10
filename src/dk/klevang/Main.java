package dk.klevang;

import dk.klevang.columnmodifiers.ColumnProperty;
import dk.klevang.columnmodifiers.ColumnType;

public class Main
{

    public static void main(String[] args)
    {
        // Creates a table with a column property (NOT NULL) and Primary key
        Table table = new Table.Builder("Persons")
                .withColumn("PersonID", ColumnType.INT()).asPrimaryKey()
                .withColumn("FirstName", ColumnType.VAR_CHAR(255), ColumnProperty.NOT_NULL)
                .withColumn("LastName", ColumnType.VAR_CHAR(255))
                .withColumn("City", ColumnType.VAR_CHAR(255))
                .withColumn("Address", ColumnType.VAR_CHAR(255))
                .build();

        System.out.println(table.getCreateString() + "\n");

        // creates a table with a Primary and Foreign key
        Table orderTable = new Table.Builder("Orders")
                .withColumn("Name", ColumnType.VAR_CHAR(255))
                .withColumn("OrderID", ColumnType.INT()).asPrimaryKey()
                .withColumn("PersonID", ColumnType.INT()).asForeignKeyWith("Persons", "PersonID")
                .build();

        System.out.println(orderTable.getCreateString() + "\n");

        //creates a table with only a Foreign key
        Table orderAmounts = new Table.Builder("OrderAmounts")
                .withColumn("Amount", ColumnType.FLOAT(10))
                .withColumn("OrderID", ColumnType.INT()).asForeignKeyWith("Orders", "OrderID")
                .build();

        System.out.println(orderAmounts.getCreateString() + "\n");

        // creates a table with no keys and no column properties
        Table weirdTable = new Table.Builder("Weird")
                .withColumn("Monkey", ColumnType.BLOB(255))
                .build();

        System.out.println(weirdTable.getCreateString() + "\n");
    }
}
