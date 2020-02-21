package executor;

import com.sun.codemodel.internal.*;
import metamodel.Entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Executor {

    private Model model;
    private String[] columnNames;

    public Executor(Model model) {
        this.model = model;

    }

    private void read(){
        File file = model.getFile();
        try (Scanner sc = new Scanner(file)) {
            //first name,last name,car1 model,car1 year,car2 model,car2 year,engine name,engine type,good oil,bad oil,fine oil,street,city
            columnNames = sc.nextLine().split(",");

            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public String toJson() {
        read();
        return "";
    }


    // https://stackoverflow.com/questions/121324/a-java-api-to-generate-java-source-files
    // https://sookocheff.com/post/java/generating-java-with-jcodemodel/
    public void generateCode() throws JClassAlreadyExistsException, IOException {
//        JCodeModel codeModel = new JCodeModel();
//        JDefinedClass definedClass = codeModel._class("foo.Bar");
//        JMethod method = definedClass.method(0, int.class, "foo");
//        method.body()._return(JExpr.lit(5));
//        File file = new File("./target/classes");
//        file.mkdirs();
//        codeModel.build(file);
    }
}
