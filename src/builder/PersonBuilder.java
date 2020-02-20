package builder;

import com.sun.codemodel.internal.JClassAlreadyExistsException;

import java.io.IOException;

public class PersonBuilder extends Builder {

    private Model model;

    protected void build() {
        model = load("src/csv files/person.csv").
                entity("person").
                    attribute("column name: first name", String.class).
                    attribute("column name: last name", String.class).
                    entityList("car").
                        entity("car").
                            attribute("column name: model name1", String.class).
                            attribute("coumn name: year1", int.class).
                        entity("car").
                            attribute("column name: model name2", String.class).
                            attribute("coulmn name: year2", int.class).
                    end().
                    entity("address").
                        attribute("column name: street", String.class).
                        attribute("colmn name: city", int.class).
                validate();
    }

    public void run() throws IOException, JClassAlreadyExistsException {
        build();
        model.toJson();
        model.generateCode();
    }

}
