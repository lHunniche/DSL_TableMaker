package dk.klevang;

import java.util.ArrayList;

public class Table
{
    private String tableName;
    private ArrayList<Column> columnList;
    private String createString;
    private String primaryKey = null;

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

        printString = printString.replace("$table_name", this.tableName);

        for (int index = 0; index < columnList.size() - 1; index++)
        {
            //replaces $column with column details (name and type), and appends a new $column
            Column currentColumn = columnList.get(index);

            String columnName = currentColumn.getName() + " ";
            String columnType = (currentColumn.getProperty().length() == 0 ? currentColumn.getType() : currentColumn.getType() + " ");
            String columnProperty = currentColumn.getProperty();

            //printString = printString.replace("$column", columnList.get(index).getName() + " " + columnList.get(index).getType() + ",\n\t$column");
            printString = printString.replace("$column", columnName + columnType + columnProperty + ",\n\t$column");
        }

        // if a primary key is set, the last column is inserted, and a placeholder "PRIMARY KEY($key)" is inserted, which is then replaced by the actual key.
        // is no primary key is set, the last column is inserted in the place of "$column"
        Column lastColumn = columnList.get(columnList.size()-1);
        if (primaryKeyExists())
        {
            printString = printString.replace("$column", lastColumn.getName() + " " + lastColumn.getType() + ",\n\tPRIMARY KEY ($key)");
            printString = printString.replace("$key", this.primaryKey);
        }
        else
        {
            printString = printString.replace("$column", lastColumn.getName() + " " + lastColumn.getType());
        }


        this.createString = printString;
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

        public Builder withColumn(String columnName, String columnType, String columnProperty)
        {
            columnList.add(new Column(columnName, columnType, columnProperty));
            return this;
        }

        public Builder withPrimaryKey(String columnName)
        {
            this.primaryKey = columnName;
            return this;
        }

        public Table build()
        {
            Table table = new Table();
            table.columnList = this.columnList;
            table.tableName = this.tableName;
            table.primaryKey = this.primaryKey;
            table.makeCreateString();

            return table;
        }
    }
}
