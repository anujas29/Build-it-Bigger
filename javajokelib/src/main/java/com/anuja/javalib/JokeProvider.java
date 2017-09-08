package com.anuja.javalib;

import java.util.Random;

public class JokeProvider {


    private static final String[] jokes = {
            "If some one calls you 'UGLY; hava a good comeback and sa 'EXCUSE ME, I AM NOT A MIRROR'.",
            "Roses are red, violets are blue" +
                    "a face like yours belongs in a zoo." +
                    "Don't you worry I'll be there too, not in the cage but laughing at you!",
            " I bought some shoes from a drug dealer. I don't know what he laced them with, but I've been tripping all day.",
            "Wife says to her programmer husband, \"Go to the store and buy a loaf of bread. If they have eggs, buy a dozen.\"\n" +
                    "\n" +
                    "Husband returns with 12 loaves of bread.",
            "If Plan A fails, remember that you have 25 letters left" ,
            "I don't care what people think of me... at least mosquitoes find me attractive",
            "I am so good at sleeping I can do it with my eyes closed."
    };

    public static String GetJoke(){
        int size = jokes.length;

        Random r = new Random();
        int index = r.nextInt(size);

        return jokes[index];
    }
}
