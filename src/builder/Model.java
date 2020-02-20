package builder;

import com.sun.codemodel.internal.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {

    private File file;
    private String[] columnNames;

    public Model(File file) {
        this.file = file;
    }

    private void read(){
        ArrayList<Double> latitudes = new ArrayList<>();
        ArrayList<Double> longitudes = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            //first name,last name,street,city,car1 model,car2 model,car1 year,car2 year
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
