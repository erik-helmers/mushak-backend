package core;

import logger.Log;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


//TODO: upgrade this with static class + loader

@Service
public class Settings {

    public static final String CONFIG_PATH = "config.properties";

    public static enum Key{

        ENABLE_SECURITY ("security", "true"),
        MUSIC_DIRECTORY ("music-directory", "Music Sample"),
        MUSHAK_DB ("mushak-db-path", "mushak.db"),
        UPDATE_DB_ON_LOAD ("update-db-on-load", "true");

        public final String name;

        public final String default_value;

        Key(String name, String default_value){
            this.name = name;
            this.default_value = default_value;
        }

    }

    private Properties prop = new Properties();

    private void create_new(File file) {

        OutputStream output = null;

        try {
            output = new FileOutputStream(file);

            for (Key key : Key.values()){
                prop.setProperty(key.name, key.default_value);
            }
            
            prop.store(output, "============= MuShaK Settings ===============");
            output.close();
        } catch (FileNotFoundException e) {
            throw new Error(CONFIG_PATH + " should be a file, not a directory", e);
        } catch (IOException e) {
            throw new Error("Could not create properties files... Has the application write accesss ?", e);

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


        }

        public String get(Key id){
            String result = prop.getProperty(id.name);
            //System.out.println("Requested : " + id + " result is '"+result+"'");
            return result;
        }

        public Path getPath(Key id){
            Log.debug_call();
            Path output = Paths.get(get(id));
            Log.debug_value("output", output.toAbsolutePath());
            if (!output.toFile().exists())
            {
                Log.debug_value("output", output.toAbsolutePath());
                return null;
            }

            return output;
        }

        public boolean getBoolean(Key id){
            return get(id).equals("true");
        }


    }