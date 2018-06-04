package core;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


//TODO: upgrade this with static class + loader

@Service
public class Settings {


    public static final String CONFIG_PATH = "config.properties";
    public static final String ENABLE_SECURITY = "security";
    public static final String MUSIC_DIRECTORY = "music-directory";
    public static final String MUSIC_DB_PATH = "music-db-path";
    public static final String UPDATE_DB_ON_LOAD = "update-db-on-load";
    public static final String USERS_DB_PATH= "users-db-path";

    private Properties prop = new Properties();

    private void create_new(File file) {

        OutputStream output = null;

        try {
            output = new FileOutputStream(file);
            prop.setProperty(ENABLE_SECURITY, "true");
            prop.setProperty(MUSIC_DIRECTORY, "Music Sample");
            prop.setProperty(MUSIC_DB_PATH, "mushak.db");
            prop.setProperty(UPDATE_DB_ON_LOAD, "true");
            prop.setProperty(USERS_DB_PATH, "users.db");
            prop.store(output, "============= MuShaK Settings ===============");
            output.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new Error(CONFIG_PATH + " should be a file, not a directory");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new Error("Could not create properties files... Has the application write accesss ?");

        }
    }



    public Settings(){

            InputStream input = null;
            File config_file = new File(CONFIG_PATH);

            try {
                if (!config_file.exists()){
                    create_new(config_file);
                }

                input = new FileInputStream(CONFIG_PATH);
                prop.load(input);

            } catch (FileNotFoundException e){
                System.out.println(e.getMessage());
                throw new Error(CONFIG_PATH + " should be a file, not a directory");
            } catch (IOException e){
                System.out.println(e.getMessage());
                throw new Error("Could not create properties files... Has the application write accesss ?");
            } finally {
                if (input != null){
                    try {
                        input.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            System.out.println(prop.get(Settings.USERS_DB_PATH));
            System.out.println(prop);

        }

        public String get(String id){
            String result = prop.getProperty(id);
            //System.out.println("Requested : " + id + " result is '"+result+"'");
            return result;
        }

        public Path getPath(String id){
            Path output = Paths.get(prop.getProperty(id));
            if (!output.toFile().exists())
                return null;
            return output;
        }

        public boolean getBoolean(String id){
            return prop.getProperty(id).equals("true");
        }


    }