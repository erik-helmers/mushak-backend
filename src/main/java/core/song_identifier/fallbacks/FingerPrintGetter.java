package core.song_identifier.fallbacks;


import core.Settings;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class FingerPrintGetter {

    Settings settings;

    public FingerPrintGetter(Settings settings){
        this.settings = settings;
    }

    public void find(Path path){

        ProcessBuilder child = new ProcessBuilder("");

        //TODO: implement this (accoustid recognition as last fallback)

    }
}
