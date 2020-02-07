package dk.klevang;

import dk.klevang.columnmodifiers.ColumnProperty;
import dk.klevang.columnmodifiers.ColumnType;

public class Main
{

    public static void main(String[] args)
    {
        Table table = new Table.Builder("Persons")
                .withColumn("PersonID", ColumnType.INT())
                .withColumn("FirstName", ColumnType.VAR_CHAR(255), ColumnProperty.NOT_NULL)
                .withColumn("LastName", ColumnType.VAR_CHAR(255))
                .withColumn("City", ColumnType.VAR_CHAR(255))
                .withColumn("Address", ColumnType.VAR_CHAR(255))
                .withPrimaryKey("PersonID")
                .build();

        System.out.println(table.getCreateString());
    }
}
