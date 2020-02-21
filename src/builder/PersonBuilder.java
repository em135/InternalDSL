package builder;

import com.sun.codemodel.internal.JClassAlreadyExistsException;
import executor.Executor;
import executor.Model;
import metamodel.Entity;

import java.io.IOException;
import java.util.Stack;

public class PersonBuilder extends Builder {

    private Model model;

    protected void build() {
        model = load("src/test_files/person.csv").
                    entity("person").
                        attribute("first name", String.class).
                        attribute("last name", String.class).
                        entityList("car").
                            entity("car").
                                attribute("car model1", String.class).
                                attribute("car year1", int.class).
                                entity("engine").
                                    attribute("engine name", String.class).
                                    attribute("engine type", String.class).
                                    entityList("oil").
                                        entity("oil").
                                            attribute("good oil", String.class).
                                        end().
                                        entity("oil").
                                            attribute("bad oil", String.class).
                                        end().
                                        entity("oil").
                                            attribute("fine oil", String.class).
                                        end().
                                    end().
                                end().
                            end().
                            entity("car").
                                attribute("car model name2", String.class).
                                attribute("car year2", int.class).
                            end().
                        end().
                        entity("address").
                            attribute("street", String.class).
                            attribute("city", int.class).
                        end().
                    end().
                init();
    }

    public void run() throws IOException, JClassAlreadyExistsException {
        build();
        System.out.println(model.getEntity());
        Executor executor = new Executor(model);

//        executor.toJson();
//        executor.generateCode();
    }

}
