package dk.klevang.columnmodifiers;

public class ColumnType
{
    private static final String INT = "INT";
    private static final String VAR_CHAR = "VARCHAR($size)";
    private static final String CHAR = "CHAR($size)";
    private static final String BINARY = "BINARY($size)";
    private static final String VAR_BINARY = "VARBINARY($size)";
    private static final String TINY_BLOB = "TINYBLOB";
    private static final String TINY_TEXT = "TINYTEXT";
    private static final String TEXT = "TEXT($size)";
    private static final String BLOB = "BLOB($size)";
    private static final String MEDIUM_TEXT = "MEDIUMTEXT";
    private static final String MEDIUM_BLOB = "MEDIUMBLOB";
    private static final String LONG_TEXT = "LONGTEXT";
    private static final String LONG_BLOB = "LONGBLOB";
    private static final String BIT = "BIT($size)";
    private static final String TINY_INT = "TINYINT($size)";
    private static final String BOOLEAN = "BOOLEAN";
    private static final String SMALL_INT = "SMALLINT($size)";
    private static final String MEDIUM_INT = "MEDIUMINT($size)";
    private static final String BIG_INT = "BIGINT($size)";
    private static final String FLOAT = "FLOAT($size)";
    private static final String DOUBLE = "DOUBLE($size)";
    private static final String DECIMAL = "DECIMAL($size)";


    public static String BINARY(int size)
    {
        return BINARY.replace("$size", size + "");
    }

    public static String VAR_BINARY(int size)
    {
        return VAR_BINARY.replace("$size", size + "");
    }

    public static String TINY_BLOB(int size)
    {
        return TINY_BLOB;
    }

    public static String TINY_TEXT(int size)
    {
        return TINY_TEXT;
    }

    public static String TEXT(int size)
    {
        return TEXT.replace("$size", size + "");
    }

    public static String BLOB(int size)
    {
        return BLOB.replace("$size", size + "");
    }

    public static String MEDIUM_TEXT()
    {
        return MEDIUM_TEXT;
    }

    public static String MEDIUM_BLOB()
    {
        return MEDIUM_BLOB;
    }

    public static String LONG_TEXT()
    {
        return LONG_TEXT;
    }

    public static String LONG_BLOB()
    {
        return LONG_BLOB;
    }

    public static String BIT(int size)
    {
        return BIT.replace("$size", size + "");
    }

    public static String TINY_INT(int size)
    {
        return TINY_INT.replace("$size", size + "");
    }

    public static String BOOLEAN()
    {
        return BOOLEAN;
    }

    public static String SMALL_INT(int size)
    {
        return SMALL_INT.replace("$size", size + "");
    }

    public static String MEDIUM_INT(int size)
    {
        return MEDIUM_INT.replace("$size", size + "");
    }

    public static String BIG_INT(int size)
    {
        return BIG_INT.replace("$size", size + "");
    }

    public static String FLOAT(int size)
    {
        return FLOAT.replace("$size", size + "");
    }

    public static String DOUBLE(int size)
    {
        return DOUBLE.replace("$size", size + "");
    }

    public static String DECIMAL(int size)
    {
        return DECIMAL.replace("$size", size + "");
    }

    public static String VAR_CHAR(int size)
    {
        return VAR_CHAR.replace("$size", size + "");
    }

    public static String INT()
    {
        return INT;
    }

    public static String CHAR(int size)
    {
        return CHAR.replace("$size", size + "");
    }
}