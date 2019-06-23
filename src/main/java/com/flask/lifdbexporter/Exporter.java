package com.flask.lifdbexporter;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Exporter {
    public static void main(String[] args) {
        System.out.println("Exporter v0.1.0 started.");

        if(args.length != 4) {
            System.out.println("Run with: java -jar lif-db-exporter.jar <server> <username> <password> <dbname>");
            System.exit(0);
        }

        String server = args[0];
        String username = args[1];
        String password = args[2];
        String dbname = args[3];

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://" + server + "/" + dbname, username, password)){

            try (Statement stmt = conn.createStatement()) {

                File objects_types = new File("./objects_types.xml");
                FileWriter fw = new FileWriter(objects_types);
                fw.write("<?xml version=\"1.0\" encoding=\"utf8\"?>\r\n");
                fw.write("<!--generated from database with lifdbexporter-->\r\n");
                fw.write("<table name=\"objects_types\">\r\n");
                try(ResultSet rs = stmt.executeQuery("select * from objects_types")) {
                    ResultSetMetaData meta = rs.getMetaData();
                    int colCount = meta.getColumnCount();
                    while(rs.next()) {
                        fw.write("  <row>\r\n");
                        for(int col=1; col <= colCount; col++) {
                            if(meta.getColumnName(col).equals("WorkAreaTop") || meta.getColumnName(col).equals("WorkAreaLeft") || meta.getColumnName(col).equals("WorkAreaWidth") || meta.getColumnName(col).equals("WorkAreaHeight") || meta.getColumnName(col).equals("BtnCloseTop") || meta.getColumnName(col).equals("BtnCloseLeft") || meta.getColumnName(col).equals("BasePrice") || meta.getColumnName(col).equals("OwnerTimeout") || meta.getColumnName(col).equals("AllowExportFromRed") || meta.getColumnName(col).equals("AllowExportFromGreen")) continue;
                            Object value = rs.getObject(col);
                            String className = meta.getColumnClassName(col);
                            fw.write("    <" + meta.getColumnName(col) + ">");
                            if(value != null) {
                                if(className.equals("java.lang.Int")) {
                                    fw.write(Integer.toString((int)value));
                                } else if (className.equals("java.lang.Long")) {
                                    fw.write(Long.toString((long)value));
                                } else if (className.equals("java.lang.Boolean")) {
                                    fw.write((boolean)value?"1":"0");
                                } else {
                                    fw.write(value.toString());
                                }
                            } else {
                                if(className.contains("Int") || className.contains("Long")) {
                                    fw.write("0");
                                }
                            }
                            fw.write("</" + meta.getColumnName(col) + ">\r\n");
                        }
                        fw.write("  </row>\r\n");
                    }
                }
                fw.write("</table>");
                fw.flush();
                fw.close();

                File recipe_requirement = new File("./recipe_requirement.xml");
                fw = new FileWriter(recipe_requirement);
                fw.write("<?xml version=\"1.0\" encoding=\"utf8\"?>\r\n");
                fw.write("<!--generated from database with lifdbexporter-->\r\n");
                fw.write("<table name=\"recipe_requirement\">\r\n");
                try(ResultSet rs = stmt.executeQuery("select * from recipe_requirement")) {
                    ResultSetMetaData meta = rs.getMetaData();
                    int colCount = meta.getColumnCount();
                    while(rs.next()) {
                        fw.write("  <row>\r\n");
                        for(int col=1; col <= colCount; col++) {
                            Object value = rs.getObject(col);
                            String className = meta.getColumnClassName(col);
                            fw.write("    <" + meta.getColumnName(col) + ">");
                            if(value != null) {
                                if(className.equals("java.lang.Int")) {
                                    fw.write(Integer.toString((int)value));
                                } else if (className.equals("java.lang.Long")) {
                                    fw.write(Long.toString((long)value));
                                } else if (className.equals("java.lang.Boolean")) {
                                    fw.write((boolean)value?"1":"0");
                                } else {
                                    fw.write(value.toString());
                                }
                            } else {
                                if(className.contains("Int") || className.contains("Long")) {
                                    fw.write("0");
                                }
                            }
                            fw.write("</" + meta.getColumnName(col) + ">\r\n");
                        }
                        fw.write("  </row>\r\n");
                    }
                }
                fw.write("</table>");
                fw.flush();
                fw.close();

                File recipe = new File("./recipe.xml");
                fw = new FileWriter(recipe);
                fw.write("<?xml version=\"1.0\" encoding=\"utf8\"?>\r\n");
                fw.write("<!--generated from database with lifdbexporter-->\r\n");
                fw.write("<table name=\"recipe\">\r\n");
                try(ResultSet rs = stmt.executeQuery("select * from recipe")) {
                    ResultSetMetaData meta = rs.getMetaData();
                    int colCount = meta.getColumnCount();
                    while(rs.next()) {
                        fw.write("  <row>\r\n");
                        for(int col=1; col <= colCount; col++) {
                            Object value = rs.getObject(col);
                            String className = meta.getColumnClassName(col);
                            fw.write("    <" + meta.getColumnName(col) + ">");
                            if(value != null) {
                                if(className.equals("java.lang.Int")) {
                                    fw.write(Integer.toString((int)value));
                                } else if (className.equals("java.lang.Long")) {
                                    fw.write(Long.toString((long)value));
                                } else if (className.equals("java.lang.Boolean")) {
                                    fw.write((boolean)value?"1":"0");
                                } else {
                                    fw.write(value.toString());
                                }
                            } else {
                                if(className.contains("Int") || className.contains("Long")) {
                                    fw.write("0");
                                }
                            }
                            fw.write("</" + meta.getColumnName(col) + ">\r\n");
                        }
                        fw.write("  </row>\r\n");
                    }
                }
                fw.write("</table>");
                fw.flush();
                fw.close();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
