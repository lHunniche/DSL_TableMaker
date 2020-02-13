package dk.klevang;

import java.util.ArrayList;

public class Table
{
    private String tableName;
    private ArrayList<Column> columnList;
    private String createString;
    private String primaryKey = null;
    private String foreignKey = null;

    // private constructor, so we only can use Builder
    private Table()
    {
    }

    private void makeCreateString()
    {
        String printString = "CREATE TABLE $table_name (" +
                "\n" +
                "\t$column" +
                "\n" +
                ");";

        //inserting the table name into the $table_name placeholder
        printString = printString.replace("$table_name", this.tableName);

        //run every column through, and add it to the creation String
        for (int index = 0; index < columnList.size() - 1; index++)
        {
            Column currentColumn = columnList.get(index);

            //elements from the column are taken out of the object. If a property, such as NOT NULL is selected, a space is added to the "columnType" String.
            String columnName = currentColumn.getName() + " ";
            String columnType = (currentColumn.getProperty() == null ? currentColumn.getType() : currentColumn.getType() + " ");
            String columnProperty = currentColumn.getPropertiesAsString();

            //printString = printString.replace("$column", columnList.get(index).getName() + " " + columnList.get(index).getType() + ",\n\t$column");
            printString = printString.replace("$column", columnName + columnType + columnProperty + ",\n\t$column");
        }

        // if a primary key is set, the last column is inserted, and a placeholder "PRIMARY KEY($key)" is inserted, which is then replaced by the actual key.
        // is no primary key is set, the last column is inserted in the place of "$column"
        Column lastColumn = columnList.get(columnList.size() - 1);
        if (primaryKeyExists())
        {
            if (foreignKeyExists())
            {   // both primary and foreign keys exists
                printString = printString.replace("$column", lastColumn.getName() + " " + lastColumn.getType() + ",\n\t$p_key,\n\t$f_key");
                printString = printString.replace("$p_key", this.primaryKey);
                printString = printString.replace("$f_key", this.foreignKey);
            }
            else
            {
                // just primary key exists
                printString = printString.replace("$column", lastColumn.getName() + " " + lastColumn.getType() + ",\n\t$p_key");
                printString = printString.replace("$p_key", this.primaryKey);
            }
        }
        if (foreignKeyExists())
        {   // just foreign exists
            printString = printString.replace("$column", lastColumn.getName() + " " + lastColumn.getType() + ",\n\t$f_key");
            printString = printString.replace("$f_key", this.foreignKey);
        }
        else
        {   // no keys exist
            printString = printString.replace("$column", lastColumn.getName() + " " + lastColumn.getType());
        }

        this.createString = printString;
    }

    private boolean foreignKeyExists()
    {
        return this.foreignKey != null;
    }

    private boolean primaryKeyExists()
    {
        return this.primaryKey != null;
    }

    public String getCreateString()
    {
        return this.createString;
    }


    public static class Builder
    {
        private String tableName;
        private ArrayList<Column> columnList;
        private String primaryKey = null;
        private String foreignKey = null;

        public Builder(String tableName)
        {
            this.tableName = tableName;
            columnList = new ArrayList<>();
        }

        public Builder withColumn(String columnName, String columnType)
        {
            columnList.add(new Column(columnName, columnType));
            return this;
        }

        public Builder withColumn(String columnName, String columnType, String... columnProperty)
        {
            columnList.add(new Column(columnName, columnType, columnProperty));
            return this;
        }

        public Builder asPrimaryKey()
        {
            Column lastInsertedColumn = columnList.get(columnList.size() - 1);
            this.primaryKey = "PRIMARY KEY (" + lastInsertedColumn.getName() + ")";
            return this;
        }

        public Builder asForeignKeyWith(String refTable, String refColumn)
        {
            Column lastInsertedColumn = columnList.get(columnList.size() - 1);
            this.foreignKey = "FOREIGN KEY (" + lastInsertedColumn.getName() + ") REFERENCES " + refTable + "(" + refColumn + ")";
            return this;
        }

        public Table build()
        {
            Table table = new Table();
            table.columnList = this.columnList;
            table.tableName = this.tableName;
            table.primaryKey = this.primaryKey;
            table.foreignKey = this.foreignKey;
            table.makeCreateString();

            return table;
        }
    }
}
