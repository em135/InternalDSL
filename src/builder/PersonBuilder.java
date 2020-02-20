package builder;

import com.sun.codemodel.internal.JClassAlreadyExistsException;
import metamodel.Entity;

import java.io.IOException;

public class PersonBuilder extends Builder {

    private Entity model;

    protected void build() {
        model = load("src/csv files/person.csv").
                    entity("person").
                        attribute("first name", String.class).
                        attribute("last name", String.class).
                        entityList("car").
                            entity("car").
                                attribute("model name1", String.class).
                                attribute("year1", int.class).
                            end().
                            entity("car").
                                attribute("model name2", String.class).
                                attribute("year2", int.class).
                            end().
                        end().
                        entity("address").
                            attribute("street", String.class).
                            attribute("city", int.class).
                        end().
                    end().
                last();
    }

    public void run() throws IOException, JClassAlreadyExistsException {
        build();
        System.out.println(model);
//        model.toJson();
//        model.generateCode();
    }

}
