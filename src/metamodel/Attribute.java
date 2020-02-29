package metamodel;

public class Attribute {

    private String name;
    private String columnName;
    private Object type;

    public Attribute(String name, String columnName, Object type) {
        this.name = name;
        this.columnName = columnName;
        this.type = type;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getName() {
        return name;
    }

    public Object getType() {
        return type;
    }

}
