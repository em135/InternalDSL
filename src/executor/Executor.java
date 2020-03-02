package executor;

import metamodel.Attribute;
import metamodel.Entity;
import metamodel.EntityList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Executor {

    /**
     * Represents the column names in the CSV and their list of values
     */
    private Map<String, ArrayList<String>> columnValues = new HashMap<>();
    private StringBuilder JSON = new StringBuilder();
    private int currentRow;
    private int rowCount;


    /**
     * Calls readCSV to read the CSV file
     * An Entity from the Model is interpreted and JSON representing a
     * list of the Entity's object graph with values from the CSV is printed
     *
     * @param model The Model with a File and Entity created by a Builder
     */
    public void toJSON(Model model) {
        readCSV(model.getFile());
        String objectName = model.getEntity().getName();
        JSON.append("{").append("\"").append(objectName).append("\":[");

        for (currentRow = 0; currentRow < rowCount; currentRow++) {
            interpretEntity(model.getEntity());
        }

        checkForExtraComma();
        JSON.append("]}");

        System.out.println(JSON);
    }


    /**
     * Reads the file into the columnValues instance variable
     *
     * @param file the File to read
     */
    public void readCSV(File file) {
        try (Scanner scanner = new Scanner(file)) {
            String[] columnNames = scanner.nextLine().split(",");
            for (String columnName : columnNames) {
                columnValues.put(columnName, new ArrayList<>());
            }
            while (scanner.hasNextLine()) {
                String[] cells = scanner.nextLine().split(",");
                for (int i = 0; i < cells.length; i++) {
                    columnValues.get(columnNames[i]).add(cells[i]);
                }
            }
            rowCount = columnValues.get(columnNames[0]).size();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets the value for the column name found in the Attribute
     *
     * @param attribute the Attribute to get a value for
     * @return a string of the value from the CSV
     */
    private String getValue(Attribute attribute) {
        String cellValue = columnValues.get(attribute.getColumnName()).get(currentRow);

        String attributeJSON = "\"" + attribute.getName() + "\":";
        if (attribute.getType() != String.class) {
            return attributeJSON + cellValue + ",";
        }
        return attributeJSON + "\"" + cellValue + "\",";
    }


    /**
     * Interprets the Entity to JSON
     *
     * @param entity The Entity to interpret
     */
    private void interpretEntity(Entity entity) {
        List<Attribute> attributes = entity.getAttributes();
        List<Entity> entities = entity.getEntities();
        List<EntityList> entityLists = entity.getEntityLists();

        JSON.append("{");
        interpretAttributes(attributes);
        interpretAttributeEntities(entities);
        interpretEntityLists(entityLists);
        checkForExtraComma();
        JSON.append("},");
    }

    /**
     * Interprets the Attributes of an Entity to JSON
     *
     * @param attributes The attributes of an Entity to interpret
     */
    private void interpretAttributes(List<Attribute> attributes) {
        for (Attribute attribute : attributes) {
            JSON.append(getValue(attribute));
        }
    }

    /**
     * Interpret Entities belonging to an Entity to JSON
     *
     * @param entities The Entities belonging to an Entity to interpret
     */
    private void interpretAttributeEntities(List<Entity> entities) {
        for (Entity e : entities) {
            JSON.append("\"").append(e.getName()).append("\":");
            interpretEntity(e);
        }
    }

    /**
     * Interpret Entities belonging to an EntityList to JSON
     *
     * @param entities The Entities belonging to an EntityList to interpret
     */
    private void interpretEntityListEntities(List<Entity> entities) {
        for (Entity e : entities) {
            interpretEntity(e);
        }
    }

    /**
     * Interpret Entities belonging to an EntityList to JSON
     *
     * @param entityLists The Entities belonging to an EntityList to interpret
     */
    private void interpretEntityLists(List<EntityList> entityLists) {
        for (EntityList el : entityLists) {
            JSON.append("\"").append(el.getType()).append("\":").append("[");
            interpretEntityListEntities(el.getEntities());
            checkForExtraComma();
            JSON.append("]");
        }
    }


    /**
     * Doing the process of interpreting objects additional commas will be added
     * This method checks for erroneous commas and removes them to keep a valid JSON syntax
     */
    private void checkForExtraComma() {
        int index = JSON.length() - 1;
        if (JSON.lastIndexOf(",") == index) {
            JSON.replace(index, index + 1, "");
        }
    }

}

