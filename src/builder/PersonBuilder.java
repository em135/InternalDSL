package builder;

import executor.Executor;
import executor.Model;

import java.io.File;

public class PersonBuilder extends Builder {

    private Model model;

    /**
     * Creates an metamodel instance
     */
    protected void build() {
        //first name,last name,car1 model,car1 year,car2 model,car2 year,engine name,engine type,good oil,bad oil,fine oil,street,city
            model = entity("person").
                        attribute("firstName", "first name", String.class).
                        attribute("lastName", "last name", String.class).
                        entityList("car").
                            entity("car").
                                attribute("model", "car1 model", String.class).
                                attribute("year", "car1 year", int.class).
                                entity("engine").
                                    attribute("name", "engine name", String.class).
                                    attribute("type", "engine type", String.class).
                                    entityList("oil").
                                        entity("oil").
                                            attribute("quality", "good oil", String.class).
                                        end().
                                        entity("oil").
                                            attribute("quality", "bad oil", String.class).
                                        end().
                                        entity("oil").
                                            attribute("quality", "fine oil", String.class).
                                        end().
                                    end().
                                end().
                            end().
                            entity("car").
                                attribute("model", "car2 model", String.class).
                                attribute("year", "car2 year", int.class).
                            end().
                        end().
                        entity("address").
                            attribute("street", "street", String.class).
                            attribute("city", "city", String.class).
                        end().
                    end().
                init();

            // Use two csv files with the same columns with the same model
            model.setFile(new File("src/test_files/dummyDataPerson.csv"));
//            model.load("src/test_files/dummyDataFunPerson.csv");
    }

    public void run() {
        build();
        Executor executor = new Executor(model);
        executor.toJson();
    }

}
